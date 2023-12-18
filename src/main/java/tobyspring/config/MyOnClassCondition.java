package tobyspring.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;

public class MyOnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attrs = metadata.getAnnotationAttributes(ConditionalMyClass.class.getName());
        String value = (String) attrs.get("value");

        // 해당 라이브러리(value에 있는 값)가 있는 지 체크
        return ClassUtils.isPresent(value, context.getClassLoader());
    }
}
