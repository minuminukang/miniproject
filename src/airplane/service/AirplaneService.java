package airplane.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import airplane.model.dao.AirplaneDao;
import airplane.model.vo.Airplane;

public class AirplaneService {
	private AirplaneDao airplaneDao = null;
	private Connection conn = null;
	
	public AirplaneService() {
		init();
	}
	
	public void init() {
		if(conn != null) {
			close(conn);
		}
		conn = getConnection();
		airplaneDao = new AirplaneDao(conn);
	}

	@Override
	protected void finalize() throws Throwable { // 객체 종료시 호출되는 메소드
		close(conn);
	}
	
	public List<Airplane> selectAll() {
		airplaneDao.setConn(conn);
		return airplaneDao.selectAll();
	}
	
	public Airplane selectId(String id) {
		if(id == null) {
			return null;
		}
		return airplaneDao.selectId(id);
	}
		
	public int insertPayment(Airplane a) {
		
		int result = airplaneDao.insertAirplane(a);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	public int deleteAirplane(String id) {
		int result = deleteAirplane(id);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
