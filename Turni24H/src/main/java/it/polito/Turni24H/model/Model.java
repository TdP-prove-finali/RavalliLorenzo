package it.polito.Turni24H.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



import it.polito.Turni24H.db.Turni24HDao;


public class Model {
	
	private Turni24HDao dao;
	private List<Accoppiamenti> miglioriAccoppiamenti;
	private List<Turno> turni;
	private List<Staff> allStaff;
	private List<GiorniOff> giorniOff;
	private List<RichiestaTurni> richiesteTurni;
	private boolean solTrovata;
	private int contatore;
	private int migliorPunteggio;
	private int turniTotaliRichiesti;
	private int turniTotaliDisponibili;
	
	
	
	
	public int getTurniTotaliRichiesti() {
		return turniTotaliRichiesti;
	}

	public int getTurniTotaliDisponibili() {
		return turniTotaliDisponibili;
	}

	public Model() { 				
		if(this.dao== null)
			dao = new Turni24HDao();
		if(this.turni == null) {
			turni = new ArrayList<>();
			turni.addAll(dao.getAllTurni());
		}
		if(this.allStaff == null) {
			allStaff = new ArrayList<>();
			allStaff.addAll(dao.getAllStaff());
		}
		if(this.giorniOff== null)
			giorniOff = new ArrayList<>();
			giorniOff.addAll(dao.getAllGiorniOff());
		
		if(this.richiesteTurni == null) {
			richiesteTurni = new ArrayList<>();
			richiesteTurni.addAll(dao.getAllRichiestaTurniOn());
			richiesteTurni.addAll(dao.getAllRichiestaTurniOff());
			Collections.sort(richiesteTurni, new Comparator<RichiestaTurni>() {  //ordino le richieste per importanza
                public int compare(RichiestaTurni s1, RichiestaTurni s2) {
                    return s2.getPeso() - s1.getPeso();
                }});
		}
		
		this.turniTotaliDisponibili = turniDisp(allStaff);
		this.turniTotaliRichiesti = turniTot(turni);
	}

	public List<Accoppiamenti> cercaLista(int profondita, boolean richiestiOff, boolean richiestiOn, int weekend, int straordinario){	
		
		List<Accoppiamenti> parziale = new ArrayList<>();
		miglioriAccoppiamenti = new ArrayList<>();
		solTrovata = false;
		migliorPunteggio = 1000000000;

	
		if(richiestiOn) {														
			aggiungiTurni(parziale, richiestiOff, weekend, straordinario);		//aggiungo i turni richiesti 
		}

		for(int i = 0; i< profondita ; i++) {
			contatore = 0; 
			Collections.shuffle(turni);  									//ordino casualmete i turni
			cerca(parziale, richiestiOff, weekend, straordinario, turniTotaliRichiesti, migliorPunteggio);	
			System.out.println(i+" = "+ miglioriAccoppiamenti.size()+ " di punteggio: " + this.migliorPunteggio);
		}
		
		System.out.println("BEST = "+ miglioriAccoppiamenti.size() + " di punteggio: " + this.migliorPunteggio);
		
		return miglioriAccoppiamenti;
	}
	
	private void aggiungiTurni(List<Accoppiamenti> parziale,boolean richiestiOff, int weekend, int straordinario){
			for(Turno t : turni) {
				if(turniCompletati(t, parziale)==false) {
					for(RichiestaTurni r : this.richiesteTurni) {
						if(r.isOffDay==false && r.getGiorno() == t.getGiorno() && 
									r.getTipoTurno().compareTo(t.getTipoTurno()) == 0) {
							for(Staff s : allStaff) {
								if(s.getId() == r.getIdStaff()) {
									if(isDisponibile(parziale, s, t, richiestiOff, weekend, straordinario)
											&& turniCompletati(t, parziale)==false) {
										Accoppiamenti a = new Accoppiamenti(s,t);
										parziale.add(a);
										break;
									}
								}
							}
						}
					}
				}
			}
	}

	private void cerca(List<Accoppiamenti> parziale, boolean richiestiOff, int weekend, int straordinario,int turniDisponibili, int punteggio) {
		//cond Terminazione con o suluzione ottima oppure al raggiungimento di un limite di tentatativi con un contatore
		if(solTrovata || contatore == 15000)
			return;
		
		contatore ++;			//ogni iterazione aggiorno il contatore
		
		int p = calcolaPunteggio(parziale);
		
		if(p==0) {					//sol ottima
			this.miglioriAccoppiamenti.clear();
			this.miglioriAccoppiamenti.addAll(parziale); 
			this.solTrovata = true;
			return;
		}
		
		if(p < punteggio) {			//soluzione migliore di quella trovata fin'ora
			this.miglioriAccoppiamenti.clear();
			this.miglioriAccoppiamenti.addAll(parziale); 
			this.migliorPunteggio = p;
			punteggio = p;
			
		}
		
		
		
		for(Turno t : turni) {
			if(turniCompletati(t, parziale)==false) {
				for(Staff s : allStaff) {
					if(isDisponibile(parziale, s, t, richiestiOff, weekend, 0)
							&& turniCompletati(t, parziale)==false) {
						Accoppiamenti a = new Accoppiamenti(s,t);
						parziale.add(a);
						cerca(parziale, richiestiOff, weekend, straordinario, turniDisponibili, punteggio);
						parziale.remove(a);
					}
				}

			}
		}
		if(straordinario !=0){
			for(Turno t : turni) {
				if(turniCompletati(t, parziale)==false) {
					for(Staff s : allStaff) {
						if(isDisponibile(parziale, s, t, richiestiOff, weekend, straordinario)
								&& turniCompletati(t, parziale)==false) {
							Accoppiamenti a = new Accoppiamenti(s,t);
							parziale.add(a);
							cerca(parziale, richiestiOff, weekend, straordinario, turniDisponibili, punteggio);
							parziale.remove(a);
						}
					}
	
				}
			}
		}
	}
	
	private int calcolaPunteggio(List<Accoppiamenti> lista) {
		int punteggio = 0;
		
		for(Turno t : turni) {
			int personaleATurno = 0;
			for(Accoppiamenti a : lista) {
				if(t.equals(a.getTurno())) {
					personaleATurno ++;
				}
			}
			if(t.getRichiesta()>personaleATurno)
				punteggio += (Math.pow(10,(t.getRichiesta()-personaleATurno)) + 10); //se turno non completato
				
		}
		for(Staff s : allStaff) {
			int turniFatti = 0;
			for(Accoppiamenti a : lista) {
				if(s.equals(a.getStaff())) {
					turniFatti ++;
				}
			}
			if(((s.getMinTotalMin() /60) /8) > turniFatti) //se staff fa meno turni del dovuto
				punteggio += (Math.pow(10, (((s.getMinTotalMin() /60)/8)-turniFatti)) + 10);
			if(((s.getMaxTotalMin() /60) /8) < turniFatti) //se staff fa straordinari
				punteggio += Math.pow(10, (turniFatti - ((s.getMaxTotalMin() /60) /8)));
		}
		
		return punteggio;
	}

	private int turniTot(List<Turno> lista) {
		int i = 0;
		for(Turno t : lista) {
			i += t.getRichiesta();
		}
		
		return i;
	}

	private int turniDisp(List<Staff> lista) {
	int i = 0;
	for(Staff s : lista) {
		i += ((s.getMaxTotalMin()/60)/8);
	}
	
	return i;
}

	private boolean turniCompletati(Turno t, List<Accoppiamenti> parziale) {
		int completati = 0;
		boolean b = false;
		for(Accoppiamenti a : parziale) {
			if(a.getTurno().equals(t))
				completati ++;
		}
		if(t.getRichiesta()<=completati)
			b = true;
		
		return b;
	}

	//metodo per vedere se dipendente è disponibile a entrare a far parte del turno
	private boolean isDisponibile(List<Accoppiamenti> parziale, Staff s, Turno t, boolean richiestiOff, int weekend, int straordinari) {
		List<Turno> turniAssegnati = new ArrayList<>();
		for(Accoppiamenti a : parziale) {
			if(a.getStaff().equals(s))
				turniAssegnati.add(a.getTurno());
		}
		
		//supero le ore massime
		int minutiFatti = turniAssegnati.size() * 60 * 8;
	
		if(minutiFatti >= (s.getMaxTotalMin() + (60*8*straordinari))) {
			return false;
		}
		
		//se turno lo stesso giorno non assegno
		for(Turno tt : turniAssegnati) {
			if(tt.getGiorno() == t.getGiorno())
				return false;
		}
		
		//max 5 gg a settimana (5 su 7) // comprende max 5 gg e poi pausa
		int ggPrimaSett = 0;
		for(Turno tt : turniAssegnati) {
			if(tt.getGiorno() == t.getGiorno() - 1 ||
					tt.getGiorno() == t.getGiorno() -2 ||
					tt.getGiorno() == t.getGiorno() -3 ||
					tt.getGiorno() == t.getGiorno() -4 ||
					tt.getGiorno() == t.getGiorno() -5 ||
					tt.getGiorno() == t.getGiorno() -6 ||
					tt.getGiorno() == t.getGiorno() -7 )
				ggPrimaSett ++;
		}
		if(ggPrimaSett==5)
			return false;
		
		//max 2 notti di fila
		if(t.getTipoTurno().compareTo("S")==0 ) {
			int ggPrimaN = 0;
			int ggDopoN = 0;
			int dueggPrimaN = 0;
			int dueggDopoN = 0;
			
			for(Turno tt : turniAssegnati) {
				if(((tt.getGiorno() == (t.getGiorno() - 1) && tt.getTipoTurno().compareTo("S") == 0)))
					ggPrimaN ++;
				if(((tt.getGiorno() == (t.getGiorno() + 1) && tt.getTipoTurno().compareTo("S") == 0)))
					ggDopoN++;
				if(((tt.getGiorno() == (t.getGiorno() - 2) && tt.getTipoTurno().compareTo("S") == 0)))
					dueggPrimaN++;
				if(((tt.getGiorno() == (t.getGiorno() + 2) && tt.getTipoTurno().compareTo("S") == 0)))
					dueggDopoN++;
			}
			if((ggPrimaN == 1 && dueggPrimaN == 1) ||
					(ggPrimaN == 1 && ggDopoN == 1)||
					(ggDopoN ==1 && dueggDopoN == 1))
				return false;
		}

		
		//se notte prima no mattina o pomeriggio
		if(t.getTipoTurno().compareTo("M")==0 || t.getTipoTurno().compareTo("P")==0) {
			for(Turno tt : turniAssegnati) {
				if((tt.getGiorno() == t.getGiorno() - 1 && tt.getTipoTurno().compareTo("S") == 0))
					return false;
			}
		}
		if(t.getTipoTurno().compareTo("S")==0 ) { 				//ggdopo se gia assegnato
			for(Turno tt : turniAssegnati) {
				if((tt.getGiorno() == t.getGiorno() + 1 && tt.getTipoTurno().compareTo("M") == 0))
					return false;
				if((tt.getGiorno() == t.getGiorno() + 1 && tt.getTipoTurno().compareTo("P") == 0))
					return false;
			}
		}
		
		 //se pomeriggio prima no mattina 
		if(t.getTipoTurno().compareTo("M")==0) {
			for(Turno tt : turniAssegnati) {
				if(tt.getGiorno() == t.getGiorno() - 1 && tt.getTipoTurno().compareTo("P") == 0) {
					return false;
				}
			}
		}
		if(t.getTipoTurno().compareTo("P")==0) {
			for(Turno tt : turniAssegnati) {
				if(tt.getGiorno() == t.getGiorno() + 1 && tt.getTipoTurno().compareTo("M") == 0) {
					return false;
				}
			}
		}
		// weekend (quanti giorni di weeekend a riposo si vogliono minimo su 8 gg)
		if(t.isWeekEnd()) {
			int weekendTot = 0;
			for(Turno tt : turniAssegnati) {
				if(tt.isWeekEnd())
					weekendTot ++;
			}
			if(weekendTot >= (8 - weekend)) {						
				return false;
			}
		}
		
		//Rispetto giorni off
		for(GiorniOff gg : this.giorniOff) {
			if(gg.getIdStaff()==s.getId() && gg.getGiorno() == t.getGiorno())
				return false;
		}
		//Rispetto richieste 
		if(richiestiOff) {
			for(RichiestaTurni r : this.richiesteTurni) {
				if(r.isOffDay() && r.getIdStaff()==s.getId() 
						&& r.getGiorno() == t.getGiorno() && 
						r.getTipoTurno().compareTo(t.getTipoTurno())==0) {
					return false;
				}
			}
		}
		
		//Non supero 1/3 dei turni in notti
		if(t.getTipoTurno().compareTo("S")==0) {
			int sere = 0;
			for(Turno tt : turniAssegnati) {
				if(tt.getTipoTurno().compareTo("S") == 0)
					sere ++;
			}
			if((sere* 60 * 8) >= ((s.getMaxTotalMin() + (60*8*straordinari))/3))
				return false;
		}
		//Non supero 1/2 dei turni in mattina
		if(t.getTipoTurno().compareTo("M")==0) {
			int mattine = 0;
			for(Turno tt : turniAssegnati) {
				if(tt.getTipoTurno().compareTo("M") == 0)
					mattine ++;
			}
			if((mattine* 60 * 8) >= ((s.getMaxTotalMin() + (60*8*straordinari))/2))
				return false;
		}
		//Non supero 1/2 dei turni in pomeriggio
		if(t.getTipoTurno().compareTo("P")==0) {
			int pomeriggi = 0;
			for(Turno tt : turniAssegnati) {
				if(tt.getTipoTurno().compareTo("P") == 0)
					pomeriggi ++;
			}
			if((pomeriggi* 60 * 8) >= ((s.getMaxTotalMin() + (60*8*straordinari))/2))
				return false;
		}
		
		return true;
		
	}
	
	//vede quali turni sono stati assegnati a settimana per dipendente (utile per la classe controller)
	public List<TurniAssegnatiSettimanali> generaListaSettimana(int i){
		String gg1; 	//inizializzati a riposo
		String gg2;
		String gg3;
		String gg4;
		String gg5;
		String gg6;
		String gg7;
		
		List<TurniAssegnatiSettimanali> turniAss = new ArrayList<>();
		
		
		if(i == 1) {
			for(Staff s :allStaff) {
				gg1 = "R";
				gg2 = "R";
				gg3 = "R";
				gg4 = "R";
				gg5 = "R";
				gg6 = "R";
				gg7 = "R";
				for(Accoppiamenti a : miglioriAccoppiamenti) {
					if(a.getStaff().equals(s) && a.getTurno().getIdTurno() < 21) {
						if(a.getTurno().getGiorno() == 0)
							gg1 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 1)
							gg2 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 2)
							gg3 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 3)
							gg4 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 4)
							gg5 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 5)
							gg6 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 6)
							gg7 = a.getTurno().getTipoTurno();
					}
				}
				TurniAssegnatiSettimanali tas = new TurniAssegnatiSettimanali(s, gg1, gg2, gg3, gg4, gg5, gg6, gg7);
				turniAss.add(tas);
			}
		}
		if(i == 2) {
			for(Staff s :allStaff) {
				gg1 = "R";
				gg2 = "R";
				gg3 = "R";
				gg4 = "R";
				gg5 = "R";
				gg6 = "R";
				gg7 = "R";
				for(Accoppiamenti a : miglioriAccoppiamenti) {
					if(a.getStaff().equals(s) && a.getTurno().getIdTurno() >= 21 && a.getTurno().getIdTurno() < 42) {
						if(a.getTurno().getGiorno() == 7)
							gg1 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 8)
							gg2 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 9)
							gg3 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 10)
							gg4 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 11)
							gg5 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 12)
							gg6 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 13)
							gg7 = a.getTurno().getTipoTurno();
					}
				}
				TurniAssegnatiSettimanali tas = new TurniAssegnatiSettimanali(s, gg1, gg2, gg3, gg4, gg5, gg6, gg7);
				turniAss.add(tas);
			}
		}
		if(i == 3) {
			for(Staff s :allStaff) {
				gg1 = "R";
				gg2 = "R";
				gg3 = "R";
				gg4 = "R";
				gg5 = "R";
				gg6 = "R";
				gg7 = "R";
				for(Accoppiamenti a : miglioriAccoppiamenti) {
					if(a.getStaff().equals(s) && a.getTurno().getIdTurno() >= 42 && a.getTurno().getIdTurno() < 63) {
						if(a.getTurno().getGiorno() == 14)
							gg1 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 15)
							gg2 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 16)
							gg3 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 17)
							gg4 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 18)
							gg5 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 19)
							gg6 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 20)
							gg7 = a.getTurno().getTipoTurno();
					}
				}
				TurniAssegnatiSettimanali tas = new TurniAssegnatiSettimanali(s, gg1, gg2, gg3, gg4, gg5, gg6, gg7);
				turniAss.add(tas);
			}
		}
		if(i == 4) {
			for(Staff s :allStaff) {
				gg1 = "R";
				gg2 = "R";
				gg3 = "R";
				gg4 = "R";
				gg5 = "R";
				gg6 = "R";
				gg7 = "R";
				for(Accoppiamenti a : miglioriAccoppiamenti) {
					if(a.getStaff().equals(s) && a.getTurno().getIdTurno() >= 63) {
						if(a.getTurno().getGiorno() == 21)
							gg1 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 22)
							gg2 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 23)
							gg3 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 24)
							gg4 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 25)
							gg5 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 26)
							gg6 = a.getTurno().getTipoTurno();
						if(a.getTurno().getGiorno() == 27)
							gg7 = a.getTurno().getTipoTurno();
					}
				}
				TurniAssegnatiSettimanali tas = new TurniAssegnatiSettimanali(s, gg1, gg2, gg3, gg4, gg5, gg6, gg7);
				turniAss.add(tas);
			}
		}
		return turniAss;
	}

	public List<Staff> getAllStaff() {
		return allStaff;
	}
	
	

	
	
	
}

