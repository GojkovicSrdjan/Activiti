package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.beans.AgentKompanije;
import model.beans.Kompanija;

public class KompanijaDao extends BaseDAO {
	
	public List<Kompanija> companiesFromCategory(Integer category, String num){
		List<Kompanija> result=null;
		Connection conn=null;
		
		String sqlQuery="Select * from kompanija where"
				+ " kategorija_posla="+ category +" and ponudjen_posao=0 limit "+num +";";
		
		try {
			conn=ds.getConnection();
			PreparedStatement p=conn.prepareStatement(sqlQuery);
			ResultSet rs=p.executeQuery();
			while(rs.next()){
				if(result==null)
					result=new ArrayList<Kompanija>();
				Kompanija k=new Kompanija(rs.getInt("id"),
						rs.getString("naziv"), rs.getString("mail"));
						k.setAgentId(rs.getString("agent"));
						k.setKategorijaId(rs.getInt("kategorija_posla"));
				result.add(k);
			}
			p.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public AgentKompanije agent(String id){
		AgentKompanije a = null;
		Connection conn=null;
		
		String sqlQuery="Select ID_, FIRST_, LAST_, EMAIL_, PWD_, id from act_id_user, kompanija where ID_= '"+id+"';";
		
		try {
			conn=ds.getConnection();
			PreparedStatement p=conn.prepareStatement(sqlQuery);
			ResultSet rs=p.executeQuery();
			if(rs.next()){
				a=new AgentKompanije(rs.getString("ID_"), rs.getString("FIRST_"), rs.getString("LAST_"), rs.getString("PWD_"), rs.getString("EMAIL_"));
				a.setIdKomanije(rs.getInt("id"));
			}
			p.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}
	
	public void update(Integer id){
		String sqlQuery="Update kompanija Set ponudjen_posao=? where id= "+id+";";
		Connection conn=null;
		
		try {
			conn=ds.getConnection();
			PreparedStatement p=conn.prepareStatement(sqlQuery);
			p.setBoolean(1, true);
			p.executeUpdate();
			p.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Kompanija getCompany(Integer id){
		Kompanija k=null;
		Connection conn=null;
		String sqlQuery="Select * from kompanija where id="+id+";";
		try {
			conn=ds.getConnection();
			PreparedStatement p=conn.prepareStatement(sqlQuery);
			ResultSet rs=p.executeQuery();
			if(rs.next())
				k=new Kompanija(rs.getInt("id"),
						rs.getString("naziv"), rs.getString("mail"));
						k.setAgentId(rs.getString("agent"));
			p.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	
	
}
