package models;

import static org.junit.Assert.*;

import org.junit.Test;

// JUnit test case to test the Rating class
public class RatingTest {

		Rating rating0 = new Rating(0l, 0l, 0);
		Rating rating1 = new Rating(1l, 1l, 1);
		
		
		@Test
		public void testToString(){
			assertEquals("Rating{0, 0, 0.0}", rating0.toString());
		}
		
		@Test
		public void testEquals(){
			assertEquals(rating0, rating0);
			assertEquals(rating1, rating1);
			assertNotEquals(rating0, rating1);
		}
		
}


