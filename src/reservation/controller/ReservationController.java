package reservation.controller;

import static common.JDBCTemplate.*;

import java.util.List;

import reservation.model.vo.Reservation;
import reservation.service.ReservationService;

public class ReservationController {
	private ReservationService reservationService = new ReservationService();
	
	public List<Reservation> selectAll() {
		return reservationService.selectAll();
	}
	
	public Reservation selectReservationNum(String num) {
		return reservationService.selectReservationNum(num);
	}
	
	public List<String> searchReservationNum(String pspt) {
		return reservationService.searchReservationNum(pspt);
	}

	public int updatePSPT(String reNum, String pspt) {
		return reservationService.updatePSPT(reNum, pspt);
	}
	
	public int insertReservation(Reservation r) {
		return reservationService.insertReservation(r);
	}
	
	public int deletReservation(String searchNum) {
		return reservationService.deletReservation(searchNum);
	}

}
