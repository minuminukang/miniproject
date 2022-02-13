package schedule.model.vo;

import java.sql.Date;

public class Schedule {
	private String flightId;
	private String flightNum;
	private String airport;
	private String boardingAirport;
	private String arrivedAirport;
	private Date boardingTime;
	private Date arrivedTime;
	private String elapseTime;
	private int remainSeatNum; 
	private String boardingGate;
	private String arrivedGate;
	private String airplaneId;
	
	public Schedule() {
		super();
	}

	public Schedule(String flightId, String flightNum, String airport, String boardingAirport, String arrivedAirport,
			Date boardingTime, Date arrivedTime, String elapseTime, int remainSeatNum, String boardingGate, String arrivedGate,
			String airplaneId) {
		super();
		this.flightId = flightId;
		this.flightNum = flightNum;
		this.airport = airport;
		this.boardingAirport = boardingAirport;
		this.arrivedAirport = arrivedAirport;
		this.boardingTime = boardingTime;
		this.arrivedTime = arrivedTime;
		this.elapseTime = elapseTime;
		this.remainSeatNum = remainSeatNum;
		this.boardingGate = boardingGate;
		this.arrivedGate = arrivedGate;
		this.airplaneId = airplaneId;
	}
	
	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	public String getBoardingAirport() {
		return boardingAirport;
	}

	public void setBoardingAirport(String boardingAirport) {
		this.boardingAirport = boardingAirport;
	}

	public String getArrivedAirport() {
		return arrivedAirport;
	}

	public void setArrivedAirport(String arrivedAirport) {
		this.arrivedAirport = arrivedAirport;
	}

	public Date getBoardingTime() {
		return boardingTime;
	}

	public void setBoardingTime(Date boardingTime) {
		this.boardingTime = boardingTime;
	}

	public Date getArrivedTime() {
		return arrivedTime;
	}

	public void setArrivedTime(Date arrivedTime) {
		this.arrivedTime = arrivedTime;
	}
	
	public String getElapseTime() {
		return elapseTime;
	}
	
	public void setElapseTime(String elapseTime) {
		this.elapseTime = elapseTime;
	}

	public int getRemainSeatNum() {
		return remainSeatNum;
	}

	public void setRemainSeatNum(int remainSeatNum) {
		this.remainSeatNum = remainSeatNum;
	}

	public String getBoardingGate() {
		return boardingGate;
	}

	public void setBoardingGate(String boardingGate) {
		this.boardingGate = boardingGate;
	}

	public String getArrivedGate() {
		return arrivedGate;
	}

	public void setArrivedGate(String arrivedGate) {
		this.arrivedGate = arrivedGate;
	}

	public String getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(String airplaneId) {
		this.airplaneId = airplaneId;
	}


	@Override
	public String toString() {
		//StringBuffer sb = new StringBuffer();
				
		return "[ 운항ID = " + flightId + ", 편명 = " + flightNum + ", 항공사 = " + airport
				+ ", 출발공항 = " + boardingAirport + ", 도착공항 = " + arrivedAirport + ", 출발시간 = "
				+ boardingTime + ", 도착시간 = " + arrivedTime + ", 소요시간 = " + elapseTime + ", 잔여좌석 수 = " + remainSeatNum + ", 출발게이트 ="
				+ boardingGate + ", 도착게이트 = " + arrivedGate + ", 비행기ID = " + airplaneId + " ]";
	}
}
