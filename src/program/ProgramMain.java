/**
 * 
 */
package program;

import model.MemberRegistration;
import view.Console;

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
		view.Console c = new Console();
		model.MemberRegistration mr = new MemberRegistration();
		c.runMemberRegistration();
	}

}
