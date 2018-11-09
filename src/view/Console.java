/**
 * 
 */
package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import model.Boat;
import model.Member;

/**
 * @author cw222kq
 *
 */
public class Console {	//i början
	
	public Console(){printWelcome();}
	
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
		
		System.out.println();
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
	public void printCreateMember(model.RegistrationFacade a_registrationFacade){

		System.out.println("Input members social security number(yymmddxxxx)");
		a_registrationFacade.setMemberSSN(getUsersInputStringOneWord());
		
		System.out.println("Input members name(first and lastname)");
		a_registrationFacade.setMemberName(getUsersInputStringTwoWords());
		
		System.out.println("Input members password(no line feeds allowed)");
		a_registrationFacade.setMemberPassword(getUsersInputStringOneWord());	
	}
	
	// delete a member menu
	public void printDeleteMember(model.RegistrationFacade a_registrationFacade){
		
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete");
		a_registrationFacade.setMemberSSN(getUsersInputStringOneWord());	
	}
	
	// change members information menu
	public void printChangeMember(model.RegistrationFacade a_registrationFacade){
		
		System.out.println("Make the wanted changes.");
		System.out.println("Input the new social security number(yymmddxxxx)");
		a_registrationFacade.setMemberSSN(getUsersInputStringOneWord());
		
		System.out.println("Input the new name");
		a_registrationFacade.setMemberName(getUsersInputStringTwoWords());
		
		System.out.println("Input the new password");
		a_registrationFacade.setMemberPassword(getUsersInputStringOneWord());	
	}
	
	// look at members information menu
	public void printLookAtMember(model.RegistrationFacade a_registrationFacade){
		
		System.out.println("Input the social security number(yymmddxxxx) of the specific member");
		a_registrationFacade.setMemberSSN(getUsersInputStringOneWord());		
	}
	
	// ******** SUBMENUS TO THE MAIN MENU boat ********
	// register a new boat
	public void printRegisterBoat(model.RegistrationFacade a_registrationFacade){
		
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to register a new boat for");
		a_registrationFacade.setMemberSSN(getUsersInputStringOneWord());
		
		System.out.println("Input the size of the boat");
		a_registrationFacade.setBoatSize(getUsersInputInteger());
		
		System.out.println("Input the type of the boat");
		a_registrationFacade.setBoatType(getUsersInputStringTwoWords());
		
		System.out.println("Input the path to the image of the boat");
		a_registrationFacade.setImagePath(getUsersInputStringOneWord());
			
	}
	
	// delete a boat methods
	// Choosing member to delete a boat from
	public void printChooseMember(model.RegistrationFacade a_registrationFacade){
		
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete a boat from");
		a_registrationFacade.setMemberSSN(getUsersInputStringOneWord());
	}
	
	// Choosing the specific boat to delete
	public void printDeleteBoat(model.RegistrationFacade a_registrationFacade){
		
		System.out.println("Input the id for the boat you with to delete");
		a_registrationFacade.setBoatId(getUsersInputInteger());	
	}
	
	// change boat information methods
	// Selects which boat to change 
	public void printSelectBoat(model.RegistrationFacade a_registrationFacade){
		
		System.out.println("Input the id for the boat you wish to change");
		a_registrationFacade.setBoatId(getUsersInputInteger());	
	}
	
	// changing the selected boats information
	public void printChangeBoat(model.RegistrationFacade a_registrationFacade){
		
		System.out.println("Make the wanted changes.");
		System.out.println("Input the new size of the boat");
		a_registrationFacade.setBoatSize(getUsersInputInteger());
		
		System.out.println("Input the new type of the boat");
		a_registrationFacade.setBoatType(getUsersInputStringTwoWords());
		
		System.out.println("Input the new path to the image of the boat");
		a_registrationFacade.setImagePath(getUsersInputStringOneWord());
		
	}
	
	// ******** METHODS THAT GETS THE DATA FROM THE DATABASE *********
	// VIEW FÅR INTE VETA NÅGOT OM RESULTSET
	// list member as compact list
	//tillagt nu i kompletteringen
	public void printCompactList(Iterable<model.Member> listOfMembers){
		Iterator<model.Member> listOfmembersIterator = listOfMembers.iterator();
		while(listOfmembersIterator.hasNext()){
			model.Member member = listOfmembersIterator.next();
			System.out.println("Member name: " + member.getName() + ", Member id: " + member.getId() + ", Number of boats: " + member.getNumberOfBoats());
		}
	}
		
	// used for list member as verbose list and view members information
	//tillagt nu i kompletteringen 
	public void printMembersInformation(Iterable<model.Member> listOfMembers){
		int currentMemberID = 0; // tillagt nu
		Iterator<model.Member> listOfmembersIterator = listOfMembers.iterator();
		while(listOfmembersIterator.hasNext()){	
			model.Member member = listOfmembersIterator.next();
			if(member.getId() != currentMemberID){ // tillagt nu
				System.out.println("\n" + "Member name: " + member.getName() + ", SSN: " + member.getSSN() + ", Member id: " + member.getId());
				for(Object boat: member.getBoats()){
					System.out.println("Boat id: " + ((Boat) boat).getId() + ", Boat size: " + ((Boat) boat).getSize() + ", Boat type: " + ((Boat) boat).getType() + ", Image path: " + ((Boat) boat).getImagePath());
				}
			}
			currentMemberID = member.getId();	
		}			
	}
	
	// prints all boats owned of a specific member
	//tillagt nu i kompletteringen 
	public void printMembersBoats(ArrayList listOfBoats){
		Iterator<model.Boat> listOfBoatsIterator = listOfBoats.iterator();
		while(listOfBoatsIterator.hasNext()){	
			model.Boat boat = listOfBoatsIterator.next();
			System.out.println("Boat id: " + ((Boat) boat).getId() + ", Boat size: " + ((Boat) boat).getSize() + ", Boat type: " + ((Boat) boat).getType() + ", Image path: " + ((Boat) boat).getImagePath());		
		}	
	}
	
	// print all information about the member (i.e all the data in the member table)
	// tillagt i kompletteringen 
	public void printMemberTest(ArrayList<model.Member> listOfMembers){	
		Iterator<model.Member> listOfmembersIterator = listOfMembers.iterator();
		if(listOfMembers.isEmpty()){System.out.println("null"); 
			return;
		}
		while(listOfmembersIterator.hasNext()){
			model.Member member = listOfmembersIterator.next();
			System.out.println("Member id: " + member.getId() + ", SSN: " + member.getSSN() + ", Member name: " + member.getName() + ", Member password: " + member.getPassword());
		}
				
	}
	
	// tillagt i kompletteringen 
	public void printBoatTest(ArrayList<model.Boat> listOfBoats){
		Iterator<model.Boat> listOfBoatsIterator = listOfBoats.iterator();
		while(listOfBoatsIterator.hasNext()){
				model.Boat boat = listOfBoatsIterator.next();
				System.out.println("Boat id: " + boat.getId() + ", Boat size: " + boat.getSize() + ", Boat type: " + boat.getType() + ", Image path: " + boat.getImagePath());
		}
	}
	
	//  ******** HEADLINES  *********
	public void printHeadingCreateMember(){
		System.out.println("1. CREATE NEW MEMBER");
	}
	
	public void printHeadingCompactList() {
		System.out.println("2. COMPACT LIST");
	}
	
	public void printHeadingVerboseList() {
		System.out.println("3. VERBOSE LIST");
	}
	
	public void printHeadingDeleteMember() {
		System.out.println("4. DELETE A MEMBER");
	}
	
	public void printHeadingChangeMember() {
		System.out.println("5. CHANGE MEMBERS INFORMATION");
	}
	
	public void printHeadingLookAtMember() {
		System.out.println("6. LOOK AT A SPECIFIC MEMBER INFORMATION");
	}
	
	public void printHeadingRegisterBoat() {
		System.out.println("7. REGISTER A NEW BOAT");
	}
	
	public void printHeadingDeleteBoat() {
		System.out.println("8. DELETE A BOAT");
	}
	
	public void printHeadingChangeBoat() {
		System.out.println("9. CHANGE A BOATS INFORMATION");
	}
	
	//  ******** WELCOME MESSAGE  *********
	private void printWelcome(){
		System.out.println("**** Welcome to The Jolly Pirate ****");
	}
	
	//  ******** ERROR MESSAGE  *********
	public void printErrorChoice(){
		System.out.println("There is no event on this choice please choose again! Make your selection from the menu!");
	}
	
	public void printErrorUser(){
		System.out.println("The user is not in the database. Please add the user first");
	}
	
	//  ******** SUCCESS MESSAGE  *********
	public void printSuccededInsert(){
		System.out.println("The data has been saved");
	}
	
	public void printSuccededDelete(){
		System.out.println("The data has been deleted");
	}
}
