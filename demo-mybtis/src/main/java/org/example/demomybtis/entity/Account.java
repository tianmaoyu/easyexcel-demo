package org.example.demomybtis.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @TableName account
 */
@Data
public class Account {
    private Integer id;
    private String userName;
    private Integer age;
    private LocalDateTime birthday;
    private AccountStatus accountStatus;
}