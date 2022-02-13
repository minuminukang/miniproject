package reservation.model.vo;

public class Reservation {
	private String reservationNum;
	private String seatNum;
	private String mealSelect;
	private String luggage;
	private String psptNum;
	private String flightId;
	
	public Reservation() {
		super();
	}

	public Reservation(String reservationNum, String seatNum, String mealSelect, String luggage, String psptNum,
			String flightId) {
		super();
		this.reservationNum = reservationNum;
		this.seatNum = seatNum;
		this.mealSelect = mealSelect;
		this.luggage = luggage;
		this.psptNum = psptNum;
		this.flightId = flightId;
	}

	public String getReservationNum() {
		return reservationNum;
	}

	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
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

	public String getPsptNum() {
		return psptNum;
	}

	public void setPsptNum(String psptNum) {
		this.psptNum = psptNum;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	@Override
	public String toString() {
		return "Reservation [reservationNum=" + reservationNum + ", seatNum=" + seatNum + ", mealSelect=" + mealSelect
				+ ", luggage=" + luggage + ", psptNum=" + psptNum + ", flightId=" + flightId + "]";
	}
}


