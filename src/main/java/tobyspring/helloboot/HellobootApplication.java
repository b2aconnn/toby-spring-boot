package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		// serverFactory.getWebServer() : Servlet Container(Tomcat)를 생성
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			// Servlet Container에 Servlet 등록
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp)
						throws ServletException, IOException {
					String name = req.getParameter("name");

					resp.setStatus(HttpStatus.OK.value()); // http 응답 코드
					resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE); // http header
					resp.getWriter().println("Hello Servlet !" + name); // http body
				}
			}).addMapping("/hello");
		});
		webServer.start(); // 서블릿 컨테이너(톰캣)가 동작
	}
}
