package schedule.controller;

import static common.JDBCTemplate.*;

import java.util.List;
import java.util.Map;

import schedule.service.ScheduleService;
import schedule.model.vo.Schedule;


public class ScheduleController {
	private ScheduleService scheduleService = new ScheduleService();
	
	public Map<Integer, Schedule> searchTicket(String boardingAirport, String arrivedAirport, String boardingDate) {
		return scheduleService.searchTicket(boardingAirport, arrivedAirport, boardingDate);
	}
	
	public List<Schedule> selectAll() {
		return scheduleService.selectAll();
	}
	
	public Schedule selectFlightId(String searchId) {
		return scheduleService.selectFlightId(searchId);
	}
	
	public List<Schedule> selectDeparture(String searchBoarding, String searchArrived, String boardingDate) {
		return scheduleService.selectDeparture(searchBoarding, searchArrived, boardingDate);
	}
	
	public int updateRemainSeat(String flightId) {
		return scheduleService.updateRemainSeat(flightId);
	}
	
	public List<Schedule> selectEvent(String guam) {
		return scheduleService.selectEvent(guam);
	}
	
}
