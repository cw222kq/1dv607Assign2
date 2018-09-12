/**
 * 
 */
package view;

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
	// written with inspiration from: https://github.com/tobias-dv-lnu/1dv607/blob/master/MV_DiceGame/MV_DiceGame_java/view/Console.java
	// running the program as long as the user don't choose q in the menu
	/*public void runMemberRegistration(){
		printMainMenu();
		while(getUsersInputChar() != 'Q'){
			if(inChar == '1'){
				System.out.println("Användaren valde 1. Inne i första if satsen.");
				printCreateNewMemberMenu();
				inChar = '0';
			}
			if(inChar == '2'){
				System.out.println("COMPACT LIST");
				inChar = '0';
			}
			if(inChar == '3'){
				System.out.println("VERBOSE LIST");
				inChar = '0';
			}
			if(inChar == '4'){
				printDeleteAMemberMenu();
				inChar = '0';
			}
			if(inChar == '5'){
				printChangeMembersInformationMenu();
				inChar = '0';
			}
			if(inChar == '6'){
				printLookAtMembersInformationMenu();
				inChar = '0';
			}
			if(inChar == '7'){
				printRegisterANewBoatMenu();
				inChar = '0';
			}
			if(inChar == '8'){
				printDeleteABoatMenu();
				inChar = '0';
			}
			if(inChar == '9'){
				printChangeBoatsInformationMenu();
				inChar = '0';
			}
			System.out.println("The user don´t want to quit");
			printMainMenu();
		}
		System.out.println("You want to quit. Bye for now!!");
		System.exit(0);
		
	} TA BORT DENNA LIGGER I CONTROLLER ISTÄLLET NU*/
	// ******** SUBMENUS TO THE MAIN MENU member ********
	// create new member menu JOBBA HÄR!!!!!!!!!!!!!!!!!!!!!!!!!
	public void printCreateNewMemberMenu(){
		System.out.println("CREATE NEW MEMBER");
		System.out.println("MEMBERS INFORMATION");
		System.out.println("Input members social security number(yymmddxxxx)");
		String SSN = getUsersInputStringOneWord();
		System.out.println("SSN: " + SSN);
		
		System.out.println("Input members name(first and lastname)");
		String name = getUsersInputStringTwoWords();
		System.out.println("name: " + name);
		
		System.out.println("Input members password(no line feeds allowed)");
		String password = getUsersInputStringOneWord();
		System.out.println("password: " + password);
	}
	// delete a member menu
	public void printDeleteAMemberMenu(){
		System.out.println("DELETE A MEMBER");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete");
		String SSN = getUsersInputStringOneWord();
		System.out.println("SSN: " + SSN);
		
	}
	// change members information menu(only changes the information if the user input some new data, if the user only presses enter the old value remains)
	public void printChangeMembersInformationMenu(){
		System.out.println("CHANGE MEMBERS INFORMATION");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to make some changes to");
		String SSN = getUsersInputStringOneWord();
		System.out.println("SSN: " + SSN);
		
		//databasen hämtar ut och skriver ut attributen och dess nuvarande värden
		// använd samma metod som för nedanstående metod
		
		System.out.println("Make the wanted changes. If you don't want to change the value just press enter and the old value remains");
		System.out.println("Input the new social security number(yymmddxxxx)");
		String SSNNew = getUsersInputStringOneWord();
		System.out.println("SSNNew: " + SSNNew);
		
		System.out.println("Input the new name");
		String name = getUsersInputStringTwoWords();
		System.out.println("name: " + name);
		
		System.out.println("Input the new password");
		String password = getUsersInputStringOneWord();
		System.out.println("password: " + password);
		
	}
	// look at members information menu
	public void printLookAtMembersInformationMenu(){
		System.out.println("LOOK AT MEMBERS INFORMATION");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to look at");
		String SSN = getUsersInputStringOneWord();
		System.out.println("SSN: " + SSN);
		
		
	}
	// ******** SUBMENUS TO THE MAIN MENU boat ********
	// register a new boat
	public void printRegisterANewBoatMenu(){
		System.out.println("REGISTER A NEW BOAT");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to register a new boat for");
		String SSN = getUsersInputStringOneWord();
		System.out.println("SSN: " + SSN);
		
		System.out.println("Input the size of the boat");
		int size = getUsersInputInteger();
		System.out.println("size: " + size);
		
		System.out.println("Input the type of the boat");
		String type = getUsersInputStringTwoWords();
		System.out.println("type: " + type);
		
		System.out.println("Input the path to the image of the boat(optional, possible to leave empty)");
		String path = getUsersInputStringOneWord();
		System.out.println("path: " + path);
		
		
	}
	// delete a boat
	public void printDeleteABoatMenu(){
		System.out.println("DELETE A BOAT");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete a boat from");
		String SSN = getUsersInputStringOneWord();
		System.out.println("SSN: " + SSN);
		// medlemmens personnummer och namn skrivs utsamt medlemmens alla båtar med tillhörande båtid
		System.out.println("Input the id for the boat you with to delete");
		int id = getUsersInputInteger();
		System.out.println("id: " + id);
		
	}
	// change boats information
	public void printChangeBoatsInformationMenu(){
		System.out.println("CHANGE BOATS INFORMATION");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to change a boats information from");
		String SSN = getUsersInputStringOneWord();
		System.out.println("SSN: " + SSN);
		// medlemmens personnummer och namn skrivs ut samt medlemmens alla båtar med tillhörande båtid
		System.out.println("Input the id for the boat you with to change");
		int id = getUsersInputInteger();
		System.out.println("id: " + id);
		//den valda båtens information skrivs ut
		System.out.println("Make the wanted changes. If you don't want to change the value just press enter and the old value remains");
		System.out.println("Input the size of the boat");
		int size = getUsersInputInteger();
		System.out.println("size: " + size);
		
		System.out.println("Input the type of the boat");
		String type = getUsersInputStringTwoWords();
		System.out.println("type: " + type);
		
		System.out.println("Input the path to the image of the boat(optional, possible to leave empty)");
		String path = getUsersInputStringOneWord();
		System.out.println("path: " + path);
		
	}

}
