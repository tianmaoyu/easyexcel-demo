package com.example.demo.swagger;

import com.example.demo.tenum.IEnum;
import com.google.common.base.Optional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import java.lang.reflect.Method;
import java.util.*;

@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER - 100)
@Component
public class EnumResponsePlugin implements OperationBuilderPlugin {

    @Override
    public void apply(OperationContext context) {
        // 1. 获取返回类型
        Class<?> returnType = context.getReturnType().getErasedType();


        if (!returnType.isEnum() || !IEnum.class.isAssignableFrom(returnType)) {
            return;
        }
        // 2. 收集枚举元数据
        List<String> codes = new ArrayList<>();
        Map<String, String> codeDescMap = new LinkedHashMap<>();
        collectEnumMetadata(returnType, codes, codeDescMap);

//        Set<ResponseMessage> responseMessages = new HashSet<>();
//        ResponseMessage msg= new ResponseMessageBuilder()
//                .code(200)
//                .message("返回枚举值:")
//                .responseModel(new springfox.documentation.schema.ModelRef(returnType.getSimpleName()))
//                .build();
//        responseMessages.add(msg);

        // 3. 修改响应描述和 Schema
        context.operationBuilder()
            .responseModel(new springfox.documentation.schema.ModelRef(returnType.getSimpleName()))
            .deprecated(buildResponseDescription("", codeDescMap));
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

    private String buildResponseDescription(String originalDesc, Map<String, String> codeDescMap) {
        StringBuilder sb = new StringBuilder(originalDesc == null ? "" : originalDesc);
        sb.append("\n返回枚举值:");
        codeDescMap.forEach((code, desc) -> sb.append("\n").append(code).append(": ").append(desc));
        return sb.toString();
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}