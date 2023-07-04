package it.polito.Turni24H.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.Turni24H.db.Turni24HDao;

public class Risultati {
	private Turni24HDao dao;
	private List<Accoppiamenti> miglioreSol;
	private List <Staff> allStaff;
	private List<Turno> turni;
	
	private List<Turno> turniNonCompletati;
	
	
	public Risultati(List<Accoppiamenti> miglioreSol) {
		if(this.dao == null)
			dao = new Turni24HDao();
		if(this.turni == null) {
			turni = new ArrayList<>();
			turni.addAll(dao.getAllTurni());
		}
		if(this.allStaff == null) {
			allStaff = new ArrayList<>();
			allStaff.addAll(dao.getAllStaff());
		}
		this.miglioreSol = miglioreSol;
		
		turniNonCompletati = new ArrayList<>();
		
	}
	
	
	public Map <Staff, Integer> getStaffOreInMeno (){
		Map<Staff, Integer> mappa = new HashMap<>();
		for(Staff s : allStaff) {
			int k = 0;
			for(Accoppiamenti a : miglioreSol ) {
				if(s.equals(a.getStaff())) {
					k++;
				}	
			}
			if(k < ((s.getMinTotalMin()/60)/8))
				mappa.put(s, (((s.getMinTotalMin()/60)/8) - k));
		}
		return mappa;	
	}
	
	public Map <Staff, Integer> getStaffConOreDisponibili (){
		Map<Staff, Integer> mappa = new HashMap<>();
		for(Staff s : allStaff) {
			int k = 0;
			for(Accoppiamenti a : miglioreSol ) {
				if(s.equals(a.getStaff())) {
					k++;
				}	
			}
			if(k >= ((s.getMinTotalMin()/60)/8) && k < ((s.getMaxTotalMin()/60)/8))
				mappa.put(s, (((s.getMaxTotalMin()/60)/8)) - k);
		}
		return mappa;	
	}

	
	public Map<Staff, Integer> getStaffStraordinario(){
		Map<Staff, Integer> mappa = new HashMap<>();
		for(Staff s : allStaff) {
			int k = 0;
			for(Accoppiamenti a : miglioreSol ) {
				if(s.equals(a.getStaff())) {
					k++;
				}	
			}
			if(k > ((s.getMaxTotalMin()/60)/8))
				mappa.put(s, (k - ((s.getMaxTotalMin()/60)/8)));
		}
		return mappa;
	}
	
	public int getTurniNonCompletati(){
		turniNonCompletati.clear();
		int tot = 0;
		for(Turno t : turni) {
			int kk = 0;
			for(Accoppiamenti a :miglioreSol ) {
				if(t.equals(a.getTurno())) {
					kk ++;
				}
			}
			if(t.getRichiesta()>kk) {
				turniNonCompletati.add(t);
				tot += (t.getRichiesta() - kk);
			}
		}
		return tot;
	}
	
	public Map <Turno, Integer> getMapTurniIncompleti (){
		Map<Turno, Integer> mappa = new HashMap<>();
		for(Turno t : turni) {
			int kk = 0;
			for(Accoppiamenti a :miglioreSol ) {
				if(t.equals(a.getTurno())) {
					kk ++;
				}
			}
			if(t.getRichiesta()>kk)
				mappa.put(t, (t.getRichiesta() - kk));
		}
		return mappa;	
	}
	
	public int staffTurniInPiu(Staff s){
		int k = 0;
		for(Accoppiamenti a :miglioreSol) {
			if(a.getStaff().equals(s))
				k ++;
		}
		int differenza = (k - ((s.getMaxTotalMin()/60)/8));
		return differenza;
	}
	
	public int staffTurniInMeno(Staff s){
		int k = 0;
		for(Accoppiamenti a :miglioreSol) {
			if(a.getStaff().equals(s))
				k ++;
		}
		int differenza = (((s.getMinTotalMin()/60)/8) - k);
		return differenza;
	}
	
	public int turniInMeno (Turno t) {
		int k = 0;
		for(Accoppiamenti a :miglioreSol) {
			if(a.getTurno().equals(t))
				k ++;
		}
		int differenza = (t.getRichiesta() - k);
		return differenza;
	}
	
}
