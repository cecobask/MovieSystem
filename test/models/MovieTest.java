package models;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

// JUnit test case to test the Movie class
public class MovieTest {

	public List<Rating> ratings = new ArrayList<Rating>();
	
	Movie train = new Movie("Trainspotting", "23-Feb-1996", "http://www.imdb.com/title/tt0117951/");
	Movie t2 = new Movie("T2 Trainspotting", "27-Jan-2017", "http://www.imdb.com/title/tt2763304/");
	
	@Test
	public void testCreate(){
		assertEquals("Trainspotting", train.title);
		assertEquals("23-Feb-1996", train.releaseDate);
		assertEquals("http://www.imdb.com/title/tt0117951/", train.imdb);
		assertEquals("T2 Trainspotting", t2.title);
		assertEquals("27-Jan-2017", t2.releaseDate);
		assertEquals("http://www.imdb.com/title/tt2763304/", t2.imdb);
		assertNotSame("T2 Trainspotting", train.title);
	}
	
	@Test
	public void testToString(){
		assertEquals("Movie{" + train.id + ", Trainspotting, 23-Feb-1996, http://www.imdb.com/title/tt0117951/}", train.toString());
	}
	
	@Test
	public void testEquals(){
		assertEquals(train, train);
		assertFalse(train.equals(t2));
		assertNotEquals(train, t2);
		assertEquals(t2, t2);
	}
	
}
