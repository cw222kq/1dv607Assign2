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
	
	private boolean start = true;
	private Scanner scan;
	private Scanner scanner;
	private char inChar;

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
		System.exit(0);
	}
	// print out main menu
	public void printMainMenu(){
		// welcome messages who prints out when the user starts the program
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
	// ******** METHODS WHO GETS THE USERS INPUT *********
	// Get the input value from the keyboard. Written with inspiration from: https://stackoverflow.com/questions/15446689/what-is-the-use-of-system-in-read
	public char getUsersInputChar() {
	    try {
	      inChar = Character.toUpperCase((char)System.in.read());
	      // don´t return value if value is enter or line feed
	      while (inChar == '\r' || inChar =='\n') {
	    	  inChar = Character.toUpperCase((char)System.in.read());
		  }
	         
	      return inChar;
	    } 
	    catch (java.io.IOException e) {
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

		System.out.println("Input members social security number(yymmddxxxx)");
		a_member.setSSN(getUsersInputStringOneWord());
		
		System.out.println("Input members name(first and lastname)");
		a_member.setName(getUsersInputStringTwoWords());
		
		System.out.println("Input members password(no line feeds allowed)");
		a_member.setPassword(getUsersInputStringOneWord());
		
	}
	// delete a member menu
	public void printDeleteAMemberMenu(model.Member a_member){
		
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete");
		a_member.setSSN(getUsersInputStringOneWord());
		
	}
	// change members information menu(only changes the information if the user input some new data, if the user only presses enter the old value remains)
	public void printChangeMembersInformationMenu(model.Member a_member){
		
		System.out.println("Make the wanted changes. If you don't want to change the value just press enter and the old value remains");
		System.out.println("Input the new social security number(yymmddxxxx)");
		a_member.setSSN(getUsersInputStringOneWord());
		
		System.out.println("Input the new name");
		a_member.setName(getUsersInputStringTwoWords());
		
		System.out.println("Input the new password");
		a_member.setPassword(getUsersInputStringOneWord());
		
	}
	// look at members information menu
	public void printLookAtMembersInformationMenu(model.Member a_member){
		
		System.out.println("Input the social security number(yymmddxxxx) of the specific member");
		a_member.setSSN(getUsersInputStringOneWord());
			
	}
	// ******** SUBMENUS TO THE MAIN MENU boat ********
	// register a new boat
	public void printRegisterANewBoatMenu(model.Member a_member, model.Boat a_boat){
		
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to register a new boat for");
		a_member.setSSN(getUsersInputStringOneWord());
		
		System.out.println("Input the size of the boat");
		a_boat.setSize(getUsersInputInteger());
		
		System.out.println("Input the type of the boat");
		a_boat.setType(getUsersInputStringTwoWords());
		
		System.out.println("Input the path to the image of the boat(optional, possible to leave empty)");
		a_boat.setImagePath(getUsersInputStringOneWord());
			
	}
	// delete a boat methods
	// Choosing member to delete a boat from
	public void printDeleteABoatMenuChooseMember(model.Member a_member){
		
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete a boat from");
		a_member.setSSN(getUsersInputStringOneWord());
	}
	// Choosing the specific boat to delete
	public void printDeleteABoatMenuChooseBoat(model.Boat a_boat){
		
		System.out.println("Input the id for the boat you with to delete");
		a_boat.setId(getUsersInputInteger());	
	}
	// change boat information methods
	// Selects which boat to change 
	public void printChangeBoatInformationMenuChooseBoat(model.Boat a_boat){
		
		System.out.println("Input the id for the boat you with to change");
		a_boat.setId(getUsersInputInteger());	
	}
	// changing the selected boats information
	public void printChangeBoatsInformationMenu(model.Member a_member, model.Boat a_boat){
		
		System.out.println("Make the wanted changes. If you don't want to change the value just press enter and the old value remains");
		System.out.println("Input the new size of the boat");
		a_boat.setSize(getUsersInputInteger());
		
		System.out.println("Input the new type of the boat");
		a_boat.setType(getUsersInputStringTwoWords());
		
		System.out.println("Input the new path to the image of the boat(optional, possible to leave empty)");
		a_boat.setImagePath(getUsersInputStringOneWord());
		
	}
	// ******** RESULTSET METHODS GETTING THE DATA FROM THE DATABASE *********
	// list member as compact list
	public void printCompactList(ResultSet r){
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
		try {
			while(r.next()){
				System.out.println("Member name: " + r.getString("member name") + ", SSN: " + r.getString("social security number") + ", Member id: " + r.getInt("member id")+ ", Boat id: " + r.getInt("boat id") + ", Boat size: " + r.getInt("boat size") + ", Boat type: " + r.getString("boat type")+ ", Image path: " + r.getString("image path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// prints the whole member information (i.e from all the tables: member, boat, image)
	public void printWholeMembersInformation(ResultSet r){
		try {
			while(r.next()){
				System.out.println("Member name: " + r.getString("member name") + ", SSN: " + r.getString("social security number") + ", Member id: " + r.getInt("member id")  + ", Member password: " + r.getString("member password") + ", Boat id: " + r.getInt("boat id") + ", Boat size: " + r.getInt("boat size") + ", Boat type: " + r.getString("boat type")+ ", Image path: " + r.getString("image path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// prints all boats owned of a specific member
	public void printAllMembersBoats(ResultSet r){
		try {
			while(r.next()){
				System.out.println("Boat id: " + r.getInt("id") + ", Size: " + r.getInt("size") + ", Type: " + r.getString("type") + ", Image path: " + r.getString("path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// print all information about the member (i.e all the data in the member table)
	public void printMembersInformation(ResultSet r){
		try {
			while(r.next()){
				System.out.println("Member id: " + r.getInt("member id") + ", SSN: " + r.getString("social security number") + ", Member name: " + r.getString("member name") + ", Member password: " + r.getString("member password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	// prints a specific boat
	public void printASpecificBoat(ResultSet r){
		try {
			while(r.next()){
				System.out.println("Boat id: " + r.getInt("id") + ", Boat size: " + r.getString("size") + ", Boat type: " + r.getString("type") + ", Image path: " + r.getString("path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//  ******** HEADLINES  *********
	// prints out the headlines for the submenus
	public void printHeadlineCreateNewMember(){
		System.out.println("1. CREATE NEW MEMBER");
	}
	public void printHeadlineCompactList() {
		System.out.println("2. COMPACT LIST");
	}
	public void printHeadlineVerboseList() {
		System.out.println("3. VERBOSE LIST");
	}
	public void printHeadlineDeleteAMember() {
		System.out.println("4. DELETE A MEMBER");
	}
	public void printHeadlineChangeMembersInformation() {
		System.out.println("5. CHANGE MEMBERS INFORMATION");
	}
	public void printHeadlineLookAtASpecificMembersInformation() {
		System.out.println("6. LOOK AT A SPECIFIC MEMBER INFORMATION");
	}
	public void printHeadlineRegisterANewBoat() {
		System.out.println("7. REGISTER A NEW BOAT");
	}
	public void printHeadlineDeleteABoat() {
		System.out.println("8. DELETE A BOAT");
	}
	public void printHeadlineChangeABoatsInforamtion() {
		System.out.println("9. CHANGE A BOATS INFORMATION");
	}

}
