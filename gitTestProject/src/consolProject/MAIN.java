package consolProject;

// ����̰� ����� ������ �Ǵ� �� �״�� ���� Ŭ����
public class MAIN {
	
	// ���� �Լ����� �����Ѵ�
	public static void main(String[] args) {
		
		// ������ Ŭ���� ��ü�� �������ش�.
		Item[] item = new Item[2];					// ������ ��ü�� �ΰ� ����
		Shop shop = new Shop(item);					// ���� ��ü �����ؼ� ������ ������ �������� �޴´�
		Menu menu = new Menu();						// �޴� ��ü ����
		Inventory inventory = new Inventory();		// �κ� ��ü ����
		Worldmap worldmap = new Worldmap();			// ����� ��ü ����
		
		// ������ ������ ��ü���� ������ �ٴϱ� ���� �Ű������� �������ش�.
		// ���� �޴��� �����ֱ� ���� �޼ҵ带 Menu Ŭ���� �ȿ��� �ҷ��´�.
		menu.showMenu(menu, shop, inventory, worldmap, item, menu.init());
	}
}
