package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Address;
import guru.springframework.domain.Cart;
import guru.springframework.domain.CartDetail;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Order;
import guru.springframework.domain.OrderDetail;
import guru.springframework.domain.OrderStatus;
import guru.springframework.domain.Product;
import guru.springframework.domain.Role;
import guru.springframework.domain.User;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ProductService;
import guru.springframework.services.RoleService;
import guru.springframework.services.UserService;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private static final Integer ELEMENTS_COUNT = 5;
	
	private ProductService productService;
	private CustomerService customerService;
	private RoleService roleService;
	private UserService userService;
	
	@Autowired
	public void setProductService(final ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setCustomerService(final CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setRoleService(final RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setUserService(final UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loadProducts();
		loadCustomers();
		loadCarts();
		loadOrderHistory();
		loadRoles();
		assignUsersToDefaultRole();
//		loadUsers();
	}
	
	private void assignUsersToDefaultRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role ->{
            if(role.getRole().equalsIgnoreCase("CUSTOMER")){
                users.forEach(user -> {
                    user.addRole(role);
                    userService.createOrUpdateObject(user);
                });
            }
        });
    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("CUSTOMER");
        roleService.createOrUpdateObject(role);
    }
	
	private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user ->{
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);

            products.forEach(product -> {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
        });
    }
//	
	private void loadCarts() {
        List<User> users = (List<User>) userService.listAll();
        List<Product> products = (List<Product>) productService.listAll();

        users.forEach(user -> {
            user.setCart(new Cart());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
            userService.createOrUpdateObject(user);
        });
    }
	
	public void loadProducts() {
		Product product;

		for(int i = 0; i < ELEMENTS_COUNT; i++) {
			product = createProduct(i + 1);
			productService.createOrUpdateObject(product);
		}
	}
	
	public void loadCustomers() {
		Customer customer;
		
		for(int i = 0; i < ELEMENTS_COUNT; i++) {
			customer = createCustomer(i + 1);
//			customerService.createOrUpdateObject(customer);
		}
	}
	
	public void loadUsers() {
		User user;
		
		for(int i = 0; i < ELEMENTS_COUNT; i++) {
			user = createUser(i + 1);
			userService.createOrUpdateObject(user);
		}
	}
	
	private Product createProduct(final Integer productId) {
		final Product product = new Product();
        product.setId(productId);
        product.setDescription(String.format("Product %1$s", productId));
        product.setPrice(new BigDecimal("12.99"));
        product.setImageUrl(String.format("http://example.com/product%1$s", productId));
        
        return product;
	}
	
	private Customer createCustomer(final Integer customerId) {
//		User user = new User();
//		user.setId(customerId + 15);
//		user.setUsername(String.format("username%1$s", customerId));
//		user.setPassword(String.format("password%1$s", customerId));
		
		User user = createUser(customerId);
		final Customer customer = new Customer();
        customer.setId(customerId);
        customer.setFirstName(String.format("Customer %1$02d", customerId));
    	customer.setLastName(String.format("Customer %1$02d LN", customerId));
    	customer.setEmail(String.format("customer%1$s@domain.com", customerId));
    	customer.setPhoneNumber("3214569870");
    	customer.setBillingAddress(new Address());
    	customer.getBillingAddress().setAddressLine1("Address line one");
    	customer.getBillingAddress().setAddressLine2("Address line two");
    	customer.getBillingAddress().setCity("City"); 
    	customer.getBillingAddress().setState("State");
    	customer.getBillingAddress().setZipCode("111705");
    	customer.setShippingAddress(new Address());
    	customer.getShippingAddress().setAddressLine1("Address line one");
    	customer.getShippingAddress().setAddressLine2("Address line two");
    	customer.getShippingAddress().setCity("City"); 
    	customer.getShippingAddress().setState("State");
    	customer.getShippingAddress().setZipCode("111705");
    	user.setCustomer(customer);
    	userService.createOrUpdateObject(user);
        
        return customer;
	}
	
	private User createUser(final Integer userId) {
		final User user = new User();
        user.setId(userId);
        user.setPassword(String.format("Password %1$s", userId));
        user.setUsername(String.format("User %1$02d", userId));
        
        return user;
	}
	
}
