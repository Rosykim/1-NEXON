package consolProject;

import java.util.ArrayList;
import java.util.Scanner;

// ������ ���� �������� �����ϴ� ���� Ŭ����
public class Shop {

	Scanner input = new Scanner(System.in);					// �Է� �ޱ� ���� ��ĳ
	ArrayList<Item> shopItems = new ArrayList<>();			// ������ �����ϱ� ���� ArrayList

	// �����ڿ��� ������ �Ű������� ���� �޴´�
	public Shop(Item[] item) {
		
		// ������ Ŭ������ �ִ� �������� �Է����ش�
		item[0] = new Item();							// 1�� �������� ����
		item[0].num = 1;								// ������ ������ȣ�� 1������
		item[0].type = "����";							// Ÿ���� ����
		item[0].name = "������ �˰��� ���� ��";				// ������ �̸�
		item[0].description = "������ ���� �ƴϴ�.";			// ������ ����
		item[0].atk = 4;								// �ɷ�ġ
		item[0].price = 1000;							// ����
		shopItems.add(item[0]);							// �̷��� ���� ������ ������ ����ִ´�.
		
		// ���� ���������� 2�� �������� ������ �ִ´�
		item[1] = new Item();
		item[1].num = 2;
		item[1].type = "��";
		item[1].name = "������ ������ �˾Ҵ� ����";
		item[1].description = "�����ߴ�. ������ ������ �ƴϾ���.";
		item[1].hp = 3;
		item[1].price = 1000;
		shopItems.add(item[1]);							// 2�� �������� ������ ���� �ִ´�.
	}
	
	// �������� �������� �����ֱ� ���� �޼ҵ�. ������ �Ű��������� MAINŬ�������� ������ menu, shop, inven, world, item, player�� ������ ���� �޴´�. 
	public void ShowShopItem(Menu menu, Shop shop, Inventory inven, Worldmap world, Item[] item, Player player) {
		
		// ������ ��ȣ �̿��� ���� ������ ��� �ݺ����Ѿ� �ϱ� ������ �ݺ����� ����ؾ� �Ѵ�.
		while (true) {
			// txt�� ���� ���� ����
			String str = "";
			String str2 = "";
			String str3 = "";
			String str4 = "";
			String str5 = "";
			String str6 = "";

			// ���� ����Ʈ�� �ִ� ������ ������ ���� ������ ������ ����(������ ��ȣ, �̸�, ����, ����)�� ��½����ش�.
			for (int i = 0; i < shopItems.size(); i++) {
				System.out.println(str = ("============================"));
				System.out.println(str2 = ("������ ��ȣ : " + shopItems.get(i).num));
				System.out.println(str3 = ("������ �̸� : " + shopItems.get(i).name));
				System.out.println(str4 = ("������ ���� : " + shopItems.get(i).description));
				System.out.println(str5 = ("������ ���� : " + shopItems.get(i).price));
				System.out.println(str6 = ("============================"));
				
				// txt�� ����
				LogWriter.logWriter(str);
				LogWriter.logWriter(str2);
				LogWriter.logWriter(str3);
				LogWriter.logWriter(str4);
				LogWriter.logWriter(str5);
				LogWriter.logWriter(str6);
			}

			// ���� ������ �������� �ϳ��� ���� ���
			if (shopItems.size() == 0) {
				
				// ������ ���� ������ ��½����ش�
				System.out.println(str = "��� �ִ� �������� �����! ");
				System.out.print(str2 = "0���� ������ ���� �޴��� ���ư��� >> ");
				
			// �������� ���� ���
			} else {
				
				// �ش� ������ ��½����ش�
				System.out.println(str = "�� �췡��? ��ȣ ��󺸼���");
				System.out.print(str2 = "0���� ������ ���� �޴��� ���ư��� >> ");
			}

			// ��ȣ�� �Է� �޴´�
			int selectNum = input.nextInt();
			
			// ����� �����ߴ��� ��½����ش�.
			String num = Integer.toString(selectNum) + "���� �����߾��!";
			
			// txt�� ����
			LogWriter.logWriter(str);
			LogWriter.logWriter(str2);
			LogWriter.logWriter(num);

			// 0���� ���� ���� ���
			if (selectNum == 0) {
				
				// ���� �޴��� ���ư��� ���� MenuŬ������ �ִ� showMenu �޼ҵ带 ȣ���Ѵ�.
				// ������ ������ �״�� ��� �ٽ� �������ش�.
				System.out.println(str = "�޴��� ���ư���!");
				LogWriter.logWriter(str);
				menu.showMenu(menu, Shop.this, inven, world, item, player);
				
				// �׸��� �ݺ����� �������´�.
				break;
				
			// �ش� ��ȣ�� �´� �������� ���� ���
			} else if ((selectNum == 1 || selectNum == 2) && shopItems.size() != 0) {
				
				// � �������� �����ߴ��� ��½����ش�.
				System.out.println(str = (item[selectNum - 1].name + "�� �����߽��ϴ�!\n"));
				
				// BuyItem�޼ҵ带 ȣ���Ͽ� �� ������ �����Ѵ�.
				BuyItem(item, selectNum, inven);
				
				// �ش� �������� ���� �ߴٸ� �������� �ش� �������� ��Ͽ��� �Ⱥ��̰� �ϱ� ���� �ش� �������� ������ �����.
				shopItems.remove(item[selectNum -1]);
				
				// ���� ��¹��� txt�� ��½�Ų��
				LogWriter.logWriter(str);
			} 
		}
	}
	
	// �������� ���� �ϸ� �� �κ��丮�� �ֱ� ���� �޼ҵ�. �Ű������� ������ �������� ����, ��ȣ�� �Ѱ��ش�.
	public void BuyItem(Item[] item, int selectNum, Inventory inven) {
		
		// �κ��丮 Ŭ������ �ִ� addItem�޼ҵ带 ȣ���ϰ� �����۰� ������ ��ȣ�� ������ �Ѱ��ش�.
		inven.addItem(item, selectNum);
	}
}
