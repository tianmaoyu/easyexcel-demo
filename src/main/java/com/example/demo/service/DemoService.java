package com.example.demo.service;

import com.example.demo.entity.UserData;

import java.io.IOException;
import java.util.List;

public interface DemoService {
    void doSomething();

    void doSomethingElse();

    public byte[] excelReport(Integer id) throws IOException;
}
