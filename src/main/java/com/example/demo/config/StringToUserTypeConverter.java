package com.example.demo.config;

import com.example.demo.domain.UserType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
   public class StringToUserTypeConverter implements Converter<String, UserType> {
       @Override
       public UserType convert(String source) {
           try {
               return UserType.fromCode(source);
           } catch (Exception e) {
               throw new IllegalArgumentException("Invalid UserType: " + source);
           }
       }
   }