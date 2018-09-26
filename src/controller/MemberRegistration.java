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
				
				// 1. Create a new member
				if(a_view.getInChar() == '1'){
					
					a_view.printCreateNewMemberMenu(a_member);
					
					// Inserting the member in the member table in the db
					a_db.startTransaction();
					a_db.insert(a_member, a_boat, "member");
					a_db.commitTransaction();
				}
				// 2. List all members as compact list
				else if(a_view.getInChar() == '2'){
					
					a_view.printCompactList(a_db.getCompactList());
					
				}
				// 3. List all members as verbose list
				else if(a_view.getInChar() == '3'){
					
					a_view.printVerboseList(a_db.getVerboseList());
		
				}
				// 4. Delete a member
				else if(a_view.getInChar() == '4'){
					
					a_view.printDeleteAMemberMenu(a_member);
					
					// Deleting the member in the db 
					a_db.startTransaction();
					a_db.deleteMember(a_member.getSSN());
					a_db.commitTransaction();
				
				}
				// 5. Change members information
				else if(a_view.getInChar() == '5'){
					
					// printing the current member information on the specific member from the db
					a_view.printLookAtMembersInformationMenu(a_member);
					a_view.printMembersInformation(a_db.changeMembersInformation(a_member));
					
					// printing out the menu for changing the specific member and setting the new values
					a_view.printChangeMembersInformationMenu(a_member);
					
					// updating the information about the member in the db
					a_db.startTransaction();
					a_db.updateMemberInformation(a_member);
					a_db.commitTransaction();
				
				}
				// 6. View members information
				else if(a_view.getInChar() == '6'){
					
					// choose the specific member
					a_view.printLookAtMembersInformationMenu(a_member);
					
					// printing out the specific members information
					a_view.printWholeMembersInformation(a_db.getMemberInformation(a_member.getSSN()));
				
				}
				// 7. Register a new boat
				else if(a_view.getInChar() == '7'){
					
					// choose the member that the user wants to register a new boat for
					a_view.printRegisterANewBoatMenu(a_member,a_boat);
					a_member.setId(a_db.getMemberId(a_member.getSSN()));
							
					// inserting the data into the boat table
					a_db.startTransaction();
					a_db.insert(a_member, a_boat, "boat");
					a_db.commitTransaction();
					
					// getting the boat id of the latest boat that the member has added (for setting the boat_id into the image table)
					a_boat.setId(a_db.getMembersLatestAddedBoatId(a_member.getId()));
					
					//inserting the data into the image table
					a_db.startTransaction();
					a_db.insert(a_member, a_boat, "image");
					a_db.commitTransaction();
					
				}
				// 8. Delete a boat
				else if(a_view.getInChar() == '8'){
					
					// Choose the member that the user wants to delete a boat from
					a_view.printDeleteABoatMenuChooseMember(a_member);
					
					// printing out all the specific members boats
					a_view.printAllMembersBoats(a_db.getMembersBoats(a_member.getSSN()));
					
					// Choose the specific boat to delete from the member
					a_view.printDeleteABoatMenuChooseBoat(a_boat);
					
					// Deleting the boat in the db
					a_db.startTransaction();
					a_db.deleteBoat(a_boat.getId());
					a_db.commitTransaction();
				
				}
				// 9. Change boats information
				else if(a_view.getInChar() == '9'){
					
					// Choose the member that the user wants to modify a boat from
					a_view.printLookAtMembersInformationMenu(a_member);
					
					// printing out all boats from the specific member
					a_view.printAllMembersBoats(a_db.getMembersBoats(a_member.getSSN()));
					
					// Choose the specific boat to modify from the member
					a_view.printChangeBoatInformationMenuChooseBoat(a_boat);
					a_view.printASpecificBoat(a_db.getASpecificBoat(a_boat.getId()));
					
					// printing out the menu for changing the specific boat and setting the new values
					a_view.printChangeBoatsInformationMenu(a_member, a_boat); 
					
					// updating the information about the boat in the db
					a_db.startTransaction();
					a_db.updateBoatInformation(a_boat);
					a_db.commitTransaction();
				
				}
				// If the user inputs a value that don't exists
				else {System.out.println("There is no event on this choice please choose again! Make your selection from the menu!");}
				System.out.println("The user don´t want to quit");
				a_view.printMainMenu();
			}
			// If the user choose Q ends the while loop and the application quits
			a_view.quit();
			a_db.closeConnection();
			
		}

}
