package it.polito.Turni24H.model;

import java.util.Objects;

public class Turno {
	int idTurno;
	int giorno;
	String tipoTurno;
	int richiesta;
	
	@Override
	public int hashCode() {
		return Objects.hash(giorno, idTurno, richiesta, tipoTurno);
	}
	@Override
	public String toString() {
		return giorno + " " + tipoTurno + ", richiesta:" + richiesta ;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turno other = (Turno) obj;
		return giorno == other.giorno && idTurno == other.idTurno && richiesta == other.richiesta
				&& Objects.equals(tipoTurno, other.tipoTurno);
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	public String getTipoTurno() {
		return tipoTurno;
	}

	public void setTipoTurno(String tipoTurno) {
		this.tipoTurno = tipoTurno;
	}

	public int getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(int richiesta) {
		this.richiesta = richiesta;
	}


	public Turno(int idTurno, int giorno, String tipoTurno, int richiesta) {
		super();
		this.idTurno = idTurno;
		this.giorno = giorno;
		this.tipoTurno = tipoTurno;
		this.richiesta = richiesta;
	}
	
	public boolean isWeekEnd() {
		boolean b = false;
		int k = this.giorno;
		if(k == 5 || k == 6 ||
			k == 12 || k == 13 ||
			k == 19 || k == 20 ||
			k == 26 || k == 27) {
			b = true;
		}
		
		return b;
	}

	public String toStringCompleto() {
		if(this.tipoTurno.compareTo("M") == 0)
			return "Giorno " + (this.giorno + 1) + " mattina, di richiesta " + this.richiesta;
		if(this.tipoTurno.compareTo("P") == 0)
			return "Giorno " + (this.giorno + 1) + " pomeriggio, di richiesta " + this.richiesta;
		else
			return "Giorno " + (this.giorno + 1) + " sera, di richiesta " + this.richiesta;
			
	}
		
}
