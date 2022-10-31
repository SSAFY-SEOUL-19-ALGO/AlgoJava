package week12.week12_이상원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 카드섞기 {static int n;
    static int[] p, s, defaultArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        p = new int[n];
        s = new int[n];
        defaultArr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            defaultArr[i] = i % 3;
            p[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        int[] first = defaultArr.clone();
        int cnt = 0;
        while (true) {
            if (Arrays.equals(p, defaultArr)) {
                break;
            }
            int[] tmp = defaultArr.clone();
            for (int i = 0; i < n; i++) {
                defaultArr[i] = tmp[s[i]];
            }

            if (Arrays.equals(first, defaultArr)) {
                System.out.println(-1);
                System.exit(0);
            }
            cnt++;
        }

        System.out.println(cnt);


    }
}
