package model.beans;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import model.dao.KompanijaDao;
import model.dao.PonudaDao;

public class SelektovanjeKompanija implements JavaDelegate {
	
	public void execute(DelegateExecution execution) throws Exception {
		KompanijaDao kDao=new KompanijaDao();
		PonudaDao pDao=new PonudaDao();
		String category=(String) execution.getVariable("kategorijaPosla");
		String requiredNum=(String) execution.getVariable("brojPonuda");

		Double additionalNum =(Double) execution.getVariable("dodatniBroj");
		List<String> emails=new ArrayList<String>();
		List<Kompanija> kompanije=new ArrayList<Kompanija>();
		
		if(additionalNum!=null){
			Integer n=additionalNum.intValue();
			kompanije=kDao.companiesFromCategory(Integer.parseInt(category), n.toString());
		}else{
			kompanije=kDao.companiesFromCategory(Integer.parseInt(category), requiredNum);
		}
		if(kompanije!=null){
			for (Kompanija kompanija : kompanije) {
				emails.add(kompanija.getEmail());
				System.out.println(kompanija.getEmail());
				kDao.update(kompanija.getId());
			}
		}
		List<Kompanija>k=(List<Kompanija>) execution.getVariable("kompanijePrihvaceno");
		execution.setVariable("kompanije", kompanije);
		execution.setVariable("emails", emails);
		//execution.setVariable("izabranaPonuda", null);
		execution.setVariable("odlukaKlijenta", null);
		execution.setVariable("izabranaKompanija", null);
		if(execution.getVariable("kompanijePrihvaceno")==null){
			execution.setVariable("kompanijePrihvaceno", null);
		}
		
		if(execution.getVariable("zatraziNovePonude")!=null){
			boolean b=(Boolean) execution.getVariable("zatraziNovePonude");
			if(b==true){
				if(execution.getVariable("f")==null){
					execution.setVariable("kompanijePrihvaceno", null);
					execution.setVariable("counter", null);
					execution.setVariable("f", "f");
					pDao.deleteAll();
				}
			}
		}
	}

}
