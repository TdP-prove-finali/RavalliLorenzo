package it.polito.Turni24H.model;

import java.util.Objects;

public class GiorniOff {
	int idStaff;
	int Giorno;
	
	public int getIdStaff() {
		return idStaff;
	}
	public void setIdStaff(int idStaff) {
		this.idStaff = idStaff;
	}
	public int getGiorno() {
		return Giorno;
	}
	public void setGiorno(int giorno) {
		Giorno = giorno;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Giorno, idStaff);
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
		return Giorno == other.Giorno && idStaff == other.idStaff;
	}
	public GiorniOff(int idStaff, int giorno) {
		super();
		this.idStaff = idStaff;
		Giorno = giorno;
	}
	
	

}
