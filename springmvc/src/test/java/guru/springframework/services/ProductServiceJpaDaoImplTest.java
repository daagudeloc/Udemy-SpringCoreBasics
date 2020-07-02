package guru.springframework.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceJpaDaoImplTest {
	
	private static int productCount = 5;
	private ProductService productService;

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Test
	public void shouldReturnProductList() {
		
		final List<Product> products = (List<Product>) productService.listAll();
		
		assertThat(products.size(), is(productCount));
	}

	@Test
	public void shouldReturnSpecificProduct() {
		
		final Product product = productService.getObjectById(1);
		
		assertThat(product.getId(), is(1));
		assertThat(product.getDescription(), is("Product 1"));
	}

	@Test
	public void shouldCreateProduct() {
		
		final Product product = createProduct(++productCount);
		productService.createOrUpdateObject(product);
		final List<Product> products = (List<Product>) productService.listAll();
		
		assertThat(products.size(), is(productCount));
	}

	@Test
	public void shouldUpdateProduct() {
		
		Product product = productService.getObjectById(2);
		product.setDescription("New product description");
		productService.createOrUpdateObject(product);
		Product retrievedProductFromDb = productService.getObjectById(2);
		
		assertThat(product.getDescription(), is(retrievedProductFromDb.getDescription()));
	}
	
	@Test
	public void shouldDeleteProduct() {
		
		productService.deleteObject(productCount);
	
		final List<Product> products = (List<Product>) productService.listAll();
		
		assertThat(--productCount, is(products.size()));
	}
	
	private void loadProducts(final Integer productCount) {
		
		for(int i = 0; i < productCount; i++) {
			productService.createOrUpdateObject(createProduct(i + 1));
		}
        System.out.println("Products loaded!!");
	}
	
	private Product createProduct(final Integer productId) {
		final Product product = new Product();
        product.setId(productId);
        product.setDescription(String.format("Product %1$s", productId));
        product.setPrice(new BigDecimal("12.99"));
        product.setImageUrl(String.format("http://example.com/product%1$s", productId));
        
        return product;
	}
}
