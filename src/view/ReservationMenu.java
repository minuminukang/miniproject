package view;

import java.util.Random;

import reservation.controller.ReservationController;
import reservation.model.vo.Reservation;
import schedule.controller.ScheduleController;
import schedule.model.vo.Schedule;
import user.controller.UserController;

public class ReservationMenu {
	ReservationController rc = new ReservationController();
	UserController uc = new UserController();
	ScheduleController sc = new ScheduleController(); 

	public void reservationView(Schedule s) {

		System.out.println("======= 예약 ========");

		String reNum = reservationNum();
		String seat = reservationSeat(s);
		String meal = mealChoice();
		String luggage = luggageChoice();
		String pspt = inputPSPT();
		String fi = s.getFlightId(); // 예약할 항공권 스케쥴 id 불러오기	 

		Reservation r = new Reservation(reNum, seat, meal, luggage, pspt, fi);

		System.out.println("결제 페이지로 넘어갑니다.");
		new PaymentMenu().paymentView(r);
	}

	public void reservationViewRoundTrip(Schedule s1, Schedule s2) {

		System.out.println("======= 예약 ========");
		System.out.println("===== 가는편 예약 =====");

		String pspt = inputPSPT(); 
		
		String reNum1 = reservationNum();
		String seat1 = reservationSeat(s1);
		String meal1 = mealChoice();
		String luggage1 = luggageChoice();
		
		String fi1 = s1.getFlightId(); // 예약할 항공권 스케쥴 id 불러오기	 

		Reservation r1 = new Reservation(reNum1, seat1, meal1, luggage1, pspt, fi1);

		System.out.println("===== 오는편 예약 =====");

		String reNum2 = reservationNum();
		String seat2 = reservationSeat(s2);
		String meal2 = mealChoice();
		String luggage2 = luggageChoice();
		//String pspt2 = inputPSPT();
		String fi2 = s2.getFlightId();

		Reservation r2 = new Reservation(reNum2, seat2, meal2, luggage2, pspt, fi2);

		System.out.println("결제 페이지로 넘어갑니다.");
				
		new PaymentMenu().paymentViewRoundTrip(r1,r2);

	}
	
	/* 예약번호는 임의의 문자열 랜덤 
	 * select * from reservation where num = ? 에 있을 시에는
	 * 다른 예약번호 부여
	 */
	public String reservationNum() {
		String reNum = null;
		Random r = new Random();

		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 6; i++) {
			sb.append((char)(r.nextInt(26) + 'A'));
		}				
		reNum = "" + sb.toString();

		return reNum;	
	}

	public String inputPSPT() {
		String pspt = null;

		/* 여권번호 입력 */
		while (true) {
			System.out.print("여권번호 입력 : ");
			pspt = Util.readStrForConsol();

			if (pspt.length() > 10) {
				System.out.println("정확한 여권번호를 입력해주세요.");
				continue;
			}

			if(uc.searchPSPT(pspt) == false){
				System.out.println("여권번호가 존재하지 않습니다. 다시 입력해주세요.");
			}
			else { // 여권번호 존재함
				break;
			}
		}
		return pspt;
	}

	public String reservationSeat(Schedule s) {
		// String seatView = null;
		System.out.print("좌석번호 : ");
		String seat = Util.readStrForConsol();
		return seat;
	}

	public String mealChoice() {
		String meal = null;
		/* 기내식 선택 */
		while (true) {
			System.out.print("기내식 선택(Y/N) : ");
			meal = Util.readStrForConsol();
			if ((meal.toUpperCase()).equals("Y") || (meal.toUpperCase()).equals("N")) {
				break;
			} else {
				System.out.println("다시 입력해주세요.");
				continue;
			}
		}
		return meal;
	}

	public String luggageChoice() {
		String luggage = null;
		/* 수화물 선택 */
		while (true) {
			System.out.print("수화물 유무(Y/N) : ");
			luggage = Util.readStrForConsol();
			if ((luggage.toUpperCase()).equals("Y") || (luggage.toUpperCase()).equals("N")) {
				break;
			} else {
				System.out.println("다시입력해주세요.");
				continue;
			}
		}
		return luggage;
	}
}
