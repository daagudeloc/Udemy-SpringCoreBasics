package guru.springframework.services;

public class HelloWorldFactory {
	public HelloWorldService createHelloWorldService(String language) {
		HelloWorldService helloWorldService;
		
		switch(language) {
			case "en":
				helloWorldService = new HelloWorldServiceEng();
				break;
			case "sp":
				helloWorldService = new HelloWorldServiceSpa();
				break;
			case "fr":
				helloWorldService = new HelloWorldServiceFre();
				break;
			case "de":
				helloWorldService = new HelloWorldServiceGer();
				break;
			default:
				helloWorldService = new HelloWorldServiceEng();
				break;
		}
		
		return helloWorldService;
		
	}
}
