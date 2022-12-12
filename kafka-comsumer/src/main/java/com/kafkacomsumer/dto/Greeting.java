package com.kafkacomsumer.dto;

import lombok.*;

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
