package join.vo;

import java.util.Date;

public class Join {
	String reservationNum;
	String flightNum;	
	String airport;	    
	String boardAirport;	
	String arrAirport;	
	Date boardTime;	    
	Date arrTime;	    
	String boardGate;	    
	String arrGate;	    
	String seatNum;		
	String mealSelect; 	 	
	String luggage;
	
	public Join() {
		super();
	}

	public Join(String reservationNum, String flightNum, String airport, String boardAirport,
			String arrAirport, Date boardTime, Date arrTime, String boardGate, String arrGate, String seatNum,
			String mealSelect, String luggage) {
		super();
		this.reservationNum = reservationNum;
		this.flightNum = flightNum;
		this.airport = airport;
		this.boardAirport = boardAirport;
		this.arrAirport = arrAirport;
		this.boardTime = boardTime;
		this.arrTime = arrTime;
		this.boardGate = boardGate;
		this.arrGate = arrGate;
		this.seatNum = seatNum;
		this.mealSelect = mealSelect;
		this.luggage = luggage;
	}

	public String getReservationNum() {
		return reservationNum;
	}

	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
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

	public String getBoardAirport() {
		return boardAirport;
	}

	public void setBoardAirport(String boardAirport) {
		this.boardAirport = boardAirport;
	}

	public String getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}

	public Date getBoardTime() {
		return boardTime;
	}

	public void setBoardTime(Date boardTime) {
		this.boardTime = boardTime;
	}

	public Date getArrTime() {
		return arrTime;
	}

	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}

	public String getBoardGate() {
		return boardGate;
	}

	public void setBoardGate(String boardGate) {
		this.boardGate = boardGate;
	}

	public String getArrGate() {
		return arrGate;
	}

	public void setArrGate(String arrGate) {
		this.arrGate = arrGate;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
	
	public String getMealSelect() {
		return mealSelect;
	}

	public void setMealSelect(String mealSelect) {
		this.mealSelect = mealSelect;
	}

	public String getLuggage() {
		return luggage;
	}

	public void setLuggage(String luggage) {
		this.luggage = luggage;
	}

	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("???????????? : " + reservationNum + "\n");
		sb.append("?????? : " + flightNum + "\n");
		sb.append("????????? : " + airport + "\n");
		sb.append("?????? ?????? : " + boardAirport + "\n");
		sb.append("?????? ?????? : " + arrAirport + "\n");
		sb.append("?????? ?????? : " + boardTime + "\n");
		sb.append("?????? ?????? : " + arrTime + "\n");
		sb.append("?????? ????????? : " + boardGate + "\n");
		sb.append("?????? ????????? : " + arrGate + "\n");
		sb.append("?????? ?????? : " + seatNum + "\n");
		sb.append("????????? ?????? ?????? : " + mealSelect + "\n");
		sb.append("????????? : " + luggage + "\n");
		
		return sb.toString();
	}
}
