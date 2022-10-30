package week12.week12_이예은;

import java.io.*;
import java.util.*;

public class A054_BJ1347_미로만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String inputs = br.readLine();

        boolean[][] map = new boolean[101][101];
        int y = 50;
        int x = 50;
        int d = 0;
        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, -1, 0, 1};
        // 남서북동 0123
        // 시계방향 1

        int left_x = 50;
        int left_y = 50;
        int right_x = 50;
        int right_y = 50;
        map[50][50] = true;
        for(int i = 0; i < N; i++) {
            char c = inputs.charAt(i);

            if(c == 'L') {
                d -= 1;
                d += d < 0 ? 4: 0;
            } else if (c == 'R') {
                d = (d + 1) % 4;
            } else {
                x = x + dx[d];
                y = y + dy[d];

                left_y = left_y > y ? y : left_y;
                left_x = left_x > x ? x : left_x;
                right_y = right_y < y ? y : right_y;
                right_x = right_x < x ? x : right_x;
            }
            map[y][x] = true;
        }

        for(y = left_y; y <= right_y ; y++){
            for(x = left_x; x <= right_x; x++){
                char c = map[y][x]? '.' : '#';
                sb.append(c);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
