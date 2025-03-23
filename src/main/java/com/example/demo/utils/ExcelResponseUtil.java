package com.example.demo.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ExcelResponseUtil {

    private static final MediaType APPLICATION_OCTET_STREAM = MediaType.APPLICATION_OCTET_STREAM;

    public static ResponseEntity<byte[]> getResponseEntity(byte[] byteArray, String fileName) throws UnsupportedEncodingException {

        if (byteArray == null || fileName == null) {
            throw new IllegalArgumentException("byteArray and fileName must not be null");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_OCTET_STREAM);

        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20");

        String contentDisposition = String.format("attachment; filename=\"%s\"; filename*=UTF-8''%s",
                encodedFileName, encodedFileName);
        headers.add("Content-Disposition", contentDisposition);

        // 返回响应实体
        return ResponseEntity.ok()
                .headers(headers)
                .body(byteArray);
    }
}