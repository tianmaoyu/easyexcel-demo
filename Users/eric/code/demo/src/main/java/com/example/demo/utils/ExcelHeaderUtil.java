package com.example.demo.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.util.ListUtils;
import com.example.demo.config.SpringContextHolder;
import org.springframework.boot.BootstrapContextClosedEvent;
import org.springframework.context.MessageSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ExcelHeaderUtil {

    private static MessageSource messageSource;

    public static List<List<String>> buildHeaders(Class<?> clazz, Locale locale) {
        if (messageSource == null) {
            synchronized (ExcelHeaderUtil.class) {
                if (messageSource == null) {
                    // 确保 MessageSource Bean 已经被正确加载
                    if (SpringContextHolder.containsBean("messageSource")) {
                        messageSource = SpringContextHolder.getBean(MessageSource.class);
                    } else {
                        throw new IllegalStateException("MessageSource bean not found in Spring context");
                    }
                }
            }
        }

        List<List<String>> lists = ListUtils.newArrayList();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty != null) {
                List<String> list = new ArrayList<>();
                String key = excelProperty.value()[0]; // 假设只有一个值
                String name = messageSource.getMessage(key, null, locale);
                list.add(name);
                lists.add(list);
            }
        }
        return lists;
    }
}