package com.example.services.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisService {

    private final Jedis jedis;

    @Autowired
    public RedisService(Jedis jedis) {
        this.jedis = jedis;
    }

    public long getCounter() {
        String value = this.jedis.get("counter");

        if (value == null) {
            return 0L;
        } else {
            return Long.parseLong(value);
        }
    }

    public void incrementCounter() {
        long currentCounter = this.getCounter();
        this.jedis.set("counter", String.valueOf(currentCounter));
    }
}
