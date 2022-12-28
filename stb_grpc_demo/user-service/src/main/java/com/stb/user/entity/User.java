package com.stb.user.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class User {

    @Id
    private String id;
    private String name;
    private String accountNumber;
    private String bankName;
    private String gender;
}
