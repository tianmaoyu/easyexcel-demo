package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DelegatingRedisMessageSource extends DelegatingMessageSource {

    private final MessageSource redisMessageSource;
    private final MessageSource parentMessageSource;

    public DelegatingRedisMessageSource(RedisMessageSource redisMessageSource, MessageSource defaultMessageSource) {
        this.redisMessageSource = redisMessageSource;
        this.parentMessageSource = defaultMessageSource;
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        String message = parentMessageSource.getMessage(code, args, null, locale);
        if (message != null) {
            return message;
        }
        return redisMessageSource.getMessage(code, args, defaultMessage, locale);
    }
    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        try {
            return parentMessageSource.getMessage(code, args, locale);
        } catch (NoSuchMessageException e) {
            return redisMessageSource.getMessage(code, args, locale);
        }
    }
    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        try {
            return parentMessageSource.getMessage(resolvable, locale);
        } catch (NoSuchMessageException e) {
            return redisMessageSource.getMessage(resolvable, locale);
        }
    }
}