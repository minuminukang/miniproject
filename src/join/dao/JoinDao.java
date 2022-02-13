package join.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import join.vo.Join;
import user.model.vo.User;

public class JoinDao {
	private Connection conn = null;
	private Properties prop = null;

	public JoinDao (Connection conn){
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
	
	/* 리턴타입 리스트로 변경 */
	public List<Join> selectUserReservationPayment(User user) {
		
		List<Join> list = new ArrayList<Join>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = prop.getProperty("Search_reservation"); //유저id순으로
			
//			Search_reservation = SELECT RESERVATION_NUM, FLIGHT_NUM, AIRPORT, 
//					BOARDING_AIRPORT, ARRIVED_AIRPORT, BOARDING_TIME, ARRIVED_TIME, BOARDING_GATE, ARRIVED_GATE, SEAT_NUM, PAYMENT, MEAL_SELECT, LUGGAGE 
//			FROM TABLE_RESERVATION 
//			JOIN TABLE_USER USING(PSPT_NUM) 
//			JOIN TABLE_SCHEDULE USING(FLIGHT_ID) 
//			WHERE PSPT_NUM = ?
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, user.getPsptNum());
			
			rs = pstmt.executeQuery();
			
			while(rs.next() == true) {
				String reservationNum      = rs.getString("RESERVATION_NUM"); 	
				String flightNum	     = rs.getString("FLIGHT_NUM");
				String airport	     = rs.getString("AIRPORT");
				String boardAirport	     = rs.getString("BOARDING_AIRPORT");
				String arrAirport	     = rs.getString("ARRIVED_AIRPORT");
				Date boardTime	     = rs.getDate("BOARDING_TIME");
				Date arrTime	     = rs.getDate("ARRIVED_TIME");
				String boardGate	     = rs.getString("BOARDING_GATE");
				String arrGate 	     = rs.getString("ARRIVED_GATE");
				String seatNum			 = rs.getString("SEAT_NUM");
				String mealSelect 	 	 = rs.getString("MEAL_SELECT");
				String luggage 	 	 = rs.getString("LUGGAGE");
				
				Join j = new Join(reservationNum, flightNum, airport, boardAirport,
						arrAirport, boardTime, arrTime, boardGate, arrGate,
						seatNum, mealSelect, luggage);
				
				list.add(j);				
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
