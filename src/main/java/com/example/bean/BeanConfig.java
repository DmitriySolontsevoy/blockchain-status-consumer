package com.example.bean;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class BeanConfig {

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public Jedis getJedisCluster() {
        return new Jedis("localhost:6379");
    }
}
