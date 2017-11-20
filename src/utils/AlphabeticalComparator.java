package utils;

import java.util.Comparator;

import models.Movie;

public class AlphabeticalComparator implements Comparator<Movie> {

	public int compare(Movie a, Movie b) {
		return a.title.compareToIgnoreCase(b.title);
	}
}