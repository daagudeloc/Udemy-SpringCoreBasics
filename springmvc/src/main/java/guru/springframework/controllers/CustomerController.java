package guru.springframework.controllers;

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
	
	@RequestMapping("/customers")
	public String listCustomers(Model model) {
		model.addAttribute("customers", customerService.listAll());
		return "customer/customers";
	}
	
	@RequestMapping("/customer/{id}")
	public String getCustomerById(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getObjectById(id));
		return "customer/customer";
	}
	
	@RequestMapping("/customer/edit/{id}")
	public String editCustomerById(@PathVariable Integer id, Model model) {
		model.addAttribute("customer", customerService.getObjectById(id));
		return "customer/customer-form";
	}
	
	@RequestMapping("/customer/delete/{id}")
	public String deleteCustomerById(@PathVariable Integer id) {
		customerService.deleteObject(id);
		return "redirect:/customers";
	}
	
	@RequestMapping("/customer/new")
	public String createNewCustomer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer/customer-form";
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public String createOrUpdateCustomer(Customer customer) {
		Customer modifiedCustomer = customerService.createOrUpdateObject(customer);
		return String.format("redirect:/customer/%1$s", modifiedCustomer.getId());
	}
	
}
