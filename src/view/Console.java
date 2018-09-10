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
	// print out main menu
	private void printMainMenu(){
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
	private char getUsersInput() {
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
	// written with inspiration from: https://github.com/tobias-dv-lnu/1dv607/blob/master/MV_DiceGame/MV_DiceGame_java/view/Console.java
	public void runMemberRegistration(){
		printMainMenu();
		while(getUsersInput() != 'Q'){
			System.out.println("The user don´t want to quit");
			printMainMenu();
		}
		System.out.println("You want to quit. Bye for now!!");
		
	}

}
