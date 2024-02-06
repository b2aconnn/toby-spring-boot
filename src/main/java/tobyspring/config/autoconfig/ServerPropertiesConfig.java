package tobyspring.config.autoconfig;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

//@MyAutoConfiguration
public class ServerPropertiesConfig {
//    @Bean
    public ServerProperties serverProperties(Environment environment) {
        // Property Source에 있는 값들을 ServerProperties Object에 binding 시켜줌
        return Binder.get(environment).bind("", ServerProperties.class).get();
    }
}
