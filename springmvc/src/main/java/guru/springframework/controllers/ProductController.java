package guru.springframework.controllers;

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
	
	@RequestMapping("/products")
	public String listProducts(Model model) {
		model.addAttribute("products", productService.listAll());
		return "product/products";
	}
	
	@RequestMapping("/product/{id}")
	public String getProductById(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getObjectById(id));
		return "product/product";
	}
	
	@RequestMapping("/product/edit/{id}")
	public String editProductById(@PathVariable Integer id, Model model) {
		model.addAttribute("product", productService.getObjectById(id));
		return "product/product-form";
	}
	
	@RequestMapping("/product/delete/{id}")
	public String deleteProductById(@PathVariable Integer id) {
		productService.deleteObject(id);
		return "redirect:/products";
	}
	
	@RequestMapping("/product/new")
	public String createNewProduct(Model model) {
		model.addAttribute("product", new Product());
		return "product/product-form";
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public String createOrUpdateProduct(Product product) {
		Product modifiedProduct = productService.createOrUpdateObject(product);
		return String.format("redirect:/product/%1$s", modifiedProduct.getId());
	}
	
}
