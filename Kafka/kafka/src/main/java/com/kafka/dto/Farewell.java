package com.kafka.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farewell {

    private String message;
    private Integer remainingMinutes;

    @Override
    public String toString() {
        return message + ". In " + remainingMinutes + "!";
    }

}
