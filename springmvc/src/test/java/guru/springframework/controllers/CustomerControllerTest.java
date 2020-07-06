package guru.springframework.controllers;

import static guru.springframework.controllers.constants.TemplateConstants2.CUSTOMER;
import static guru.springframework.controllers.constants.TemplateConstants2.CUSTOMERS;
import static guru.springframework.controllers.constants.TemplateConstants2.CUSTOMER_FORM;
import static guru.springframework.controllers.constants.TemplateConstants2.getCustomerTemplate;
import static guru.springframework.controllers.constants.TestingConstants.ADDRESS_LINE_ONE;
import static guru.springframework.controllers.constants.TestingConstants.CITY;
import static guru.springframework.controllers.constants.TestingConstants.EMAIL;
import static guru.springframework.controllers.constants.TestingConstants.ID;
import static guru.springframework.controllers.constants.UrlConstants2.CUSTOMERS_URL;
import static guru.springframework.controllers.constants.UrlConstants2.CUSTOMER_ID_URL;
import static guru.springframework.controllers.constants.UrlConstants2.CUSTOMER_URL;
import static guru.springframework.controllers.constants.UrlConstants2.DELETE_CUSTOMER_URL;
import static guru.springframework.controllers.constants.UrlConstants2.EDIT_CUSTOMER_URL;
import static guru.springframework.controllers.constants.UrlConstants2.NEW_CUSTOMER_URL;
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

import guru.springframework.controllers.CustomerController;
import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;

public class CustomerControllerTest {
	
	private static final Integer ID_VALUE = 1;
	
	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	
	private MockMvc mockMvc;
	
	private String endpoint;
	
	@Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //initilizes controller and mocks

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception{

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        Mockito.when(customerService.listAll()).thenReturn((List) customers);

        mockMvc.perform(get(CUSTOMERS_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(getCustomerTemplate(CUSTOMERS)))
                .andExpect(model().attribute(CUSTOMERS, org.hamcrest.Matchers.hasSize(2)));

    }

    @Test
    public void testShow() throws Exception{

        when(customerService.getObjectById(ID_VALUE)).thenReturn(new Customer());
        
        endpoint = String.format(CUSTOMER_ID_URL, ID_VALUE);
        
        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(view().name(getCustomerTemplate(CUSTOMER)))
                .andExpect(model().attribute(CUSTOMER, instanceOf(Customer.class)));
    }

    @Test
    public void testEdit() throws Exception{

        when(customerService.getObjectById(ID_VALUE)).thenReturn(new Customer());

        endpoint = String.format(EDIT_CUSTOMER_URL, ID_VALUE);

        mockMvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(view().name(getCustomerTemplate(CUSTOMER_FORM)))
                .andExpect(model().attribute(CUSTOMER, instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {

        verifyNoInteractions(customerService);

        mockMvc.perform(get(NEW_CUSTOMER_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(getCustomerTemplate(CUSTOMER_FORM)))
                .andExpect(model().attribute(CUSTOMER, instanceOf(Customer.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String addressLineOne = "Test Address";
        String city = "Test city";
        String email = "email@example.com";

        Customer returnCustomer = new Customer();
        returnCustomer.setId(id);
        returnCustomer.setEmail(email);

        when(customerService.createOrUpdateObject(Mockito.<Customer>any())).thenReturn(returnCustomer);

        mockMvc.perform(post(CUSTOMER_URL)
        .param(ID, ID_VALUE.toString())
        .param(ADDRESS_LINE_ONE, addressLineOne)
        .param(CITY, city)
        .param(EMAIL, email))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(redirectTo(CUSTOMER_URL, ID_VALUE)))
                .andExpect(model().attribute(CUSTOMER, instanceOf(Customer.class)))
                .andExpect(model().attribute(CUSTOMER, hasProperty(ID, is(id))))
                .andExpect(model().attribute(CUSTOMER, hasProperty(EMAIL, is(email))));

        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).createOrUpdateObject(boundCustomer.capture());

        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(email, boundCustomer.getValue().getEmail());
    }

    @Test
    public void testDelete() throws Exception{

    	endpoint = String.format(DELETE_CUSTOMER_URL, ID_VALUE);
    	
        mockMvc.perform(get(endpoint))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(redirectTo(CUSTOMERS_URL)));

        verify(customerService, times(1)).deleteObject(ID_VALUE);
    }
}
