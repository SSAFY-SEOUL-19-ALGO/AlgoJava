package week4_안려환;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 차이를_최대로_NextPermutation {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int [] arr2 = new int [N];
		String [] arr = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr2[i] = Integer.parseInt(arr[i]);
		}  // 입력부 완
		
		Arrays.sort(arr2);  // 정렬을 먼저 해준 후 넥퍼 돌려야함.
		int result = 0;
		
		do {
            result = Math.max(result, getFormulaResult(arr2));
        } while(np(arr2));

        System.out.println(result);
		}
	
	
		// 넥퍼 구현부
		public static boolean np(int [] numbers) {  // numbers 배열의 상태를 근거로 다음 순열 생성, 다음 순열 존재하면 true, 아니면 false
		
		int N = numbers.length;
		// 1. 꼭대기 찾기
		int i = N -1; 
		while(i>0 && numbers[i-1]>=numbers[i]) --i;
		
		if(i==0) return false ; // 다음 순열을 만들 수 없는 이미 가장 큰 순열의 상태!
		
		// 2. 꼭대기의 바로 앞자리(i-1)의 값을 크게 만들 교환 값 뒤쪽에서 찾기
		int j = N -1 ;// 암기 포인트
		while(numbers[i-1] >= numbers[j])  --j; // 나보다 큰 수 찾을 때까지 앞으로 온다. // 암기 포인트
		
		// 3. i-1 위치값과 j 위치값을 교환
		swap(numbers, i-1, j);
		
		
		// 4. i 위치에서부터 맨 뒤까지의 수열을 가장 작은 형태의 오름차순으로 변경
		int k = N -1;// 암기 포인트
		while(i<k) swap(numbers, i++, k--);  // 스와핑하고 돌아오면 증가 // 암기 포인트
		
		return true;
		
		}
		
		
		public static void swap(int[] numbers, int i , int j) {
			int tmp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = tmp;
		}
		
		
		private static int getFormulaResult(int[] arr2) {
	        int sum = 0;
	        for (int i = 0; i < arr2.length - 1; i++) {  // i+1 위치의 인덱스 값 연산을 위해
	            sum += Math.abs(arr2[i] - arr2[i + 1]);  // 문제의 형태
	        }
	        return sum;
	    }

}
