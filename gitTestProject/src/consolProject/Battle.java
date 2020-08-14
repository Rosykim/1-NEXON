package consolProject;

import java.util.Scanner;

// 민지가 담당한 전투를 담당하는 클래스
public class Battle {
	
	// 전투에 들어서가 위한 메소드. 매개변수로 플레이어와 에너미를 전달 받는다.
	public void SetBattle(Player player, Enemy enemy) {
		
		// 내가 어떤 행동을 할건지를 정하기 위한 스캐너.
		Scanner input = new Scanner(System.in);
	
		// 전투는 둘중 누군가가 끝날때까지 반복되어야 한다.
		while (true) {
			
			// 플레이어와 에너미의 이름, 체력, 공격력등을 표시해준다.
			System.out.println(player.name + " : lv: " + player.level + "   Hp : " + player.totalHp + "   Atk : " + player.totalAtk);
			System.out.println(enemy.name + ": " +  "Hp : " + enemy.currentHp + "   Atk : " + enemy.currentAtk);

			// 플레이어가 무엇을 할지 선택하자
			System.out.println("무엇을 할까?");
			System.out.println("1. 공격");
			System.out.print("나의 선택은? >> ");
			int selectNum = input.nextInt();
			
			// 공격을 선택했다면
			if (selectNum == 1) {
				
				// 플레이어의 공격함수를 호출하고 플레이어와 에너미를 매개변수로 전달한다.
				player.Attack(player, enemy);
				
				// 플레이어나 에너미 둘중 하나가 죽지 않았다면
				if (player.totalHp > 0 || enemy.currentHp > 0) {
					
					// 이번엔 에너미의 공격차례.
					enemy.Attack(enemy, player);
				}
			
			// 그 이외는 
			} else {
				System.out.println("잘못 입력했어요!");
			}
			
			// 둘중 하나라도 죽었다면 빠져나온다.
			if (player.totalHp <= 0 || enemy.currentHp <= 0) break;
		}
	}
}
