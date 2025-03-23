package com.example.demo.service;

import com.alibaba.excel.EasyExcel;
import com.example.demo.entity.UserData;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

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

    public byte[] excelReport(Integer id) throws IOException {

       List<UserData> userDataList = null;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            EasyExcel.write(outputStream, UserData.class).sheet("用户列表").doWrite(userDataList);

            return outputStream.toByteArray();
        }
    }
}
