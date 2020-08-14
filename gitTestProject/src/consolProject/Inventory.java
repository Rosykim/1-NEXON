package consolProject;

import java.util.ArrayList;
import java.util.Scanner;

// �����̰� ����� �÷��̾��� �κ��丮�� �����ϴ� Ŭ����
public class Inventory {
	
	// �κ��丮�� �ֱ� ���� ����Ʈ
	ArrayList<Item> inven = new ArrayList<>();
	
	// ���â���� ���� �ϱ� ���� ����Ʈ
	ArrayList<Item> myEquip = new ArrayList<>();
	
	
	// �κ��丮�� �������� �߰� ��Ű�� ���� �޼ҵ�. �Ű������� �����۰� ������ ��ȣ�� ���޹޴´�
	public void addItem(Item[] item, int selectNum) {
		
		// ������ ��ȣ�� �´� �������� �κ��丮�� �־��ش�.
		inven.add(item[selectNum - 1]);
	}
	
	
	// �κ��丮�� ������ �����ִ� �޼ҵ�. �Ű������� ������ �͵��� ���� �޴´�.
	public void ShowInventory(int select, Menu menu, Shop shop, Inventory inven, Worldmap world, Item[] item, Player player) {
		
		// �������� �����ؼ� �����ϰų� Ȥ�� ���� �޴��� ���������� �ݺ����Ѿ� �ϱ� ������ �ݺ����� ����ϰ�
		while (true) {
			
			// ����� ����� ��Ʈ������
			String str = "";
			String str2 = "";
			
			// �÷��̾��� ����â�� �����ֱ� ���� �޼ҵ�
			Status(item, player);
			
			// �÷��̾��� ���â�� �����ֱ� ���� �޼ҵ�
			ShowEquip(item);
			
			// �÷��̾��� �κ��丮�� �����ֱ� ���� �޼ҵ�
			myInven();
			
			// � ��� �����ų���� ��½�ų ����
			System.out.println(str = "������ ��� ��ȣ�� �������ּ���");
			System.out.print(str2 = "0���� ������ ���� �޴��� ���ư��� >> ");
			LogWriter.logWriter(str);
			LogWriter.logWriter(str2);
			
			// ������ ��ȣ�� �����Ű��
			Scanner input = new Scanner(System.in);
			int selectNum = input.nextInt();
			
			// ����� �����ߴ��� ��½����ִ� ����. ������� 1���� ���������� 1���� �����ߴٰ� ���´�.
			String num = Integer.toString(selectNum) + "���� �����߾��!";
			LogWriter.logWriter(num);
			
			// ���θ޴� ��ư�� �����ٸ�
			if (selectNum == 0) {
				
				// �޴� Ŭ������ �ִ� showMenu�޼ҵ带 ȣ���ؼ� ���θ޴��� ����������.
				menu.showMenu(menu, shop, Inventory.this, world, item, player);
				break;
			
			// �������� ���� �ߴٸ�
			} else if (selectNum == 1 || selectNum == 2){
				
				// �ش� ��ȣ�� �´� �������� ������Ű�� ���� SetEquip�޼ҵ带 ȣ���Ͽ� �Ķ���͸� �Ѱ��ش�.
				SetEquip(item, selectNum);
				
			// �� �̿��� ��ȣ�� ����������� �ٽ� �Է��ؾ� �Ѵ�	
			} else {
				System.out.println("�߸��� ��ȣ����!");
			}
		}
	}
	
	// �÷��̾��� ����â�� �����ִ� �޼ҵ�. �Ű������� �����۰� �÷��̾ ���� �޴´�
	private void Status(Item[] item, Player player) {
		
		// �������� �ɷ�ġ�� ������ ���� 2��
		int atk = 0;
		int hp = 0;
		
		// ����� ���� ����
		String[] str = new String[4];
		
		// ���� ���� �������� ���� �ߴٸ�
		if (myEquip.size() != 0) {
			
			// ������ ������ŭ �����ְ�
			for (int i = 0; i < myEquip.size(); i++) {
				
				// ������ Ÿ���� "����"�� ���
				if (myEquip.get(i).type.equals("����")) {
					
					// ������ ���ݷ��� �����ϰ�
					atk = myEquip.get(i).atk;
					
					// �÷��̾��� ���� ���ݷ� + ������ݷ��� ���� �� ���ݷ��� ������ش�.
					player.totalAtk = player.currentAtk + atk;
					
				// ������ Ÿ���� "��"�� ���	
				} else if (myEquip.get(i).type.equals("��")) {
					
					// ���� ü���� �����ϰ�
					hp = myEquip.get(i).hp;
					
					// �÷��̾��� ���� ü�� + �������� ü���� ���� �� ü���� ������ش�.
					player.totalHp = player.currentHp + hp;
					
					// �÷��̾��� ǮHp�� �÷��̾��� �� ü���̴�.
					player.fullHp = player.totalHp;
				} 
			}
		}
		
		// �Ʒ��� ���� ����â�� ��½����ش�.
		// �������� �������� �ʾҴٸ� ���ݷ� : 5 + 0 ���� ������
		// �������� �����ߴٸ� ���ݷ� : 5 + 4 �̷������� ���´�.
		System.out.println(str[0] = "============ ����â ============");
		System.out.println(str[1] = "���ݷ� : " + player.currentAtk + " + " + atk);
		System.out.println(str[2] = "ü�� : " + player.currentHp + " + " + hp);
		System.out.println(str[3] = "==============================\n");
		
		// txt�� ��½����ش�
		for (int i = 0; i < str.length; i++) {
			LogWriter.logWriter(str[i]);
		}
	}
	
	// �÷��̾��� ���â�� �����ִ� �޼ҵ�
	private void ShowEquip(Item[] item) {
		
		// ����� ���� ��Ʈ�� ����
		String[] str = new String[4];
		
		// ���â�� ��½����ش�
		System.out.println(str[0] = "============ ���ĭ ============");
		
		// ������ �̸��� ������ ����
		String nameString = "";
		String nameString2 = "";
		
		// ���� ��� �����ߴٸ�
		if (myEquip.size() != 0) {
			
			// �����ŭ �����ְ�
			for (int i = 0; i < myEquip.size(); i++) {
				
				// Ÿ���� ������
				if (myEquip.get(i).type.equals("����")) {
					
					// ������ �̸��� �����Ѵ�.
					nameString = myEquip.get(i).name;
					
				// Ÿ���� �����	
				} else if (myEquip.get(i).type.equals("��")) {
					
					// ���� ������ �̸��� �����Ѵ�.
					nameString2 = myEquip.get(i).name;
				} 
			}
		}
		
		// �ش� ��� �����ߴٸ� � �������� �����ߴ��� �ؿ��� ��½����ش�
		// �������� �������� �ʾҴٸ� �ƹ��͵� ������ �ʴ´�.
		System.out.println(str[1] = "���� : " + nameString);
		System.out.println(str[2] = "�� : " + nameString2);
		System.out.println(str[3] = "==============================\n");
		
		// txt�� ��½����ش�.
		for (int i = 0; i < str.length; i++) {
			LogWriter.logWriter(str[i]);
		}
	}
	
	// �������� ���� ��Ű�� ���� �޼ҵ�
	private void SetEquip(Item[] item, int selectNum) {
		
		// � �������� ������״��� �ش� �������� ��ȣ�� �޾� myEquip�� �־���
		myEquip.add(item[selectNum -1]);
		
		// �ش� �������� �����߱� ������ �κ��丮������ �ش� �������� ���̻� �������� �ʱ� ���� �ش� �������� �����.
		inven.remove(item[selectNum -1]);
	}
	
	// �� �κ��丮�� �����ֱ� ���� �޼ҵ�
	private void myInven() {
		
		// ����� ���� ����
		String str = "";
		String str2 = "";
		String str3 = "";
		String str4 = "";
		String str5 = "";
		
		// �κ��丮�� �����ֱ� ���� �޼ҵ�
		System.out.println(str = "========= ���� �κ��丮 ==========");
		LogWriter.logWriter(str);
		
		// �κ��丮�� �������� �ִٸ�
		if (inven.size() != 0) {
			
			// ������ ������ŭ �����ְ�
			for (int i = 0; i < inven.size(); i++) {
				
				// �������� ������ȣ, �̸�, ������ ��½����ش�. �������� ���ٸ� �ƹ��͵� �ȳ��´�.
				System.out.println(str2 = "������  ��ȣ : " + inven.get(i).num);
				System.out.println(str3 = "������  �̸� : " + inven.get(i).name);
				System.out.println(str4 = "������  ���� : " + inven.get(i).description);

				LogWriter.logWriter(str2);
				LogWriter.logWriter(str3);
				LogWriter.logWriter(str4);				
			}
		} 
		System.out.println(str5 = "==============================");
		LogWriter.logWriter(str5);
	}
}
