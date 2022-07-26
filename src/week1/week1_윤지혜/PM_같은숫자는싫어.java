package week1.week1_윤지혜;

import java.util.*;

public class PM_같은숫자는싫어 {

	    public static int[] solution(int []arr) {
	        
	        Stack<Integer> stack = new Stack<>();
	        
	        stack.push(arr[0]);
	        
	        for(int i = 1; i < arr.length; i++){
	            if (stack.peek() != arr[i]){
	                stack.push(arr[i]);
	            }
	        }
	        
	        int n = stack.size();
	        
	        int[] answer = new int[n];
	        
	        for(int i = n-1; i >= 0; i--){
	            answer[i] = stack.peek();
	            stack.pop();
	        }
	        
	        return answer;
	    }
	
	public static void main(String[] args) {
		
		int arr[] = {1,1,3,3,0,1,1};
		solution(arr);

	}

}
