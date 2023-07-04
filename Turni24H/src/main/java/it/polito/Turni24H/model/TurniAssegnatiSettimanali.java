package it.polito.Turni24H.model;

public class TurniAssegnatiSettimanali {
	private Staff s;
	private String gg1;
	private String gg2;
	private String gg3;
	private String gg4;
	private String gg5;
	private String gg6;
	private String gg7;
	
	
	public TurniAssegnatiSettimanali(Staff s, String gg1, String gg2, String gg3, String gg4, String gg5, String gg6,
			String gg7) {
		super();
		this.s = s;
		this.gg1 = gg1;
		this.gg2 = gg2;
		this.gg3 = gg3;
		this.gg4 = gg4;
		this.gg5 = gg5;
		this.gg6 = gg6;
		this.gg7 = gg7;
	}


	public Staff getS() {
		return s;
	}


	public String getGg1() {
		return gg1;
	}


	public String getGg2() {
		return gg2;
	}


	public String getGg3() {
		return gg3;
	}


	public String getGg4() {
		return gg4;
	}


	public String getGg5() {
		return gg5;
	}


	public String getGg6() {
		return gg6;
	}


	public String getGg7() {
		return gg7;
	}
	
	
}
