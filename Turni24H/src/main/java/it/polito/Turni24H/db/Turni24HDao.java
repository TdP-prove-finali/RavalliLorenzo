package it.polito.Turni24H.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.Turni24H.model.GiorniOff;
import it.polito.Turni24H.model.RichiestaTurni;
import it.polito.Turni24H.model.Staff;
import it.polito.Turni24H.model.Turno;


public class Turni24HDao {

	public List<Staff> getAllStaff(){
		final String sql = "SELECT * FROM Staff";
		List<Staff> result = new LinkedList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Staff(res.getInt("ID"), res.getString("Name"),res.getInt("MaxTotalMin"),res.getInt("MinTotalMin")));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}
	
	public List<Turno> getAllTurni(){
		final String sql = "SELECT * FROM Turni";
		List<Turno> result = new LinkedList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Turno(res.getInt("ID"), res.getInt("Giorno"),res.getString("TipoTurno"),res.getInt("Richiesta") ));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}
	
	public List<GiorniOff> getAllGiorniOff(){
		final String sql = "SELECT * FROM Giorni_Off";
		List<GiorniOff> result = new LinkedList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new GiorniOff(res.getInt("StaffID"), res.getInt("Giorno")));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}
	
	public List<RichiestaTurni> getAllRichiestaTurniOff(){
		final String sql = "SELECT * FROM Turni_off_richiesta";
		List<RichiestaTurni> result = new LinkedList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new RichiestaTurni(res.getInt("StaffID"), res.getInt("Giorno"),res.getString("TipoTurno"),true, res.getInt("Peso")));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}


	public List<RichiestaTurni> getAllRichiestaTurniOn(){
		final String sql = "SELECT * FROM Turni_on_richiesta";
		List<RichiestaTurni> result = new LinkedList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new RichiestaTurni(res.getInt("StaffID"), res.getInt("Giorno"),res.getString("TipoTurno"),false, res.getInt("Peso")));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}


}