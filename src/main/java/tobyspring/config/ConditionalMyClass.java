package tobyspring.config;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// @Conditional : 해당 Configuration 클래스에 있는 Bean들을 등록할 지 말 지 설정
// Condition Interface를 구현한 클래스를 엘리먼트로 등록해야함
@Conditional(MyOnClassCondition.class)
public @interface ConditionalMyClass {
    String value();
}
