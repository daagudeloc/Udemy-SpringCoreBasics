package guru.springframework.services;

public class HelloWorldServiceSpa implements HelloWorldService {
	@Override
	public String getGreeting() {
		return "Hola mundo!!!!!";
	}
}
