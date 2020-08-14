package consolProject;

// 주현씨가 담당한 에너미를 관리하는 클래스
public class Enemy extends Character {
	
	// 아무것도 없는 빈 생성자
	public Enemy() {}
	
	// 매개변수를 받는 생성자
	public Enemy(int num) {
		
		// num이라는 매개변수를 받아서 넘겨준다
		selectEnemy(num);
	}
	
	// 넘겨받은 매개변수 가지고 에너미를 선택하는 메소드
	private void selectEnemy(int num) {
		
		// 매개변수를 넘겨받고 각 숫자에 맞는 에너미를 만들어낸다.
		switch (num) {
		case 1:
			this.name = "슬라임";
			this.currentAtk = 1;
			this.currentHp = 8;
			this.currentExp = 50;
			break;
			
		case 2:
			this.name = "서큐버스";
			this.currentAtk = 2;
			this.currentHp = 14;
			this.currentExp = 80;
			break;
			
		case 3:
			this.name = "골렘";
			this.currentAtk = 3;
			this.currentHp = 20;
			this.currentExp = 100;
			break;
		}
	}
	
	// 에너미의 공격메소드, 에너미와 플레이어를 매개변수로 전달 받는다
	public void Attack(Enemy enemy, Player player) {
		
		// 출력을 위한 스트링변수
		String str = "";
		String str2 = "";
		
		// 현재 에너미의 체력이 0 이상이라면 = 살아있다면
		if (enemy.currentHp > 0) {
			
			// 에너미가 공격했다는 출력 문구
			str = enemy.name + "이(가)" + enemy.currentAtk + "의 데미지로 공격을 했다!";
			System.out.println(str);
			LogWriter.logWriter(str);
			
			// 플레이어의 TakeDamage메소드를 호출해서 데미지를 받게 한다.
			player.TakeDamage(player, enemy);;

			// 현재 플레이어의 체력이 0 이상이라면 = 살아있다면
			if (player.totalHp > 0) {
				
				// 플레이어가 데미지를 받았다는것을 출력시켜준다.
				str2 = player.name + "(은)는" + enemy.currentAtk + "의 데미지를 받아 " + player.totalHp +"의 체력이 남았다\n";
				System.out.println(str2);
				
			// 그외 = 즉 플레이어가 죽었다면
			} else {
				
				// 플레이어의 체력이 0이 되어 쓰러졌다는것을 출력시켜준다.
				str2 = player.name + "은(는) 체력이 0이 되어 쓰러졌다!\n";
				System.out.println(str2);
				LogWriter.logWriter(str2);
			}
		} 
	}
	
	// 에너미가 데미지를 받은 함수. 
	public void TakeDamage(Enemy enemy, Player player) {
		enemy.currentHp -= player.totalAtk;
	}
}
