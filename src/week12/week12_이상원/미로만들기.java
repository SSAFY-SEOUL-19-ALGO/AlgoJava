package week12.week12_이상원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 미로만들기 {
    static int l;
    static String content;
    static int[] right = {2, 1}; // 오른쪽으로 보기
    static int[] left = {-2, -1}; // 왼쪽으로 보기
    static int[] dy = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dx = {0, 1, 0, -1};
    static String[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        l = Integer.parseInt(br.readLine());
        content = br.readLine();
        // 입력 완료

        map = new String[101][101];
        map[50][50] = ".";
        int[] start = {50, 50};
        int dir = 2;
        int maxY = Integer.MIN_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;


        for (int i = 0; i < content.length(); i++) {
            minY = Math.min(minY, start[0]);
            minX = Math.min(minX, start[1]);
            maxY = Math.max(maxY, start[0]);
            maxX = Math.max(maxX, start[1]);
            switch (content.charAt(i)) {
                case 'R':
                    dir = (dir + 1) % 4;
                    break;
                case 'L':
                    dir = (dir + 3) % 4;
                    break;
                case 'F':
                    start[0] += dy[dir];
                    start[1] += dx[dir];
                    map[start[0]][start[1]] = ".";
                    break;
            }
            minY = Math.min(minY, start[0]);
            minX = Math.min(minX, start[1]);
            maxY = Math.max(maxY, start[0]);
            maxX = Math.max(maxX, start[1]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                if (!".".equals(map[i][j])) sb.append("#");
                else sb.append(".");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
