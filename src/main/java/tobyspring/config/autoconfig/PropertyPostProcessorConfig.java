package tobyspring.config.autoconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.MyConfigurationProperties;

import java.util.Map;

import static org.springframework.core.annotation.AnnotationUtils.getAnnotationAttributes;

@MyAutoConfiguration
public class PropertyPostProcessorConfig {
    @Bean
    BeanPostProcessor propertyPostProcesscor(Environment env) {
        return new BeanPostProcessor() {

            // Bean Object 초기화가 끝난 다음에 호출 (모든 Bean Object 가 생성될 때마다 생성됨)
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                // AnnotationUtils.findAnnotation() : 파라미터로 지정한 어노테이션 클래스가 선언된 빈이 있는 지 찾아줌. 없으면 null을 return
                MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyConfigurationProperties.class);
                if (annotation == null) return bean;

                Map<String, Object> attributes = getAnnotationAttributes(annotation);
                String prefix = (String) attributes.get("prefix");

                // Binder class : Property 값을 특정 클래스에 binding
                // bindOrCreate()
                // 1. binding을 시도 후 존재 하지 않을 경우, Class의 Object를 생성 후 return
                // 2. 첫번째 파라미터 name에 prefix의 값을 주면 앞에 name의 값을 붙여 property가 존재하는 지 체크해줌
                // (ex. prefix값이 server일 경우, server.propertyName 의 값을 찾아줌)
                return Binder.get(env).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
