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
		model.Member m_m = new model.Member();
		model.Boat m_b = new model.Boat();
		model.DAO m_d = new model.DAO();
	
		// run while memberRegistration is true
		c.runMemberRegistration(v, m_m, m_b, m_d);
	}

}
