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
	// written with inspiration from: https://github.com/tobias-dv-lnu/1dv607/blob/master/MV_DiceGame/MV_DiceGame_java/view/Console.java
		// running the program as long as the user don't choose q in the menu
	public void runMemberRegistration(view.Console a_view, model.RegistrationFacade a_registrationFacade){ 
		a_view.printMainMenu();
		while(!a_view.wantsToQuit()){
			
			// 1. Create a new member
			if(a_view.getInChar() == '1'){
				
				a_view.printHeadlineCreateNewMember();
				a_view.printCreateNewMemberMenu(a_registrationFacade);
				
				// Inserting the member in the member table in the db
				a_registrationFacade.startTransaction();
				a_registrationFacade.insert("member");
				a_registrationFacade.commitTransaction();
			}
			// 2. List all members as compact list
			else if(a_view.getInChar() == '2'){
				
				a_view.printHeadlineCompactList();
				a_view.printCompactList(a_registrationFacade.getCompactList());
				
			}
			// 3. List all members as verbose list
			else if(a_view.getInChar() == '3'){
			
				a_view.printHeadlineVerboseList();
				a_view.printVerboseList(a_registrationFacade.getVerboseList());
	
			}
			// 4. Delete a member
			else if(a_view.getInChar() == '4'){
				
				a_view.printHeadlineDeleteAMember();
				a_view.printDeleteAMemberMenu(a_registrationFacade);
				
				// Deleting the member in the db
				a_registrationFacade.startTransaction();
				a_registrationFacade.deleteMember(a_registrationFacade.getMemberSSN());
				a_registrationFacade.commitTransaction();
			
			}
			// 5. Change members information 
			else if(a_view.getInChar() == '5'){
				
				a_view.printHeadlineChangeMembersInformation();
				
				// printing the current member information on the specific member from the db
				a_view.printLookAtMembersInformationMenu(a_registrationFacade);
				if(a_registrationFacade.getMembersInformation() == null){
					a_view.printErrorMessageUserIsNotInDB();
				}
				else {
					a_view.printMembersInformation(a_registrationFacade.getMembersInformation());
				
					// printing out the menu for changing the specific member and setting the new values
					a_view.printChangeMembersInformationMenu(a_registrationFacade);
					
					// updating the information about the member in the db 
					a_registrationFacade.startTransaction();
					a_registrationFacade.updateMemberInformation();
					a_registrationFacade.commitTransaction();
				}
			
			}
			// 6. View members information
			else if(a_view.getInChar() == '6'){
				
				a_view.printHeadlineLookAtASpecificMembersInformation();
				// choose the specific member
				a_view.printLookAtMembersInformationMenu(a_registrationFacade);
				
				// printing out the specific members information
				a_view.printWholeMembersInformation(a_registrationFacade.getMemberAndBoatsInformation(a_registrationFacade.getMemberSSN()));
			
			}
			// 7. Register a new boat
			else if(a_view.getInChar() == '7'){
				
				a_view.printHeadlineRegisterANewBoat();
				// choose the member that the user wants to register a new boat for
				a_view.printRegisterANewBoatMenu(a_registrationFacade);
				a_registrationFacade.setMemberId(a_registrationFacade.getMemberId(a_registrationFacade.getMemberSSN()));
						
				// inserting the data into the boat table 
				a_registrationFacade.startTransaction();
				a_registrationFacade.insert("boat");
				a_registrationFacade.commitTransaction();
				
				// getting the boat id of the latest boat that the member has added (for setting the boat_id into the image table)
				a_registrationFacade.setBoatId(a_registrationFacade.getMembersLatestAddedBoatId(a_registrationFacade.getMemberId()));
				
				//inserting the data into the image table 
				a_registrationFacade.startTransaction();
				a_registrationFacade.insert("image");
				a_registrationFacade.commitTransaction();
			}
			// 8. Delete a boat
			else if(a_view.getInChar() == '8'){
				
				a_view.printHeadlineDeleteABoat();
				// Choose the member that the user wants to delete a boat from
				a_view.printDeleteABoatMenuChooseMember(a_registrationFacade);
				
				// printing out all the specific members boats
				a_view.printAllMembersBoats(a_registrationFacade.getMembersBoats(a_registrationFacade.getMemberSSN()));
				
				// Choose the specific boat to delete from the member
				a_view.printDeleteABoatMenuChooseBoat(a_registrationFacade);
				
				// Deleting the boat in the db 
				a_registrationFacade.startTransaction();
				a_registrationFacade.deleteBoat(a_registrationFacade.getBoatId());
				a_registrationFacade.commitTransaction();
			
			}
			// 9. Change boats information 
			else if(a_view.getInChar() == '9'){
				
				a_view.printHeadlineChangeABoatsInforamtion();
				// Choose the member that the user wants to modify a boat from
				a_view.printLookAtMembersInformationMenu(a_registrationFacade);
				
				// printing out all boats from the specific member 
				a_view.printAllMembersBoats(a_registrationFacade.getMembersBoats(a_registrationFacade.getMemberSSN()));
				
				// Choose the specific boat to modify from the member
				a_view.printChangeBoatInformationMenuChooseBoat(a_registrationFacade);
				a_view.printASpecificBoat(a_registrationFacade.getASpecificBoat(a_registrationFacade.getBoatId()));
						
				// printing out the menu for changing the specific boat and setting the new values
				a_view.printChangeBoatsInformationMenu(a_registrationFacade); 
					
				// updating the information about the boat in the db 
				a_registrationFacade.startTransaction();
				a_registrationFacade.updateBoatInformation();
				a_registrationFacade.commitTransaction();	
					
			}
			// If the user inputs a value that don't exists
			else {a_view.printErrorMessageWrongChoice();}
			a_view.printMainMenu();
		}
		// If the user choose Q ends the while loop and the application quits
		a_view.quit();
		a_registrationFacade.closeConnection();
		
	}
	
	

}
