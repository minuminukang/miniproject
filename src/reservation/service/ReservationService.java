package reservation.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import reservation.dao.ReservationDao;
import reservation.model.vo.Reservation;

public class ReservationService {
	private ReservationDao reservationDao = null;
	private Connection conn = null;
	
	public ReservationService() {
		init();
	}

	public void init() {
		if(conn != null) {
			close(conn);
		}
		conn = getConnection();
		reservationDao = new ReservationDao(conn);
	}

	@Override
	protected void finalize() throws Throwable {
		close(conn);
	}
	
	public List<Reservation> selectAll() {
		reservationDao.setConn(conn);
		return reservationDao.selectAll();
	}
	
	public Reservation selectReservationNum(String num) {
		return reservationDao.selectReservationNum(num);
	}
	
	public List<String> searchReservationNum(String pspt) {
		return reservationDao.searchReservationNum(pspt);
	}
	
	public int updatePSPT(String reNum, String pspt) {
		return reservationDao.updatePSPT(reNum, pspt);
	}
	
	public int insertReservation(Reservation r) {
		
		int result = reservationDao.insert(r);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;		
	}
	
	public int deletReservation(String searchNum) {
		
		Reservation tmpR = selectReservationNum(searchNum);
		if(tmpR == null) {
			return ERROR_CODE_RESERVATION_DUPLE;
		}
		
		int result = reservationDao.delete(searchNum);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;		
	}
}
