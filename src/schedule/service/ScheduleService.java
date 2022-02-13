package schedule.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import schedule.model.dao.ScheduleDao;
import schedule.model.vo.Schedule;

public class ScheduleService {
	private ScheduleDao scheduleDao = null;
	private Connection conn = null;

	public ScheduleService() {
		init();
	}

	public void init() {
		if(conn != null) {
			close(conn);
		}
		conn = getConnection();
		scheduleDao = new ScheduleDao(conn);
	}

	@Override
	protected void finalize() throws Throwable { // 객체 종료시 호출되는 메소드
		close(conn);
	}
	
	/* 항공권 조회했을 때
	 * 1 인천 -> 다낭 10시
	 * 2 인천 -> 다낭 11시 ....
	 * 이렇게 앞에 숫자 넣으면 사용자가 목록 중에서 선택하기 편하라고(?)
	 * 숫자 키를 부여하였고 value는 조회된 schedule 리스트에서 schedule 객체 하나씩
	 * 넣었습니다. */
	public Map<Integer, Schedule> searchTicket(String boardingAirport, String arrivedAirport, String boardingDate) {
		
		List<Schedule> sList = new ArrayList<Schedule>();
		Map<Integer, Schedule> sMap = new HashMap<Integer, Schedule>();
		
		sList = selectDeparture(boardingAirport, arrivedAirport, boardingDate);
		if(sList == null) {
			return null;
		}
		
		for(int i = 0; i < sList.size(); i++) {
			sMap.put(i+1, sList.get(i));
		}
		return sMap;		
	}
	
	public List<Schedule> selectAll() {
		scheduleDao.setConn(conn);
		return scheduleDao.selectAll();
	}
	
	public Schedule selectFlightId(String searchId) {
		if(searchId == null) {
			return null;
		}
		return scheduleDao.selectFlightId(searchId);
	}
	
	public List<Schedule> selectDeparture(String searchBoarding, String searchArrived, String boardingDate) {
		return scheduleDao.selectDeparture(searchBoarding, searchArrived, boardingDate);
	}
	
	public int insertSchedule(Schedule s) {	
		
		int result = scheduleDao.insertSchedule(s);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public int updateRemainSeat(String flightId) {
		if(flightId == null) {
			return ERROR_CODE_SCHEDULE_DUPLE;
		}
		
		int result = scheduleDao.updateRemainSeat(flightId);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public List<Schedule> selectEvent(String gaum) {
		scheduleDao.setConn(conn);
		return scheduleDao.selectEvent(gaum);
	}

}
