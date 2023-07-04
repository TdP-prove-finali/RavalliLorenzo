package it.polito.Turni24H.model;

import java.util.Objects;

public class Staff {
	int idStaff;
	String name;
	int maxTotalMin;
	int minTotalMin;
	
	public int getId() {
		return idStaff;
	}

	public void setId(int id) {
		this.idStaff = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaxTotalMin() {
		return maxTotalMin;
	}

	public void setMaxTotalMin(Integer maxTotalMin) {
		this.maxTotalMin = maxTotalMin;
	}

	public Integer getMinTotalMin() {
		return minTotalMin;
	}

	public void setMinTotalMin(Integer minTotalMin) {
		this.minTotalMin = minTotalMin;
	}

	public Staff(int id, String name, Integer maxTotalMin, Integer minTotalMin) {
		super();
		this.idStaff = id;
		this.name = name;
		this.maxTotalMin = maxTotalMin;
		this.minTotalMin = minTotalMin;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idStaff, maxTotalMin, minTotalMin, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		return Objects.equals(idStaff, other.idStaff) && Objects.equals(maxTotalMin, other.maxTotalMin)
				&& Objects.equals(minTotalMin, other.minTotalMin) && Objects.equals(name, other.name);
	}
	
}
