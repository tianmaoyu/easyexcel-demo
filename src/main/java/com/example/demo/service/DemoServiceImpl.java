package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public void doSomething() {
        System.out.println("Executing doSomething...");
    }

    @Override
    public void doSomethingElse() {
        System.out.println("Executing doSomethingElse...");
    }
}
