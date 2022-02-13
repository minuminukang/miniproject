package view;

import java.util.List;

import join.controller.JoinController;
import join.vo.Join;
import user.controller.UserController;
import user.model.vo.User;

public class LoginMenu {
	UserController userController = new UserController();
	String str = null;
	{
		StringBuffer sb = new StringBuffer();
		sb.append("=========로그인 메뉴==========\n");
		sb.append("1. 로그인\n");
		sb.append("2. 비밀번호 찾기\n");
		sb.append("3. 아이디 찾기\n");
		sb.append("4. 회원 가입\n");
		sb.append("9. 메인 화면으로\n");
		sb.append("메뉴 번호 선택 : ");
		str = sb.toString(); //로그인 메인 메뉴 목록
	}
	String str2 = null;
	{
		StringBuffer sb2 = new StringBuffer();
		sb2.append("=========회원 메뉴==========\n");
		sb2.append("1. 회원 정보 수정\n");
		sb2.append("2. 예약 내역 확인\n");
		sb2.append("3. 회원 탈퇴\n");
		sb2.append("9. 메인 화면으로\n");
		sb2.append("메뉴 번호 선택 : ");
		str2 = sb2.toString(); //회원 메뉴 목록
	}
	String str3 = null;
	{
		StringBuffer sb3 = new StringBuffer();
		sb3.append("=========정보 수정==========\n");
		sb3.append("1. 개인정보 수정\n");
		sb3.append("2. 항공사 회원번호 수정\n");
		sb3.append("3. 여권번호 수정\n");
		sb3.append("9. 이전 화면으로\n");
		sb3.append("메뉴 번호 선택 : ");
		str3 = sb3.toString(); //정보수정 메뉴 목록
	}

	User thisUser = new User();

	public void loginMainMenu() {
		while(true) {
			System.out.println(str);
			int menuNum = Util.readIntForConsol();

			switch(menuNum) {
			case 1 :
				this.login();
				break;
			case 2 : 
				this.searchPw();
				break;
			case 3 : 
				this.searchId();
				break;
			case 4 : 
				this.join();
				break;
			case 9 : 
				this.backToMain();
				break;
			default : 
				System.out.println("잘못 입력하셨습니다. 올바른 메뉴 번호를 입력해주세요.");
				continue;
			}

		}

	}

	// 로그인
	public void login() {
		while(true) {
			System.out.print("아이디를 입력해주세요 : ");
			String id = Util.readStrForConsol();
			System.out.print("비밀번호를 입력해주세요 : ");
			String pw = Util.readStrForConsol();

			if(userController.login(id, pw) == 1) {
				System.out.println("로그인에 성공하였습니다.");
				thisUser = userController.setMe(id, pw);
				break;
			} else if (userController.login(id, pw) == 0) {
				System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요.");
				continue;
			} else {
				System.out.println("비밀번호가 잘못되었습니다. 다시 입력해주세요.");
				continue;
			}
			/*
			 * controller의 login 메소드에서 1을 리턴받으면 로그인 성공과 동시에 thisMember에 로그인한
			 * 사용자의 정보를 업데이트, 이후 회원정보수정시 불러내어 사용합니다.
			 * 0을 리턴받으면 아이디가 db에 없는 경우
			 * -1을 리턴받으면 아이디를 찾았으나 pw가 서칭되지 않는 경우
			 */
		}

		this.userMenu(thisUser);
		//로그인하면 회원메뉴가 로드되도록 설계했습니다.
	}
	public void searchPw() {//비밀번호 서칭 메소드
		while(true) {
			System.out.print("비밀번호를 찾고자 하는 아이디를 입력해주세요 : ");
			String id = Util.readStrForConsol();
			System.out.print("가입시 기재한 이메일을 입력해주세요 : ");
			String email = Util.readStrForConsol();

			if(userController.searchPw(id,email)==1) {
				break;
			} else if (userController.searchPw(id,email) == 0) {
				System.out.println("존재하지 않는 아이디입니다. 다시 입력해주세요.");
				continue;
			} else {
				System.out.println("이메일이 일치하지 않습니다. 다시 입력해주세요.");
				continue;
			}
			/*
			 * 아이디와 이메일로 db에서 해당하는 회원을 찾아 패스워드를 찾도록 했습니다.
			 * 역시 로그인 메소드처럼 0,1,-1을 리턴하여 결과를 나누었습니다.
			 */

		}

	}
	public void searchId() { //아이디 서칭 메소드
		while(true) {
			System.out.print("이름을 입력해주세요 : ");
			String name = Util.readStrForConsol();
			System.out.print("회원가입시 기재한 전화번호를 입력해주세요.(-포함) : ");
			String phone = Util.readStrForConsol();

			if(userController.searchId(name ,phone)==1) {
				break;
			} else if (userController.searchId(name,phone) == 0) {
				System.out.println("존재하지 않는 이름입니다. 다시 입력해주세요.");
				continue;
			} else {
				System.out.println("전화번호가 일치하지 않습니다. 다시 입력해주세요.");
				continue;
			}
		}
		/*
		 * 이름과 전화번호로 회원의 아이디를 찾으며, 역시 0,1,-1로 결과를 나누었습니다.
		 */

	}


	public void join() {//회원가입 메소드

		String id = null;
		while(true) {
			System.out.print("아이디를 입력해주세요 : ");
			id = Util.readStrForConsol();
			if(userController.checkId(id) == 1) {
				continue;
			} else {
				break;
			} 
			/*
			 * controller의 checkID 메소드로 중복된 아이디가 있는지 확인한 후 
			 * 중복이 없으면 가입합니다. 중복이 있으면 while문을 다시 돕니다.
			 */
		}

		System.out.print("비밀번호를 입력해주세요 : ");
		String pw = Util.readStrForConsol();

		while(true) {
			System.out.print("비밀번호를 확인해주세요 : ");
			String pwCheck = Util.readStrForConsol();
			if(pwCheck.equals(pw)) {
				System.out.println("비밀번호가 일치합니다.");
				break;
			}else {
				System.out.println("비밀번호가 불일치합니다. 다시 입력해주세요.");
				continue;
			}
		}
		/*
		 * 패스워드를 두 번 받아 일치시에만 가입할 수 있씁니다.
		 */

		System.out.print("이름을 입력해주세요 : ");
		String name = Util.readStrForConsol();

		System.out.print("주민등록번호를 입력해주세요 : ");
		String ssn = Util.readStrForConsol();
		System.out.print("전화번호를 입력해주세요 : ");
		String phone = Util.readStrForConsol();
		System.out.print("이메일을 입력해주세요 : ");
		String email = Util.readStrForConsol();
		System.out.print("여권번호를 입력해주세요 : ");
		String pspt = Util.readStrForConsol();


		User user = new User(id, pw, name, ssn, phone, email, pspt, null, 0);
		userController.insertUser(user);
		
		System.out.println("회원가입이 완료되었습니다.");
		/*
		 * 입력받은 값을 한꺼번에 생성자에 넣어 객체를 생성하고, member객체를
		 * controller의 insertMember 메소드를 통해서 db에 삽입합니다.
		 */

	}
	
	public void userMenu(User thisUser) { //회원 메뉴
		while(true) {
			System.out.println(str2);
			int menuNum = Util.readIntForConsol();

			switch(menuNum) {
			case 1 :
				this.update(); //회원정보 수정
				break;
			case 2 : 
				this.check(thisUser); //예약 확인
				break;
			case 3 : 
				this.deleteMember(); //회원 탈퇴
				break;
			case 9 : 
				this.backToMain(); //메인 메뉴로
				break;
			default : 
				System.out.println("잘못 입력하셨습니다. 올바른 메뉴 번호를 입력해주세요.");
				continue;
			}
		}
	}
	
	public void update() { //회원정보 수정 메소드
		while(true) {
			System.out.println(str3);
			int menuNum = Util.readIntForConsol();

			switch(menuNum) {
			case 1 :
				this.updateMember(); //개인정보 수정
				break;
			case 2 : 
				this.airlineMemberNum(); //항공사 회원번호 수정
				break;
			case 3 : 
				this.updatePSPT(); //여권번호 수정
				break;
			case 9 :  //이전 화면으로 - 수정을 마치고 다시 회원메뉴로 돌아갑니다. 메인 x
				return;
			default : 
				System.out.println("잘못 입력하셨습니다. 올바른 메뉴 번호를 입력해주세요.");
				continue;
			}
		}

	}
	public void updateMember() { 
		/*
		 * 개인정보 수정 메소드이고, 여기서는 패스워드, 전화번호, 이메일만 수정할 수 있습니다.
		 * 여권번호와 항공사 회원번호는 따로 수정하고
		 * 마일리지는 결제에 따라 자동 업데이트인지 수기인지 잘 모르겠어서 일단 비워뒀습니다
		 */
		String pwNew = null;

		System.out.print("현재 비밀번호를 입력해주세요 : ");
		String pwNow = Util.readStrForConsol();

		if(thisUser.getUserPw().equals(pwNow)) { //로그인한 객체(thisMember)의 패스워드와 일치하는지 비교
			System.out.print("수정할 비밀번호를 입력해주세요 : ");
			pwNew = Util.readStrForConsol();	
			//패스워드는 항상 수정하는 것으로 하고, 동일한 패스워드여도 상관없으며 패스워드가 일치해야만 개인정보 수정할 수 있음
		}else {
			System.out.print("비밀번호가 불일치합니다.");
			System.out.println();
			return;
		}//패스워드 불일치시 아예 이전 페이지로 돌아가게 함

		System.out.print("수정할 전화번호를 입력해주세요 : ");
		String phone = Util.readStrForConsol(); 
		System.out.print("수정할 이메일을 입력해주세요 : ");
		String email = Util.readStrForConsol();

		int result = userController.updateUser(thisUser.getUserId(), pwNew, phone, email);
		
		System.out.println(result > 0 ? "수정 완료되었습니다." : "수정에 실패하였습니다.");
	}

	public void airlineMemberNum() { //항공사 회원번호 수정 메소드
		System.out.print("항공사 회원번호를 입력해주세요 : ");
		String aNum = Util.readStrForConsol();
		userController.airlineMemberNum(thisUser.getUserId(), aNum);
		//아이디로만 당사자를 찾아서 db에 수정하도록 했습니다.
	}
	public void updatePSPT() { //여궘번호 수정 메소드
		while(true) {

			System.out.print("여권번호를 입력해주세요 : ");
			String pspt = Util.readStrForConsol();
			
			int result = userController.updatePSPT(thisUser, thisUser.getUserId(), pspt);
			if(result > 0) {
				System.out.println("수정 완료되었습니다.");
				break;
			}
			else if(result == 0) {
				System.out.println("중복되는 여권번호가 있습니다. 다시 입력해주세요.");
				continue;
			}
			else {
				System.out.println("수정에 실패하였습니다.");
				continue;
			}
			/*
			 * 여권번호가 primary key인 만큼 중복되는 여권번호를 넣을 수 없도록
			 * controller에서 중복확인과정을 거치도록 했습니다.
			 */
		}

	}
	public void check(User thisUser) {
		JoinController jc = new JoinController();
		Join j = new Join();
		
		List<Join> jList = jc.selectUserReservationPayment(thisUser);
		
		System.out.println("----- 예약 내역 -----");
		
		for(int i = 0; i < jList.size(); i++) {
			System.out.println(jList.get(i));
			System.out.println("---------------------");
		}
	}
	
	public void deleteMember() { //회원 탈퇴 메소드.
		while(true) {
			/*
			 * 탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가하오니 신중하게 선택하시기 바랍니다.
			 * y . n
			 * if y
			 * 비밀번호 입력하세요 : 
			 * 	if 일치  -> 회원탈퇴가 완료되었습니다.
			 * 	else  -> 비밀번호를 다시 입력해주세요
			 * else n
			 * return 
			 */
			System.out.print("비밀번호를 입력해주세요 : ");
			String pwCheck = Util.readStrForConsol();
			if(pwCheck.equals(thisUser.getUserPw())) {
				userController.deleteUser(thisUser.getUserId());
				System.out.println("회원탈퇴가 완료되었습니다.");
				this.loginMainMenu();
				break;
			}else {
				System.out.println("비밀번호가 불일치합니다. 다시 입력해주세요.");
				continue;
			}
		}//비밀번호가 일치하지 않으면 탈퇴할 수 없습니다.

	}

	public void backToMain() {
		//메인화면 메소드로 연결
		new TotalMainMenu().mainMenu();
	}

}
