/**
 * 
 */
package program;

/**
 * @author cw222kq
 *
 */
public class ProgramMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		view.Console v = new view.Console();
		controller.MemberRegistration c = new controller.MemberRegistration();
		model.RegistrationFacade m = new model.RegistrationFacade();
		
		// run while memberRegistration is true
		c.runMemberRegistration(v, m);
	}

}
