package reservation.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import reservation.model.vo.Reservation;
import user.model.vo.User;

public class ReservationDao {
	private Connection conn = null;
	private Properties prop = null;

	public ReservationDao (Connection conn){
		this.conn = conn;
		prop = new Properties();
		FileReader fr;
		FileReader fr2;
		try {
			fr = new FileReader("./resources/data-source.properties");
			fr2 = new FileReader("./resources/query.properties");
			prop.load(fr);
			prop.load(fr2);
			fr.close();
			fr2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public List<Reservation> selectAll() {
		List<Reservation> list = new ArrayList<Reservation>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			String sql = prop.getProperty("ReservationDao_selectAll");
			// SELECT * FROM TABLE_RESERVATION
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String reservationNum 	= rs.getString("RESERVATION_NUM"); 
				String seatNum			= rs.getString("SEAT_NUM");        
				String mealSelect		= rs.getString("MEAL_SELECT");     
				String luggage			= rs.getString("LUGGAGE");        
				String psptNum			= rs.getString("PSPT_NUM");        
				String flightId			= rs.getString("FLIGHT_ID");
				
				Reservation r = new Reservation(reservationNum, seatNum, mealSelect,
									luggage, psptNum, flightId);
				
				list.add(r);
			}
			rs.close();
			pstmt.close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public Reservation selectReservationNum(String num) {
		Reservation r = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("ReservationDao_selectRNum");
			// SELECT * FROM TABLE_RESERVATION WHERE RESERVATION_NUM = ?
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			String reservationNum 	= rs.getString("RESERVATION_NUM");
			String seatNum			= rs.getString("SEAT_NUM");       
			String mealSelect		= rs.getString("MEAL_SELECT");    
			String luggage			= rs.getString("LUGGAGE");        
			String psptNum			= rs.getString("PSPT_NUM");       
			String flightId			= rs.getString("FLIGHT_ID");      
			
			r = new Reservation(reservationNum, seatNum, mealSelect,
					luggage, psptNum, flightId);
			
			rs.close();
			pstmt.close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public int insert(Reservation r) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("ReservationDao_insert");
			// INSERT INTO TABLE_RESERVATION VALUES (?,?,?,?,?,?)
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReservationNum());
			pstmt.setString(2, r.getSeatNum()); 
			pstmt.setString(3, r.getMealSelect()); 
			pstmt.setString(4, r.getLuggage()); 
			pstmt.setString(5, r.getPsptNum()); 
			pstmt.setString(6, r.getFlightId());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 여권번호 수정을 위해서 
	public List<String> searchReservationNum(String pspt) {
		List<String> list = new ArrayList<String>();
				
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("ReservationDao_searchReservationNum");
			// SELECT RESERVATION_NUM FROM TABLE_RESERVATION WHERE PSPT_NUM = ?
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pspt);
			
			rs = pstmt.executeQuery();
			while(rs.next() == true) {
				String reservationNum 	= rs.getString("RESERVATION_NUM"); 
				
				list.add(reservationNum);
			}
			
			rs.close();
			pstmt.close();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
 	public int updatePSPT(String reNum, String pspt) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("ReservationDao_updatePSPT");
			// UPDATE TABLE_RESERVATION SET PSPT_NUM = ? WHERE RESERVATION_NUM = ?
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pspt);
			pstmt.setString(2, reNum);
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(String searchNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("ReservationDao_delete");
			// DELETE FROM TABLE_RESERVATION WHERE RESERVATION_NUM = ?

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchNum);
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
}
