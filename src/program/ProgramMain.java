/**
 * 
 */
package program;

import java.sql.SQLException;

/**
 * @author cw222kq
 *
 */
public class ProgramMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		storage.DB db = new storage.DB();
		view.Console v = new view.Console();
		controller.MemberRegistration c = new controller.MemberRegistration();
		model.Member m_m = new model.Member();
		model.Boat m_b = new model.Boat();
		
		// connect to the db if the connection is null
		db.connect();
	
		// run while memberRegistration is true
		c.runMemberRegistration(v, m_m, m_b,db);
	}

}
