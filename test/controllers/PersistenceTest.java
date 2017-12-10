package controllers;


import static org.junit.Assert.*;
import org.junit.Test;
import models.Movie;
import models.User;
import models.Rating;
import static models.Fixtures.users;
import static models.Fixtures.movies;
import static models.Fixtures.ratings;

// JUnit test case for testing the persistence of the application
public class PersistenceTest {

	MovieRecommenderAPI movRec;
	
	void generate(MovieRecommenderAPI movRec){
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
	
	@Test
	public void testEmpty(){
		movRec = new MovieRecommenderAPI(null);
		assertEquals(0, movRec.movieIndex.size());
		assertEquals(0, movRec.ratingsTable.size());
		assertEquals(0, movRec.userIndex.size());
		generate(movRec);
	}
	
	@Test
	public void testGenerate(){
		movRec = new MovieRecommenderAPI(null);
		assertEquals(0, movRec.movieIndex.size());
		assertEquals(0, movRec.ratingsTable.size());
		assertEquals(0, movRec.userIndex.size());
		generate(movRec);
		
		assertEquals(4, movRec.getUsers().size());
		assertEquals(4, movRec.getMovies().size());
	}

}
