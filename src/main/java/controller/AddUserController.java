package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.User;

/**
 * Servlet implementation class AddUserController
 */
public class AddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	IdentityService idSrv = pe.getIdentityService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstname=null;
		String lastname=null;
		String username=null;
		String password=null;
		String email=null;
		
		if(request.getParameter("firstname")!="")
			firstname=request.getParameter("firstname");
		
		if(request.getParameter("lastname")!="")
			lastname=request.getParameter("lastname");
		
		if(request.getParameter("newUsername")!=null)
			username=request.getParameter("newUsername");
		
		if(request.getParameter("newPassword")!=null)
			password=request.getParameter("newPassword");
		
		if(request.getParameter("email")!="")
			email=request.getParameter("email");
		
		User user=idSrv.newUser(username);
		user.setPassword(password);
		if(firstname!=null)
			user.setFirstName(firstname);
		if(lastname!=null)
			user.setLastName(lastname);
		if(email!=null)
			user.setEmail(email);
		idSrv.saveUser(user);
		response.sendRedirect("mainMenu.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
