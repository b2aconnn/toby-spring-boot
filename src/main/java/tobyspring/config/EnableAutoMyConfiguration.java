package tobyspring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// @Import : @ComponentScan 어노테이션의 스캔 범위에서 벗어난 클래스들을 지정해서 빈으로 등록할 수 있음
@Import(MyAutoConfigImportSelector.class)
public @interface EnableAutoMyConfiguration {
}
