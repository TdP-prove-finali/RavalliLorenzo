package it.polito.Turni24H.model;

import java.util.Objects;

public class RichiestaTurni {
	int idStaff;
	int giorno;
	String tipoTurno;
	int peso;
	boolean isOffDay; //true se richiesto giorno OFF, false se richiesto turno ON
	
	public RichiestaTurni(int idStaff, int giorno, String tipoTurno, int peso, boolean isOffDay) {
		super();
		this.idStaff = idStaff;
		this.giorno = giorno;
		this.tipoTurno = tipoTurno;
		this.peso = peso;
		this.isOffDay = isOffDay;
	}

	public int getIdStaff() {
		return idStaff;
	}

	public void setIdStaff(int idStaff) {
		this.idStaff = idStaff;
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

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public boolean isOffDay() {
		return isOffDay;
	}

	public void setOffDay(boolean isOffDay) {
		this.isOffDay = isOffDay;
	}

	@Override
	public int hashCode() {
		return Objects.hash(giorno, idStaff, isOffDay, peso, tipoTurno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichiestaTurni other = (RichiestaTurni) obj;
		return giorno == other.giorno && idStaff == other.idStaff && isOffDay == other.isOffDay && peso == other.peso
				&& Objects.equals(tipoTurno, other.tipoTurno);
	}
	
}
