package utils;

import java.util.Comparator;

import models.Movie;

public class MovieComparator implements Comparator<Movie> {

	@Override
	public int compare(Movie movie, Movie other) {
		if (movie.ratings < other.ratings) 
			 return +1;
		 else if (movie.ratings > other.ratings) 
			 return -1;
		 else return  0;
	}

}
