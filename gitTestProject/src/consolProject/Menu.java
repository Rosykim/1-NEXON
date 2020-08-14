package consolProject;

import java.util.Scanner;

// 메뉴를 관리하는 메뉴 클래스
public class Menu {

	// 플레이어의 정보를 초기화 하기 위해서 만든 메소드
	public Player init() {
		
		// 출력 하기 위한 String 변수
		String[] str = new String[2];	
		System.out.println(str[0] = ("모험에 앞서 당신의 이름을 정해주세요 : "));
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		
		// 여기에서 플레이어의 객체를 생성해서 들고 다닌다. 매개변수는 입력한 이름을 받고
		// 체력이나 공격력 등은 자유롭게 지정해준다.
		Player player = new Player(name, 1, 10, 5, 0);
		System.out.println(str[1] = (player.name + "의 모험이 시작됩니다!"));
		
		// 이렇게 만든 플레이어의 정보를 반환시켜준다.
		return player;
	}
	
	// 메인 메뉴를 보여주기 위한 메소드. 각각의 매개변수에는 MAIN클래스에서 생성한 menu, shop, inven, world, item, player의 정보를 전달 받는다.
	public void showMenu(Menu menu, Shop shop, Inventory inven, Worldmap world, Item[] item, Player player) {
		
		// 출력하기 위한 변수
		String[] str = new String[4];
		
		// 플레이어의 체력가 쓰러질때까지 계속 돌린다.
		if (player.totalHp > 0) {
			
			// 메인 메뉴라는 문구를 출력시켜준다
			System.out.println(str[0] = ("=========== 메인 메뉴 ==========="));
		} 
		
		// 플레이어의 체력이 남아있으면서 목표거리까지 남아있을때까지 반복시킨다.
		// 이 둘중 하나라도 조건에 안맞다면 플레이어가 죽었거나 혹은 목표거리까지 이동했다는 뜻이다.
		// 메인 메뉴는 지정된 번호 이외의 것이 눌리면 계속 반복시켜야 하기 때문에 반복문을 사용해야 한다.
		while (player.totalHp > 0 && world.getRemain() > 0) {
			
			// 각각의 문구를 출력시켜준다
			System.out.println(str[1] = ("메뉴를 선택 해보세용"));
			System.out.println(str[2] = ("1. 상점, 2. 인벤토리, 3. 월드맵 이동"));
			System.out.print(str[3] = ("나의 선택은? >> "));
			
			// txt파일에 쓰기 위한 반복문
			for (int i = 0; i < str.length; i++) {
				
				// log에 데이터를 쓰기 위한 로그는 객체를 계속해서 생성하고 쓰고를 반복할 필요가 없기 때문에
				// 전역으로 만들어서 어디서든 불러오게 하였다.
				LogWriter.logWriter(str[i]);
			}
			
			// 그리고 Scanner를 사용하여 해당 번호를 저장할 변수를 선언한다.
			Scanner input = new Scanner(System.in);
			int selectNum = input.nextInt();
			
			// 그리고 해당 번호에 맞는 메뉴에 이동 하기 위해 mainMenu메소드를 호출하고
			// 각각의 정보를 그대로 넘겨준다.
			mainMenu(selectNum, menu, shop, inven, world, item, player);
		}
	}
	
	// 선택한 번호에 맞는 메뉴에 이동 하기 위한 메소드. 
	// 각각의 매개변수에는 위에서 선택한 selectNum과 MAIN클래스에서 생성한 menu, shop, inven, world, item, player의 정보를 전달 받는다.
	public void mainMenu(int selectNum, Menu menu, Shop shop, Inventory inven, Worldmap world, Item[] item, Player player) {
		
		// 출력해주기 위한 string 변수
		String str = "";
		
		// showMenu 메소드에서 선택한 selectNum변수를 전달받아 해당 번호에 맞는 메뉴로 이동한다
		switch (selectNum) {
		
			// 1번을 선택했을경우 상점으로 이동한다
			case 1:		
				System.out.println(str ="상점으로 이동 합니당.");
				LogWriter.logWriter(str);
				
				// shop클래스에서 ShowShopItem메소드를 호출하고 매개변수들을 전달해준다.
				shop.ShowShopItem(menu, shop, inven, world, item, player);
				
				// 해당 메뉴로 이동하고 탈출해야 한다.
				break;		
				
			// 2번을 선택했을경우 상태창(Status)로 이동한다.
			case 2:		
				System.out.println(str ="상태창으로 이동해용\n");
				LogWriter.logWriter(str);
				
				// inventory클래스에서 내 인벤정보를 보여주기 위한 ShowInventory메소드를 호출하고 매개변수들을 전달해준다.
				inven.ShowInventory(selectNum, menu, shop, inven, world, item, player);
				
				// 해당 메뉴로 이동하고 탈출해야 한다.
				break;		
				
			// 3번을 선택했을경우 월드맵으로 이동한다.
			case 3:
				System.out.println(str = "월드맵으로 이동 할게용!\n");
				LogWriter.logWriter(str);
				
				// worldmap클래스에서 던전에 들어가기 위한 Exploration메소드를 호출하고 매개변수들을 전달해준다.
				world.Exploration(player);
				
				// 해당 메뉴로 이동하고 탈출해야 한다.
				break;		
				
			// 그 외의 번호를 입력 했을경우 다시 번호를 입력하게 한다.	
			default:
				System.out.println(str = "다른 번호를 눌러주세요!!\n");
				LogWriter.logWriter(str);
				break;	
		}
	}
}
