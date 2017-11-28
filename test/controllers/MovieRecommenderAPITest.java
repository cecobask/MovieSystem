package controllers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.Fixtures;
import models.Movie;
import models.User;
import models.Rating;

import static models.Fixtures.users;
import static models.Fixtures.movies;
import static models.Fixtures.ratings;

// JUnit test case for testing the MovieRecommenderAPI class
public class MovieRecommenderAPITest {

	MovieRecommenderAPI movRec;
	Fixtures fixtures;
	User user;
	
	@Before
	public void setup(){
		movRec = new MovieRecommenderAPI();
		
		for(User user : users){
			movRec.addUser(user.firstName, user.surname, user.age, user.gender, user.job, user.zip, user.password, user.role);
		}
		
		for(Movie movie : movies){
			movRec.addMovie(movie.title, movie.releaseDate, movie.imdb);
		}
		for(Rating rating : ratings){
			movRec.addRating(rating.userId, rating.movieId, rating.rating);
		}
	}
	
	@After
	public void tearDown(){
		movRec = null;
	}
	
	
	@Test
	public void testUser(){
		assertEquals(users.length, movRec.getUsers().size());
		movRec.addUser("Heinz", "Ketchup", 69, "male", "food", "Gmbh", "1234", "admin");
		assertEquals (users.length+1, movRec.getUsers().size());	
	}
	
	@Test
	public void testMovie(){
		assertEquals(movies.length, movRec.getMovies().size());
		movRec.addMovie("Terminator", "1984", "http://www.imdb.com/title/tt0088247/");
		assertEquals(movies.length+1, movRec.getMovies().size());
	}
	
	@Test
	public void testRating(){
		assertEquals(ratings.length, movRec.getRatings().size());
		movRec.addRating(0, 0, 0.0);
		assertEquals(ratings.length+1, movRec.getRatings().size());
	}
	
	@Test
	public void testRemoveUser() throws Exception{
		assertEquals(users.length, movRec.getUsers().size());
		User damian = movRec.addUser("Damian", "Mamak", 21, "male", "cook", "CL234", "4321", "regular");
		assertEquals(users.length+1, movRec.getUsers().size());
		movRec.removeUser(4l);
		assertEquals(users.length, movRec.getUsers().size());
	}

	@Test
	public void testRemoveMovie() throws Exception{
		assertEquals(movies.length, movRec.getMovies().size());
		Movie terminator = movRec.addMovie("Terminator", "1984", "http://www.imdb.com/title/tt0088247/");
		assertEquals(movies.length+1, movRec.getMovies().size());
		movRec.removeMovie(terminator);
		assertEquals(movies.length, movRec.getMovies().size());	
	}
	
}
