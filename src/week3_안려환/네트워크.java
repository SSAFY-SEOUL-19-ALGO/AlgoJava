package week3_안려환;

import java.util.LinkedList;
import java.util.Queue;

public class 네트워크 {

	class Solution {
	    public int solution(int n, int[][] computers) {
	        int answer = 0;
	        boolean [] visited = new boolean [n];  // 큐 자료구조를 이용하는 것이 정석이라고 함.
	       
			for (int i = 0; i < n; i++) {  // 방문체크할 배열의 길이만큼 반복
				if(visited[i]) continue;  // 방문했으면 넘어감
				Queue<Integer> queue = new LinkedList<Integer>();
				queue.offer(i); //  1. 탐색 시작 노드를 큐에 삽입한다.
				while(!queue.isEmpty()) {  // 더 이상 수행할 수 없을 때까지 실행
					int current = queue.poll();   // 큐에서 노드를 꺼내
					visited[current] = true;	// 방문처리를 한다.
					
					for (int j = 0; j < n; j++) { // 방문해서 해야할 일 처리
						if(current != j && computers[current][j] == 1 && !visited[j]) {  // 자기 자신이 아니면서 연결이 되었나 체크
							queue.offer(j); // 해당 노드의 인접 노드 중에서 방문하지 않은 노드를 모두 큐에 삽입 	
						}
					}
				}
				
				answer++; // 연결된 애들끼리 끝나면 네트워크가 하나 더 필요하니 더해준다.
			}
			return answer;
		}	
	 }
}


