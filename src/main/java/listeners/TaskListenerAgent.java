package listeners;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import model.beans.Kompanija;
import model.beans.Ponuda;
import model.dao.PonudaDao;

public class TaskListenerAgent implements TaskListener {
	//napravi novi taskListener na on create u enum ubacis vrednost liste kompanija
	public void notify(DelegateTask dt) {
		Integer i=(Integer) dt.getVariable("counter");
		if(i==null)
			i=0;
		i++;
		System.out.println("counter: "+i);
		dt.setVariable("counter", i);
		List<Kompanija> kompanije=(List<Kompanija>) dt.getVariable("kompanijePrihvaceno");
		PonudaDao pDao=new PonudaDao();
		Kompanija k=(Kompanija) dt.getVariable("kompanija");
		System.out.println(k.getNazivKompanije());
		//List<Long> k=(List<Long>) dt.getVariable("prihvaceno");
		boolean b=(Boolean) dt.getVariable("prihvatiPonudu");
		if(kompanije==null){
			kompanije=new ArrayList<Kompanija>();
		}
		if(b==true){
			k.setPrihvacenPosao(true);
			k.setPonuda((Long) dt.getVariable("cena"));
			kompanije.add(k);
			dt.setVariable("kompanijePrihvaceno", kompanije);
			//tabela ponuda getProcessId
			//if(i>=Integer.parseInt(dt.getVariable("brojPonuda").toString())){
			for (Kompanija kompanija : kompanije) {
				Ponuda p=new Ponuda();
				p.setKompanijaId(kompanija.getId());
				p.setCena((int) kompanija.getPonuda());
				pDao.addPonuda(p);
				
			}
			
		}
	}

}
