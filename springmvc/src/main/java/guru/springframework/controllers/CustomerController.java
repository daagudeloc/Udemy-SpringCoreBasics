package guru.springframework.controllers;

import static guru.springframework.controllers.constants.TemplateConstants.*;
import static guru.springframework.controllers.constants.UrlConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;

@Controller
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public void setProductService(CustomerService productService) {
		this.customerService = productService;
	}
	
	@RequestMapping(CUSTOMERS_URL)
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerService.listAll());
		return getCustomerTemplate(CUSTOMERS);
	}
	
	@RequestMapping(CUSTOMER_ID_URL)
	public String getCustomerById(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getObjectById(id));
		return getCustomerTemplate(CUSTOMER);
	}
	
	@RequestMapping(EDIT_CUSTOMER_URL)
	public String editCustomerById(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getObjectById(id));
		return getCustomerTemplate(CUSTOMER_FORM);
	}
	
	@RequestMapping(DELETE_CUSTOMER_URL)
	public String deleteCustomerById(@PathVariable Integer id) {
		customerService.deleteObject(id);
		return redirectTo(CUSTOMERS_URL);
	}
	
	@RequestMapping(NEW_CUSTOMER_URL)
	public String createNewCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return getCustomerTemplate(CUSTOMER_FORM);
	}
	
	@RequestMapping(value = CUSTOMER_URL, method = RequestMethod.POST)
	public String createOrUpdateCustomer(Customer customer) {
		Customer modifiedCustomer = customerService.createOrUpdateObject(customer);
		return redirectTo(CUSTOMER_URL, modifiedCustomer.getId());
	}
	
}
