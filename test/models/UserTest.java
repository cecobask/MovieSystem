package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import static models.Fixtures.users;

// JUnit test case for testing the User class
public class UserTest {

	User tsvetoslav = new User("Tsvetoslav", "Dimov", 21, "male", "customer assistant", "x91 v5w6");
	User ivan = new User("Ivan", "Dimov", 42, "male", "engineer", "x91 v5w6");
	
	@Test
	public void testCreate(){
		assertEquals("Tsvetoslav", tsvetoslav.firstName);
		assertEquals("Dimov", tsvetoslav.surname);
		assertEquals(21, tsvetoslav.age);
		assertEquals("male", tsvetoslav.gender);
		assertEquals("customer assistant", tsvetoslav.job);
		assertEquals("x91 v5w6", tsvetoslav.zip);
	}
	
	@Test
	public void testIds(){
		Set<Long> ids = new HashSet<>();
		for(User user: users){
			ids.add(user.userId);
		}
		assertEquals(users.length, ids.size());
	}
	
	@Test
	public void testToString(){
		assertEquals("User{" + tsvetoslav.userId + ", Tsvetoslav, Dimov, 21, male, customer assistant, x91 v5w6}", tsvetoslav.toString());
	}
	
	@Test
	public void testEquals(){
		User tsssvetoslav = new User("Tsvetoslav", "Dimov", 21, "male", "customer assistant", "x91 v5w6");
		
		assertEquals(tsvetoslav, tsvetoslav);
		assertEquals(tsvetoslav, tsssvetoslav);
		assertNotEquals(tsvetoslav, ivan);
	}

}
