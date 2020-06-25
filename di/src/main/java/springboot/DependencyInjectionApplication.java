package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import guru.springframework.controllers.GreetingController;

@SpringBootApplication
@ImportResource("classpath:/spring/spring-config.xml")
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DependencyInjectionApplication.class, args);
		GreetingController controller = ctx.getBean(GreetingController.class);
		controller.sayHello();
	}

}
