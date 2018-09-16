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
		public void runMemberRegistration(view.Console a_view, model.Member a_member, model.Boat a_boat, storage.DB a_db){
			a_view.printMainMenu();
			while(!a_view.wantsToQuit()){
				if(a_view.getInChar() == '1'){
					a_view.printCreateNewMemberMenu(a_member);
					a_db.startTransaction();
					a_db.insert(a_member, a_boat, "member");
					a_db.commitTransaction();
				}
				else if(a_view.getInChar() == '2'){
					a_view.printCompactList(a_db.getCompactList());
				}
				else if(a_view.getInChar() == '3'){
					a_view.printVerboseList(a_db.getVerboseList());
				}
				else if(a_view.getInChar() == '4'){
					a_view.printDeleteAMemberMenu(a_member);
					a_db.startTransaction();
					a_db.deleteMember(a_member.getSSN());
					a_db.commitTransaction();
				}
				else if(a_view.getInChar() == '5'){
					a_view.printChangeMembersInformationMenuPre(a_member);
					a_view.printChangeMembersInformationPre(a_db.changeMembersInformationPre(a_member));
					a_view.printChangeMembersInformationMenu(a_member);
					a_db.startTransaction();
					a_db.updateMemberInformation(a_member);
					a_db.commitTransaction();
				
				}
				else if(a_view.getInChar() == '6'){
					a_view.printLookAtMembersInformationMenu(a_member);
					a_view.printMembersInformation(a_db.getMemberInformation(a_member.getSSN()));
					
				}
				else if(a_view.getInChar() == '7'){
					a_view.printRegisterANewBoatMenu(a_member,a_boat);
					a_member.setId(a_db.getMemberId(a_member.getSSN()));
					a_db.startTransaction();
					a_db.insert(a_member, a_boat, "boat");
					a_db.commitTransaction();
					a_boat.setId(a_db.getMembersLatestAddedBoatId(a_member.getId()));
					a_db.startTransaction();
					a_db.insert(a_member, a_boat, "image");
					a_db.commitTransaction();
				
				}
				else if(a_view.getInChar() == '8'){
					a_view.printDeleteABoatMenuChooseMember(a_member);
					a_view.printMembersBoats(a_db.getMembersBoats(a_member.getSSN()));
					a_view.printDeleteABoatMenuChooseBoat(a_boat);
					a_db.deleteBoat(a_boat.getId());
					
				}
				else if(a_view.getInChar() == '9'){
					a_view.printChangeBoatsInformationMenuPre(a_member);
					a_view.printMembersBoats(a_db.getMembersBoats(a_member.getSSN()));
					a_view.printChangeBoatInformationMenuChooseBoat(a_boat);
					a_view.printASpecificBoat(a_db.getASpecificBoat(a_boat.getId()));
					a_view.printChangeBoatsInformationMenu(a_member, a_boat); 
					a_db.startTransaction();
					a_db.updateBoatInformation(a_boat);
					a_db.commitTransaction();
					
				}
				else {System.out.println("There is no event on this choice please choose again! Make your selection from the menu!");}
				System.out.println("The user don�t want to quit");
				a_view.printMainMenu();
			}
			a_view.quit();
			a_db.closeConnection();
			
		}

}
