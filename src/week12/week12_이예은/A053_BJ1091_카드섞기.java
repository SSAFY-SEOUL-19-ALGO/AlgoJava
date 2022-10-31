package week12.week12_이예은;

import java.io.*;
import java.util.*;

public class A053_BJ1091_카드섞기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] inputs = br.readLine().split(" "); // 결과
        String[] inputs2 = br.readLine().split(" "); // 카드 이동
        int[] P = new int[N];
        int[] S = new int[N];
        int[] cards = new int[N];

        for(int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(inputs[i]);
            S[i] = Integer.parseInt(inputs2[i]);
            cards[i] = i;
        }

        int count = 0;
        while (true) {

         //step 1: 카드 확인하기
            boolean isOk = true;
            for(int i = 0; i < N; i++){
                int v = cards[i] % 3;
                if(v != P[i]) {
                    isOk = false;
                    break;
                }
            }
            if(isOk) break; // 정답인 경우

            isOk = true;
            for(int i = 0; i < N; i++){
                if(count == 0) break;
                if(cards[i] != i) {
                    isOk = false;
                    break;
                }
            }
            if(count != 0 && isOk) {
                count = -1;
                break;
            }

            // step 2: 회전
            int[] temp = new int[N];
            for (int i = 0; i < N; i++) {
               temp[i] = cards[S[i]];
               //System.out.println(S[i] + " " + cards[i]);
            }
            cards = temp;
            //System.out.println(Arrays.toString(cards));
            count++;
        }

        bw.write(count+ "");
        bw.flush();
        br.close();
        bw.close();
    }
}
