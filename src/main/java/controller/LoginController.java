package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	TaskService taskSrv=pe.getTaskService();
	IdentityService idSrv = pe.getIdentityService();
    String userId;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		if(username!=null && password!=null){
		if(idSrv.checkPassword(username, password)){
				idSrv.setAuthenticatedUserId(username);
				userId=username;
				request.getSession().setAttribute("userId", userId);

			
				getServletContext().getRequestDispatcher("/userMenu.jsp").forward(request, response);
			
			}else{
				response.sendRedirect("login.jsp");
			}
		}else{
			List<Task> tasks = taskSrv.createTaskQuery().taskAssignee(userId).list();
			if(tasks.size()!=0){
				request.setAttribute("assigned", tasks);
			}
			
			List<Task> candidateTasks = taskSrv.createTaskQuery().taskCandidateUser(userId).list();
			if(candidateTasks.size()!=0){
				request.setAttribute("candidate", candidateTasks);
			}
			getServletContext().getRequestDispatcher("/tasks.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
