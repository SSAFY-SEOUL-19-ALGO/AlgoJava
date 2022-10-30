package 스터디문제풀이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;



// 틀린 코드
// 저는 일단 0 1 2 3 4 5 6 7 8 9 ... 
// 라는 카드가 있고
// 처음 주어지는 p 수열을 가지고 3명의 플레이어가 있으니 
// ex) 1 1 2 0 2 0 1 0 2 2 1 0
//     5 0 9 7 1 8 3 10 4 11 6 2
// 이러면 1 1 2 0 2 0 1 0 2 2 1 0
//     이걸 가지고 1번 플레이어한테 0번 인덱스 주고 1번 플레이어한테 1번 인덱스 주고 2번 플레이어한테 2번 인덱스 주고 0번 플레이어한테 3번 인덱스 주고
// 이런 식으로 생각을 하여 
// 속임수라는 것이 처음 카드 구성을 알고있으니 특정 패턴으로 여러 번 셔플을 하여 결국은 본인이 암기하고 있는 덱의 구성을 만드는 것으로 이해하여
// 위의 방식으로 만들어진 현재의 카드 구성에서 5 0 9 7 1 8 3 10 4 11 6 2 라는 패턴으로 카드를 계속 섞어서
// 현재의 카드 구성으로 다시 돌아오기까지의 횟수를 구하려 했습니다.
// 0 1 2 0 1 2 했을 때 0 1 2 3 4 5 이렇게 가게 됩니다.
// 틀린 원인은 이상한 재귀를 사용함 그리고 문제 이해가 잘못됨 풀이 방식도 예제의 답에 끼워맞추는 식으로 풀다보니 틀렸다고 생각합니다.
// 이 방법으로 풀릴지는 모르겠지만 이딴거 버리겠읍니다.




public class 카드섞기 {
	// 문제도 이해하기가 어렵다...
	
	
	/**
	 * 0번째 위치에 있던 카드가 플레이어 0 에게 가고 1번은 1에게...
	 * 근데 속임수를 쓴다고 하는데 여기부터 이해가 안된다.
	 * 
	 * 섞기 전의 카드 순서를 알고 있고 이 정보를 이용해서 각 카드가 특정한 플레이어에게
	 * 보내지게 한다고 한다.
	 * 
	 * 카드 한 번 섞을 때 주어진 방법을 이용해서만 섞을 수 있고 이 방법은 길이가 N인 수열 S 로 주어진다. 
	 * 섞고 나면 i번째 위치에 있던 카드가 S[i] 번으로 이동하게 된다고 한다.
	 * 
	 * 2 0 1 수열 P
	 * 1 2 0 ( 수열 S )
	 * 이 부분을 다시 이해해보자... i번쨰 위치 그니까 0번째 위치에 있는 2라는 카드가 0으로 이동하고 
	 * 2번 플레이어한테 1 0번 플레이어한테 2 1번 플레이어한테 0 주라는건가?
	 * -> 예제 2번이 설명이 안되잖아
	 * 
	 * 
	 * 그니까 s 수열이 바꾸는 방식이고 처음 주어진 수열 P가 처음 카드의 상태이다. 
	 * 
	 * 0 1 2 라는 카드의 위치가 
	 * 1 2 0 인 상태로 카드가 있는 것이다. 적용된 이 1 2 0 이  2 0 1 이 될 때까지 돌리는 것이다.
	 * 
	 * 이제 1 2 0 의 패턴으로 돌리는 것이니 
	 * 
	 * 0 1 2 가 되고 
	 * 
	 * 또 돌리면 
	 * 
	 * 2 0 1 가 된다.
	 * 이렇게 원 상태로 왔으니 끝나는 것이다....
	 * 
	 * 이렇게 카드를 0번한테 2 주고 1번한테 0 주고 2번한테 1 줄 수 있다.
	 * 
	 * 2번 예제는 0 1 2 0 1 2 순서로 그냥 주는거니까 안섞어도 딱 맞는다.
	 * 
	 * **/
	
	
	private static int N , answer = 0;
	private static int [] p,s,real,finish;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		p = new int[N];
		s = new int[N];
		finish = new int[N];
		int [] a = new int [N/3];
		int [] b = new int [N/3];
		int [] c = new int [N/3];
		
		int ac = 0, bc = 0, cc = 0 ;
		real = new int [N];
		
		for (int i = 0; i < N; i++) {
			real[i] = i;
		}
		
		String [] arr = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			if(Integer.parseInt(arr[i]) == 0) {
				a[ac] = real[i];
				ac += 1;
			}
			else if (Integer.parseInt(arr[i]) == 1) {
				b[bc]= real[i];
				bc += 1;
			} 
			else if (Integer.parseInt(arr[i]) == 2) {
				 c[cc] = real[i];
				 cc += 1;
			}
		}
		
		int count = 0;
		for (int i = 0; i < N; i+=3) {
			p[i] = a[count];
			p[i+1] = b[count];
			p[i+2] = c[count];
			count++;
		}
		
		arr = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			s[i] = Integer.parseInt(arr[i]);
		}  // 입력부 완
		
		
		if(Arrays.equals(p, real)) {  // 0 1 2 패턴인 것
			System.out.println(0);
			return;
		}
		
		// 불가능 조건 -> s 에 i 와 S[i] 가 같으면서 p가 적용된 수열의 i 와 s[i] 가 다르다면 그 수는 다른채로 계속 돌릴 것이기 때문에
		for (int i = 0; i < N; i++) {  //  아무리 섞어도 안되는 것..
			if(s[i] == i) {
				if(p[i] != s[i]) {
					System.out.println(-1);
					return;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			finish[i] = p[i];
		}
		
		// 그럼 가능한 애들에 한해서 shuffle() 메소드가 돌아갈 것이다.
		shuffle();
		System.out.println(answer-1);
		
			
	}
	
	private static void shuffle() {  // 이 안에 종료 조건도 넣어야 한다. (s[i]랑 같아졌니)
		answer += 1;
		int [] temp = new int [N];
		for (int i = 0; i < N; i++) {
			temp[s[i]] = finish[i]; 
		}
		
		for (int i = 0; i < N; i++) {
			finish[i] = temp[i];
		}

		if(Arrays.equals(finish, p)) {
			return;
		}else {
			shuffle();
		}
	}
	


}
