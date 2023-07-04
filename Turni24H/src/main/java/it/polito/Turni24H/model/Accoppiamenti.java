package it.polito.Turni24H.model;

import java.util.Objects;

public class Accoppiamenti {
	Staff staff;
	Turno turno;

	public Accoppiamenti(Staff staff, Turno turno) {
		super();
		this.staff = staff;
		this.turno = turno;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	@Override
	public int hashCode() {
		return Objects.hash(staff, turno);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accoppiamenti other = (Accoppiamenti) obj;
		return Objects.equals(staff, other.staff) && Objects.equals(turno, other.turno);
	}

	
	
	

}
