package com.example.services.rmq;

import com.example.dto.BlockDTO;
import com.example.services.database.psql.PSQLService;
import com.example.utils.MessageParser;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

@Component
public class RMQListener {

    private final PSQLService psqlService;

    private final MessageParser messageParser;

    @Autowired
    public RMQListener(PSQLService psqlService, MessageParser messageParser) {
        this.psqlService = psqlService;
        this.messageParser = messageParser;
    }

    @SneakyThrows
    @PostConstruct
    public void setup() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("Test", false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);

            System.out.println(" [x] Received '" + message + "'");

            BlockDTO newBlock = messageParser.parseMessage(message);

            psqlService.insertRecord(newBlock);
        };
        channel.basicConsume("Test", true, deliverCallback, consumerTag -> {});
    }
}
