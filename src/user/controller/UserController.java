package user.controller;

import static common.JDBCTemplate.ERROR_CODE_USER_DUPLE;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.rollback;

import java.util.List;

import user.model.dao.UserDao;
import user.service.UserService;
import user.model.vo.User;


public class UserController {
	
	private UserService userService = new UserService();
	
	public int login(String id, String pw) {
		return userService.login(id, pw);
	}
	
	public User setMe(String id, String pw) {
		return userService.setMe(id, pw);
	}
	
	public User selectId(String id) {
		return userService.selectId(id);
	}
	
	public int searchPw(String id, String email) {
		return userService.searchPw(id, email);
	}

	public boolean searchPSPT(String pspt) {
		return userService.searchPSPT(pspt);
	}
	
	public int searchId(String name, String phone) { 
		return userService.searchId(name, phone);
	}

	public int checkId(String id) {
		return userService.checkId(id);
	}

	public void insertUser(User user) { 
		userService.insertUser(user);
	}

	public int updateUser(String id, String pw, String phone, String email) {
		return userService.updateUser(id, pw, phone, email);
	}
	
	public void airlineMemberNum(String id, String num) { 
		userService.airlineMemberNum(id, num);
	}
	
	public int updatePSPT(User user, String id, String pspt) {
		return userService.updatePSPT(user, id, pspt);
	}
	
	public void deleteUser(String id) {
		userService.deleteUser(id);	
	}
}
