package user.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import payment.controller.PaymentController;
import reservation.controller.ReservationController;
import user.model.dao.UserDao;
import user.model.vo.User;

public class UserService {

	private UserDao userDao = null;
	private Connection conn = null;

	public UserService() {
		init();
	}

	public void init() {
		if(conn != null) {
			close(conn);
		}
		conn = getConnection();
		userDao = new UserDao(conn);
	}

	@Override
	protected void finalize() throws Throwable { // 객체 종료시 호출되는 메소드
		close(conn);
	}

	public int login(String id, String pw) { //로그인 메소드
		User user = userDao.selectId(id);

		if(user == null) {
			return 0;
		} else if (user.getUserPw().equals(pw)){
			return 1;
		} else {
			return -1;
		}
		/*
		 * 메뉴 클래스에서 전달받은 아이디로 db에서 서칭 후,
		 * pw의 일치 여부에 따라 값을 다르게 리턴합니다.
		 */
	}

	/*
	 *  menu에서 회원정보수정할 때 쉽게 당사자 정보 끌어오려고 만듦
	 */
	public User setMe(String id, String pw) {//로그인 당사자 세팅
		User user = userDao.selectId(id);
		return user;
	}

	public User selectId(String id) {
		return userDao.selectId(id);
	}

	public int searchPw(String id, String email) {//패스워드 서칭 메소드
		User user = userDao.selectId(id);

		if(user == null) {
			return 0;
		} else if (user.getEmail().equals(email)){
			String pw = user.getUserPw();
			System.out.println("비밀번호는 "+ pw + "입니다.");
			return 1;
		} else {
			return -1;
		}
		/*
		 * 아이디를 통해서 db에서 객체 서칭한 후, email일치 여부에 따라 
		 * pw를 출력하고 결과값을 int로 리턴합니다.
		 */
	}

	public boolean searchPSPT(String pspt) {
		List<String> list = userDao.readPSPT();
		boolean exist = false;

		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(pspt)) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	public int searchId(String name, String phone) { //아이디 서칭 메소드
		List<User> list = userDao.selectName(name);
		int k = 0;
		if(list.isEmpty()) {
			return 0;
		} else {
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getPhone().equals(phone)) {
					System.out.println("아이디는 "+list.get(i).getUserId() + "입니다.");
					k = 1;
					break;
				}
				k = -1;
			}
			return k;
		}

	}

	public int checkId(String id) { //회원가입시 중복아이디 있는지 체크하는 메소드
		if(userDao.checkId().contains(id)) {
			System.out.println("중복되는 아이디가 있습니다. 다른 아이디를 입력해주세요.");
			return 1;
		} else {
			System.out.println("중복되는 아이디가 없습니다. 가입 가능합니다.");
			return 0;
		}
		/*
		 * db에서 테이블의 id값만 받아온 후 그 안에 입력받은 id가 존재하는지 
		 * contains 메소드로 찾아내는 메소드입니다.
		 */
	}

	public int insertUser(User user) { //회원 가입 메소드
		return userDao.insertUser(user);
		//가입 회원의 정보를 db에 삽입하는 메소드

	}

	public int updateUser(String id, String pw, String phone, String email) {
		if(id == null) {
			return ERROR_CODE_USER_DUPLE;
		}

		int result = userDao.updateUser(id,pw,phone,email);
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
		//로그인한 사용자의 아이디와 입력받은 값들로 db를 수정
	}

	public int airlineMemberNum(String id, String num) { 
		//로그인한 사용자의 아이디와 입력받은 회원넘버로 db를 수정
		return userDao.updateAirlineNum(id, num);
	}


	public int updatePSPT(User user, String id, String pspt) {
		// 결제테이블, 예약테이블 PSPT 모두 업데이트 하기위해 user받아옴
		// userDao.updatePSPT에는 user가 필요없기 때문에 매개변수 변경함
		ReservationController rc = new ReservationController();
		PaymentController pc = new PaymentController();
		List<String> rList = new ArrayList<String>();
		List<String> pList = new ArrayList<String>();

		int result = 0;
		if(userDao.readPSPT().contains(pspt)) {//모든psptnum을 리스트로 받아온 후 입력한 여권번호가 있는지 확인
			return 0; //menu로 돌아가 다시 입력받기
		}else{
			rList = rc.searchReservationNum(user.getPsptNum());
			// 해당 여권번호에 해당하는 예약번호들 리스트로 받기
			for(int i = 0; i < rList.size(); i++) {
				int tmpResult = rc.updatePSPT(rList.get(i), pspt);
				if(tmpResult > 0) {
					commit(conn);
				} else {
					rollback(conn);
				}
			}
			
			pList = pc.searchPaymentNum(user.getPsptNum());
			// 해당 여권번호에 해당하는 결제번호들 리스트로 받기
			for(int i = 0; i < pList.size(); i++) {
				int tmpResult = pc.updatePSPT(pList.get(i), pspt);
				if(tmpResult > 0) {
					commit(conn);
				} else {
					rollback(conn);
				}
			}
			result = userDao.updatePSPT(id,pspt); //중복이 없는 경우 사용자의 아이디와 입력된 여권번호를 이용, 업데이트
		}
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		return result;

	}

	public int deleteUser(String id) {
		return userDao.deleteUser(id);	
	}
}