package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Objects;

// User class which contains all the characteristics and constructors for creating a user
public class User {
	public Long counter=(long) 01;
	public Long userId;
	public String firstName;
	public String surname;
	public int age;
	public String gender;
	public String job;
	public String zip;
	public String password;
	public String role;
	public List<Rating> ratings = new ArrayList<>();

	
	public User() {
	}

	// Constructor used by Cliche CLI for creating users
	public User(String firstName, String surname, int age, String gender, String job, String zip, String password, String role) {
		this.userId = counter++;
		this.firstName = firstName;
		this.surname = surname;
		this.age = age;
		this.gender = gender;
		this.job = job;
		this.zip = zip;
		this.password = password;
		this.role = "default";
	}
	
	// Constructor used when user is being imported by Importer
		public User(Long userId, String firstName, String surname, int age, String gender, String job, String zip){
			this.userId = userId;
			if(userId >= counter){
				counter = userId + 1;
			}
			this.firstName = firstName;
			this.surname = surname;
			this.age = age;
			this.gender	= gender;
			this.job = job;
			this.zip= zip;
		}
	
	public String toString(){
		return toStringHelper(this).addValue(userId)
								   .addValue(firstName)
								   .addValue(surname)
								   .addValue(age)
								   .addValue(gender)
								   .addValue(job)
								   .addValue(zip)
								   .addValue(password)
								   .addValue(role)
								   .toString();
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.firstName, this.surname, this.age, this.gender, this.job, this.zip, this.password, this.role);
	}
	
	@Override
	public boolean equals(final Object obj){
		if(obj instanceof User){
			final User other = (User) obj;
			return Objects.equal(firstName, other.firstName)
					&& Objects.equal(surname, other.surname)
					&& Objects.equal(age, other.age)
					&& Objects.equal(gender, other.gender)
					&& Objects.equal(job, other.job)
					&& Objects.equal(zip, other.zip)
					&& Objects.equal(password, other.password)
					&& Objects.equal(role, other.role)
					&& Objects.equal(ratings, other.ratings);
		}else{
			return false;
		}
	}
	
	// Getters and Setters
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
