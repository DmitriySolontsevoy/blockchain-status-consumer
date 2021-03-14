package com.example.queries;

public final class Queries {

    public static final String ADD_BLOCK_NUM_QUERY = "insert into blocknumbers\n" +
            "values(?, ?, ?)";

    public static final String ADD_BLOCK_TIME_QUERY = "insert into blocktimes\n" +
            "values(?, ?, ?, ?)";
}
