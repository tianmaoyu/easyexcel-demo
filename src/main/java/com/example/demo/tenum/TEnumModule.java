//package com.example.demo.tenum;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.*;
//import com.fasterxml.jackson.databind.deser.Deserializers;
//import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
//import com.fasterxml.jackson.databind.module.SimpleDeserializers;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//
//import java.io.IOException;
//import java.lang.reflect.ParameterizedType;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class TEnumModule extends SimpleModule {
//
//
//    public TEnumModule() {
//        // 修复反序列化器注册方式
//        // 使用通配符类型注册序列化器
//        addSerializer((Class<IEnum<?>>) (Class<?>) IEnum.class, new TEnumSerializer());
//        setDeserializers(new TEnumDeserializers());
//    }
//
//    // 新增自定义反序列化器注册器
//    private static class TEnumDeserializers extends SimpleDeserializers {
//        @Override
//        public JsonDeserializer<?> findEnumDeserializer(Class<?> type,
//                                                    DeserializationConfig config,
//                                                    BeanDescription beanDesc)
//            throws JsonMappingException {
//            if (IEnum.class.isAssignableFrom(type)) {
//                return new TEnumCodeDeserializer<>(type);
//            }
//            return super.findEnumDeserializer(type, config, beanDesc);
//        }
//    }
//
//    // 枚举反序列化器实现
//    private static class TEnumCodeDeserializer<T extends Enum<T> & IEnum<?>>
//            extends StdDeserializer<T> {
//
//        private final Class<T> enumType;
//        private final Map<Object, T> codeMap = new ConcurrentHashMap<>();
//
//        public TEnumCodeDeserializer(Class<T> enumType) {
//            super(enumType);
//            this.enumType = enumType;
//            initCodeMap();
//        }
//
//        private void initCodeMap() {
//            // 获取泛型参数类型
//            Class<?> codeType = resolveCodeType();
//
//            for (T enumConstant : enumType.getEnumConstants()) {
//                Object code = convertCode(enumConstant.getCode(), codeType);
//                codeMap.put(code, enumConstant);
//            }
//        }
//
//        private Class<?> resolveCodeType() {
//            ParameterizedType pt = (ParameterizedType) enumType.getGenericInterfaces()[0];
//            return (Class<?>) pt.getActualTypeArguments()[0];
//        }
//
//        private Object convertCode(Object rawCode, Class<?> targetType) {
//            if (rawCode == null) return null;
//            if (targetType.isInstance(rawCode)) return rawCode;
//
//            // 处理常见类型转换
//            if (targetType == String.class) {
//                return rawCode.toString();
//            } else if (targetType == Integer.class) {
//                return Integer.parseInt(rawCode.toString());
//            } else if (targetType == Long.class) {
//                return Long.parseLong(rawCode.toString());
//            }
//            throw new IllegalArgumentException("Unsupported code type: " + targetType);
//        }
//
//        @Override
//        public T deserialize(JsonParser p, DeserializationContext ctxt)
//                throws IOException {
//
//            JsonNode node = p.getCodec().readTree(p);
//            Object inputCode = parseInputValue(node);
//
//            T result = codeMap.get(inputCode);
//            if (result != null) {
//                return result;
//            }
//
//            // 兼容原生 name 处理
//            try {
//                return Enum.valueOf(enumType, node.asText());
//            } catch (IllegalArgumentException e) {
//                throw new InvalidFormatException(p,
//                        "无效枚举值: " + inputCode + " for " + enumType.getSimpleName(),
//                        node,
//                        enumType);
//            }
//        }
//
//        private Object parseInputValue(JsonNode node) {
//            if (node.isTextual()) return node.asText();
//            if (node.isInt()) return node.asInt();
//            if (node.isLong()) return node.asLong();
//            return node.toString();
//        }
//    }
//
//    // 序列化器（保持不变）
//    private static class TEnumSerializer extends StdSerializer<IEnum<?>> {
//        public TEnumSerializer() {
//            super(IEnum.class, false);
//        }
//
//        @Override
//        public void serialize(IEnum<?> value, JsonGenerator gen, SerializerProvider provider)
//            throws IOException {
//            gen.writeObject(value.getCode());
//        }
//    }
//}