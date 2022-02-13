package run;

import java.util.ArrayList;
import java.util.List;

import schedule.controller.ScheduleController;
import schedule.model.vo.Schedule;
import view.TotalMainMenu;

public class Run {
	public static void main(String[] args) {
		
		new TotalMainMenu().mainMenu();
		System.out.println("---- 프로그램 종료 ----");
	}
}
