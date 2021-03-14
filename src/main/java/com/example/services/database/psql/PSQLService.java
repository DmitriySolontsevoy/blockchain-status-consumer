package com.example.services.database.psql;

import com.example.dto.BlockDTO;
import com.example.queries.Queries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PSQLService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PSQLService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertRecord(BlockDTO newBlock) {
        UUID blockId = UUID.randomUUID();
        long timeAt = System.currentTimeMillis();

        jdbcTemplate.update(Queries.ADD_BLOCK_NUM_QUERY, blockId, newBlock.getBlockNumber(), timeAt);
        jdbcTemplate.update(Queries.ADD_BLOCK_TIME_QUERY, UUID.randomUUID(), blockId, newBlock.getBlockNumber(), timeAt);
    }
}
