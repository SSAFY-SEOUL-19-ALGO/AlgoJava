package week4_안려환;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 뒤집기 {

	public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        char[] arr = s.toCharArray();
        int zero=0, one=0;

        int cnt = 0;
        char tmp = arr[0];
        if(arr[0] == '0') {  // 0 이랑 1 구분
            zero++;
        }
        else {
            one++;
        }


        for(int i=1 ; i<arr.length ; i++) {
            if(arr[i-1] != arr[i]) {
                if (arr[i] == '0') {
                    zero++;
                }

                if (arr[i] == '1') {
                    one++;
                }
            }

        }

        System.out.println(Math.min(zero,one));

	}

}
