package week1.week1_윤지혜;

import java.util.*;

public class PM_기능개발 {
	
	public static List<Integer> solution(int[] progresses, int[] speeds) {
        
        // 각 기능이 며칠이 걸리는지 구해서 배열에 넣어주기
        int[] howLong = new int[progresses.length];	// 각 기능 걸리는 시간 넣을 배열
        int day = 1;
        for(int i = 0; i < progresses.length; i++) {
        	int N = 100-progresses[i];	// 남은 진도% = 진도 100% - 현재 진도%
        	while(N>speeds[i]*day) {	// 남은 진도 > 속도*날짜이면
        		day++;	// 날짜 하루 더 추가
        	}
        	howLong[i] = day;	// 속도*날짜가 남은 진도보다 크거나 같아지는 그 날짜를 배열에 추가
        	day = 1;	// 뒤의 progresses 남은 날짜 계산 위해 다시 day는 1
        }
        
        
        // Queue에 넣어 답 구하기
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < howLong.length; i++) {	// howLong 배열값들을 Queue에 넣어주기
        	queue.add(howLong[i]);
        }
        
   
        int n = 0;
        int Front = 0;
        int cnt = 0;
        
        List<Integer> answer = new ArrayList<Integer>();	// 답 배열의 크기 가늠할 수 없기 때문에 List로 받음
        
        while(!queue.isEmpty()) {
        	
        	if(cnt == 0) {
        		Front = queue.poll();	// 현재 queue의 맨 앞 요소 Front 변수에 저장하고 삭제
        		cnt++;
        	}else {
        		if (!queue.isEmpty()) {
            		if(Front >= queue.peek()) {
                		cnt++;
                		queue.remove();
                	}else {
                		answer.add(cnt);
                		n++;
                		cnt = 0;
                	}
            	}
        	}
        	
        }
        answer.add(cnt);
        
        return answer;
    }

	
	
	
	public static void main(String[] args) {
		
		int progresses[] = {95, 90, 99, 99, 80, 99};
		int speeds[] = {1, 1, 1, 1, 1, 1};
		solution(progresses, speeds);

	}

}
