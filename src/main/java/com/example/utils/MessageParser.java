package com.example.utils;

import com.example.dto.BlockDTO;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageParser {

    private final Gson gson;

    @Autowired
    public MessageParser(Gson gson) {
        this.gson = gson;
    }

    @SneakyThrows
    public BlockDTO parseMessage(String message) {
        return gson.fromJson(message, BlockDTO.class);
    }
}
