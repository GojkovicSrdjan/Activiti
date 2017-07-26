package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;

import model.beans.Ponuda;
import model.dao.KompanijaDao;
import model.dao.PonudaDao;

/**
 * Servlet implementation class TaskController
 */
public class TaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskController() {
    }
    ProcessEngine pe=ProcessEngines.getDefaultProcessEngine();
    TaskService taskSrv=pe.getTaskService();
    FormService formSrv=pe.getFormService();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String assigned=request.getParameter("aId");
		String candidate=request.getParameter("cId");
		
		String pId=request.getParameter("propertyId");
		String newValue=request.getParameter("newValue");
		
		System.out.println(pId);
		System.out.println(newValue);
		String completed=request.getParameter("completed");
		
		String userId=(String) request.getSession().getAttribute("userId");
		String executingTask=(String) request.getSession().getAttribute("executing");
		HashMap<String, String> fpValues=(HashMap<String, String>) request.getSession().getAttribute("values");
//		for (Entry<String, String> e: fpValues.entrySet()) {
//			System.out.println("key"+e.getKey()+" "+"value"+e.getValue());
//		}
		
		HashMap<String, String> enumValues=new HashMap<String, String>();
		HashMap<String, String> categories=new HashMap<String, String>();
		PonudaDao pDao=new PonudaDao();
		KompanijaDao kDao=new KompanijaDao();
		if(assigned!=null){
			HashMap<String, String> v=new HashMap<String, String>();
			request.getSession().setAttribute("values", v);
			request.getSession().setAttribute("executing", assigned);
			for (Task t: taskSrv.createTaskQuery().taskAssignee(userId).list()) {
				System.out.println(t.getDescription());				
				if(t.getId().equals(assigned)){
					TaskFormData tfd=formSrv.getTaskFormData(assigned);
					List<FormProperty> formProps=tfd.getFormProperties();
					if(formProps!=null && !formProps.isEmpty()){
						for (FormProperty formProperty : formProps) {
							if(formProperty.getId().equals("listaKompanija")){
							List<Ponuda> p= pDao.getPonude();
							HashMap<String, String> ponude=new HashMap<String, String>();
							for (Ponuda ponuda : p) {								
								ponude.put(ponuda.getKompanijaId().toString(), "Naziv kompanije: "+kDao.getCompany(ponuda.getKompanijaId()).getNazivKompanije()+" Cena usluge: "+ponuda.getCena());
							}
								request.setAttribute("ponude", ponude);
							}						
							if(formProperty.getType().getName().equals("enum")){
//								if(formProperty.getId().equals("processId")){
//									request.getSession().setAttribute("processId", formProperty.getValue());
//									System.out.println(formProperty.getValue());
//								}
								
								if(formProperty.getId().equals("kategorijaPosla")){
									HashMap<String, String> values=(HashMap<String, String>) formProperty.getType().getInformation("values");
									for (Entry<String, String> e: values.entrySet()) {
										if(e.getValue()!=null){
											categories.put(e.getKey(), e.getValue());
										}
									}
									request.setAttribute("jobCategories", categories);
								}else{
									HashMap<String, String> values=(HashMap<String, String>) formProperty.getType().getInformation("values");
								for (Entry<String, String> e: values.entrySet()) {
									if(e.getValue()!=null){
										enumValues.put(e.getKey(), e.getValue());
									}
								}

								request.setAttribute("enumValues", enumValues);
								}
							}
							
							
						}
						request.setAttribute("formProperties", formProps);
						getServletContext().getRequestDispatcher("/form.jsp").forward(request, response);
					}
				}
			}
		}
		
		else if(executingTask!=null){
			if(pId!=null && newValue!=null){
				for (Task t: taskSrv.createTaskQuery().taskAssignee(userId).list()) {
					if(t.getId().equals(executingTask)){
						TaskFormData tfd=formSrv.getTaskFormData(executingTask);
						List<FormProperty> formProps=tfd.getFormProperties();
						for (FormProperty formProperty : formProps) {
							if(formProperty.isWritable()){
								if(formProperty.getId().equalsIgnoreCase(pId)){
									//enteredValues.put(formProperty.getId(), newValue);
									fpValues.put(formProperty.getId(), newValue);
								}
							}
						}
					}
			
				}
			}else if(completed!=null){
				for (Task t: taskSrv.createTaskQuery().taskAssignee(userId).list()) {
					if(t.getId().equals(executingTask)){
						TaskFormData tfd  = formSrv.getTaskFormData(executingTask);
						List<FormProperty> formProps = tfd.getFormProperties();
						if(formProps!=null && !formProps.isEmpty()){
							formSrv.submitTaskFormData(executingTask, fpValues );
						}else{
							taskSrv.complete(executingTask);
						}
					}
				}
				response.sendRedirect("userMenu.jsp");
			}
			
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
