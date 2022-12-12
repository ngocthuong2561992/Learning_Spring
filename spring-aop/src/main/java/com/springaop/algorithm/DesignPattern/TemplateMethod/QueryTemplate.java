package com.springaop.algorithm.DesignPattern.TemplateMethod;

import com.springaop.algorithm.DesignPattern.TemplateMethod.obj.RowMapper;

import java.util.Arrays;
import java.util.List;

public class QueryTemplate {
    private void createConnection() {
        System.out.println("connected");
    }
    private void closeConnection() {
        System.out.println("disconnected");
    }
    private List<String> excute(String query, RowMapper mapper) {
        System.out.println("excute query: " + query);
        return Arrays.asList("row1","row2");
    }

    public List<String> excuteQuery(String query, RowMapper mapper) {
        createConnection();
        List<String> result = excute(query, mapper);
        closeConnection();
        return result;
    }

}
