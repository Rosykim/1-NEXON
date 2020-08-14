package consolProject;

// �������� ����� ���ʹ̸� �����ϴ� Ŭ����
public class Enemy extends Character {
	
	// �ƹ��͵� ���� �� ������
	public Enemy() {}
	
	// �Ű������� �޴� ������
	public Enemy(int num) {
		
		// num�̶�� �Ű������� �޾Ƽ� �Ѱ��ش�
		selectEnemy(num);
	}
	
	// �Ѱܹ��� �Ű����� ������ ���ʹ̸� �����ϴ� �޼ҵ�
	private void selectEnemy(int num) {
		
		// �Ű������� �Ѱܹް� �� ���ڿ� �´� ���ʹ̸� ������.
		switch (num) {
		case 1:
			this.name = "������";
			this.currentAtk = 1;
			this.currentHp = 8;
			this.currentExp = 50;
			break;
			
		case 2:
			this.name = "��ť����";
			this.currentAtk = 2;
			this.currentHp = 14;
			this.currentExp = 80;
			break;
			
		case 3:
			this.name = "��";
			this.currentAtk = 3;
			this.currentHp = 20;
			this.currentExp = 100;
			break;
		}
	}
	
	// ���ʹ��� ���ݸ޼ҵ�, ���ʹ̿� �÷��̾ �Ű������� ���� �޴´�
	public void Attack(Enemy enemy, Player player) {
		
		// ����� ���� ��Ʈ������
		String str = "";
		String str2 = "";
		
		// ���� ���ʹ��� ü���� 0 �̻��̶�� = ����ִٸ�
		if (enemy.currentHp > 0) {
			
			// ���ʹ̰� �����ߴٴ� ��� ����
			str = enemy.name + "��(��)" + enemy.currentAtk + "�� �������� ������ �ߴ�!";
			System.out.println(str);
			LogWriter.logWriter(str);
			
			// �÷��̾��� TakeDamage�޼ҵ带 ȣ���ؼ� �������� �ް� �Ѵ�.
			player.TakeDamage(player, enemy);;

			// ���� �÷��̾��� ü���� 0 �̻��̶�� = ����ִٸ�
			if (player.totalHp > 0) {
				
				// �÷��̾ �������� �޾Ҵٴ°��� ��½����ش�.
				str2 = player.name + "(��)��" + enemy.currentAtk + "�� �������� �޾� " + player.totalHp +"�� ü���� ���Ҵ�\n";
				System.out.println(str2);
				
			// �׿� = �� �÷��̾ �׾��ٸ�
			} else {
				
				// �÷��̾��� ü���� 0�� �Ǿ� �������ٴ°��� ��½����ش�.
				str2 = player.name + "��(��) ü���� 0�� �Ǿ� ��������!\n";
				System.out.println(str2);
				LogWriter.logWriter(str2);
			}
		} 
	}
	
	// ���ʹ̰� �������� ���� �Լ�. 
	public void TakeDamage(Enemy enemy, Player player) {
		enemy.currentHp -= player.totalAtk;
	}
}
