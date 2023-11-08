package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

// 해당 클래스가 구성 정보라는 것을 알려주기 위한 선언
@Configuration
// 해당 클래스가 있는 패키지부터 시작해서 하위 패키지까지 탐색을 해 @Component가 붙은 클래스들을 모두 Bean으로 등록을 해줌
@ComponentScan
public class ConfigurationFrontController {
    // 팩토리 메소드의 return한 객체를 Spring Container에 Bean으로 등록
//    @Bean
//    public HelloController helloController(HelloService helloService) {
//        return new HelloController(helloService);
//    }
//    @Bean
//    public HelloService helloService() {
//        return new SimpleHelloService();
//    }

    public void service() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",
                            new DispatcherServlet(this)
                    ).addMapping("/*");
                });
                webServer.start();
            }
        };

        // Bean을 찾기 위해 구성 정보 클래스를 등록해줘야 함
        applicationContext.register(ConfigurationFrontController.class);
        applicationContext.refresh(); // Spring Container를 초기화
    }
}
