package controllers;

import java.util.Collection;
import java.util.List;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movie;
import models.Rating;
import models.User;

public class DefaultMenu {

	private Long userId;
	private User user;
	private Movie movie;
	private Rating rating;
	private MovieRecommenderAPI movRec;

	public DefaultMenu(MovieRecommenderAPI movRec, User user) {
		this.movRec = movRec;
		this.setId(user.userId);
		this.user = user;
	}

	@Command(description = "Save info")
	public String save() throws Exception {
		movRec.write();
		return "You successfully saved the information.";
	}

	@Command(description = "List all users")
	public void listUsers() {
		Collection<User> users = movRec.getUsers();
		for (User user : users) {
			System.out.println(user);
		}
	}

	@Command(description = "Search for a user by ID(userId)")
	public String searchUsers(@Param(name = "userId") long userId) {
		user = movRec.getUserById(userId);
		return user.toString();
	}

	@Command(description = "List all movies")
	public void listMovies() {
		Collection<Movie> movies = movRec.getMovies();
		for (Movie movie : movies) {
			System.out.println(movie);
		}
	}
	
	@Command(description = "Get all ratings for a specific movie(movieId)", abbrev="gmr")
	public void getAllRatingsForMovie(@Param(name = "movieId") long movieId) {
		List<Rating> ratingsMovie = movRec.getAllRatingsForMovie(movieId);
		for (Rating rat : ratingsMovie) {
			System.out.println(rat);
		}
	}

	@Command(description = "Search for a movie by ID(movieId)")
	public String searchMovies(@Param(name = "movieId:") long movieId) {
		movie = movRec.getMovieById(movieId);
		return movie.toString();
	}

	@Command(description = "Sort by Title")
	public void sortedByTitle() {
		for (Movie movie : movRec.sortedByTitle()) {
			System.out.println(movie.title + " " + movie.releaseDate + " " + movie.imdb);
		}
	}

	@Command(description = "Search for movie title(searchTerm)")
	public void searchMoviesByTitle(@Param(name = "searchTerm") String searchTerm) {
		List<Movie> movies = movRec.getMovieByName(searchTerm);
		for (Movie movie : movies) {
			System.out.println(movie.title + "  " + movie.releaseDate);
		}
	}

	@Command(description = "Add a new rating(userId, movieId, rating)")
	public String addRating(@Param(name = "userId") long userId, @Param(name = "movieId") long movieId,
			@Param(name = "rating") int rating) {
		movRec.addRating(userId, movieId, rating);
		movie = movRec.getMovieById(movieId);
		return "\nYou have successfully rated movie " + movie.title + " with a rating of " + rating;
	}

	@Command(description = "Search for rating by user for a specific movie(userId, movieId)")
	public String getRating(@Param(name = "userId") long userId, @Param(name = "movieId") long movieId) {
		rating = movRec.getRating(userId, movieId);
		return rating.toString();
	}

	@Command(description = "Get all ratings by a specific user(userId)")
	public void getUserRatings(@Param(name = "userId") long userId) {
		List<Rating> userRatings = movRec.getUserRatings(userId);
		for(Rating rat : userRatings) {
			System.out.println(rat);
		}
	}

	@Command(description = "Delete a rating by a user for specific movie(userId, movieId)")
	public String removeRating(@Param(name = "userId") long userId, @Param(name = "movieId") long movieId) {
		rating = movRec.removeRating(userId, movieId);
		return "You successfully removed rating for movie with ID: " + rating.movieId + ", made by user with ID: " + rating.userId;
	}

	@Command(description = "Average movie rating(movieId)", abbrev="avg")
	public double averageMovieRating(@Param(name = "movieID") Long movieId) {
		double average = movRec.averageMovieRating(movieId);
		return average;
	}
	
	@Command(description="List all ratings")
	public void listRatings(){
		Collection<Rating> ratings = movRec.getRatings();
		for(Rating rating: ratings) {
			System.out.println(rating);
		}
	}
	
	@Command(description = "Top 10 movies", abbrev="top10")
	public void getTopTenMovies() {

		List<Movie> topTenMovies = movRec.getTopTenMovies();
		double score;

		for (Movie mov : topTenMovies) {
			score = movRec.averageMovieRating(mov.id);
			System.out.println(topTenMovies.indexOf(mov) + 1 + ". " + mov.title + ", Score: " + score);
		}
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long userId) {
		this.userId = userId;
	}
}