package consolProject;

import java.util.ArrayList;
import java.util.Scanner;

// 상점에 대한 정보들을 관리하는 상점 클래스
public class Shop {

	Scanner input = new Scanner(System.in);					// 입력 받기 위한 스캐
	ArrayList<Item> shopItems = new ArrayList<>();			// 상점에 진열하기 위한 ArrayList

	// 생성자에서 아이템 매개변수를 전달 받는다
	public Shop(Item[] item) {
		
		// 아이템 클래스에 있는 정보들을 입력해준다
		item[0] = new Item();							// 1번 아이템을 생성
		item[0].num = 1;								// 아이템 고유번호를 1번으로
		item[0].type = "무기";							// 타입은 무기
		item[0].name = "전설의 검같이 생긴 검";				// 아이템 이름
		item[0].description = "전설의 검이 아니다.";			// 아이템 설명
		item[0].atk = 4;								// 능력치
		item[0].price = 1000;							// 가격
		shopItems.add(item[0]);							// 이렇게 만든 아이템 정보를 집어넣는다.
		
		// 위와 마찬가지로 2번 아이템의 정보를 넣는다
		item[1] = new Item();
		item[1].num = 2;
		item[1].type = "방어구";
		item[1].name = "전설의 방어구인줄 알았던 갑옷";
		item[1].description = "사기당했다. 전설의 갑옷이 아니었다.";
		item[1].hp = 3;
		item[1].price = 1000;
		shopItems.add(item[1]);							// 2번 아이템의 정보를 집어 넣는다.
	}
	
	// 상점에서 아이템을 보여주기 위한 메소드. 각각의 매개변수에는 MAIN클래스에서 생성한 menu, shop, inven, world, item, player의 정보를 전달 받는다. 
	public void ShowShopItem(Menu menu, Shop shop, Inventory inven, Worldmap world, Item[] item, Player player) {
		
		// 지정된 번호 이외의 것이 눌리면 계속 반복시켜야 하기 때문에 반복문을 사용해야 한다.
		while (true) {
			// txt에 쓰기 위한 변수
			String str = "";
			String str2 = "";
			String str3 = "";
			String str4 = "";
			String str5 = "";
			String str6 = "";

			// 현재 리스트에 있는 아이템 갯수에 따라서 각각의 아이템 정보(아이템 번호, 이름, 설명, 가격)를 출력시켜준다.
			for (int i = 0; i < shopItems.size(); i++) {
				System.out.println(str = ("============================"));
				System.out.println(str2 = ("아이템 번호 : " + shopItems.get(i).num));
				System.out.println(str3 = ("아이템 이름 : " + shopItems.get(i).name));
				System.out.println(str4 = ("아이템 설명 : " + shopItems.get(i).description));
				System.out.println(str5 = ("아이템 가격 : " + shopItems.get(i).price));
				System.out.println(str6 = ("============================"));
				
				// txt에 쓴다
				LogWriter.logWriter(str);
				LogWriter.logWriter(str2);
				LogWriter.logWriter(str3);
				LogWriter.logWriter(str4);
				LogWriter.logWriter(str5);
				LogWriter.logWriter(str6);
			}

			// 현재 상점에 아이템이 하나도 없을 경우
			if (shopItems.size() == 0) {
				
				// 다음과 같은 문구를 출력시켜준다
				System.out.println(str = "재고 있는 아이템이 없어요! ");
				System.out.print(str2 = "0번을 누르면 메인 메뉴로 돌아가요 >> ");
				
			// 아이템이 있을 경우
			} else {
				
				// 해당 문구를 출력시켜준다
				System.out.println(str = "뭐 살래용? 번호 골라보세용");
				System.out.print(str2 = "0번을 누르면 메인 메뉴로 돌아가요 >> ");
			}

			// 번호를 입력 받는다
			int selectNum = input.nextInt();
			
			// 몇번을 선택했는지 출력시켜준다.
			String num = Integer.toString(selectNum) + "번을 선택했어요!";
			
			// txt에 쓴다
			LogWriter.logWriter(str);
			LogWriter.logWriter(str2);
			LogWriter.logWriter(num);

			// 0번을 선택 했을 경우
			if (selectNum == 0) {
				
				// 메인 메뉴로 돌아가기 위해 Menu클래스에 있는 showMenu 메소드를 호출한다.
				// 각각의 정보는 그대로 들고 다시 전달해준다.
				System.out.println(str = "메뉴로 돌아가요!");
				LogWriter.logWriter(str);
				menu.showMenu(menu, Shop.this, inven, world, item, player);
				
				// 그리고 반복문을 빠져나온다.
				break;
				
			// 해당 번호에 맞는 아이템이 있을 경우
			} else if ((selectNum == 1 || selectNum == 2) && shopItems.size() != 0) {
				
				// 어떤 아이템을 구매했는지 출력시켜준다.
				System.out.println(str = (item[selectNum - 1].name + "을 구입했습니다!\n"));
				
				// BuyItem메소드를 호출하여 각 정보를 전달한다.
				BuyItem(item, selectNum, inven);
				
				// 해당 아이템을 구매 했다면 상점에서 해당 아이템을 목록에서 안보이게 하기 위해 해당 아이템의 정보를 지운다.
				shopItems.remove(item[selectNum -1]);
				
				// 위에 출력문을 txt에 출력시킨다
				LogWriter.logWriter(str);
			} 
		}
	}
	
	// 아이템을 구매 하면 내 인벤토리에 넣기 위한 메소드. 매개변수로 구매한 아이템의 정보, 번호를 넘겨준다.
	public void BuyItem(Item[] item, int selectNum, Inventory inven) {
		
		// 인벤토리 클래스에 있는 addItem메소드를 호출하고 아이템과 선택한 번호의 정보를 넘겨준다.
		inven.addItem(item, selectNum);
	}
}
