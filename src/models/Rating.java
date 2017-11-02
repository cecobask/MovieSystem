package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

// Rating class which contains all the characteristics and a constructor for creating a rating
public class Rating {

	public Long counter=(long) 01;
	public long id;
	public Long userId;
	public Long movieId;
	public double rating;
	
	// Constructor used by Cliche CLI and Importer
	public Rating(Long userId, Long movieId, double rating) {
		this.userId = userId;
		this.movieId = movieId;
		this.rating = rating;
	}

	@Override
	public String toString(){
		return toStringHelper(this).addValue(userId)
				                   .addValue(movieId)
				                   .addValue(rating)
				                   .toString();
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.userId, this.movieId, this.rating);
	}
		
	// Getters and Setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
