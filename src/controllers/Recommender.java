package controllers;

import java.util.Collection;

import models.Movie;
import models.Rating;
import models.User;

public interface Recommender {
	
	public void load() throws Exception;
	
	public void write() throws Exception;

	public User addUser(String firstName, String surname, int age, String gender, String job, String zip);
	
	public void removeUser(Long userId);
	
	public Collection<User> getUsers();
	
	public User getUserById(long userId);
	
	public void prime() throws Exception;
	
	public Movie addMovie(String title, String releaseDate, String imdb);
	
	public void removeMovie(Movie movie) throws Exception;
	
	public Collection<Movie> getMovies();
	
	public Movie getMovieById(long movieId);
	
	public Rating addRating(long userId, long movieId, double rating);
	
	public Collection<Rating> getRatings();
	
	public Rating getRatingsById(long movieId);
	
}
