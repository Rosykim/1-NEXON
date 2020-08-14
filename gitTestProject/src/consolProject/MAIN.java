package consolProject;

// 희원이가 담당한 메인이 되는 말 그대로 메인 클래스
public class MAIN {
	
	// 메인 함수에서 실행한다
	public static void main(String[] args) {
		
		// 각각의 클래스 객체를 생성해준다.
		Item[] item = new Item[2];					// 아이템 객체를 두개 생성
		Shop shop = new Shop(item);					// 상점 객체 생성해서 위에서 생성한 아이템을 받는다
		Menu menu = new Menu();						// 메뉴 객체 생성
		Inventory inventory = new Inventory();		// 인벤 객체 생성
		Worldmap worldmap = new Worldmap();			// 월드맵 객체 생성
		
		// 위에서 생성한 객체들을 가지고 다니기 위해 매개변수로 전달해준다.
		// 메인 메뉴를 보여주기 위한 메소드를 Menu 클래스 안에서 불러온다.
		menu.showMenu(menu, shop, inventory, worldmap, item, menu.init());
	}
}
