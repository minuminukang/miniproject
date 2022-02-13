package view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import payment.controller.PaymentController;
import payment.model.vo.Payment;
import reservation.controller.ReservationController;
import reservation.model.vo.Reservation;
import schedule.controller.ScheduleController;

public class PaymentMenu {
	PaymentController pc = new PaymentController();
	ReservationController rc = new ReservationController();
	ScheduleController sc = new ScheduleController();
	DecimalFormat df = new DecimalFormat("#,###,###");	

	// 편도 예약
	public void paymentView(Reservation r) {
		System.out.println("======= 결제 ========");

		String payNum = paymentNum();
		int pay = price();
		System.out.println("결제금액 : " + df.format(pay) + "원");
		String cardNum = inputCard();
		String payDay = payDay();
		int mileage = getMileage(pay);
		String reNum = r.getReservationNum();
		String pspt = r.getPsptNum();

		Payment p = new Payment(payNum, cardNum, pay, payDay, mileage, reNum, pspt);


		pc.insertPayment(p);
		rc.insertReservation(r); // 결제 후 예약확정이므로 여기에 새로운 예약을 insert했습니다
		sc.updateRemainSeat(r.getFlightId()); // 예약 되었으니까 잔여좌석 -1 해주기

		// 결제 완료 페이지 출력
		System.out.println("결제가 완료되었습니다.");

		StringBuffer sb = new StringBuffer();
		sb.append("결제번호 : " + payNum + "\n");
		sb.append("결제금액 : " + df.format(pay) + "원\n");
		sb.append("결제날짜 : " + payDay + "\n");
		sb.append("카드번호 : " + cardNum + "\n");
		sb.append("마일리지 : " + df.format(mileage) + "적립\n");

		System.out.println(sb.toString());

		new TotalMainMenu().mainMenu(); //메인으로....
	}

	// 왕복 예약
	public void paymentViewRoundTrip(Reservation r1, Reservation r2) {

		System.out.println("======= 결제 ========");

		String payNum1 = paymentNum(); // 갈 때
		String payNum2 = paymentNum(); // 올 때


		int pay1 = price();
		System.out.println("가는 편 결제금액 : " + df.format(pay1));
		int pay2 = price();
		System.out.println("오는 편 결제금액 : " + df.format(pay2));

		String cardNum = inputCard();

		String payDay = payDay();

		int mileage1 = getMileage(pay1);
		int mileage2 = getMileage(pay2);

		String reNum1 = r1.getReservationNum(); // 갈때 스케줄 1 - 예약 1
		String reNum2 = r2.getReservationNum(); // 올때 스케줄 1 - 예약 1

		String pspt = r1.getPsptNum(); // 예약은 2개지만 예약자는 1명이므로 여권번호는 한개만 받기

		Payment p1 = new Payment(payNum1, cardNum, pay1, payDay, mileage1, reNum1, pspt);
		Payment p2 = new Payment(payNum2, cardNum, pay2, payDay, mileage2, reNum2, pspt);

		pc.insertPayment(p1); // 한꺼번에 결제했지만 스케줄이 두개라서 따로 insert 
		pc.insertPayment(p2);

		rc.insertReservation(r1); // 결제 후 예약확정이므로 여기에 새로운 예약을 insert했습니다
		rc.insertReservation(r2);

		sc.updateRemainSeat(r1.getFlightId()); // 예약 되었으니까 잔여좌석 -1 해주기
		sc.updateRemainSeat(r2.getFlightId());

		// 결제 완료 페이지 출력
		System.out.println("결제가 완료되었습니다.");

		StringBuffer sb1 = new StringBuffer();
		sb1.append("결제번호 : " + payNum1 + "\n");
		sb1.append("결제금액 : " + df.format(pay1) + "원\n");
		sb1.append("결제날짜 : " + payDay + "\n");
		sb1.append("카드번호 : " + cardNum + "\n");
		sb1.append("마일리지 : " + df.format(mileage1) + "적립\n");

		StringBuffer sb2 = new StringBuffer();
		sb2.append("결제번호 : " + payNum2 + "\n");
		sb2.append("결제금액 : " + df.format(pay2) + "원\n");
		sb2.append("결제날짜 : " + payDay + "\n");
		sb2.append("카드번호 : " + cardNum + "\n");
		sb2.append("마일리지 : " + df.format(mileage2) + "적립\n");

		System.out.println(sb1.toString());
		System.out.println(sb2.toString());

		new TotalMainMenu().mainMenu(); //메인으로....
	}


	// 결제 번호 결제날짜+랜덤대문자4글자
	public String paymentNum() {

		Random r = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1= Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());

		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 4; i++) {
			sb.append((char)(r.nextInt(26) + 'A'));
		}				
		String payNum = "" + strToday + sb.toString();

		return payNum;
	}

	// 카드 번호
	public String inputCard() {
		String inputCard = "";

		while(true) {
			System.out.println("결제하실 카드 번호를 입력하세요. (0000-0000-0000-0000)");
			System.out.print(": ");
			inputCard = Util.readStrForConsol();

			String[] tmpCard = inputCard.split("-");
			if(tmpCard.length != 4) {
				System.out.println("카드번호 잘못 입력하셨습니다. 다시 입력해주세요.");
				continue;
			}

			boolean isCorrectInput = true;
			for(int i = 0; i < tmpCard.length; i++) {
				if(tmpCard[i].length() != 4) {
					// 4자리-4자리-4자리-4자리가 아닐 때 
					System.out.println("카드번호 잘못 입력하셨습니다. 다시 입력해주세요.");
					isCorrectInput = false;
					break;
				}
				if(isNumber(tmpCard[i]) == false) {
					// 입력한 카드 번호가 숫자가 아닐 때 
					System.out.println("카드번호 잘못 입력하셨습니다. 다시 입력해주세요.");
					isCorrectInput = false;
					break;
				}
			}

			if(isCorrectInput == false) {
				continue;
			}
			break;
		}
		return inputCard;
	}

	private boolean isNumber(String tmpCard) {
		boolean isNumber = false;

		for(int i = 0; i < tmpCard.length(); i++) {
			char card = tmpCard.charAt(i);

			if(Character.isDigit(card) == false) {
				isNumber = false; // 숫자가 아닌게 1개라도 나오면
				return false;		// 바로 false 리턴
			}
			else {
				isNumber = true;
			}
		}
		return isNumber;
	}

	// 가격 
	public int price() {
		int pay = (int)(Math.random() * 10000000); // 9,999,999

		return pay;
	}

	// 결제한 날짜
	public String payDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1= Calendar.getInstance();
		String day = sdf.format(c1.getTime());

		return day;
	}

	// 마일리지 적립
	private int getMileage(int pay) {		
		int mileage = (pay / 1000) * 2; // 마일리지 1000원당 2점

		return mileage;
	}
}
