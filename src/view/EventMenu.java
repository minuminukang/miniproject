package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import schedule.controller.ScheduleController;
import schedule.model.vo.Schedule;

public class EventMenu {
	ScheduleController sc = new ScheduleController();

	String strEventMenu = null;
	{
		StringBuffer sb = new StringBuffer();
		sb.append("=========이벤트 메뉴=========\n");
		sb.append("1.10월 2주간 괌 여행 항공권 50% 할인\n");
		sb.append("2.동남아 IHG 투숙시 1박 무료\n");
		sb.append("3.12월까지 수수료 없이 최저가 예매!\n");
		sb.append("4.메인 화면으로\n");
		sb.append("--------------------------\n");
		sb.append("메뉴 번호 선택 : ");

		strEventMenu = sb.toString();
	}

	public void EventMainMenu() {

		while(true) {
			System.out.print(strEventMenu);
			int menuNum = Util.readIntForConsol();

			switch(menuNum) {
			case 1 : 
				this.event1();
				break;
			case 2 : 
				this.event2();
				break;
			case 3 : 
				this.event3();
				break;
			case 4 : 
				return;
			default : 
				System.out.println("잘못 입력하셨습니다. 올바른 메뉴 번호를 입력해주세요.");
				continue;
			}
		}

	}

	private void event1() {
		
		System.out.println("-----이벤트 티켓-----");
		String gaum = "괌";
		List<Schedule> list = sc.selectEvent(gaum);
		Map<Integer, Schedule> map = new HashMap<Integer, Schedule>();
		
		for(int i = 0; i < list.size(); i++) {
			map.put(i+1, list.get(i));
			System.out.println("리스트 : " + list.get(i));
		}
		
		for(int key : map.keySet()) {
			Schedule value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------");
		System.out.print("예매하시겠습니까?(Y/N) : ");
		String yn = Util.readStrForConsol();
		
		if((yn.toUpperCase()).equals("Y")) {
			System.out.println("원하시는 항공편의 번호를 입력하세요.");
			System.out.print("선택 : ");
			int num = Util.readIntForConsol();
			
			Schedule s = map.get(num);
			
			new ReservationMenu().reservationView(s);
			// 예매로 넘어감...
			
		} else if ((yn.toUpperCase()).equals("N")) {
			System.out.println("메인 메뉴로 돌아갑니다.");
			return;
		} else {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
			
	}
	
	private void event2() {
		StringBuffer s = new StringBuffer();
		
		s.append("행운의 1인에게 증정되는\n");
		s.append("동남아 왕복 항공권도 놓치지 마세요!\n");
		s.append("2021년 9월 13일 ~ 2021년 12월 31일\n");
		
		System.out.println(s.toString());
	}
	
	private void event3() {
		StringBuffer s = new StringBuffer();
		
		s.append("12월까지 수수료 없이 최저가 예매!\n");
		s.append("비교하여 최저가로 티켓 구매하세요\n");
		s.append("2021년 8월 1일 ~ 2021년 12월 31일\n");
		
		System.out.println(s.toString());
	}
	
}