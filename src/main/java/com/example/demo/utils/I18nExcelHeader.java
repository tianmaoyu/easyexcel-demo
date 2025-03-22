package com.example.demo.utils;

import java.lang.annotation.*;
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface I18nExcelHeader {
    String key() default "fond no title";
}