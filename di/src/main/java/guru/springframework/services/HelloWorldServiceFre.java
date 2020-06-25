package guru.springframework.services;

public class HelloWorldServiceFre implements HelloWorldService {
	@Override
	public String getGreeting() {
		return "Salut le monde!";
	}
}
