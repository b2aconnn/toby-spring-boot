package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
// @Condition : 해당 Configuration 클래스에 있는 Bean들을 등록할 지 말 지 설정
// Condition Interface를 구현한 클래스를 엘리먼트로 등록해야함
@Conditional(JettyWebServerConfig.JettyCondition.class)
public class JettyWebServerConfig {
    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory serverFactory() {
        return new JettyServletWebServerFactory();
    }

    static class JettyCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // ClassUtils.isPresent() : 현재 프로젝트에 특정 라이브러리 클래스가 존재하는 지 체크
            return ClassUtils.isPresent("org.eclipse.jetty.server.Server",
                    context.getClassLoader());
        }
    }
}
