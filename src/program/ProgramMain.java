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
		
		// connect to the db if the connection is null
		
	
		// run while memberRegistration is true
		view.Console v = new view.Console();
		controller.MemberRegistration c = new controller.MemberRegistration();
		c.runMemberRegistration(v);
	}

}
