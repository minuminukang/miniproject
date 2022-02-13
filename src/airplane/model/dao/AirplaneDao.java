package airplane.model.dao;

import java.io.FileReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import airplane.model.vo.Airplane;

public class AirplaneDao {
	private Connection conn = null;
	private Properties prop = null;

	public AirplaneDao (Connection conn){
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


	// SELECT ALL
	public List<Airplane> selectAll() {
		List <Airplane> list = new ArrayList<Airplane>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = prop.getProperty("AirplaneDao_selectAll");
			// SELECT * FROM TABLE_AIRPLANE_INFO

			pstmt = conn.prepareStatement(sql); // 객체 생성
			rs = pstmt.executeQuery(); // 쿼리 실행문

			while(rs.next() == true) {

				String airplaneId   = rs.getString("AIRPLANE_ID");
				String model        = rs.getString("MODEL");
				String maxSeats     = rs.getString("MAX_SEATS");
				String cockpitNum   = rs.getString("COCKPIT_NUM");
				String passengerNum = rs.getString("PASSENGER_NUM");
				String manufacturer = rs.getString("MANUFACTURER");


				Airplane airplane = new Airplane(airplaneId, model, maxSeats,
						cockpitNum, passengerNum, manufacturer);
				// System.out.println(airplane.toString());
				list.add(airplane);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}

	// SELECT ONE
	public Airplane selectId(String id) {
		Airplane airplane = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = prop.getProperty("AirplaneDao_selectId");
			// SELECT * FROM TABLE_AIRPLANE_INFO WHERE AIRPLANE_ID = ?
						
			pstmt = conn.prepareStatement(sql); 

			pstmt.setString(1, id); 

			rs = pstmt.executeQuery();
			rs.next();

			String airplaneId   = rs.getString("AIRPLANE_ID");
			String model        = rs.getString("MODEL");
			String manufacturer = rs.getString("MANUFACTURER");
			String maxSeats     = rs.getString("MAX_SEATS");
			String cockpitNum   = rs.getString("COCKPIT_NUM");
			String passengerNum = rs.getString("PASSENGER_NUM");

			airplane = new Airplane(airplaneId, model, maxSeats, cockpitNum, passengerNum, manufacturer);
			
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return airplane;

	}

	// INSERT
	public int insertAirplane(Airplane airplane) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = prop.getProperty("AirplaneDao_insertAirplane");
			//INSERT INTO TABLE_AIRPLANE_INFO VALUES (?, ?, ?, ?, ?, ?)
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, airplane.getAirplaneId());
			pstmt.setString(2, airplane.getModel());
			pstmt.setString(3, airplane.getMaxSeats());
			pstmt.setString(4, airplane.getCockpitNum());
			pstmt.setString(5, airplane.getPassengerNum());
			pstmt.setString(6, airplane.getManufacturer());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; 
	}

	// DELETE
	public int deleteAirplane(String id) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = prop.getProperty("AirplaneDao_deleteAirplane");
			// DELETE FROM TABLE_AIRPLANE_INFO WHERE AIRPLANE_ID = ?
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
}
