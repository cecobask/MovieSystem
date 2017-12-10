package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;

import models.Movie;
import models.Rating;
import models.User;
import utils.AlphabeticalComparator;
import utils.FileLogger;
import utils.Importer;
import utils.MovieComparator;
import utils.Serializer;

// All the functionality of the application is included in this class
public class MovieRecommenderAPI implements Recommender {

	// IDs matched to the User and Movie objects for creating a HashMap
	// HashBasedTable for storing the ratings
	public Map<Long, User> userIndex = new HashMap<>();
	public Map<Long, Movie> movieIndex = new HashMap<>();
	public Serializer serializer;
	Optional<User> currentUser;
	public HashBasedTable<Long, Long, Rating> ratingsTable = HashBasedTable.create();
	

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
		ratingsTable = (HashBasedTable<Long, Long, Rating>) serializer.pop();
		movieIndex = (Map<Long, Movie>) serializer.pop();
		userIndex = (Map<Long, User>) serializer.pop();
	}

	// Method used to save the data to the Serializer
	public void write() throws Exception {
		serializer.push(userIndex);
		serializer.push(movieIndex);
		serializer.push(ratingsTable);
		serializer.write();
	}

	// Add a new user object
	public User addUser(String firstName, String surname, int age, String gender, String job, String zip,
			String password, String role) {
		User user = new User(firstName, surname, age, gender, job, zip, password, role);
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

	// Method used to call in the Importer class and pass the path for the .dat file
	// This results in data being parsed and transfered into the userIndex,
	// movieIndex and ratingsTable
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
		Movie movie = new Movie(title, releaseDate, imdb, 0); // set rating to zero
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

	// Sort Movies by Title
	public Collection<Movie> sortedByTitle() {
		List<Movie> sortedByTitle = new ArrayList<Movie>(movieIndex.values());
		Collections.sort(sortedByTitle, new AlphabeticalComparator());

		return sortedByTitle;
	}

	// Search movie titles by a search term
	public List<Movie> getMovieByName(String term) {
		List<Movie> result = new ArrayList<Movie>();
		for (Movie movie : movieIndex.values()) {
			if (movie.title.toLowerCase().contains(term.toLowerCase())) {
				result.add(movie);
			}
		}
		return result;
	}

	// Calculate average movie rating
	public double averageMovieRating(long movieId) {

		Map<Long, Rating> ratingsMap = new HashMap<>();
		
		Movie mov = getMovieById(movieId);
		mov.ratings = 0; // reset to zero

		// Create a column in ratingsTable
		ratingsMap = ratingsTable.column(movieId);

		for (Rating rat : ratingsMap.values())
			mov.ratings += rat.rating;

		// Calculate the average rating of a movie
		mov.ratings /= ratingsMap.size();

		return mov.ratings;
	}

	// Add new rating object for a user
	public Rating addRating(long userId, long movieId, int rating) {
		Rating rate = new Rating(userId, movieId, rating);
		rate.id = ratingsTable.size() + 1l;

		ratingsTable.put(userId, movieId, rate);
		return rate;
	}

	// Get specific movie rating
	public Rating getRating(long userId, long movieId) {
		return ratingsTable.get(userId, movieId);
	}

	// Get all ratings from a user
	public List<Rating> getUserRatings(long userId) {
		List<Rating> userRatings = new ArrayList<>();
		userRatings.addAll(ratingsTable.row(userId).values());

		return userRatings;
	}

	// Get all the ratings for a specific movie
	public List<Rating> getAllRatingsForMovie(long movieId) {
		List<Rating> ratingsMovie = new ArrayList<>();
		ratingsMovie.addAll(ratingsTable.column(movieId).values());

		return ratingsMovie;
	}

	// Remove rating for a movie from specific user
	public Rating removeRating(long userId, long movieId) {
		return ratingsTable.remove(userId, movieId);
	}

	// List all ratings
	public Collection<Rating> getRatings() {
		return ratingsTable.values();
	}
	
	// Generate a list of Top 10 movies based on average rating score
	public List<Movie> getTopTenMovies()
	{
		// Calculate average movie rating for every movie
		for(Movie mov : movieIndex.values())
			averageMovieRating(mov.id);
		
		// Sort movies by rating
		List<Movie> moviesList = new ArrayList<>(movieIndex.values());
		MovieComparator movComparator = new MovieComparator();
		Collections.sort(moviesList, movComparator);
		
		// Return all the movies if they're less than 10
		if(moviesList.size() < 10){
			return moviesList;
		} else {
			return moviesList.subList(0, 10);
		}
	}

	// Login feature using userID and password
	public boolean login(long userId, String password) {
		Optional<User> user = Optional.fromNullable(userIndex.get(userId));
		if (user.isPresent() && user.get().password.equals(password)) {
			currentUser = user;
			FileLogger.getLogger().log("You're logged in as " + currentUser.get().userId);
			return true;
		}
		return false;
	}

	// Logout current user
	public void logout() {
		Optional<User> user = currentUser;
		if (user.isPresent()) {
			FileLogger.getLogger().log("User " + currentUser.get().userId + " just logged out...");
			currentUser = Optional.absent();
		}
		System.out.println("User " + currentUser.get().firstName + " just logged out...");
	}

}