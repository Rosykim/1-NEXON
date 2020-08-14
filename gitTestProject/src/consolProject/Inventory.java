package consolProject;

import java.util.ArrayList;
import java.util.Scanner;

// 세진이가 담당한 플레이어의 인벤토리에 관리하는 클래스
public class Inventory {
	
	// 인벤토리에 넣기 위한 리스트
	ArrayList<Item> inven = new ArrayList<>();
	
	// 장비창에서 장착 하기 위한 리스트
	ArrayList<Item> myEquip = new ArrayList<>();
	
	
	// 인벤토리에 아이템을 추가 시키기 위한 메소드. 매개변수로 아이템과 선택한 번호를 전달받는다
	public void addItem(Item[] item, int selectNum) {
		
		// 선택한 번호에 맞는 아이템을 인벤토리에 넣어준다.
		inven.add(item[selectNum - 1]);
	}
	
	
	// 인벤토리의 모든것을 보여주는 메소드. 매개변수로 이하의 것들을 전달 받는다.
	public void ShowInventory(int select, Menu menu, Shop shop, Inventory inven, Worldmap world, Item[] item, Player player) {
		
		// 아이템을 선택해서 장착하거나 혹은 메인 메뉴로 나갈때까지 반복시켜야 하기 때문에 반복문을 사용하고
		while (true) {
			
			// 출력을 담당할 스트링변수
			String str = "";
			String str2 = "";
			
			// 플레이어의 상태창을 보여주기 위한 메소드
			Status(item, player);
			
			// 플레이어의 장비창을 보여주기 위한 메소드
			ShowEquip(item);
			
			// 플레이어의 인벤토리를 보여주기 위한 메소드
			myInven();
			
			// 어떤 장비를 착용시킬건지 출력시킬 문구
			System.out.println(str = "착용할 장비 번호를 선택해주세용");
			System.out.print(str2 = "0번을 누르면 메인 메뉴로 돌아가요 >> ");
			LogWriter.logWriter(str);
			LogWriter.logWriter(str2);
			
			// 선택한 번호를 저장시키고
			Scanner input = new Scanner(System.in);
			int selectNum = input.nextInt();
			
			// 몇번을 선택했는지 출력시켜주는 문구. 예를들면 1번을 선택했으면 1번을 선택했다고 나온다.
			String num = Integer.toString(selectNum) + "번을 선택했어요!";
			LogWriter.logWriter(num);
			
			// 메인메뉴 버튼을 눌렀다면
			if (selectNum == 0) {
				
				// 메뉴 클래스에 있는 showMenu메소드를 호출해서 메인메뉴로 빠져나간다.
				menu.showMenu(menu, shop, Inventory.this, world, item, player);
				break;
			
			// 아이템을 선택 했다면
			} else if (selectNum == 1 || selectNum == 2){
				
				// 해당 번호에 맞는 아이템을 장착시키기 위해 SetEquip메소드를 호출하여 파라메터를 넘겨준다.
				SetEquip(item, selectNum);
				
			// 그 이외의 번호를 선택했을경우 다시 입력해야 한다	
			} else {
				System.out.println("잘못된 번호에요!");
			}
		}
	}
	
	// 플레이어의 상태창을 보여주는 메소드. 매개변수로 아이템과 플레이어를 전달 받는다
	private void Status(Item[] item, Player player) {
		
		// 아이템의 능력치를 저장할 변수 2개
		int atk = 0;
		int hp = 0;
		
		// 출력을 위한 변수
		String[] str = new String[4];
		
		// 현재 내가 아이템을 장착 했다면
		if (myEquip.size() != 0) {
			
			// 아이템 갯수만큼 돌려주고
			for (int i = 0; i < myEquip.size(); i++) {
				
				// 아이템 타입이 "무기"일 경우
				if (myEquip.get(i).type.equals("무기")) {
					
					// 무기의 공격력을 저장하고
					atk = myEquip.get(i).atk;
					
					// 플레이어의 현재 공격력 + 무기공격력을 더해 총 공격력을 계산해준다.
					player.totalAtk = player.currentAtk + atk;
					
				// 아이템 타입이 "방어구"일 경우	
				} else if (myEquip.get(i).type.equals("방어구")) {
					
					// 방어구의 체력을 저장하고
					hp = myEquip.get(i).hp;
					
					// 플레이어의 현재 체력 + 아이템의 체력을 더해 총 체력을 계산해준다.
					player.totalHp = player.currentHp + hp;
					
					// 플레이어의 풀Hp는 플레이어의 총 체력이다.
					player.fullHp = player.totalHp;
				} 
			}
		}
		
		// 아래와 같은 상태창을 출력시켜준다.
		// 아이템을 장착하지 않았다면 공격력 : 5 + 0 으로 나오고
		// 아이템을 장착했다면 공격력 : 5 + 4 이런식으로 나온다.
		System.out.println(str[0] = "============ 상태창 ============");
		System.out.println(str[1] = "공격력 : " + player.currentAtk + " + " + atk);
		System.out.println(str[2] = "체력 : " + player.currentHp + " + " + hp);
		System.out.println(str[3] = "==============================\n");
		
		// txt에 출력시켜준다
		for (int i = 0; i < str.length; i++) {
			LogWriter.logWriter(str[i]);
		}
	}
	
	// 플레이어의 장비창을 보여주는 메소드
	private void ShowEquip(Item[] item) {
		
		// 출력을 위한 스트링 변수
		String[] str = new String[4];
		
		// 장비창을 출력시켜준다
		System.out.println(str[0] = "============ 장비칸 ============");
		
		// 아이템 이름을 저장할 변수
		String nameString = "";
		String nameString2 = "";
		
		// 내가 장비를 착용했다면
		if (myEquip.size() != 0) {
			
			// 사이즈만큼 돌려주고
			for (int i = 0; i < myEquip.size(); i++) {
				
				// 타입이 무기라면
				if (myEquip.get(i).type.equals("무기")) {
					
					// 아이템 이름을 저장한다.
					nameString = myEquip.get(i).name;
					
				// 타입이 방어구라면	
				} else if (myEquip.get(i).type.equals("방어구")) {
					
					// 방어구의 아이템 이름을 저장한다.
					nameString2 = myEquip.get(i).name;
				} 
			}
		}
		
		// 해당 장비를 착용했다면 어떤 아이템을 착용했는지 밑에서 출력시켜준다
		// 아이템을 착용하지 않았다면 아무것도 나오지 않는다.
		System.out.println(str[1] = "무기 : " + nameString);
		System.out.println(str[2] = "방어구 : " + nameString2);
		System.out.println(str[3] = "==============================\n");
		
		// txt에 출력시켜준다.
		for (int i = 0; i < str.length; i++) {
			LogWriter.logWriter(str[i]);
		}
	}
	
	// 아이템을 착용 시키기 위한 메소드
	private void SetEquip(Item[] item, int selectNum) {
		
		// 어떤 아이템을 착용시켰는지 해당 아이템의 번호를 받아 myEquip에 넣어주
		myEquip.add(item[selectNum -1]);
		
		// 해당 아이템을 착용했기 때문에 인벤토리에서는 해당 아이템을 더이상 보여주지 않기 위해 해당 아이템을 지운다.
		inven.remove(item[selectNum -1]);
	}
	
	// 내 인벤토리를 보여주기 위한 메소드
	private void myInven() {
		
		// 출력을 위한 변수
		String str = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		String str5 = "";
		
		// 인벤토리를 보여주기 위한 메소드
		System.out.println(str = "========= 나의 인벤토리 ==========");
		LogWriter.logWriter(str);
		
		// 인벤토리에 아이템이 있다면
		if (inven.size() != 0) {
			
			// 아이템 갯수만큼 돌려주고
			for (int i = 0; i < inven.size(); i++) {
				
				// 아이템의 고유번호, 이름, 설명을 출력시켜준다. 아이템이 없다면 아무것도 안나온다.
				System.out.println(str2 = "아이템  번호 : " + inven.get(i).num);
				System.out.println(str3 = "아이템  이름 : " + inven.get(i).name);
				System.out.println(str4 = "아이템  설명 : " + inven.get(i).description);

				LogWriter.logWriter(str2);
				LogWriter.logWriter(str3);
				LogWriter.logWriter(str4);				
			}
		} 
		System.out.println(str5 = "==============================");
		LogWriter.logWriter(str5);
	}
}
