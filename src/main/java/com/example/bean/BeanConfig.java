package com.example.bean;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class BeanConfig {

    @Value("${redis.host}")
    private String host;

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    @Bean
    public Jedis getJedisCluster() {
        return new Jedis(this.host);
    }
}
