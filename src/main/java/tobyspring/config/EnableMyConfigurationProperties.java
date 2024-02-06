package tobyspring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Prefix로 Enable이 붙은 어노테이션은 일반적으로 @Import 어노테이션을 선언해서 어떤 기능을 가진 Configuration 클래스나 ImportSelector를 가져오는 게 목적
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyConfigurationPropertiesImportSelector.class)
public @interface EnableMyConfigurationProperties {
    Class<?> value();
}
