package controllers;

import java.io.File;
import java.util.Collection;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

// Main class that contains the user interface menu, implemented using Cliche CLI
public class Main {
	public static MovieRecommenderAPI movRec;
	private User user;
	private Movie movie;
	private Rating rating;

	public Main() throws Exception{
		
		File datastore = new File("myfiles.xml");
		Serializer serializer = new XMLSerializer(datastore);
		
		movRec = new MovieRecommenderAPI(serializer);
		if(datastore.isFile()){
			movRec.load();
			}	
	}
		
		public static void main(String[] args) throws Exception{
			Main main = new Main();
			Shell shell = ShellFactory.createConsoleShell("cliche> ", "Enter '?l' or '?list' to list all commands", main);
			shell.commandLoop();
			main.movRec.write();
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
		
		@Command(description="Add a new user")
		public String addUser(@Param(name="firstName") String firstName,
							@Param(name="surname") String surname,
							@Param(name="age") int age,
							@Param(name="gender") String gender,
							@Param(name="job") String job,
							@Param(name="zip") String zip){
			user = movRec.addUser(firstName, surname, age, gender, job, zip);
			return "\nYou have successfully added user: " + user.firstName;
		}
		
		
		@Command(description="Delete a user")
		public void removeUser(@Param(name="userId") long userId) throws Exception{	
			user = movRec.getUserById(userId);
			movRec.removeUser(userId);
			System.out.println("\nUser: " + user.firstName + " has been deleted from the system.");	
		}
		
		@Command(description="List all users")
		public void listUsers(){
			Collection<User> users = movRec.getUsers();
			System.out.println(users);
		}
		
		@Command(description="Search for a user by ID")
		public String searchUsers(@Param(name="userId") long userId){
			user = movRec.getUserById(userId);
			return user.toString();
		}
		
		@Command(description="Add a new movie")
		public String addMovie(@Param(name="title") String title,
							@Param(name="releaseDate") String releaseDate,
							@Param(name="imdb") String imdb){
			
			movie = movRec.addMovie(title, releaseDate, imdb);
			return "\nYou have successfully added movie: " + movie.title;
		}
		
		
		@Command(description="Delete a movie")
		public void removeMovie(@Param(name="movieId") long movieId) throws Exception{
			movie = movRec.getMovieById(movieId);
			movRec.removeMovie(movie);
			System.out.println("\nMovie: " + movie.title + " has been deleted from the system.");
		}
		
		@Command(description="List all movies")
		public void listMovies(){
			Collection<Movie> movies = movRec.getMovies();
			System.out.println(movies);
		}
		
		@Command(description="Search for a movie by ID")
		public String searchMovies(@Param(name="movieId:") long movieId){
			movie = movRec.getMovieById(movieId);
			return movie.toString();	
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
			System.out.println(ratings);
		}
		
}