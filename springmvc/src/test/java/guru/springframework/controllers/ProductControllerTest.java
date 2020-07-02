package guru.springframework.controllers;

import static guru.springframework.controllers.constants.TemplateConstants2.PRODUCT;
import static guru.springframework.controllers.constants.TemplateConstants2.PRODUCTS;
import static guru.springframework.controllers.constants.TemplateConstants2.PRODUCT_FORM;
import static guru.springframework.controllers.constants.TemplateConstants2.getProductTemplate;
import static guru.springframework.controllers.constants.TestingConstants.DESCRIPTION;
import static guru.springframework.controllers.constants.TestingConstants.ID;
import static guru.springframework.controllers.constants.TestingConstants.IMAGE_URL;
import static guru.springframework.controllers.constants.TestingConstants.PRICE;
import static guru.springframework.controllers.constants.UrlConstants2.DELETE_PRODUCT_URL;
import static guru.springframework.controllers.constants.UrlConstants2.EDIT_PRODUCT_URL;
import static guru.springframework.controllers.constants.UrlConstants2.NEW_PRODUCT_URL;
import static guru.springframework.controllers.constants.UrlConstants2.PRODUCTS_URL;
import static guru.springframework.controllers.constants.UrlConstants2.PRODUCT_ID_URL;
import static guru.springframework.controllers.constants.UrlConstants2.PRODUCT_URL;
import static guru.springframework.controllers.constants.UrlConstants2.redirectTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.controllers.ProductController;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

public class ProductControllerTest {
	
	private static final Integer ID_VALUE = 1;
	
	@Mock
	private ProductService productService;
	
	@InjectMocks
	private ProductController productController;
	
	private MockMvc mockMvc;
	
	private String endpoint;
	
	@Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testList() throws Exception{

        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        Mockito.when(productService.listAll()).thenReturn((List) products); //need to strip generics to keep Mockito happy.

        mockMvc.perform(get(PRODUCTS_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(getProductTemplate(PRODUCTS)))
                .andExpect(model().attribute(PRODUCTS, org.hamcrest.Matchers.hasSize(2)));

    }

    @Test
    public void testShow() throws Exception{

        when(productService.getObjectById(ID_VALUE)).thenReturn(new Product());
        
        endpoint = String.format(PRODUCT_ID_URL, ID_VALUE);
        
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(view().name(getProductTemplate(PRODUCT)))
                .andExpect(model().attribute(PRODUCT, instanceOf(Product.class)));
    }

    @Test
    public void testEdit() throws Exception{

        when(productService.getObjectById(ID_VALUE)).thenReturn(new Product());

        endpoint = String.format(EDIT_PRODUCT_URL, ID_VALUE);

        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(view().name(getProductTemplate(PRODUCT_FORM)))
                .andExpect(model().attribute(PRODUCT, instanceOf(Product.class)));
    }

    @Test
    public void testNewProduct() throws Exception {

        verifyNoInteractions(productService);

        mockMvc.perform(get(NEW_PRODUCT_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(getProductTemplate(PRODUCT_FORM)))
                .andExpect(model().attribute(PRODUCT, instanceOf(Product.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String description = "Test Description";
        BigDecimal price = new BigDecimal("12.00");
        String imageUrl = "example.com";

        Product returnProduct = new Product();
        returnProduct.setId(id);
        returnProduct.setDescription(description);
        returnProduct.setPrice(price);
        returnProduct.setImageUrl(imageUrl);

        when(productService.createOrUpdateObject(Mockito.<Product>any())).thenReturn(returnProduct);

        mockMvc.perform(post(PRODUCT_URL)
        .param(ID, ID_VALUE.toString())
        .param(DESCRIPTION, description)
        .param(PRICE, price.toString())
        .param(IMAGE_URL, imageUrl))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(redirectTo(PRODUCT_URL, ID_VALUE)))
                .andExpect(model().attribute(PRODUCT, instanceOf(Product.class)))
                .andExpect(model().attribute(PRODUCT, hasProperty(ID, is(id))))
                .andExpect(model().attribute(PRODUCT, hasProperty(DESCRIPTION, is(description))))
                .andExpect(model().attribute(PRODUCT, hasProperty(PRICE, is(price))))
                .andExpect(model().attribute(PRODUCT, hasProperty(IMAGE_URL, is(imageUrl))));

        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(productService).createOrUpdateObject(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(description, boundProduct.getValue().getDescription());
        assertEquals(price, boundProduct.getValue().getPrice());
        assertEquals(imageUrl, boundProduct.getValue().getImageUrl());
    }

    @Test
    public void testDelete() throws Exception{

    	endpoint = String.format(DELETE_PRODUCT_URL, ID_VALUE);
    	
        mockMvc.perform(get(endpoint))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(redirectTo(PRODUCTS_URL)));

        verify(productService, times(1)).deleteObject(ID_VALUE);
    }
}
