package tobyspring.helloboot;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest {
}

public class HelloServiceTest {
    @FastUnitTest
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
