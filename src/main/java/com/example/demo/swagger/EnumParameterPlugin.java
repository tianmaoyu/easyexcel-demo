package com.example.demo.swagger;

import com.google.common.base.Optional;
import org.springframework.stereotype.Component;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spi.service.contexts.ParameterContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class EnumParameterPlugin implements ParameterBuilderPlugin {

    @Override
    public void apply(ParameterContext context) {
        Class<?> paramType = context.resolvedMethodParameter().getParameterType().getErasedType();
        if (!paramType.isEnum()) return;

        // 1. 收集枚举的 code 和 desc
        List<String> codes = new ArrayList<>();
        Map<String, String> codeDescMap = new LinkedHashMap<>();
        collectEnumMetadata(paramType, codes, codeDescMap);

        String groupName = context.getGroupName();
        // 2. 修改参数描述和允许值
        context.parameterBuilder()
            .allowableValues(new AllowableListValues(codes, "int")) // 设置允许的 code 值
            .description(buildDescription("", codeDescMap)) // 追加描述
            .defaultValue(codes.isEmpty() ? null : codes.get(0)); // 设置默认值
    }

    private void collectEnumMetadata(Class<?> enumType, List<String> codes, Map<String, String> codeDescMap) {
        try {
            for (Object enumConstant : enumType.getEnumConstants()) {
                Method getCode = enumType.getMethod("getCode");
                Method getDesc = enumType.getMethod("getDesc");
                Object code = getCode.invoke(enumConstant);
                String desc = (String) getDesc.invoke(enumConstant);
                codes.add(String.valueOf(code));
                codeDescMap.put(String.valueOf(code), desc);
            }
        } catch (Exception e) {
            throw new RuntimeException("枚举必须包含 getCode() 和 getDesc() 方法", e);
        }
    }

    private String buildDescription(String originalDesc, Map<String, String> codeDescMap) {
        StringBuilder sb = new StringBuilder("<strong>枚举值:</strong><strong>描述</strong>");
        codeDescMap.forEach((code, desc) ->
                sb.append("<br>").append(code).append(": ").append(desc)
        );
       return sb.toString();
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}