package guru.springframework.controllers;

import static guru.springframework.controllers.constants.TemplateConstants.INDEX;
import static guru.springframework.controllers.constants.UrlConstants.INDEX_URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(INDEX_URL)
	public String index() {
		return INDEX;
	}
	
}
