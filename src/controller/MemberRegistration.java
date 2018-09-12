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
		public void runMemberRegistration(view.Console a_view, model.Member a_member, model.Boat a_boat){
			a_view.printMainMenu();
			while(!a_view.wantsToQuit()){
				if(a_view.getInChar() == '1'){
					a_view.printCreateNewMemberMenu(a_member);
					//a_view.emptyInChar();
				}
				else if(a_view.getInChar() == '2'){
					a_view.printCompactList();
					//a_view.emptyInChar();
				}
				else if(a_view.getInChar() == '3'){
					a_view.printVerboseList();
					//a_view.emptyInChar();
				}
				else if(a_view.getInChar() == '4'){
					a_view.printDeleteAMemberMenu(a_member);
					//a_view.emptyInChar();
				}
				else if(a_view.getInChar() == '5'){
					a_view.printChangeMembersInformationMenu(a_member);
					//a_view.emptyInChar();
				}
				else if(a_view.getInChar() == '6'){
					a_view.printLookAtMembersInformationMenu(a_member);
					//a_view.emptyInChar();
				}
				else if(a_view.getInChar() == '7'){
					a_view.printRegisterANewBoatMenu(a_member,a_boat);
					//a_view.emptyInChar();
				}
				else if(a_view.getInChar() == '8'){
					a_view.printDeleteABoatMenu(a_member,a_boat);
					//a_view.emptyInChar();
				}
				else if(a_view.getInChar() == '9'){
					a_view.printChangeBoatsInformationMenu(a_member,a_boat);
					//a_view.emptyInChar();
				}
				else {System.out.println("DU HAMNADE I ELSEN. SKRIV ETT FELMEDDELANDE I CONSOLEN OCH ANROPA DEN METODEN HÄR");}
				System.out.println("The user don´t want to quit");
				a_view.printMainMenu();
			}
			a_view.quit();
			
		}

}
