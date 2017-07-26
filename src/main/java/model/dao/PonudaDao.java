package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import model.beans.Ponuda;

public class PonudaDao extends BaseDAO {
	
	public List<Ponuda> getPonude(){
		List<Ponuda> ponude=null;
		Connection conn=null;
		
		String sqlQuery="select * from ponuda_kompanije";
		
		try {
			conn=ds.getConnection();
			PreparedStatement p=conn.prepareStatement(sqlQuery);
			ResultSet rs=p.executeQuery();
			while(rs.next()){
				if(ponude==null)
					ponude=new ArrayList<Ponuda>();
				Ponuda ponuda=new Ponuda(rs.getInt("id"),
						rs.getInt("kompanija_id"), rs.getInt("cena"));
				ponude.add(ponuda);
			}
			p.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ponude;
	}
	
	public void addPonuda(Ponuda p){
		String sqlQuery= "insert IGNORE into ponuda_kompanije "
				+ "(kompanija_id, cena)"
				+ "values(?,?); ";
		
		if(p!=null && this.ds!=null){
			Connection conn=null;
			try {
				conn=ds.getConnection();
				PreparedStatement ps=conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, p.getKompanijaId());
				ps.setInt(2, p.getCena());
			    int af=	ps.executeUpdate();
			    System.out.println("AffectedRows: " +af);
			    ps.close();
			    conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void deleteAll(){
		String sqlQuery="truncate ponuda_kompanije;";
		Connection conn=null;
		try {
			conn=ds.getConnection();
			PreparedStatement p=conn.prepareStatement(sqlQuery);
			p.executeQuery();
			p.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
