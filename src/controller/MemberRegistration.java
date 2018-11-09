/**
 * 
 */
package controller;

/**
 * @author cw222kq
 *
 */
public class MemberRegistration {

	// written with inspiration from: https://github.com/tobias-dv-lnu/1dv607/blob/master/MV_DiceGame/MV_DiceGame_java/view/Console.java
	// running the program as long as the user don't choose q in the menu
	public void runMemberRegistration(view.Console a_view, model.RegistrationFacade a_registrationFacade){ 
		a_view.printMainMenu();
		while(!a_view.wantsToQuit()){
			
			// 1. Create a new member
			if(a_view.getInChar() == '1'){
				
				a_view.printHeadingCreateMember();
				a_view.printCreateMember(a_registrationFacade);
				
				// Inserting the member in the member table in the db
				a_registrationFacade.startTransaction();
				a_registrationFacade.insertMember();
				a_registrationFacade.commitTransaction();
				if(a_registrationFacade.getEventSucceeded()){
					a_view.printSuccededInsert();
					a_registrationFacade.setEventSucceededToFalse();
				}
			}
			// 2. List all members as compact list
			else if(a_view.getInChar() == '2'){
				
				a_view.printHeadingCompactList();
				a_view.printCompactList(a_registrationFacade.getCompactList());	
			}
			// 3. List all members as verbose list
			else if(a_view.getInChar() == '3'){
			
				a_view.printHeadingVerboseList();
				a_view.printMembersInformation(a_registrationFacade.getVerboseList());
			}
			// 4. Delete a member
			else if(a_view.getInChar() == '4'){
				
				a_view.printHeadingDeleteMember();
				a_view.printDeleteMember(a_registrationFacade);
				
				// Deleting the member in the db
				a_registrationFacade.startTransaction();
				a_registrationFacade.deleteMember(a_registrationFacade.getMemberSSN());
				a_registrationFacade.commitTransaction();
				if(a_registrationFacade.getEventSucceeded()){
					a_view.printSuccededDelete();
					a_registrationFacade.setEventSucceededToFalse();
				}
				
			}
			// 5. Change members information 
			else if(a_view.getInChar() == '5'){
				
				a_view.printHeadingChangeMember();
				
				// printing the current member information on the specific member from the db
				a_view.printLookAtMember(a_registrationFacade);
				if(a_registrationFacade.getMembersInformation() == null){
					a_view.printErrorUser();
				}
				else {
					a_view.printMember(a_registrationFacade.getMembersInformation());
				
					// printing out the menu for changing the specific member and setting the new values
					a_view.printChangeMember(a_registrationFacade);
					
					// updating the information about the member in the db 
					a_registrationFacade.startTransaction();
					a_registrationFacade.updateMember();
					a_registrationFacade.commitTransaction();
					if(a_registrationFacade.getEventSucceeded()){
						a_view.printSuccededInsert();
						a_registrationFacade.setEventSucceededToFalse();
					}
							
				}
			}
			// 6. View members information			
			else if(a_view.getInChar() == '6'){
				
				a_view.printHeadingLookAtMember();
				// choose the specific member
				a_view.printLookAtMember(a_registrationFacade);
				
				// printing out the specific members information	
				a_view.printMembersInformation(a_registrationFacade.getMemberAndBoatsInformation(a_registrationFacade.getMemberSSN()));
				
			}
			// 7. Register a new boat
			else if(a_view.getInChar() == '7'){
				
				a_view.printHeadingRegisterBoat();
				// choose the member that the user wants to register a new boat for
				a_view.printRegisterBoat(a_registrationFacade);
				a_registrationFacade.setMemberId(a_registrationFacade.getMemberId(a_registrationFacade.getMemberSSN()));
						
				// inserting the data into the boat table 
				a_registrationFacade.startTransaction();
				a_registrationFacade.insertBoat();
				a_registrationFacade.commitTransaction();
				if(a_registrationFacade.getEventSucceeded()){
					a_view.printSuccededInsert();
					a_registrationFacade.setEventSucceededToFalse();
				}
				
				// getting the boat id of the latest boat that the member has added (for setting the boat_id into the image table)
				a_registrationFacade.setBoatId(a_registrationFacade.getMembersLatestAddedBoatId(a_registrationFacade.getMemberId()));
				
				//inserting the data into the image table 
				a_registrationFacade.startTransaction();
				a_registrationFacade.insertImage();
				a_registrationFacade.commitTransaction();
				if(a_registrationFacade.getEventSucceeded()){
					a_view.printSuccededInsert(); 
					a_registrationFacade.setEventSucceededToFalse();
				}
		
			}
			// 8. Delete a boat
			else if(a_view.getInChar() == '8'){
				
				a_view.printHeadingDeleteBoat();
				
				// Choose the member that the user wants to delete a boat from
				a_view.printChooseMember(a_registrationFacade);
				
				// printing out all the specific members boats
				a_view.printMembersBoats(a_registrationFacade.getMembersBoats(a_registrationFacade.getMemberSSN())); 
				
				// Choose the specific boat to delete from the member
				a_view.printDeleteBoat(a_registrationFacade);
				
				// Deleting the boat in the db 
				a_registrationFacade.startTransaction();
				a_registrationFacade.deleteBoat(a_registrationFacade.getBoatId());
				a_registrationFacade.commitTransaction();
				if(a_registrationFacade.getEventSucceeded()){
					a_view.printSuccededDelete(); 
					a_registrationFacade.setEventSucceededToFalse();
				}		
			}
			// 9. Change boats information 
			else if(a_view.getInChar() == '9'){
				
				a_view.printHeadingChangeBoat();
				
				// Choose the member that the user wants to modify a boat from
				a_view.printLookAtMember(a_registrationFacade);
				
				// printing out all boats from the specific member
				a_view.printMembersBoats(a_registrationFacade.getMembersBoats(a_registrationFacade.getMemberSSN())); 
				
				// Choose the specific boat to modify from the member
				a_view.printSelectBoat(a_registrationFacade);
				a_view.printBoat(a_registrationFacade.getASpecificBoat(a_registrationFacade.getBoatId()));
						
				// printing out the menu for changing the specific boat and setting the new values
				a_view.printChangeBoat(a_registrationFacade); 
					
				// updating the information about the boat in the db 
				a_registrationFacade.startTransaction();
				a_registrationFacade.updateBoat();
				a_registrationFacade.commitTransaction();
				if(a_registrationFacade.getEventSucceeded()){
					a_view.printSuccededInsert();
					a_registrationFacade.setEventSucceededToFalse();	
				}		
			}
			// If the user inputs a value that don't exists
			else {a_view.printErrorChoice();}
			a_view.printMainMenu();
		}
		// If the user choose Q ends the while loop and the application quits
		a_view.quit();
		a_registrationFacade.closeConnection();	
	}

}
