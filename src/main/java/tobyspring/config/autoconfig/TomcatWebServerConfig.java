package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@Conditional(TomcatWebServerConfig.TomcatCondition.class)
@EnableMyConfigurationProperties(ServerProperties.class)
//@Import(ServerProperties.class)
public class TomcatWebServerConfig {
//    @Bean("tomcatWebServerFactory")
//    public ServletWebServerFactory servletWebServerFactory() {
//        return new TomcatServletWebServerFactory();
//    }

    // 별도의 serverProperties 클래스로 분리 후 외부 설정
    @Bean("tomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory(ServerProperties serverProperties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setContextPath(serverProperties.getContextPath());
        factory.setPort(serverProperties.getPort());
        return factory;
    }

    static class TomcatCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // ClassUtils.isPresent() : 현재 프로젝트에 특정 라이브러리 클래스가 존재하는 지 체크
            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat",
                    context.getClassLoader());
        }
    }
}
