package consolProject;

// 주현씨 담당. 플레이어와 에너미의 기본 능력치는 여기서 상속받아 사용한다.
public class Character {	
	int totalAtk;				// 플레이어의 현재공격력 + 무기공격력을 더한 총 공격력
	int currentAtk;				// 플레이어의 상태창에서 표시해줄 현재 공격력
	int totalHp;				// 플레이어의 현재 체력 + 아이템 체력을 더한 총 체력
	int currentHp;				// 플레이어의 현재 체력	
	int currentExp;				// 플레이어의 현재 경험치
	String name;				// 플레이어나 에너미의 이름을 표시해줄 변수
}
