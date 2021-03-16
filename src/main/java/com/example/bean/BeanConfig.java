package com.example.bean;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class BeanConfig {

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public JedisCluster getJedisCluster() {
        Set<HostAndPort> masterNodes = new HashSet<>();
        String[] hostPortPairs = {"localhost:6379"};

        for (String hostPortPair : hostPortPairs) {
            String[] hostAndPort = hostPortPair.split(":");
            masterNodes.add(new HostAndPort(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
        }

        return new JedisCluster(masterNodes, 10000, 5);
    }
}
