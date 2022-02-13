package airplane.controller;

import java.util.List;

import airplane.model.vo.Airplane;
import airplane.service.AirplaneService;

public class AirplaneController {

	private AirplaneService as = new AirplaneService();
	
	public List<Airplane> selectAll() {
		return as.selectAll();
	}
	
	public Airplane selectId(String id) {
		return as.selectId(id);
	}
	
	public int insertPayment(Airplane a) {
		return as.insertPayment(a);
	}
	
	public int deleteAirplane(String id) {
		return as.deleteAirplane(id);
	}
}
