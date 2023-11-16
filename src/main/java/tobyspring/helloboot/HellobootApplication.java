package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;

@MySpringBootAnnotation
public class HellobootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellobootApplication.class, args);

//		HelloServlet helloServlet = new HelloServlet();
//		helloServlet.service();

//		FrontController frontController = new FrontController();
//		frontController.service();

//		SpringHelloController springHelloController = new SpringHelloController();
//		springHelloController.service();

//		DispatcherServletController dispatcherServletController = new DispatcherServletController();
//		dispatcherServletController.service();

//		ConfigurationFrontController configurationFrontController = new ConfigurationFrontController();
//		configurationFrontController.service();
	}
}
