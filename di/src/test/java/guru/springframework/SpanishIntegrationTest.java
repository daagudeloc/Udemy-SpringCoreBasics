package guru.springframework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.springframework.services.HelloWorldService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/helloworld-config.xml",
"classpath*:/spring/spanish-helloworld.xml"})
public class SpanishIntegrationTest {

	@Autowired
    private HelloWorldService helloWorldService;

    @Test
    public void testHelloWorld(){
        String greeting = helloWorldService.getGreeting();

        assertEquals("Hola mundo!!!!!", greeting);
    }
}
