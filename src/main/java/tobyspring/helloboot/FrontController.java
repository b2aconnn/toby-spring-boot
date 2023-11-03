package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController {
    HelloController helloController = new HelloController();

    public void service() {
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // serverFactory.getWebServer() : Servlet Container(Tomcat)를 생성
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            // Servlet Container에 Servlet 등록
            servletContext.addServlet("frontcontroller", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {
                    // 인증, 보안, 다국어 처리, 공통 기능

                    // front controller 에서 모든 요청을 받아 처리
                    if (req.getRequestURI().equals("/hello")
                            && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");

                        String ret = helloController.hello(name);

                        resp.setStatus(HttpStatus.OK.value()); // http 응답 코드 (생략 가능 : Servlet에서 default로 200 code를 리턴)
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // http header
                        resp.getWriter().println(ret); // http body
                    } else if (req.getRequestURI().equals("/user")) {
                        //
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start(); // 서블릿 컨테이너(톰캣)가 동작
    }
}
