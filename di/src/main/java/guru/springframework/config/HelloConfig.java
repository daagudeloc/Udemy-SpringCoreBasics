package guru.springframework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import guru.springframework.services.HelloWorldFactory;
import guru.springframework.services.HelloWorldService;

@Configuration
public class HelloConfig {
	
	@Bean
	public HelloWorldFactory helloWorldFactory() {
		return new HelloWorldFactory();
	}
	
	@Bean
	@Profile({"default","english"})
	@Primary
	public HelloWorldService helloWorldServiceEnglish(final HelloWorldFactory helloWorldFactory) {
		return helloWorldFactory.createHelloWorldService("en");
	}
	
	@Bean
	@Profile("spanish")
	@Primary
	public HelloWorldService helloWorldServiceSpanish(final HelloWorldFactory helloWorldFactory) {
		return helloWorldFactory.createHelloWorldService("sp");
	}
	
	@Bean(name = "french")
	public HelloWorldService helloWorldServiceFrench(final HelloWorldFactory helloWorldFactory) {
		return helloWorldFactory.createHelloWorldService("fr");
	}
	
	@Bean
	public HelloWorldService helloWorldServiceGerman(final HelloWorldFactory helloWorldFactory) {
		return helloWorldFactory.createHelloWorldService("de");
	}
}
