package com.kafka.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Greeting {

    private String msg;
    private String name;

    @Override
    public String toString() {
        return msg + ", " + name + "!";
    }
}
