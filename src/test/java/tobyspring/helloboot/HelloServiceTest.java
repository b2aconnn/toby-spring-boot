package tobyspring.helloboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();
        String rt = helloService.sayHello("Test");
        assertThat(rt).isEqualTo("helloTest");
    }

    @Test
    void helloDecorator() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);
        String rt = helloDecorator.sayHello("Test");
        assertThat(rt).isEqualTo("*Test*");
    }
}
