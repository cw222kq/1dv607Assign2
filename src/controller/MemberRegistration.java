/**
 * 
 */
package controller;

/**
 * @author cw222kq
 *
 */
public class MemberRegistration {

	/**
	 * 
	 */
	public MemberRegistration() {
		
	}
	// written with inspiration from: https://github.com/tobias-dv-lnu/1dv607/blob/master/MV_DiceGame/MV_DiceGame_java/view/Console.java
		// running the program as long as the user don't choose q in the menu
		public void runMemberRegistration(view.Console a_view){
			a_view.printMainMenu();
			while(a_view.getUsersInputChar() != 'Q'){
				if(a_view.getInChar() == '1'){
					System.out.println("Användaren valde 1. Inne i första if satsen.");
					a_view.printCreateNewMemberMenu();
					a_view.emptyInChar();
				}
				if(a_view.getInChar() == '2'){
					System.out.println("COMPACT LIST");
					a_view.emptyInChar();
				}
				if(a_view.getInChar() == '3'){
					System.out.println("VERBOSE LIST");
					a_view.emptyInChar();
				}
				if(a_view.getInChar() == '4'){
					a_view.printDeleteAMemberMenu();
					a_view.emptyInChar();
				}
				if(a_view.getInChar() == '5'){
					a_view.printChangeMembersInformationMenu();
					a_view.emptyInChar();
				}
				if(a_view.getInChar() == '6'){
					a_view.printLookAtMembersInformationMenu();
					a_view.emptyInChar();
				}
				if(a_view.getInChar() == '7'){
					a_view.printRegisterANewBoatMenu();
					a_view.emptyInChar();
				}
				if(a_view.getInChar() == '8'){
					a_view.printDeleteABoatMenu();
					a_view.emptyInChar();
				}
				if(a_view.getInChar() == '9'){
					a_view.printChangeBoatsInformationMenu();
					a_view.emptyInChar();
				}
				System.out.println("The user don´t want to quit");
				a_view.printMainMenu();
			}
			System.out.println("You want to quit. Bye for now!!");
			System.exit(0);
			
		}

}
