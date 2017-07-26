package listeners;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import model.beans.Kompanija;
import model.beans.Ponuda;
import model.dao.KompanijaDao;
import model.dao.PonudaDao;

public class TaskListenerKorisnik implements TaskListener {

	public void notify(DelegateTask dt) {
		String id=(String) dt.getVariable("listaKompanija");
		System.out.println(dt.getVariable("listaKompanija"));
		KompanijaDao kDao=new KompanijaDao();
		Kompanija k= kDao.getCompany(Integer.parseInt(id));
		
		dt.setVariable("izabranaKompanija", k);
		PonudaDao pDao=new PonudaDao();
		List<Ponuda> p= pDao.getPonude();
		dt.setVariable("nazivIzabraneKompanije", k.getNazivKompanije());
		for (Ponuda ponuda : p) {
			if(ponuda.getKompanijaId().equals(Integer.parseInt(id))){
				dt.setVariable("izabranaPonuda", "Naziv kompanije: "+k.getNazivKompanije()+" Cena usluge: "+ponuda.getCena());
			}
		}


	}

}
