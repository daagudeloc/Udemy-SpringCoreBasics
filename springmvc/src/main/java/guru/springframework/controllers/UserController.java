package guru.springframework.controllers;

import static guru.springframework.controllers.constants.TemplateConstants.*;
import static guru.springframework.controllers.constants.UrlConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;

@Controller
public class UserController {
	
//	private UserService userService;
//	
//	@Autowired
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
//	
//	@RequestMapping(PRODUCTS_URL)
//	public String listUsers(Model model) {
//		model.addAttribute("users", userService.listAll());
//		return getUserTemplate(PRODUCTS);
//	}
//	
//	@RequestMapping(PRODUCT_ID_URL)
//	public String getUserById(@PathVariable Integer id, Model model) {
//		model.addAttribute("user", userService.getObjectById(id));
//		return getUserTemplate(PRODUCT);
//	}
//	
//	@RequestMapping(EDIT_PRODUCT_URL)
//	public String editUserById(@PathVariable Integer id, Model model) {
//		model.addAttribute("user", userService.getObjectById(id));
//		return getUserTemplate(PRODUCT_FORM);
//	}
//	
//	@RequestMapping(DELETE_PRODUCT_URL)
//	public String deleteUserById(@PathVariable Integer id) {
//		userService.deleteObject(id);
//		return redirectTo(PRODUCTS_URL);
//	}
//	
//	@RequestMapping(NEW_PRODUCT_URL)
//	public String createNewUser(Model model) {
//		model.addAttribute("user", new User());
//		return getUserTemplate(PRODUCT_FORM);
//	}
//	
//	@RequestMapping(value = PRODUCT_URL, method = RequestMethod.POST)
//	public String createOrUpdateUser(User user) {
//		User modifiedUser = userService.createOrUpdateObject(user);
//		return redirectTo(PRODUCT_URL, modifiedUser.getId());
//	}
	
}
