package model.dao;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDAO {
	
	protected InitialContext ic;
	protected DataSource ds;
	
	public BaseDAO(){
		this.ic=null;
		this.ds=null;
		
		try{
			this.ic = new InitialContext();
			this.ds = (DataSource) ic.lookup("java:comp/env/jdbc/activiti");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


}
