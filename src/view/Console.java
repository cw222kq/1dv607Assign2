/**
 * 
 */
package view;

/**
 * @author cw222kq
 *
 */
public class Console {
	
	// tror denna måste vara i modellen
	public boolean start = true;

	public Console() {
		
		
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

}
