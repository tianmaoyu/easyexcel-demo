//package com.example.demo.tenum.mvc;
//
//import com.example.demo.tenum.IEnum;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.TypeDescriptor;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.core.convert.converter.GenericConverter;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(new StringToEnumConverter());
//        registry.addConverter(new IntegerToEnumConverter());
//    }
//
//    // 字符串参数转枚举
//    private static class StringToEnumConverter implements Converter<String, IEnum<?>> {
//        @Override
//        public IEnum<?> convert(String source) {
//            throw new UnsupportedOperationException("需通过具体枚举类型处理");
//        }
//    }
//
//    // 数字参数转枚举
//    private static class IntegerToEnumConverter implements Converter<Integer, IEnum<?>> {
//        @Override
//        public IEnum<?> convert(Integer source) {
//            throw new UnsupportedOperationException("需通过具体枚举类型处理");
//        }
//    }
//
//    // 动态类型转换适配
//    @Component
//    @ConditionalOnWebApplication
//    public static class GenericEnumConverter implements GenericConverter {
//        @Override
//        public Set<ConvertiblePair> getConvertibleTypes() {
//            return new HashSet<>(Arrays.asList(
//                new ConvertiblePair(String.class, IEnum.class),
//                new ConvertiblePair(Integer.class, IEnum.class),
//                new ConvertiblePair(Long.class, IEnum.class)
//            ));
//        }
//
//        @Override
//        public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
//            if (source == null) {
//                return null;
//            }
//            Class<?> enumType = targetType.getType();
//            return Arrays.stream(enumType.getEnumConstants())
//                .filter(e -> ((IEnum<?>)e).getCode().equals(source))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("无效枚举值: " + source));
//        }
//    }
//}