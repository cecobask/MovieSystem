
package controllers;

import java.io.File;
import java.io.IOException;
import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

// Main class that contains the user interface menu, implemented using Cliche CLI
public class Main implements ShellDependent {
	public MovieRecommenderAPI movRec;
	private Shell theShell;
	private static final String ADMIN = "admin";
	private User user;

	public Main() throws Exception{
		
		File datastore = new File("myfiles.xml");
		Serializer serializer = new XMLSerializer(datastore);
		
		movRec = new MovieRecommenderAPI(serializer);
		if(datastore.isFile()){
			movRec.load();
			}	
	}
	
	public void cliSetShell(Shell theShell) {
	    this.theShell = theShell;
	  }
		
		public static void main(String[] args) throws Exception{
			Main main = new Main();
			Shell shell = ShellFactory.createConsoleShell("ms", "Enter '?l' or '?list' to list all commands. If you need help for a command, use '?help' followed by command's name", main);
			shell.commandLoop();
			main.movRec.write();
		}
		
		@Command(description = "Log in(userId, password)")
		  public void logIn(@Param(name = "userId") Long userId, @Param(name = "password") String password)
		      throws IOException {

		    if (movRec.login(userId, password) && movRec.currentUser.isPresent()) {
		      user = movRec.currentUser.get();
		      System.out.println("You are logged in with the ID: " + user.userId);
		      if (user.role!=null && user.role.equals(ADMIN)) {
		        AdminMenu adminMenu = new AdminMenu(movRec, user.userId);
		        ShellFactory.createSubshell(user.firstName, theShell, "Admin", adminMenu).commandLoop();
		      } else {
		        DefaultMenu defaultMenu = new DefaultMenu(movRec, user);
		        ShellFactory.createSubshell(user.firstName, theShell, "Default", defaultMenu).commandLoop();
		      }
		    } else
		      System.out.println("Unknown username/password.");
		  }
		
		@Command(description="Add a new user(firstName, surname, age, gender, job, zip, password, role)")
		public String addUser(@Param(name="firstName") String firstName,
							@Param(name="surname") String surname,
							@Param(name="age") int age,
							@Param(name="gender") String gender,
							@Param(name="job") String job,
							@Param(name="zip") String zip,
							@Param(name="password") String password,
							@Param(name="role") String role){
			user = movRec.addUser(firstName, surname, age, gender, job, zip, password, role);
			return "\nYou have successfully added user: " + user.firstName + ", ID: " + user.userId;
		}
		
		@Command(description="Initial Load")
		public String load() throws Exception{
			movRec.write();
			movRec.load();
			movRec.prime();
			movRec.write();
			return "Information about users, movies and ratings loaded.";
		}
		
}