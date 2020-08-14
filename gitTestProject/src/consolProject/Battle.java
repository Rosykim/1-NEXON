package consolProject;

import java.util.Scanner;

// ������ ����� ������ ����ϴ� Ŭ����
public class Battle {
	
	// ������ ���� ���� �޼ҵ�. �Ű������� �÷��̾�� ���ʹ̸� ���� �޴´�.
	public void SetBattle(Player player, Enemy enemy) {
		
		// ���� � �ൿ�� �Ұ����� ���ϱ� ���� ��ĳ��.
		Scanner input = new Scanner(System.in);
	
		// ������ ���� �������� ���������� �ݺ��Ǿ�� �Ѵ�.
		while (true) {
			
			// �÷��̾�� ���ʹ��� �̸�, ü��, ���ݷµ��� ǥ�����ش�.
			System.out.println(player.name + " : lv: " + player.level + "   Hp : " + player.totalHp + "   Atk : " + player.totalAtk);
			System.out.println(enemy.name + ": " +  "Hp : " + enemy.currentHp + "   Atk : " + enemy.currentAtk);

			// �÷��̾ ������ ���� ��������
			System.out.println("������ �ұ�?");
			System.out.println("1. ����");
			System.out.print("���� ������? >> ");
			int selectNum = input.nextInt();
			
			// ������ �����ߴٸ�
			if (selectNum == 1) {
				
				// �÷��̾��� �����Լ��� ȣ���ϰ� �÷��̾�� ���ʹ̸� �Ű������� �����Ѵ�.
				player.Attack(player, enemy);
				
				// �÷��̾ ���ʹ� ���� �ϳ��� ���� �ʾҴٸ�
				if (player.totalHp > 0 || enemy.currentHp > 0) {
					
					// �̹��� ���ʹ��� ��������.
					enemy.Attack(enemy, player);
				}
			
			// �� �ܴ̿� 
			} else {
				System.out.println("�߸� �Է��߾��!");
			}
			
			// ���� �ϳ��� �׾��ٸ� �������´�.
			if (player.totalHp <= 0 || enemy.currentHp <= 0) break;
		}
	}
}
