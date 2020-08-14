package consolProject;

import java.util.Scanner;

// �޴��� �����ϴ� �޴� Ŭ����
public class Menu {

	// �÷��̾��� ������ �ʱ�ȭ �ϱ� ���ؼ� ���� �޼ҵ�
	public Player init() {
		
		// ��� �ϱ� ���� String ����
		String[] str = new String[2];	
		System.out.println(str[0] = ("���迡 �ռ� ����� �̸��� �����ּ��� : "));
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		
		// ���⿡�� �÷��̾��� ��ü�� �����ؼ� ��� �ٴѴ�. �Ű������� �Է��� �̸��� �ް�
		// ü���̳� ���ݷ� ���� �����Ӱ� �������ش�.
		Player player = new Player(name, 1, 10, 5, 0);
		System.out.println(str[1] = (player.name + "�� ������ ���۵˴ϴ�!"));
		
		// �̷��� ���� �÷��̾��� ������ ��ȯ�����ش�.
		return player;
	}
	
	// ���� �޴��� �����ֱ� ���� �޼ҵ�. ������ �Ű��������� MAINŬ�������� ������ menu, shop, inven, world, item, player�� ������ ���� �޴´�.
	public void showMenu(Menu menu, Shop shop, Inventory inven, Worldmap world, Item[] item, Player player) {
		
		// ����ϱ� ���� ����
		String[] str = new String[4];
		
		// �÷��̾��� ü�°� ������������ ��� ������.
		if (player.totalHp > 0) {
			
			// ���� �޴���� ������ ��½����ش�
			System.out.println(str[0] = ("=========== ���� �޴� ==========="));
		} 
		
		// �÷��̾��� ü���� ���������鼭 ��ǥ�Ÿ����� �������������� �ݺ���Ų��.
		// �� ���� �ϳ��� ���ǿ� �ȸ´ٸ� �÷��̾ �׾��ų� Ȥ�� ��ǥ�Ÿ����� �̵��ߴٴ� ���̴�.
		// ���� �޴��� ������ ��ȣ �̿��� ���� ������ ��� �ݺ����Ѿ� �ϱ� ������ �ݺ����� ����ؾ� �Ѵ�.
		while (player.totalHp > 0 && world.getRemain() > 0) {
			
			// ������ ������ ��½����ش�
			System.out.println(str[1] = ("�޴��� ���� �غ�����"));
			System.out.println(str[2] = ("1. ����, 2. �κ��丮, 3. ����� �̵�"));
			System.out.print(str[3] = ("���� ������? >> "));
			
			// txt���Ͽ� ���� ���� �ݺ���
			for (int i = 0; i < str.length; i++) {
				
				// log�� �����͸� ���� ���� �α״� ��ü�� ����ؼ� �����ϰ� ���� �ݺ��� �ʿ䰡 ���� ������
				// �������� ���� ��𼭵� �ҷ����� �Ͽ���.
				LogWriter.logWriter(str[i]);
			}
			
			// �׸��� Scanner�� ����Ͽ� �ش� ��ȣ�� ������ ������ �����Ѵ�.
			Scanner input = new Scanner(System.in);
			int selectNum = input.nextInt();
			
			// �׸��� �ش� ��ȣ�� �´� �޴��� �̵� �ϱ� ���� mainMenu�޼ҵ带 ȣ���ϰ�
			// ������ ������ �״�� �Ѱ��ش�.
			mainMenu(selectNum, menu, shop, inven, world, item, player);
		}
	}
	
	// ������ ��ȣ�� �´� �޴��� �̵� �ϱ� ���� �޼ҵ�. 
	// ������ �Ű��������� ������ ������ selectNum�� MAINŬ�������� ������ menu, shop, inven, world, item, player�� ������ ���� �޴´�.
	public void mainMenu(int selectNum, Menu menu, Shop shop, Inventory inven, Worldmap world, Item[] item, Player player) {
		
		// ������ֱ� ���� string ����
		String str = "";
		
		// showMenu �޼ҵ忡�� ������ selectNum������ ���޹޾� �ش� ��ȣ�� �´� �޴��� �̵��Ѵ�
		switch (selectNum) {
		
			// 1���� ����������� �������� �̵��Ѵ�
			case 1:		
				System.out.println(str ="�������� �̵� �մϴ�.");
				LogWriter.logWriter(str);
				
				// shopŬ�������� ShowShopItem�޼ҵ带 ȣ���ϰ� �Ű��������� �������ش�.
				shop.ShowShopItem(menu, shop, inven, world, item, player);
				
				// �ش� �޴��� �̵��ϰ� Ż���ؾ� �Ѵ�.
				break;		
				
			// 2���� ����������� ����â(Status)�� �̵��Ѵ�.
			case 2:		
				System.out.println(str ="����â���� �̵��ؿ�\n");
				LogWriter.logWriter(str);
				
				// inventoryŬ�������� �� �κ������� �����ֱ� ���� ShowInventory�޼ҵ带 ȣ���ϰ� �Ű��������� �������ش�.
				inven.ShowInventory(selectNum, menu, shop, inven, world, item, player);
				
				// �ش� �޴��� �̵��ϰ� Ż���ؾ� �Ѵ�.
				break;		
				
			// 3���� ����������� ��������� �̵��Ѵ�.
			case 3:
				System.out.println(str = "��������� �̵� �ҰԿ�!\n");
				LogWriter.logWriter(str);
				
				// worldmapŬ�������� ������ ���� ���� Exploration�޼ҵ带 ȣ���ϰ� �Ű��������� �������ش�.
				world.Exploration(player);
				
				// �ش� �޴��� �̵��ϰ� Ż���ؾ� �Ѵ�.
				break;		
				
			// �� ���� ��ȣ�� �Է� ������� �ٽ� ��ȣ�� �Է��ϰ� �Ѵ�.	
			default:
				System.out.println(str = "�ٸ� ��ȣ�� �����ּ���!!\n");
				LogWriter.logWriter(str);
				break;	
		}
	}
}
