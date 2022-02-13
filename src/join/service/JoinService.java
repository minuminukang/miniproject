package join.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import join.dao.JoinDao;
import join.vo.Join;
import user.model.vo.User;

public class JoinService {
	private JoinDao joinDao = null;
	private Connection conn = null;
	
	public JoinService() {
		init();
	}
	
	public void init() {
		if(conn != null) {
			close(conn);
		}
		conn = getConnection();
		joinDao = new JoinDao(conn);
	}
	
	@Override
	protected void finalize() throws Throwable { // 객체 종료시 호출되는 메소드
		close(conn);
	}
	
	public List<Join> selectUserReservationPayment(User user) {
		if(user.getPsptNum() == null) {
			return null;
		}
		return joinDao.selectUserReservationPayment(user);
	}

}
