package airplane.model.vo;

public class Airplane {
	private String airplaneId;
	private String model;
	private String manufacturer;
	private String maxSeats;
	private String cockpitNum;
	private String passengerNum;
	
	public Airplane() {
		super();
	}

	public Airplane(String airplaneId, String model, String manufacturer, String maxSeats, String cockpitNum, String passengerNum) {
		super();
		this.airplaneId = airplaneId;
		this.model = model;
		this.maxSeats = maxSeats;
		this.cockpitNum = cockpitNum;
		this.passengerNum = passengerNum;
		this.manufacturer = manufacturer;
	}

	public String getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(String airplaneId) {
		this.airplaneId = airplaneId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(String maxSeats) {
		this.maxSeats = maxSeats;
	}

	public String getCockpitNum() {
		return cockpitNum;
	}

	public void setCockpitNum(String cockpitNum) {
		this.cockpitNum = cockpitNum;
	}

	public String getPassengerNum() {
		return passengerNum;
	}

	public void setPassengerNum(String passengerNum) {
		this.passengerNum = passengerNum;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("--------비행기 정보--------\n");
		sb.append("비행기 Id : " + airplaneId + "\n");
		sb.append("모델명 : " + model + "\n");
		sb.append("제조사 : " + manufacturer + "\n");
		sb.append("최대탑승인원 : " + maxSeats + "\n");
		sb.append("조종석 수 : "	+ cockpitNum + "\n");
		sb.append("승객 수 : " + passengerNum + "\n");
		
		return sb.toString();
	}
}
