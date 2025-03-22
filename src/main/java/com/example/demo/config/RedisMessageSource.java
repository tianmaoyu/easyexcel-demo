package com.example.demo.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
public class RedisMessageSource implements MessageSource {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        String message = getMessageFromRedis(code, locale);
        return message != null ? message : defaultMessage;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        String message = getMessageFromRedis(code, locale);
        if (message == null) {
            throw new NoSuchMessageException(code, locale);
        }
        return message;
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        for (String code : resolvable.getCodes()) {
            String message = getMessageFromRedis(code, locale);
            if (message != null) {
                return message;
            }
        }
        throw new NoSuchMessageException(resolvable.getCodes()[0], locale);
    }

    private String getMessageFromRedis(String code, Locale locale) {
        // Fetch messages from Redis
        String lang = locale.getLanguage();
        String key = "messages:" + lang;
        String messagesJson = redisTemplate.opsForValue().get(key);

        if (messagesJson != null) {
            // Parse JSON data
            Map<String, String> messages = parseMessages(messagesJson);
            return messages.get(code);
        }
        return null;
    }

    @SneakyThrows
    private Map<String, String> parseMessages(String messagesJson) {
        // Use Jackson or another JSON library to parse the JSON
        return new ObjectMapper().readValue(messagesJson, new TypeReference<Map<String, String>>() {});
    }
}