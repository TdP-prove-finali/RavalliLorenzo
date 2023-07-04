package it.polito.Turni24H.db;

import java.sql.Connection;

public class TestDao {

public static void main(String[] args) {
		
		try {
			Connection connection = DBConnect.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			Turni24HDao dao = new Turni24HDao() ;
			
			System.out.println(dao.getAllTurni().size());
			System.out.println(dao.getAllStaff().size());
			System.out.println(dao.getAllGiorniOff().size());
			System.out.println(dao.getAllRichiestaTurniOff().size());
			System.out.println(dao.getAllRichiestaTurniOn().size());
		} catch (Exception e) {
			throw new RuntimeException("Test FAILED", e);
		}
	}


}
