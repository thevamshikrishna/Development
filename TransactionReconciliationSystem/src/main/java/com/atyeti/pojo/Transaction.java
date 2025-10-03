package com.atyeti.pojo;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(of={"transactionId","amount","date","description"})
public class Transaction {
    private String transactionId;
    private double amount;
    private LocalDate date;
    private String description;
}