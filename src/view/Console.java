/**
 * 
 */
package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author cw222kq
 *
 */
public class Console {
	
	// tror denna måste vara i modellen
	private boolean start = true;
	private Scanner scan;
	private Scanner scanner;
	private char inputResult;
	private char inChar;

	public Console() {
		
		
	}
	public void emptyInChar(){
		this.inChar = '0';
	}
	public char getInChar(){
		return this.inChar;
	}
	public boolean wantsToQuit(){
		return getUsersInputChar() == 'Q';
	}
	public void quit(){
		System.out.println("Quitting the application!");
		//System.exit(0);
	}
	// print out main menu
	public void printMainMenu(){
		// welcome messages who prints out when the user starts the program
		// tror denna måste vara i modellen
		if(start){
			System.out.println("**** Welcome to The Jolly Pirate ****");
			start = false;
		}
		System.out.println("Please make your choice");
		System.out.println("** MEMBER **");
		System.out.println("<1> CREATE NEW MEMBER");
		System.out.println("<2> LIST ALL MEMBERS AS COMPACT LIST");
		System.out.println("<3> LIST ALL MEMBERS AS VERBOSE LIST");
		System.out.println("<4> DELETE A MEMBER");
		System.out.println("<5> CHANGE MEMBERS INFORMATION");
		System.out.println("<6> VIEW MEMBERS INFORMATION");
		System.out.println("** BOAT **");
		System.out.println("<7> REGISTER A NEW BOAT");
		System.out.println("<8> DELETE A BOAT");
		System.out.println("<9> CHANGE BOATS INFORMATION");
		System.out.println("");
		System.out.println("<Q> QUIT");
	}
	// Get the input value from the keyboard. Written with inspiration from: https://stackoverflow.com/questions/15446689/what-is-the-use-of-system-in-read
	public char getUsersInputChar() {
	    try {
	      inChar = Character.toUpperCase((char)System.in.read());
	      // don´t return value if value is enter or line feed
	      while (inChar == '\r' || inChar =='\n') {
	    	  inChar = Character.toUpperCase((char)System.in.read());
		  }
	      System.out.print("You entered ");
	      System.out.println(inChar);
		     
	      return inChar;
	    } 
	    catch (java.io.IOException e) {
	      System.out.println("inne i catchen");
	      System.err.println("" + e);
		      
	      return 0;
	    }
	}
	// Get the string input value from the keyboard when it's only one word
	private String getUsersInputStringOneWord() {
		scanner = new Scanner(System.in);
		String inValue = scanner.next();
		return inValue;
	}
	// Get the string input value from the keyboard when it's more than one word
	private String getUsersInputStringTwoWords() {
		scanner = new Scanner(System.in);
		String inValue = scanner.nextLine();
		return inValue;
	}
	// Get the integer input value from the keyboard
	private int getUsersInputInteger() {
		scan = new Scanner(System.in);
		int inValue = scan.nextInt();
		return inValue;
	}
	// ******** SUBMENUS TO THE MAIN MENU member ********
	// create new member menu
	public void printCreateNewMemberMenu(model.Member a_member){
		System.out.println("CREATE NEW MEMBER");
		System.out.println("MEMBERS INFORMATION");
		System.out.println("Input members social security number(yymmddxxxx)");
		a_member.setSSN(getUsersInputStringOneWord());
		System.out.println("SSN: " + a_member.getSSN());
		
		System.out.println("Input members name(first and lastname)");
		a_member.setName(getUsersInputStringTwoWords());
		System.out.println("name: " + a_member.getName());
		
		System.out.println("Input members password(no line feeds allowed)");
		a_member.setPassword(getUsersInputStringOneWord());
		System.out.println("password: " + a_member.getPassword());
		
	}
	// delete a member menu
	public void printDeleteAMemberMenu(model.Member a_member){
		System.out.println("DELETE A MEMBER");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete");
		a_member.setSSN(getUsersInputStringOneWord());
		System.out.println("SSN: " + a_member.getSSN());
		
	}
	// change members information menu(only changes the information if the user input some new data, if the user only presses enter the old value remains)
	public void printChangeMembersInformationMenu(model.Member a_member){
		System.out.println("CHANGE MEMBERS INFORMATION");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to make some changes to");
		a_member.setSSN(getUsersInputStringOneWord());
		System.out.println("SSN: " + a_member.getSSN());
		
		//databasen hämtar ut och skriver ut attributen och dess nuvarande värden
		// använd samma metod som för nedanstående metod
		
		System.out.println("Make the wanted changes. If you don't want to change the value just press enter and the old value remains");
		System.out.println("Input the new social security number(yymmddxxxx)");
		a_member.setSSN(getUsersInputStringOneWord());
		System.out.println("SSN: " + a_member.getSSN());
		
		System.out.println("Input the new name");
		a_member.setName(getUsersInputStringTwoWords());
		System.out.println("name: " + a_member.getName());
		
		System.out.println("Input the new password");
		a_member.setPassword(getUsersInputStringOneWord());
		System.out.println("password: " + a_member.getPassword());
		
	}
	// look at members information menu
	public void printLookAtMembersInformationMenu(model.Member a_member){
		System.out.println("LOOK AT MEMBERS INFORMATION");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to look at");
		a_member.setSSN(getUsersInputStringOneWord());
		System.out.println("SSN: " + a_member.getSSN());
		
		
	}
	// ******** SUBMENUS TO THE MAIN MENU boat ********
	// register a new boat
	public void printRegisterANewBoatMenu(model.Member a_member, model.Boat a_boat){
		System.out.println("REGISTER A NEW BOAT");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to register a new boat for");
		a_member.setSSN(getUsersInputStringOneWord());
		System.out.println("SSN: " + a_member.getSSN());
		
		System.out.println("Input the size of the boat");
		a_boat.setSize(getUsersInputInteger());
		System.out.println("size: " + a_boat.getSize());
		
		System.out.println("Input the type of the boat");
		a_boat.setType(getUsersInputStringTwoWords());
		System.out.println("type: " + a_boat.getType());
		
		System.out.println("Input the path to the image of the boat(optional, possible to leave empty)");
		a_boat.setImagePath(getUsersInputStringOneWord());
		System.out.println("path: " + a_boat.getImagePath());
		
		
	}
	// delete a boat
	public void printDeleteABoatMenuChooseMember(model.Member a_member){
		System.out.println("DELETE A BOAT");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete a boat from");
		a_member.setSSN(getUsersInputStringOneWord());
		System.out.println("SSN: " + a_member.getSSN());
		// medlemmens personnummer och namn skrivs utsamt medlemmens alla båtar med tillhörande båtid
	}
	public void printDeleteABoatMenuChooseBoat(model.Boat a_boat){
		// medlemmens personnummer och namn skrivs utsamt medlemmens alla båtar med tillhörande båtid
		System.out.println("Input the id for the boat you with to delete");
		a_boat.setId(getUsersInputInteger());
		System.out.println("id: " + a_boat.getId());	
	}
	// change boats information
	public void printChangeBoatsInformationMenu(model.Member a_member, model.Boat a_boat){
		System.out.println("CHANGE BOATS INFORMATION");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to change a boats information from");
		a_member.setSSN(getUsersInputStringOneWord());
		System.out.println("SSN: " + a_member.getSSN());
		
		// medlemmens personnummer och namn skrivs ut samt medlemmens alla båtar med tillhörande båtid
		System.out.println("Input the id for the boat you with to change");
		a_boat.setId(getUsersInputInteger());
		System.out.println("id: " + a_boat.getId());
		
		//den valda båtens information skrivs ut
		System.out.println("Make the wanted changes. If you don't want to change the value just press enter and the old value remains");
		System.out.println("Input the size of the boat");
		a_boat.setSize(getUsersInputInteger());
		System.out.println("size: " + a_boat.getSize());
		
		System.out.println("Input the type of the boat");
		a_boat.setType(getUsersInputStringTwoWords());
		System.out.println("type: " + a_boat.getType());
		
		System.out.println("Input the path to the image of the boat(optional, possible to leave empty)");
		a_boat.setImagePath(getUsersInputStringOneWord());
		System.out.println("path: " + a_boat.getImagePath());
		
	}
	// ******** MAIN MENU LIST MEMBERS COMPACT AND VERBOSE ********
	// list member as compact list
	public void printCompactList(ResultSet r){
		System.out.println("COMPACT LIST");
		try {
			while(r.next()){
				System.out.println("Member name: " + r.getString("member name") + ", Member id: " + r.getInt("member id") + ", Number of boats: " + r.getInt("number of boats"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// list member as verbose list
	public void printVerboseList(ResultSet r){
		System.out.println("VERBOSE LIST");
		try {
			while(r.next()){
				System.out.println("Member name: " + r.getString("member name") + ", SSN: " + r.getString("social security number") + ", Member id: " + r.getInt("member id")+ ", Boat id: " + r.getInt("boat id") + ", Boat size: " + r.getInt("boat size") + ", Boat type: " + r.getString("boat type")+ ", Image path: " + r.getString("image path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// print members information
	public void printMembersInformation(ResultSet r){
		try {
			while(r.next()){
				System.out.println("Member name: " + r.getString("member name") + ", SSN: " + r.getString("social security number") + ", Member id: " + r.getInt("member id")  + ", Member password: " + r.getString("member password") + ", Boat id: " + r.getInt("boat id") + ", Boat size: " + r.getInt("boat size") + ", Boat type: " + r.getString("boat type")+ ", Image path: " + r.getString("image path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// TEST SKRIVER UT ALLT I BÅT TAS BORT I SLUTGILTIGA VERSIONEN ENDAST TILL FÖR KONTROLL ************************************************************
	public void printMembersBoats(ResultSet r){
		try {
			while(r.next()){
				System.out.println("Boat id: " + r.getInt("id") + ", Size: " + r.getInt("size") + ", Type: " + r.getString("type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// TEST SKRIVER UT ALLT I IMAGE TAS BORT I SLUTGILTIGA VERSIONEN ENDAST TILL FÖR KONTROLL **************************************************************
	public void printImage(ResultSet r){
		try {
			while(r.next()){
				System.out.println("Image id: " + r.getInt("id") + ", path: " + r.getString("path") + ", Boat id: " + r.getInt("boat_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	

}
