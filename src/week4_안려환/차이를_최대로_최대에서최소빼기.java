package week4_안려환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class 차이를_최대로_최대에서최소빼기 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList <Integer> arr2 = new ArrayList<>();
		String [] arr = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr2.add(Integer.parseInt(arr[i]));
		}  // 입력부 완
		
		
		// 음수 쪽만 절대값을 기준으로 정렬해주는 
		Collections.sort(arr2);
		ArrayList <Integer> minus = new ArrayList<>();
		int sum = 0, check = 0;
		for (int i = 0; i < arr2.size(); i++) {
			if(arr2.get(i)<0) {
				minus.add(Math.abs(arr2.get(i)));
			}
		} Collections.sort(minus);
		for (int i = 0; i < minus.size(); i++) {
			arr2.set(i, -minus.get(i));
		}
		
		
		
		while(!arr2.isEmpty()) {
			if((check ^ 1) == 1) {
				sum += Math.abs(arr2.get(0)-arr2.get(arr2.size()-1));
//				System.out.println("case 1: "+Math.abs(arr2.get(0)-arr2.get(arr2.size()-1)));
				arr2.remove(0);
				check = check ^ 1;
			}else if((check ^ 1) == 0){
				sum += Math.abs(arr2.get(arr2.size()-1) -arr2.get(0));
//				System.out.println("case 2: "+Math.abs(arr2.get(arr2.size()-1) -arr2.get(0)));
				arr2.remove(arr2.size()-1);
				check = check ^ 1;
			}
		}
		System.out.println(sum);
		
	}

}




