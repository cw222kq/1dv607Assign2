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
	
	// tror denna m�ste vara i modellen
	private boolean start = true;
	private Scanner scan;
	private Scanner scanner;
	private char inputResult;
	private char inChar;

	public Console() {
		
		
	}
	// print out main menu
	private void printMainMenu(){
		// welcome messages who prints out when the user starts the program
		// tror denna m�ste vara i modellen
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
	private char getUsersInput() {
	    try {
	      inChar = Character.toUpperCase((char)System.in.read());
	      // don�t return value if value is enter or line feed
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
	// written with inspiration from: https://github.com/tobias-dv-lnu/1dv607/blob/master/MV_DiceGame/MV_DiceGame_java/view/Console.java
	// running the program as long as the user don't choose q in the menu
	public void runMemberRegistration(){
		printMainMenu();
		while(getUsersInput() != 'Q'){
			if(inChar == '1'){
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
			System.out.println("The user don�t want to quit");
			printMainMenu();
		}
		System.out.println("You want to quit. Bye for now!!");
		
	}
	// ******** SUBMENUES TO THE MAIN MENU member ********
	// create new member menu
	public void printCreateNewMemberMenu(){
		System.out.println("CREATE NEW MEMBER");
		System.out.println("MEMBERS INFORMATION");
		System.out.println("Input members social security number(yymmddxxxx)");
		getUsersInput();
		
		System.out.println("Input members name(first and lastname)");
		getUsersInput();
		
		System.out.println("Input members password");
		getUsersInput();
	}
	// delete a member menu
	public void printDeleteAMemberMenu(){
		System.out.println("DELETE A MEMBER");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete");
		getUsersInput();
		
	}
	// change members information menu(only changes the information if the user input some new data, if the user only presses enter the old value remains)
	public void printChangeMembersInformationMenu(){
		System.out.println("CHANGE MEMBERS INFORMATION");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to make some changes to");
		getUsersInput();
		
		//databasen h�mtar ut och skriver ut attributen och dess nuvarande v�rden
		// anv�nd samma metod som f�r nedanst�ende metod
		
		System.out.println("Make the wanted changes. If you don't want to change the value just press enter and the old value remains");
		System.out.println("Input the new social security number(yymmddxxxx)");
		getUsersInput();
		
		System.out.println("Input the new name");
		getUsersInput();
		
		System.out.println("Input the new password");
		getUsersInput();
		
	}
	// look at members information menu
	public void printLookAtMembersInformationMenu(){
		System.out.println("LOOK AT MEMBERS INFORMATION");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to look at");
		getUsersInput();
		
		
	}
	// ******** SUBMENUES TO THE MAIN MENU boat ********
	// register a new boat
	public void printRegisterANewBoatMenu(){
		System.out.println("REGISTER A NEW BOAT");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to register a new boat for");
		getUsersInput();
		
		System.out.println("Input the size of the boat");
		getUsersInput();
		
		System.out.println("Input the type of the boat");
		getUsersInput();
		
		System.out.println("Input the path to the image of the boat(optional, possible to leave empty)");
		getUsersInput();
		
		
	}
	// delete a boat
	public void printDeleteABoatMenu(){
		System.out.println("DELETE A BOAT");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to delete a boat from");
		getUsersInput();
		// medlemmens personnummer och namn skrivs utsamt medlemmens alla b�tar med tillh�rande b�tid
		System.out.println("Input the id for the boat you with to delete");
		getUsersInput();
		
	}
	// change boats information
	public void printChangeBoatsInformationMenu(){
		System.out.println("CHANGE BOATS INFORMATION");
		System.out.println("Input the social security number(yymmddxxxx) of the member you want to change a boats information from");
		getUsersInput();
		// medlemmens personnummer och namn skrivs utsamt medlemmens alla b�tar med tillh�rande b�tid
		System.out.println("Input the id for the boat you with to change");
		getUsersInput();
		//den valda b�tens information skrivs ut
		System.out.println("Make the wanted changes. If you don't want to change the value just press enter and the old value remains");
		System.out.println("Input the size of the boat");
		getUsersInput();
		
		System.out.println("Input the type of the boat");
		getUsersInput();
		
		System.out.println("Input the path to the image of the boat(optional, possible to leave empty)");
		getUsersInput();
		
	}

}
