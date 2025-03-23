package com.example.demo.config;

import com.example.demo.utils.Multilingual;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MultilingualAspect {

    @Autowired
    private MessageSource messageSource;

    /**
     * 处理多语言字段
     * @param joinPoint
     * @param multilingual
     * @return
     * @throws Throwable
     */
    @Around("execution(* get*()) && @annotation(multilingual)")
    public Object handleMultilingualField(ProceedingJoinPoint joinPoint, Multilingual multilingual) throws Throwable {
        Object result = joinPoint.proceed();
        if (result instanceof String) {
             System.out.println(multilingual.key());
//           return messageSource.getMessage(multilingual.key());
        }
        return result;
    }
}