package com.example.demo.domain;
import java.time.LocalDateTime;

import lombok.Data;

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