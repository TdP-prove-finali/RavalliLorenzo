package it.polito.Turni24H.model;

import java.util.Objects;

public class GiorniOff {
	int idStaff;
	int giorno;
	
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
	@Override
	public int hashCode() {
		return Objects.hash(giorno, idStaff);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiorniOff other = (GiorniOff) obj;
		return giorno == other.giorno && idStaff == other.idStaff;
	}
	public GiorniOff(int idStaff, int giorno) {
		super();
		this.idStaff = idStaff;
		this.giorno = giorno;
	}
	
	

}
