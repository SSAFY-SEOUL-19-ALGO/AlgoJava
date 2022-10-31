package 스터디문제풀이;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 카드섞기_다시_풀기 {
	
	// 카드의 구성을 0 1 2 0 1 2 라고 생각한다. 
	
	private static int N , answer = 0;
	private static int [] p,s,real,temp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		p = new int[N];
		s = new int[N];
		real = new int[N];
		temp = new int[N];
		
		for(int i = 0; i < N; i++) { // 처음 카드의 상태
			real[i] = i % 3;  // 0 1 2 0 1 2 이런 식으로 들어간다.
		}
		
		String [] arr = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			p[i] = Integer.parseInt(arr[i]);
		}
		
		arr = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			s[i] = Integer.parseInt(arr[i]);
		}  // 입력부 완
		
		shuffle();
		
		
		
	}

	private static void shuffle() {  // 이 안에 종료 조건도 넣어야 한다. (p랑 같아졌니)
		while(true) {
			boolean check = true; // 수열 같냐 다르냐 판별
			boolean impo = true; // 섞어서 될건지 안될건지 판별
			for(int i=0; i<N; i++) {
				if(real[i] != p[i]) {  // 안에 수가 다르면 섞어야 한다.
					check = false;
					break;
				}
			}
			if(check) {  // 만약 저거 다 돌았는데 p와 같다면 이제 그만 섞어도 된다.
				System.out.println(answer);
				break;
			}else { // 아니라면 
				for(int i = 0; i < N; i++) {
					temp[i] = real[s[i]];  // s[i]의 값을 인덱스로 위치 변경
				}
				for (int i = 0; i < N; i++) {
					real[i] = temp[i];
				}  // 현재 카드의 순서를 로직대로 바꿔준다. 
				answer += 1;
			}
			
			for(int i = 0; i < N; i++) {
				if(real[i] != i % 3) {   // 이래야 그 자리의 수를 다른 위치로 바꾸는 것!
					impo = false;
				}
			} // 그니까 골고루 다 바꿔주나 파악해야됨
			
			if(impo) {  // 섞는 로직이 특정 위치는 바꾸지 않는다면...
				System.out.println(-1);
				break;
			}
		}
		
	}

}
