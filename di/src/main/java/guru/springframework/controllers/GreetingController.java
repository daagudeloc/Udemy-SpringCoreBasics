package guru.springframework.controllers;

import guru.springframework.services.HelloWorldService;

public class GreetingController {

	private HelloWorldService helloWorldService;
	private HelloWorldService helloWorldServiceGerman;
	private HelloWorldService helloWorldServiceFrench;
	
	public void setHelloWorldService(final HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	public void setHelloWorldServiceGerman(final HelloWorldService helloWorldServiceGerman) {
		this.helloWorldServiceGerman = helloWorldServiceGerman;
	}
	
	public void setHelloWorldServiceFrench(final HelloWorldService helloWorldServiceFrench) {
		this.helloWorldServiceFrench = helloWorldServiceFrench;
	}
	
	public String sayHello() {
		final String greeting = helloWorldService.getGreeting();
		System.out.println(greeting);
		System.out.println(helloWorldServiceGerman.getGreeting());
		System.out.println(helloWorldServiceFrench.getGreeting());
		
		return greeting;
	}
}
