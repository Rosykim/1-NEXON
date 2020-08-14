package consolProject;

import java.util.Scanner;

// �������� ����� �÷��̾� Ŭ����. Character�� ��ӹ޴´�
public class Player extends Character {
	
	// �÷��̾�� ����ϱ� ���� ����
	double maxExp;				// ���� �ϱ� ���� �ִ� ����ġ
	int overFlow;				// �ִ����ġ�� ���� ������� ���� ����ġ���� ���̸�ŭ ���̽����ش�
	int level;					// �÷��̾��� ����
	int fullHp;					// ������������ �ִ� ü�±��� ä���ֱ� ���� ����
	
	// ����Ʈ ������
	public Player() {}
	
	// �Ű������� �޴� ������. �̸��� ����, ü��, ���ݷ�, �������ġ���� �������ش�
	public Player(String name, int level, int hp, int atk, int currentExp) {
		
		// ������ ������ ����
		this.name = name;
		this.level = level;
		this.currentHp = hp;
		this.currentAtk = atk;
		this.totalAtk = currentAtk;
		this.totalHp = currentHp;
		this.currentExp = currentExp;
		this.fullHp = hp;
	}

	// ���� ������ �´� ������������ �����ؾ� �� ����ġ�� �����´�.
	public int getMaxExp() {
		return (int)(maxExp = level * 100 * 1.3);
	}
	
	// �÷��̾��� ���ݸ޼ҵ�. �Ķ���ͷ� �÷��̾�� ���ʹ̸� ���� �޴´�.
	public void Attack(Player player, Enemy enemy) {
		
		// ����� ���� ��Ʈ������
		String str = "";
		String str2 = "";

		// ���ʹ̰� ��� �ִٸ�
		if (enemy.currentHp > 0) {
			
			// �÷��̾ ���� ���ݷ����� �����ߴٴ� ������ ��½�Ų��.
			str = player.name + "(��)�� " + player.totalAtk + "�� �������� ������ �ߴ�!";
			System.out.println(str);
			LogWriter.logWriter(str);
			
			// ���ʹ��� TakeDamage�޼ҵ带 ȣ���Ͽ� ������ ����� ���ش�.
			enemy.TakeDamage(enemy, player);

			// ������ ��� �� ���ʹ̰� ����ִٸ�
			if (enemy.currentHp > 0) {
				
				// ���� �������� �޾Ҵٰ� ��½����ش�
				str2 = enemy.name + "��(��) " + player.totalAtk + "�� �������� �޾� " + enemy.currentHp +"�� ü���� ���Ҵ�\n";
				System.out.println(str2);
				
			// ���ʹ̰� �׾��ٸ�	
			} else {
				
				// ���ʹ̰� �׾��ٰ� ��½����ش�.
				str2 = enemy.name + "��(��) ü���� 0�� �Ǿ� ��������!\n";
				System.out.println(str2);
				LogWriter.logWriter(str2);
				
				// ����ġ ����� ���ֱ� ���� getExp �޼ҵ带 ȣ�� �Ѵ�.
				getExp(player, enemy);
				
				// ������������ �����ؾ� �� ����ġ�� ǥ�����ش�.
				System.out.println("���� Exp : " + player.currentExp + "  ���� �������� �ʿ��� Exp : " + player.maxExp);
			}
		} 
	}
	
	// �÷��̾ ���� �������� ��� �ϱ� ���� �޼ҵ�
	public void TakeDamage(Player player, Enemy enemy) {
		player.totalHp -= enemy.currentAtk;
	}
	
	// ����ġ ����� ���� �޼ҵ�
	private void getExp(Player player, Enemy enemy) {
		
		// ���� ����ġ�� ������� ���
		System.out.println(enemy.currentExp + "�� ����ġ�� ������ϴ�!");
		
		// ���� ����ġ�� 0�̱� ������ ���� ����ġ�� ��ø
		player.currentExp += enemy.currentExp;
		
		// ��Ż ����ġ���� ���ʹ��� ����ġ��ŭ ���ش�.
		player.maxExp -= enemy.currentExp;
		
		// �ִ� ���� ����ġ���� ���� ������ ����ġ�� �� ���ٸ� ������
		if (currentExp >= player.getMaxExp()) {
			System.out.println("������ �ö����ϴ�!!");
			
			player.level++;
			System.out.println("������ " + player.level + "�� �����Ͽ����ϴ�!");
			
			player.totalAtk += 1;
			System.out.println("���ݷ��� 1 �����Ͽ����ϴ�!");
			
			player.totalHp = fullHp;
			System.out.println("ü���� ���� á���ϴ�!");
			
			player.totalHp += 2;
			System.out.println("ü���� 2 �����Ͽ����ϴ�!");
			
			// ���� ���� ����ġ���� �ִ� ���� ����ġ�� ����
			player.overFlow = currentExp - (int)player.maxExp;
			
			// �������� ��� ���� ����ġ�� ������Ų��.
			player.currentExp = player.overFlow;
			
			// �������� �ִ� ����ġ�� ������ش�
			player.maxExp = player.getMaxExp();
		}
	}
}
