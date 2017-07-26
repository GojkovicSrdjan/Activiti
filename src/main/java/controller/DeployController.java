package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

/**
 * Servlet implementation class DeployController
 */
public class DeployController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	private static final String defaultFileName = "diagrams/ProjekatProcess.bpmn";
	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
	RepositoryService repSrv = pe.getRepositoryService();
	TaskService taskSrv=pe.getTaskService();
	RuntimeService runSrv;
	
    public DeployController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		repSrv.createDeployment().addClasspathResource(defaultFileName).deploy();
		runSrv=pe.getRuntimeService();
		runSrv.startProcessInstanceByKey("projekatProcess");
		response.sendRedirect("userMenu.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
