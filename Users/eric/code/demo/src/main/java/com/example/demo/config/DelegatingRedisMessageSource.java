@Override
public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
    // Try default MessageSource first
    String message = defaultMessageSource.getMessage(code, args, null, locale);
    if (message != null) {
        return message;
    }
    // Fall back to Redis
    return redisMessageSource.getMessage(code, args, defaultMessage, locale);
}
@Override
public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
    // Try default MessageSource first
    try {
        return defaultMessageSource.getMessage(code, args, locale);
    } catch (NoSuchMessageException e) {
        // Fall back to Redis
        return redisMessageSource.getMessage(code, args, locale);
    }
}
@Override
public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
    // Try default MessageSource first
    try {
        return defaultMessageSource.getMessage(resolvable, locale);
    } catch (NoSuchMessageException e) {
        // Fall back to Redis
        return redisMessageSource.getMessage(resolvable, locale);
    }
}