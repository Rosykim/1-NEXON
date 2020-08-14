package consolProject;

import java.util.Scanner;

// 주현씨가 담당한 플레이어 클래스. Character를 상속받는다
public class Player extends Character {
	
	// 플레이어만이 사용하기 위한 변수
	double maxExp;				// 도달 하기 위한 최대 경험치
	int overFlow;				// 최대경험치에 도달 했을경우 현재 경험치와의 차이만큼 전이시켜준다
	int level;					// 플레이어의 레벨
	int fullHp;					// 레벨업했을때 최대 체력까지 채워주기 위한 변수
	
	// 디폴트 생성자
	public Player() {}
	
	// 매개변수를 받는 생성자. 이름과 레벨, 체력, 공격력, 현재경험치등을 설정해준다
	public Player(String name, int level, int hp, int atk, int currentExp) {
		
		// 이하의 정보를 설정
		this.name = name;
		this.level = level;
		this.currentHp = hp;
		this.currentAtk = atk;
		this.totalAtk = currentAtk;
		this.totalHp = currentHp;
		this.currentExp = currentExp;
		this.fullHp = hp;
	}

	// 현재 레벨에 맞는 다음레벨까지 도달해야 할 경험치를 가져온다.
	public int getMaxExp() {
		return (int)(maxExp = level * 100 * 1.3);
	}
	
	// 플레이어의 공격메소드. 파라메터로 플레이어와 에너미를 전달 받는다.
	public void Attack(Player player, Enemy enemy) {
		
		// 출력을 위한 스트링변수
		String str = "";
		String str2 = "";

		// 에너미가 살아 있다면
		if (enemy.currentHp > 0) {
			
			// 플레이어가 몇의 공격력으로 공격했다는 문구를 출력시킨다.
			str = player.name + "(이)가 " + player.totalAtk + "의 데미지로 공격을 했다!";
			System.out.println(str);
			LogWriter.logWriter(str);
			
			// 에너미의 TakeDamage메소드를 호출하여 데미지 계산을 해준다.
			enemy.TakeDamage(enemy, player);

			// 데미지 계산 후 에너미가 살아있다면
			if (enemy.currentHp > 0) {
				
				// 몇의 데미지를 받았다고 출력시켜준다
				str2 = enemy.name + "은(는) " + player.totalAtk + "의 데미지를 받아 " + enemy.currentHp +"의 체력이 남았다\n";
				System.out.println(str2);
				
			// 에너미가 죽었다면	
			} else {
				
				// 에너미가 죽었다고 출력시켜준다.
				str2 = enemy.name + "은(는) 체력이 0이 되어 쓰러졌다!\n";
				System.out.println(str2);
				LogWriter.logWriter(str2);
				
				// 경험치 계산을 해주기 위해 getExp 메소드를 호출 한다.
				getExp(player, enemy);
				
				// 다음레벨까지 도달해야 할 경험치를 표시해준다.
				System.out.println("현재 Exp : " + player.currentExp + "  다음 레벨까지 필요한 Exp : " + player.maxExp);
			}
		} 
	}
	
	// 플레이어가 받은 데미지를 계산 하기 위한 메소드
	public void TakeDamage(Player player, Enemy enemy) {
		player.totalHp -= enemy.currentAtk;
	}
	
	// 경험치 계산을 위한 메소드
	private void getExp(Player player, Enemy enemy) {
		
		// 얼마의 경험치를 얻었는지 출력
		System.out.println(enemy.currentExp + "의 경험치를 얻었습니다!");
		
		// 현재 경험치가 0이기 때문에 얻은 경험치를 중첩
		player.currentExp += enemy.currentExp;
		
		// 토탈 경험치에서 에너미의 경험치만큼 빼준다.
		player.maxExp -= enemy.currentExp;
		
		// 최대 도달 경험치보다 현재 누적된 경험치가 더 많다면 레벨업
		if (currentExp >= player.getMaxExp()) {
			System.out.println("레벨이 올랐습니다!!");
			
			player.level++;
			System.out.println("레벨이 " + player.level + "로 증가하였습니다!");
			
			player.totalAtk += 1;
			System.out.println("공격력이 1 증가하였습니다!");
			
			player.totalHp = fullHp;
			System.out.println("체력이 가득 찼습니다!");
			
			player.totalHp += 2;
			System.out.println("체력이 2 증가하였습니다!");
			
			// 현재 누적 경험치에서 최대 도달 경험치를 빼고
			player.overFlow = currentExp - (int)player.maxExp;
			
			// 나머지는 모두 현재 경험치로 이전시킨다.
			player.currentExp = player.overFlow;
			
			// 다음레벨 최대 경험치를 계산해준다
			player.maxExp = player.getMaxExp();
		}
	}
}
