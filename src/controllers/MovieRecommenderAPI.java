package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Movie;
import models.Rating;
import models.User;
import utils.Importer;
import utils.Serializer;

// All the functionality of the application is included in this class
public class MovieRecommenderAPI implements Recommender {

	// IDs matched to the User, Movie, Rating objects for creating a HashMap
	public Map<Long, User> userIndex = new HashMap<>();
	public Map<Long, Movie> movieIndex = new HashMap<>();
	public Map<Long, Rating> ratingIndex = new HashMap<>();
	public Serializer serializer;

	// Empty constructor for the API 
	public MovieRecommenderAPI() {
	}

	// Constructor for Serializer
	public MovieRecommenderAPI(Serializer serializer) {
		this.serializer = serializer;
	}

	// Method used for loading data from the Serializer
	@SuppressWarnings("unchecked")
	public void load() throws Exception {
		serializer.read();
		userIndex = (Map<Long, User>) serializer.pop();
		movieIndex = (Map<Long, Movie>) serializer.pop();
		ratingIndex = (Map<Long, Rating>) serializer.pop();
	}

	// Method used to save the data to the Serializer
	public void write() throws Exception {
		serializer.push(userIndex);
		serializer.push(movieIndex);
		serializer.push(ratingIndex);
		serializer.write();
	}

	// Add a new user object
	public User addUser(String firstName, String surname, int age, String gender, String job, String zip) {
		User user = new User(firstName, surname, age, gender, job, zip);
		user.userId = userIndex.size() + 1l;
		userIndex.put(user.userId, user);
		return user;
	}

	// Remove a user by using his ID
	public void removeUser(Long userId) {
		userIndex.remove(userId);
	}

	// List all users
	public Collection<User> getUsers() {
		return userIndex.values();
	}

	// Search users by their ID
	public User getUserById(long userId) {
		return userIndex.get(userId);
	}

	// Method used to call in the Importer class and pass the path for the .dat file.
	// This results in data being parsed and transfered into the userIndex, movieIndex, ratingIndex HashMaps
	public void prime() throws Exception {
		Importer loader = new Importer();
		List<User> users = loader.loadUsers("lib/users5.dat");
		for (User user : users) {
			userIndex.put(user.userId, user);
		}

		List<Movie> movies = loader.loadMovies("lib/items5.dat");
		for (Movie movie : movies) {
			movieIndex.put(movie.id, movie);
		}

		List<Rating> ratings = loader.loadRatings("lib/ratings5.dat");
		for (Rating rating : ratings) {
			addRating(rating.userId, rating.movieId, rating.rating);
		}
	}

	// Add a new movie object 
	public Movie addMovie(String title, String releaseDate, String imdb) {
		Movie movie = new Movie(title, releaseDate, imdb);
		movie.id = movieIndex.size() + 1l;
		movieIndex.put(movie.id, movie);
		return movie;
	}

	// Remove a movie by using its ID 
	public void removeMovie(Movie movie) throws Exception {
		movieIndex.remove(movie.id);
	}

	// List all movies
	public Collection<Movie> getMovies() {
		return movieIndex.values();
	}

	// Search movies by their ID
	public Movie getMovieById(long movieId) {
		return movieIndex.get(movieId);
	}

	// Add new rating object for a user 
	public Rating addRating(long userId, long movieId, double rating) {
		Rating rate = new Rating(userId, movieId, rating);
		rate.id = ratingIndex.size() + 1l;
		ratingIndex.put(rate.id, rate);
		return rate;
	}

	// List all ratings
	public Collection<Rating> getRatings() {
		return ratingIndex.values();
	}
	
	// Search ratings by MovieID
	public Rating getRatingsById(long movieId) {
		return ratingIndex.get(movieId);
	}

}
