package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.glassfish.jersey.process.internal.RequestScope.Instance;

import bll.ParticipantManager;
import bo.Participant;
import javassist.expr.Instanceof;

/**
 * Servlet implementation class authentification
 */
@WebServlet("/authentification")
public class authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public authentification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("currentUser") instanceof Participant) {
			response.sendRedirect("/sortie.com/index");
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/authentification.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("login");
		String mdp = request.getParameter("mdp");
		String shamdp = org.apache.commons.codec.digest.DigestUtils.sha256Hex(mdp);
		ParticipantManager mgr = new ParticipantManager();
		Participant currentParticipant = new Participant();
		currentParticipant.setPseudo(login);
		currentParticipant.setMotDePasse(shamdp);
		Boolean authentificationState = mgr.authentification(currentParticipant);
		if(authentificationState == true) {
			HttpSession session = request.getSession();
			Participant currentUser = mgr.getInfoAuthenticatedUser(currentParticipant);
			session.setMaxInactiveInterval(600);
			session.setAttribute("currentUser", currentUser);
			System.out.println(currentUser.getPseudo());
			response.sendRedirect("/sortie.com/index");
		}else {
			doGet(request, response);
		}
	}

}
