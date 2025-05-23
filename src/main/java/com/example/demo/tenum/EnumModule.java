package com.example.demo.tenum;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Arrays;

public class EnumModule extends SimpleModule {
    public EnumModule() {
        addSerializer((Class<? extends IEnum<?>>) (Class<?>) IEnum.class, new EnumSerializer());
        addDeserializer(Enum.class, new EnumDeserializer());
//        addSerializer(IEnum.class, new EnumSerializer());
//        addDeserializer(Enum.class, new EnumDeserializer());
    }

    // 序列化：枚举转code
    private static class EnumSerializer extends StdSerializer<IEnum<?>> {
        protected EnumSerializer() {
            super(IEnum.class, false);
        }

        @Override
        public void serialize(IEnum<?> value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
            gen.writeObject(value.getCode());
        }
    }

    // 反序列化：code转枚举
    private static class EnumDeserializer extends StdDeserializer<Enum<?>> {
        protected EnumDeserializer() {
            super(Enum.class);
        }

        @Override
        public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            String currentName = p.currentName();
            Class<Enum> type = (Class<Enum>) ctxt.getContextualType().getRawClass();
            
            return Arrays.stream(type.getEnumConstants())
                .filter(e -> ((IEnum<?>)e).getCode().toString().equals(node.asText()))
                .findFirst()
                .orElseThrow(() -> new InvalidFormatException(p, "无效枚举值", node, type));
        }
    }
}