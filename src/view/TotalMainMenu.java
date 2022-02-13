package view;

import java.util.List;
import java.util.Scanner;

import schedule.model.vo.Schedule;


public class TotalMainMenu {
	private Scanner sc = new Scanner(System.in);
	
	String mainMenu = null;
	{
		StringBuffer sb = new StringBuffer();
		sb.append("=====항공편 예매 시스템=====\n");
		sb.append("1.이벤트\n");
		sb.append("2.항공편 조회\n");
		sb.append("3.로그인\n");
		sb.append("0.시스템 종료\n");
		sb.append("-----------------------\n");
		sb.append("선택 : ");
		
		mainMenu = sb.toString();
	}
	
	public void mainMenu() {
						
		while(true) {
			System.out.print(mainMenu);
			int choice = Util.readIntForConsol();
			
			switch(choice) {
				case 1: // 이벤트
					new EventMenu().EventMainMenu();
					break;
				case 2: // 항공편 조회
					new FlightMenu().flightMainMenu();
					break;
				case 3: // 로그인
					new LoginMenu().loginMainMenu();
					break;
				case 0: // 종료
					return;
				default: 
					System.out.println("잘못 입력하셨습니다.");
			}	
		}
	}
}
