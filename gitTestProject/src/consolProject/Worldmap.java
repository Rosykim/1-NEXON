package consolProject;

import java.util.Scanner;

// 민지가 담당한 던전에 들어와 모험을 하기 위한 클래스
public class Worldmap {
	
	// 목표 이동거리. 샘플로 20으로 넣었음.
	int remain = 20;
	
	// 목표거리까지 도달한 값을 판별하기 위해서 get함수로 만들었다.
	public int getRemain() {
		return remain;
	}

	// 던전에서 이동 하기 위한 클래스. 적과 만나면 전투에 들어서고 그 이외는 이동을 한다.
	public void Exploration(Player player) {
	
		Scanner input = new Scanner(System.in);
		
		// 배틀에 돌입하기 위한 배틀 객체를 생성한다.
		Battle battle = new Battle();

		// 내가 이동한 거리를 표시 해주기 위한 변
		int move = 0;
		
		// 전투에 들어섰는지를 체크하기 위한 Boolean 변수
		// 전투에 아직 들어서지 않았기 때문에 초기는 false
		boolean enCount = false;
		
		// 던전에 들어오면 던전에 들어왔다고 먼저 출력해준다. 이건 한번만 출력해줌.
		System.out.println("================================");
		System.out.println("======= 던전에 들어왔습니다! =======");
		System.out.println("================================");
		
		// 목표이동거리를 다 채울때까지 반복시켜줘야 하기 때문에 반복문을 쓴다.
		// 플레이어가 살아있으면서 남은 거리가 0보다 클때까지 반복시킨다. 목표거리에 도달하면 자동으로 빠져 나온다.
		while (remain > 0 && player.totalHp > 0) {
			
			// 전투에 들어서지 않았다면 = 즉 이동중이라면
			if (enCount == false) {
				
				// 남은 거리를 표시해준다.
				System.out.println("남은 거리 : " + remain + "m");
				
				// 내가 얼만큼 이동시킬건지 입력 받는다.
				System.out.print("얼마나 이동 할래요? ");
				move = input.nextInt();
				
				// 내가 입력한 숫자만큼 이동시켜야 하기 때문에 for문을 사용해서
				// 입력한 숫자만큼 반복시켜준다.
				for (int i = 0; i < move; i++) {
					
					// 1미터씩 이동시켜준다. 3바퀴 돌면 3미터 이동했다고 표시해준다.
					System.out.println((i + 1) + "m만큼 이동했어요.");
					
					// 목표거리에서 -1씩 감소시켜준다.
					remain -= 1;
					
					// 랜덤 카운트를 하나 저장시켜준다
					int randCount = (int)(Math.random() * 3 + 1);

					// 목표거리만큼 도달하면 하나 출력 시켜주고 for문을 빠져나온다
					if (remain <= 0) {
						System.out.println("목표거리까지 도달! 축하해요!");
						break;
					}
					
					// 위에서 뽑은 랜덤숫자와 동일할 경우 전투에 돌입한다.
					if (randCount == 1) {
						
						// 전투중인지 아닌지를 판별하는 boolean 변수를 true로 바꿔준다. 즉 전투에 들어섰다는 뜻.
						enCount = true;
						
						// 전투에 돌입했다는 문구를 출력시켜주고
						System.out.println("적과 마주쳤다!");
						System.out.println("전투에 들어선다!");
						
						// 현재 이동하는걸 멈추고 for문을 빠져 나온다.
						// 예를들면 이동거리를 5미터를 입력 했는데 3미터 이동하다 전투에 돌입하면
						// 3미터만큼만 이동시키고 전투에 돌입.
						// 밑에 있는 else로 이동한다.
						break;
					}			
				}
				
			// enCount변수가 true이기 때문에 전투에 돌입하기 위해 이쪽으로 들어온다.
			} else {
				
				// 어떤 적을 등장시킬지를 위한 랜덤 변수.
				int rand = (int)(Math.random() * 3 + 1);
				
				// 에너미 객체를 하나 생성시켜주고 변수로 위에서 정한 랜덤변수를 집어 넣는다
				// 그러면 랜덤으로 3마리중 하나가 생성된다.
				Enemy enemy = new Enemy(rand);
				
				// 전투에 돌입하는 SetBattle메소드. 매개변수로 플레이어와 위에서 생성한 에너미를 전달해준다.
				battle.SetBattle(player, enemy);
				
				// 만약 전투가 끝난 후 플레이어의 체력이 0 이하이면 메뉴로 빠져 나와서 게임을 끝낸다.
				if (player.totalHp <= 0) {
					System.out.println(player.name + "의 체력이 0이 되어 게임을 종료합니다.");
					break;
				}
				
				// 전투가 끝나면 SetBattle메소드를 빠져 나와서 이쪽으로 온다.
				// 전투가 끝났기 때문에 enCount변수는 다시 false로 주고
				// 다시 위쪽 while문으로 돌아가서 처음부터 다시 이동한다. 
				enCount = false;
			}
			
		}
	}
}
