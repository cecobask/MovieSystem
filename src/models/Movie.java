package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

// Movie class which contains all the characteristics and a constructor for creating a movie
public class Movie {
	
	static Long counter = (long) 01;
	public Long id;
	public String title;
	public String releaseDate;
	public String imdb;
	public int ratings;
	
	// Constructor used by Cliche CLI for creating movies and by Importer 
	public Movie(String title, String releaseDate, String imdb) {
		this.id = counter++;
		this.title = title;
		this.releaseDate = releaseDate;
		this.imdb = imdb;
	}
	
	public Movie(String title, String releaseDate, String imdb, int ratings) {
		this.id = counter++;
		this.title = title;
		this.releaseDate = releaseDate;
		this.imdb = imdb;
		this.ratings=ratings;
	}
	
	@Override
	public String toString(){
		return toStringHelper(this).addValue(id)
				                   .addValue(title)
				                   .addValue(releaseDate)
				                   .addValue(imdb)
				                   .toString();
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.id, this.title, this.releaseDate, this.imdb);
	}
	
	@Override
	public boolean equals(final Object obj){
		if (obj instanceof Movie){
			final Movie other = (Movie) obj;
			return Objects.equal(title, other.title)
					&& Objects.equal(releaseDate, other.releaseDate)
					&& Objects.equal(imdb, other.imdb)
					&& Objects.equal(ratings, other.ratings);
		}else{
			return false;
		}
		
	}

	//Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getImdb() {
		return imdb;
	}

	public void setImdb(String imdb) {
		this.imdb = imdb;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

}
