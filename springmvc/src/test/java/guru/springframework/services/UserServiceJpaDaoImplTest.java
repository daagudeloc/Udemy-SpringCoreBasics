package guru.springframework.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.Cart;
import guru.springframework.domain.CartDetail;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceJpaDaoImplTest {
	
	private static int userCount = 5;
	private UserService userService;
	private ProductService productService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setUserService(ProductService productService) {
		this.productService = productService;
	}
	
	@Test
	public void shouldReturnUserList() {
		
		final List<User> users = (List<User>) userService.listAll();
		
		System.out.println("shouldReturnUserList");
		System.out.println("User count: " + userCount + " User list size: " + users.size());
		assertThat(users.size(), is(userCount));
	}

	@Test
	public void shouldReturnSpecificUser() {
		
		final User user = userService.getObjectById(6);
		
		System.out.println("shouldRetrieveUser");
		System.out.println("User pss: " + user.getPassword() + " User encrypted pss: " + user.getEncryptedPassword());
		
		assertThat(user.getId(), is(6));
		assertThat(user.getUsername(), is("User 01"));
	}

	@Test
	public void shouldCreateUser() {
		
		final User user = createUser(++userCount);
		userService.createOrUpdateObject(user);
		final List<User> users = (List<User>) userService.listAll();
		System.out.println("shouldCreateUser");
		System.out.println("User pss: " + user.getPassword() + " User encrypted pss: " + user.getEncryptedPassword());
		System.out.println("User count: " + userCount + " User list size: " + users.size());
		assertThat(users.size(), is(userCount));
	}

	@Test
	public void shouldSaveUserWithCustomer() {
		
		final User user = new User();
		user.setId(10);
		user.setUsername("User 01");
		user.setPassword("Very strong password");
		
		final Customer customer = new Customer();
		customer.setId(11);
		customer.setFirstName("Customer 01");
		customer.setLastName("Customer 01LN");
		
		user.setCustomer(customer);
		
		final User savedUser = userService.createOrUpdateObject(user);
		userCount++;
		
		assertThat(savedUser.getId(), is(not(nullValue())));
		assertThat(savedUser.getVersion(), is(not(nullValue())));
		assertThat(savedUser.getCustomer(), is(not(nullValue())));
		assertThat(savedUser.getCustomer().getId(), is(not(nullValue())));
	}
	
	@Test
	public void shouldUpdateUser() {
		
		User user = userService.getObjectById(7);
		System.out.println("shouldUpdateUser");
		System.out.println("User pss: " + user.getPassword() + " User encrypted pss: " + user.getEncryptedPassword());
		user.setUsername("New username");
		userService.createOrUpdateObject(user);
		User retrievedUserFromDb = userService.getObjectById(7);
		
		assertThat(user.getUsername(), is(retrievedUserFromDb.getUsername()));
	}
	
	@Test
	public void shouldDeleteUser() {
		
		userService.deleteObject(10);
	
		final List<User> users = (List<User>) userService.listAll();
		assertThat(--userCount, is(users.size()));
		
		System.out.println("shouldCreateUser");
		System.out.println("User count: " + userCount + " User list size: " + users.size());
		
	}
	
	@Test
    public void testAddCartToUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        User savedUser = userService.createOrUpdateObject(user);
        userCount++;
        
        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart() != null;
        assert savedUser.getCart().getId() != null;
    }

    @Test
    public void testAddCartToUserWithCartDetails() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.createOrUpdateObject(user);
        userCount++;

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart() != null;
        assert savedUser.getCart().getId() != null;
        assert savedUser.getCart().getCartDetails().size() == 2;
    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetails() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.createOrUpdateObject(user);

        assert savedUser.getCart().getCartDetails().size() == 2;

        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));

        userService.createOrUpdateObject(savedUser);
        userCount++;

        assert savedUser.getCart().getCartDetails().size() == 1;
    }
	
	private User createUser(final Integer productId) {
		final User user = new User();
        user.setId(productId);
        user.setPassword(String.format("Password %1$s", productId));
        user.setUsername(String.format("User %1$02d", productId));
        
        return user;
	}
}
