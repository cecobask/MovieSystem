package models;
import models.Movie;
import models.Rating;
import models.User;

// Class that contains fixed data that is used by other test cases
public class Fixtures {
	
	public static User[] users = {
			new User("Tsvetoslav", "Dimov", 21, "male", "customer assistant", "x91 v5w6", "1234", "admin"),
			new User("Ivan", "Dimov", 42, "male", "engineer", "x91 v5w6", "4321", "default"),
			new User("Aleksandra", "Dimova", 8, "female", "student", "123ABC", "9876", "admin"),
			new User("Geoff", "Lagadec", 45, "male", "singer", "666AVE", "6969", "fucker")
	};
	
	public static Movie[] movies ={
			new Movie("Trainspotting", "23-Feb-1996", "http://www.imdb.com/title/tt0117951/"),
			new Movie("T2 Trainspotting", "27-Jan-2017", "http://www.imdb.com/title/tt2763304/"),
			new Movie("The Big Lebowski", "24-Apr-1998", "http://www.imdb.com/title/tt0118715/"),
			new Movie("Lock, Stock and Two Smoking Barrels", "28-Aug-1998", "http://www.imdb.com/title/tt0120735/")
	};
	
	public static Rating[] ratings ={
			new Rating(0l, 0l, 2.0),
			new Rating(1l, 1l, 3.0),
			new Rating(2l, 2l, 4.0),
			new Rating(4l, 4l, 4.0)
	};
}
