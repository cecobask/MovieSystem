package controllers;

import java.util.Collection;

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
	
	@Command(description="Load info")
	public String load() throws Exception{
		movRec.load();
		movRec.prime();
		return "Information about users, movies and ratings loaded.";
	}
	
	
	@Command(description="Save info")
	public String save() throws Exception{
		movRec.write();
		return "You successfully saved the information.";
	}
	
	@Command(description="List all users")
	public void listUsers(){
		Collection<User> users = movRec.getUsers();
		for (User user: users) {
			System.out.println(user);
		}
	}
	
	@Command(description="Search for a user by ID")
	public String searchUsers(@Param(name="userId") long userId){
		user = movRec.getUserById(userId);
		return user.toString();
	}
	
	@Command(description="List all movies")
	public void listMovies(){
		Collection<Movie> movies = movRec.getMovies();
		for (Movie movie: movies) {
		System.out.println(movie);
		}
	}
	
	@Command(description="Search for a movie by ID")
	public String searchMovies(@Param(name="movieId:") long movieId){
		movie = movRec.getMovieById(movieId);
		return movie.toString();	
	}
	
	@Command(description="Sort by Title")
	public void sortedByTitle() {
		for(Movie movie: movRec.sortedByTitle()) {
			System.out.println(movie.title + " " + movie.releaseDate + " " + movie.imdb);
		}
	}
	
	@Command(description="Add a new rating")
	public String addRating(@Param(name="userId") long userId, @Param(name="movieId") long movieId, @Param(name="rating") double rating){
		movRec.addRating(userId, movieId, rating);
		movie = movRec.getMovieById(movieId);
		return "\nYou have successfully rated movie " + movie.title + " with a rating of " + rating;
	}
	
	@Command(description="Search for a rating by ID")
	public String searchRatings(@Param(name="movieId") long movieId){
		rating = movRec.getRatingsById(movieId);
		return rating.toString();
	}
	
	@Command(description="List all ratings")
	public void listRatings(){
		Collection<Rating> ratings = movRec.getRatings();
		for(Rating rating: ratings) {
			System.out.println(rating);
		}
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long userId) {
		    this.userId = userId;
		  }
}