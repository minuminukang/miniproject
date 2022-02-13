package join.controller;

import java.util.List;

import join.service.JoinService;
import join.vo.Join;
import user.model.vo.User;

public class JoinController {
	JoinService js = new JoinService();
	public List<Join> selectUserReservationPayment(User user) {
		return js.selectUserReservationPayment(user);
	}
}
