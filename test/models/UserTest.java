package models;

import static org.junit.Assert.*;
import org.junit.Test;

// JUnit test case for testing the User class
public class UserTest {

	User tsvetoslav = new User("Tsvetoslav", "Dimov", 21, "male", "customer assistant", "x91 v5w6", "1234", "default");
	User ivan = new User("Ivan", "Dimov", 42, "male", "engineer", "x91 v5w6", "4321", "default");
	
	@Test
	public void testCreate(){
		assertEquals("Tsvetoslav", tsvetoslav.firstName);
		assertEquals("Dimov", tsvetoslav.surname);
		assertEquals(21, tsvetoslav.age);
		assertEquals("male", tsvetoslav.gender);
		assertEquals("customer assistant", tsvetoslav.job);
		assertEquals("x91 v5w6", tsvetoslav.zip);
		assertEquals("1234", tsvetoslav.password);
		
	}
	
	@Test
	public void testToString(){
		assertEquals("User{" + tsvetoslav.userId + ", Tsvetoslav, Dimov, 21, male, customer assistant, x91 v5w6, 1234, default}", tsvetoslav.toString());
	}
	
	@Test
	public void testEquals(){
		User tsssvetoslav = new User("Tsvetoslav", "Dimov", 21, "male", "customer assistant", "x91 v5w6", "1234", "default");
		
		assertEquals(tsvetoslav, tsvetoslav);
		assertEquals(tsvetoslav, tsssvetoslav);
		assertNotEquals(tsvetoslav, ivan);
	}	
}
