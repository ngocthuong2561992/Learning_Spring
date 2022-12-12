package com.springactivemq.config;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageInfo implements Serializable {
    private Integer batchCode;
    private Integer clientMemCode;
    private String clientCode;
    private String classCode;
}
