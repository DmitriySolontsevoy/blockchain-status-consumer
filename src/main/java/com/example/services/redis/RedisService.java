package com.example.services.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

@Component
public class RedisService {

    private final JedisCluster jedisCluster;

    @Autowired
    public RedisService(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public long getCounter() {
        String value = this.jedisCluster.get("counter");

        if (value == null) {
            return 0L;
        } else {
            return Long.parseLong(value);
        }
    }

    public void incrementCounter() {
        long currentCounter = this.getCounter();
        this.jedisCluster.set("counter", String.valueOf(currentCounter));
    }
}
