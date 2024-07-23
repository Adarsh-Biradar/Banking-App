package com.bank.bank.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String accountholderName;
    private double balance;
}
