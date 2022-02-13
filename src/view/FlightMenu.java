package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import airplane.controller.AirplaneController;
import airplane.model.vo.Airplane;
import schedule.controller.ScheduleController;
import schedule.model.vo.Schedule;

public class FlightMenu {
	ScheduleController scheduleController = new ScheduleController();

	String strFlightMenu = null;
	{
		StringBuffer sb = new StringBuffer();
		sb.append("=========항공편 메뉴=========\n");
		sb.append("1.왕복\n");
		sb.append("2.편도\n");
		sb.append("3.전체 항공권 조회\n");
		sb.append("4.메인 화면으로\n");
		sb.append("--------------------------\n");
		sb.append("메뉴 번호 선택 : ");
		
		strFlightMenu = sb.toString();
	}
	public void flightMainMenu() {
		
		while(true) {
			System.out.print(strFlightMenu);
			int menuNum = Util.readIntForConsol();

			switch(menuNum) {
			case 1 : // 왕복	
				this.roundTrip();
				break;
			case 2 : // 편도
				this.oneWay();
				break;
			case 3 : // 전체 항공권 조회
				this.displayTotalFlight();
				break;
			case 4 : 
				return;
			default : 
				System.out.println("잘못 입력하셨습니다. 올바른 메뉴 번호를 입력해주세요.");
				continue;
			}
		}
	}

	/* 왕복 조회*/
	private void roundTrip() {
		while(true) {
			System.out.println("=========왕복 항공편=========");
			System.out.print("출발지 : ");
			String inputBoarding = Util.readStrForConsol();
			System.out.print("도착지 : ");
			String inputArrived = Util.readStrForConsol();
			System.out.print("가는 날짜(YYYY-MM-DD) : ");
			String toGoDay = Util.readStrForConsol();
			System.out.print("오는 날짜(YYYY-MM-DD) : ");
			String returnDay = Util.readStrForConsol();


			Map<Integer, Schedule> toGoMap = new HashMap<Integer, Schedule>();
			Map<Integer, Schedule> returnMap = new HashMap<Integer, Schedule>();

			toGoMap = scheduleController.searchTicket(inputBoarding, inputArrived, toGoDay);
			returnMap = scheduleController.searchTicket(inputArrived, inputBoarding, returnDay);

			System.out.println("---항공권 검색 결과---");
			// map == null? 체크하기
			if(toGoMap.isEmpty() == true || returnMap.isEmpty() == true) {
				System.out.println("[왕복] 검색하신 항공권이 없습니다.");

				System.out.print("[왕복] 다시 검색하시겠습니까? (Y/N) : ");
				String answer = Util.readStrForConsol();

				if((answer.toUpperCase()).equals("Y")) {
					continue;
				}
				else {
					System.out.println("항공권 조회를 종료합니다.");
					return;
				}

				// ++ 가까운 날짜 항공권 있으면 추천하기

			} else if(toGoMap != null && returnMap != null){
				System.out.println("항공권 조회 결과입니다.");
				System.out.println("-----가는 편-----");
				for(int key : toGoMap.keySet()) {
					Schedule value = toGoMap.get(key);
					System.out.println(key + " : " + value);
				}

				System.out.println("-----오는 편-----");
				for(int key : returnMap.keySet()) {
					Schedule value = returnMap.get(key);
					System.out.println(key + " : " + value);
				}

				System.out.println("-------------------");
				while(true) {
					System.out.println("1. 항공권 선택하기");
					System.out.println("2. 비행기 정보 검색하기");
					System.out.println("3. 메인으로 돌아가기");
					System.out.println("-------------------");
					System.out.print("선택 : ");

					int option = Util.readIntForConsol();

					switch(option) {
					case 1 :
						choiceTicket(toGoMap, returnMap);
						break;
					case 2 :
						System.out.println("1. 가는편 비행기 정보");
						System.out.println("2. 오는편 비행기 정보");
						System.out.print("선택 : ");
						int in = Util.readIntForConsol();
						if(in == 1)
							searchAirplaneInfo(toGoMap);
						else 
							searchAirplaneInfo(returnMap);
						break;
					case 3 : 
						return;
					}
				}
			}

		}

	}

	public void choiceTicket(Map<Integer, Schedule> toGoMap, Map<Integer, Schedule> returnMap) {
		System.out.print("가는 편 선택 (번호) : ");
		int num1 = Util.readIntForConsol();
		System.out.print("오는 편 선택 (번호): ");
		int num2 = Util.readIntForConsol();

		Schedule go = toGoMap.get(num1);
		Schedule come = returnMap.get(num2);

		// insert 예약, 결제 두개씩 만들기로
		new ReservationMenu().reservationViewRoundTrip(go, come);

	}

	public void searchAirplaneInfo(Map<Integer, Schedule> sMap) {
		System.out.println("=========비행기 정보=========");
		System.out.println("원하시는 항공편의 번호를 입력하세요.");
		System.out.print("선택 : ");
		int num = Util.readIntForConsol();

		Schedule s = sMap.get(num);
		AirplaneController ac = new AirplaneController();
		System.out.println("비행기 아이디 : " + s.getAirplaneId());
		Airplane airplane = ac.selectId(s.getAirplaneId());

		System.out.println(airplane);

	}

	/* 편도 조회*/
	private void oneWay() {
		while(true) {
			System.out.println("=========편도 항공편=========");
			System.out.print("출발지 : ");
			String inputBoarding = Util.readStrForConsol();
			System.out.print("도착지 : ");
			String inputArrived = Util.readStrForConsol();
			System.out.print("가는 날짜(YYYY-MM-DD) : ");
			String toGoDay = Util.readStrForConsol();

			Map<Integer, Schedule> toGoMap = new HashMap<Integer, Schedule>();

			toGoMap = scheduleController.searchTicket(inputBoarding, inputArrived, toGoDay);

			System.out.println("---항공권 검색 결과---");
			// map == null? 체크하기
			if(toGoMap.isEmpty()) {
				System.out.println("[편도] 검색하신 항공권이 없습니다.");

				System.out.print("[편도] 다시 검색하시겠습니까? (Y/N) : ");
				String answer = Util.readStrForConsol();

				if((answer.toUpperCase()).equals("Y")) {
					continue;
				}
				else {
					System.out.println("항공권 조회를 종료합니다.");
					return;
				}


			} else {
				System.out.println("항공권 조회 결과입니다.");
				System.out.println("----- 편도 -----");
				for(int key : toGoMap.keySet()) {
					Schedule value = toGoMap.get(key);
					System.out.println(key + " : " + value);
				}

				System.out.println("-------------------");
				while(true) {
					System.out.println("1. 항공권 예약하기");
					System.out.println("2. 비행기 정보 검색하기");
					System.out.println("3. 메인으로 돌아가기");
					System.out.println("-------------------");
					System.out.print("선택 : ");

					int option = Util.readIntForConsol();

					switch(option) {
					case 1 :
						choiceTicket(toGoMap);
						break;
					case 2 :
						searchAirplaneInfo(toGoMap);
						break;
					case 3 : 
						return;
					}
				}
			}
		}
	}
	public void choiceTicket(Map<Integer, Schedule> toGoMap) {
		System.out.print("항공편 선택 (번호) : ");
		int num1 = Util.readIntForConsol();

		Schedule go = toGoMap.get(num1);

		new ReservationMenu().reservationView(go);
	}

	/* 전체 항공권 조회 */
	private void displayTotalFlight() {

		List<Schedule> list = scheduleController.selectAll();
		for(Schedule s : list) {
			System.out.println(s);
		};
	}
}
