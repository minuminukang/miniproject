package schedule.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import schedule.model.vo.Schedule;

public class ScheduleDao {

	private Connection conn = null;
	private Properties prop = null;

	public ScheduleDao(Connection conn) {
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

	public List<Schedule> selectAll() {
		List <Schedule> list = new ArrayList<Schedule>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String sql = prop.getProperty("ScheduleDao_selectAll");
			//SELECT * FROM TABLE_SCHEDULE

			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();

			while(rs.next() == true) {

				String flightId		= rs.getString("FLIGHT_ID");       
				String flightNum	= rs.getString("FLIGHT_NUM");      
				String airport		= rs.getString("AIRPORT");        
				String boardingAirport = rs.getString("BOARDING_AIRPORT");
				String arrivedAirport = rs.getString("ARRIVED_AIRPORT"); 
				Date boardingTime	= rs.getDate("BOARDING_TIME");     
				Date arrivedTime = rs.getDate("ARRIVED_TIME");     
				String elapseTime = rs.getString("ELAPSE_TIME");
				int remainSeatNum = rs.getInt("REMAIN_SEAT_NUM");     
				String boardingGate = rs.getString("BOARDING_GATE");   
				String arrivedGate = rs.getString("ARRIVED_GATE");    
				String airplaneId = rs.getString("AIRPLANE_ID");     

				Schedule sch = new Schedule(flightId, flightNum, airport, boardingAirport, arrivedAirport,
						boardingTime, arrivedTime, elapseTime, remainSeatNum, boardingGate, arrivedGate,
						airplaneId);
				list.add(sch);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	public Schedule selectFlightId(String searchId) {
		Schedule s = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = prop.getProperty("ScheduleDao_selectFlightId");
			// SELECT * FROM TABLE_SCHEDULE WHERE FLIGHT_ID = ?
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchId);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			String flightId = rs.getString("FLIGHT_ID");       
			String flightNum = rs.getString("FLIGHT_NUM");      
			String airport = rs.getString("AIRPORT");        
			String boardingAirport = rs.getString("BOARDING_AIRPORT");
			String arrivedAirport = rs.getString("ARRIVED_AIRPORT"); 
			Date boardingTime = rs.getDate("BOARDING_TIME");     
			Date arrivedTime = rs.getDate("ARRIVED_TIME");      
			String elapseTime = rs.getString("ELAPSE_TIME");     
			int remainSeatNum = rs.getInt("REMAIN_SEAT_NUM");     
			String boardingGate = rs.getString("BOARDING_GATE");   
			String arrivedGate = rs.getString("ARRIVED_GATE");    
			String airplaneId = rs.getString("AIRPLANE_ID");     
			
			s = new Schedule(flightId, flightNum, airport, boardingAirport, arrivedAirport, 
					boardingTime, arrivedTime, elapseTime, remainSeatNum, boardingGate, 
					arrivedGate, airplaneId);
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return s;
	}

	/* ?????????, ?????????, ????????? ??????*/
	public List<Schedule> selectDeparture(String searchBoarding, String searchArrived, String boardingDate) {
		List<Schedule> list = new ArrayList<Schedule>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			/*
			 * SELECT * FROM TABLE_SCHEDULE 
				WHERE (BOARDING_AIRPORT = '?') 
				AND (ARRIVED_AIRPORT = '?') --;
				AND BOARDING_TIME BETWEEN TO_DATE('2020-07-07', 'YYYY-MM-DD') AND TO_DATE('2020-12-17', 'YYYY-MM-DD');
			 */
								
			String sql = prop.getProperty("ScheduleDao_selectDeparture");
			pstmt = conn.prepareStatement(sql);
			
			String nextday = nextDay(boardingDate);
						
			pstmt.setString(1, searchBoarding);
			pstmt.setString(2, searchArrived);
			pstmt.setString(3, boardingDate);
			pstmt.setString(4, nextday);
			/*
			 * date??? = (equal) ???????????? ?????? ????????? between?????? ????????? ????????????????????????
			 * between [date] and [date] ?????? ?????? date??? ????????? ??? ????????? ????????????..(2021-10-11, 2021-10-11)
			 * ???????????? ?????? ?????? ???????????? [String ?????????] ??? ????????????????????????
			 */
			
			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				
				String flightId		= rs.getString("FLIGHT_ID");       
				String flightNum	= rs.getString("FLIGHT_NUM");      
				String airport		= rs.getString("AIRPORT");        
				String boardingAirport = rs.getString("BOARDING_AIRPORT");
				String arrivedAirport = rs.getString("ARRIVED_AIRPORT"); 
				Date boardingTime	= rs.getDate("BOARDING_TIME");     
				Date arrivedTime = rs.getDate("ARRIVED_TIME"); 
				String elapseTime = rs.getString("ELAPSE_TIME");
				int remainSeatNum = rs.getInt("REMAIN_SEAT_NUM");     
				String boardingGate = rs.getString("BOARDING_GATE");   
				String arrivedGate = rs.getString("ARRIVED_GATE");    
				String airplaneId = rs.getString("AIRPLANE_ID");     
		
				Schedule sch = new Schedule(flightId, flightNum, airport, boardingAirport, arrivedAirport,
						boardingTime, arrivedTime, elapseTime, remainSeatNum, boardingGate, arrivedGate,
						airplaneId);
				
				list.add(sch);
			}
			
			rs.close();
			pstmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private String nextDay(String boardingDate) {
		
		String nextDay = "";
		String[] str = boardingDate.split("-");
		//str[0] = year, str[1] = month, str[2] = day
		
		// 0000-12-31??????
		if(str[1].equals("12") && str[2].equals("31")) {
			int yearInt = Integer.parseInt(str[0]) + 1; // ???????????? ??????
			str[0] = Integer.toString(yearInt);
			str[1] = "01";
			str[2] = "01";
			nextDay = str[0] + "-" + str[1] + "-" + str[2];
			return nextDay; // 0001-01-01 return ????????? ??????
		}
		
		//String[] checkMonth31 = {"01", "03", "05", "07", "08", "10"}; // 31??? ??????
		String[] checkMonth30 = {"04", "06", "09", "11"}; // 30??? ??????
		String checkMonth2 = "02";
		
		if(str[2].equals("30")) { // 30??????, ?????? ????????? ??????????????????
			for(int i = 0; i < checkMonth30.length; i++) {
				if(checkMonth30[i].equals(str[1])) {
					int monthInt = Integer.parseInt(str[1]) + 1;// ???????????? ??????
					str[1] = Integer.toString(monthInt);
					str[2] = "01";
					nextDay = str[0] + "-" + str[1] + "-" + str[2];
					return nextDay; // 0000-00(+1)-01 return ????????? ??????
				}
			}
		}
		
		else if(str[2].equals("31")) { // 31??????, ????????? ?????? ???????????????
			int monthInt = Integer.parseInt(str[1]) + 1; // ???????????? ??????
			str[1] = Integer.toString(monthInt);
			str[2] = "01";
			nextDay = str[0] + "-" + str[1] + "-" + str[2];
			return nextDay; // 0000-00(+1)-01 return ????????? ??????
		}
		
		else if(str[2].equals("28") || str[2].equals("29")) { // 2?????? ????????? ????????? ??????
			if(str[1].equals(checkMonth2)) {
				str[1] = "03";
				str[2] = "01";
				nextDay = str[0] + "-" + str[1] + "-" + str[2];
				return nextDay; // 0000-03-01 return ????????? ??????
			}
		}
		
		// ?????? ????????? ??? ?????? ?????? ???
		int monthInt = Integer.parseInt(str[1]) + 1; // ???????????? ??????
		int dayInt = Integer.parseInt(str[2]) + 1; // ???????????? ??????
		
		str[1] = Integer.toString(monthInt);
		str[2] = Integer.toString(dayInt);;
		
		nextDay = str[0] + "-" + str[1] + "-" + str[2];
		
		return nextDay;
	}
	
	public int insertSchedule(Schedule s) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("ScheduleDao_insertSchedule");
			//INSERT INTO TABLE_SCHEDULE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, s.getFlightId());
			pstmt.setString(2, s.getFlightNum());
			pstmt.setString(3, s.getAirport());
			pstmt.setString(4, s.getBoardingAirport());
			pstmt.setString(5, s.getArrivedAirport());
			pstmt.setDate(6, s.getBoardingTime());
			pstmt.setDate(7, s.getArrivedTime());
			pstmt.setString(8, s.getElapseTime());
			pstmt.setInt(9, s.getRemainSeatNum());
			pstmt.setString(10, s.getBoardingGate());
			pstmt.setString(11, s.getArrivedGate());
			pstmt.setString(12, s.getAirplaneId());

			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*????????? ????????? ??? ?????? ?????? -1*/
	public int updateRemainSeat(String flightId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		try {
			String sql = prop.getProperty("ScheduleDao_updateRemainSeat");
			// UPDATE TABLE_SCHEDULE SET REMAIN_SEAT_NUM = ? WHERE FLIGHT_ID = ?
			
			pstmt = conn.prepareStatement(sql);
			
			Schedule s = selectFlightId(flightId);
			
			pstmt.setInt(1, s.getRemainSeatNum()-1);
			pstmt.setString(2, s.getFlightId());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	// ????????? ???????????? list???
	public List<Schedule> selectEvent(String gaum) {
		List <Schedule> list = new ArrayList<Schedule>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = prop.getProperty("ScheduleDao_selectEvent");
			//SELECT * FROM TABLE_SCHEDULE WHERE (ARRIVED_AIRPORT = ?)
			
			//System.out.println("sql : " + sql);
			
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1, gaum);
			
			rs = pstmt.executeQuery();

			while(rs.next() == true) {
				//System.out.println("@@@@@@@@@@");
				String flightId		= rs.getString("FLIGHT_ID");       
				String flightNum	= rs.getString("FLIGHT_NUM");      
				String airport		= rs.getString("AIRPORT");        
				String boardingAirport = rs.getString("BOARDING_AIRPORT");
				String arrivedAirport = rs.getString("ARRIVED_AIRPORT"); 
				Date boardingTime	= rs.getDate("BOARDING_TIME");     
				Date arrivedTime = rs.getDate("ARRIVED_TIME");     
				String elapseTime = rs.getString("ELAPSE_TIME");
				int remainSeatNum = rs.getInt("REMAIN_SEAT_NUM");     
				String boardingGate = rs.getString("BOARDING_GATE");   
				String arrivedGate = rs.getString("ARRIVED_GATE");    
				String airplaneId = rs.getString("AIRPLANE_ID");     

				Schedule sch = new Schedule(flightId, flightNum, airport, boardingAirport, arrivedAirport,
						boardingTime, arrivedTime, elapseTime, remainSeatNum, boardingGate, arrivedGate,
						airplaneId);
				
				//System.out.println(sch);
				
				list.add(sch);
			}
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list; 
	}
	
}
