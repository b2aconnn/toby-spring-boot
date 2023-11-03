package tobyspring.helloboot;

public class HellobootApplication {

	public static void main(String[] args) {
//		HelloServlet helloServlet = new HelloServlet();
//		helloServlet.service();

		FrontController frontController = new FrontController();
		frontController.service();
	}
}
