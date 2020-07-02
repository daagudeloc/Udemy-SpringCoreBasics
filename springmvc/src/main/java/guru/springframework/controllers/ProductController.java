package guru.springframework.controllers;

import static guru.springframework.controllers.constants.TemplateConstants.*;
import static guru.springframework.controllers.constants.UrlConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

@Controller
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(PRODUCTS_URL)
	public String listProducts(Model model) {
		model.addAttribute("products", productService.listAll());
		return getProductTemplate(PRODUCTS);
	}
	
	@RequestMapping(PRODUCT_ID_URL)
	public String getProductById(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getObjectById(id));
		return getProductTemplate(PRODUCT);
	}
	
	@RequestMapping(EDIT_PRODUCT_URL)
	public String editProductById(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getObjectById(id));
		return getProductTemplate(PRODUCT_FORM);
	}
	
	@RequestMapping(DELETE_PRODUCT_URL)
	public String deleteProductById(@PathVariable Integer id) {
		productService.deleteObject(id);
		return redirectTo(PRODUCTS_URL);
	}
	
	@RequestMapping(NEW_PRODUCT_URL)
	public String createNewProduct(Model model) {
		model.addAttribute("product", new Product());
		return getProductTemplate(PRODUCT_FORM);
	}
	
	@RequestMapping(value = PRODUCT_URL, method = RequestMethod.POST)
	public String createOrUpdateProduct(Product product) {
		Product modifiedProduct = productService.createOrUpdateObject(product);
		return redirectTo(PRODUCT_URL, modifiedProduct.getId());
	}
	
}
