package com.example.demo.domain;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @TableName account
 */
@ApiModel
@Data
public class Account {
    private Integer id;
    private String userName;
    private Integer age;
    private LocalDateTime birthday;
    private AccountStatus accountStatus;
}