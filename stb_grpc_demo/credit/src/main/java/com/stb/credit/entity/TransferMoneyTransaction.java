package com.stb.credit.entity;

import com.stb.credit.enums.TransferMoneyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferMoneyTransaction extends AuditingEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String fromAccount;
    private String toBankName;
    private String toAccountName;
    private String toAccountNumber;
    private double amount;
    private String description;
    private String referenceId;
    private TransferMoneyStatus status;
}
