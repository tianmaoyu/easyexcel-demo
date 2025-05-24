package com.example.demo.tenum;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.io.IOException;
import java.util.Arrays;

public class EnumDeserializer extends StdDeserializer<Enum<?>> {

    private final Class<? extends Enum<?>> enumClass;

    public EnumDeserializer(Class<? extends Enum<?>> enumClass) {
        super(enumClass);
        this.enumClass = enumClass;
    }

    @Override
    public Enum<?> deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String text = node.asText();

        return Arrays.stream(enumClass.getEnumConstants())
            .filter(e -> ((IEnum<?>) e).getCode().toString().equals(text))
            .findFirst()
            .orElseThrow(() -> new InvalidFormatException(p, "无效枚举值", node, enumClass));
    }
}
