package view;

import java.util.Scanner;

public class Util {

	static private Scanner sc = new Scanner(System.in);

	public static int readIntForConsol() { 

		int value = 0;

		sc.reset(); 

		while(true) {

			String str = sc.nextLine(); 
			if(str == null || str.length() < 1 ) { 
				System.out.println("다시 숫자를 입력해주세요.");
				continue;
			}

			try {
				value = Integer.parseInt(str); 
			} catch (Exception e) {
				System.out.println("다시 숫자를 입력해주세요."); 
				continue;
			}
			break; 
		}
		return value;
	}


	public static String readStrForConsol() { 

		String str = null;

		sc.reset(); 

		while(true) {

			str = sc.nextLine(); 
			if(str == null || str.length() < 1 ) { 
				System.out.println("다시 문자를 입력해주세요.");
				continue;
			}
			break;
		}
		return str;
	}


	static boolean isDebug = true;

	public static void printDebug(String str) {

		if(isDebug == true) {
			System.out.println(str);
		}
	}
}