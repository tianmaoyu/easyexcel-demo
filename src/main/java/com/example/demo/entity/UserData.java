package com.example.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    @ExcelProperty("name")
    private String name;
    @ExcelProperty("age")
    private int age;

}