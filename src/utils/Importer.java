package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.introcs.In;
import models.Movie;
import models.Rating;
import models.User;

// Class used to read the data from users5.dat, items5.dat, ratings5.dat and import it into an ArrayList
// which is then returned by each method
public class Importer {

	public List<User> loadUsers(String users) throws Exception {
		File usersFile = new File(users);
		In usersIn = new In(usersFile);

		String delims = "[|]";
		List<User> usersList = new ArrayList<User>();
		while (!usersIn.isEmpty()) {
			String userDetails = usersIn.readLine();
			String[] userTokens = userDetails.split(delims);
			if (userTokens.length == 7) {
				Long userId = Long.parseLong(userTokens[0]);
				String firstName = userTokens[1];
				String surname = userTokens[2];
				int age = Integer.parseInt(userTokens[3]);
				String gender = userTokens[4];
				String job = userTokens[5];
				String zip = userTokens[6];

				usersList.add(new User(userId, firstName, surname, age, gender, job, zip));
			} else {
				throw new Exception("Invalid member length: " + userTokens.length);
			}
		}
		return usersList;
	}

	public List<Movie> loadMovies(String movies) throws Exception {
		File moviesFile = new File(movies);
		In moviesIn = new In(moviesFile);

		String delims = "[|]";
		List<Movie> moviesList = new ArrayList<Movie>();
		while (!moviesIn.isEmpty()) {
			String movieDetails = moviesIn.readLine();
			String[] movieTokens = movieDetails.split(delims);
			if (movieTokens.length == 23) {
				String title = movieTokens[1];
				String releaseDate = movieTokens[2];
				String imdb = movieTokens[3];

				moviesList.add(new Movie(title, releaseDate, imdb));
			} else {
				throw new Exception("Invalid movie length: " + movieTokens.length);
			}
		}
		return moviesList;
	}

	public List<Rating> loadRatings(String ratings) throws Exception {
		File ratingsFile = new File(ratings);
		In ratingsIn = new In(ratingsFile);

		String delims = "[|]";
		List<Rating> ratingsList = new ArrayList<Rating>();
		while (!ratingsIn.isEmpty()) {
			String ratingDetails = ratingsIn.readLine();
			String[] ratingTokens = ratingDetails.split(delims);
			if (ratingTokens.length == 4) {
				long userId = Long.parseLong(ratingTokens[0]);
				long movieId = Long.parseLong(ratingTokens[1]);
				int rating = Integer.parseInt(ratingTokens[2]);

				ratingsList.add(new Rating(userId, movieId, rating));
			} else {
				throw new Exception("Invalid ratings length: " + ratingTokens.length);
			}
		}
		return ratingsList;
	}

}