package week12.week12_이예은;

import java.io.*;
import java.util.*;

public class A051_BJ11559_PuyoPuyo {

    static boolean isChanged;
    public static class Pos {
        int y, x;
        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static char[][] bfs(char[][] map, boolean[][] visited, int y, int x){

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        Queue<Pos> queue = new ArrayDeque<>(); // 방문한 Pos를 저장한다
        Queue<Pos> path = new ArrayDeque<>(); // BFS 탐색을 위한 큐

        visited[y][x] = true;
        path.add(new Pos(y, x));
        queue.add(new Pos(y, x));

        int cnt = 1;
        while (!queue.isEmpty()){ 
            Pos p = queue.poll();
            y = p.y;
            x = p.x;
            char c = map[y][x];

            for(int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || ny >= 12 || nx < 0 || nx >= 6 || visited[ny][nx] || map[ny][nx] != c) continue;

                visited[ny][nx] = true;
                queue.add(new Pos(ny, nx));
                path.add(new Pos(ny, nx));
                cnt++;
            }
        }

        if(cnt >= 4) { // 모여있는 뿌요뿌요가 4개 이상일때만 터진다
            isChanged = true;
            while(!path.isEmpty()){
                Pos p = path.poll();
                y = p.y;
                x = p.x;
                map[y][x] = '.';
            }
        }

        return map;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[][] map = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String inputs = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = inputs.charAt(j);
            }
        }

        int count = 0; // 연쇄가 몇번 일어났는지 카운트 한다
        while(true){

            isChanged = false; // 게임보드에 변화가 없으면 게임이 멈춘다

            // step 1: 뿌요뿌요가 4개 이상 연결되어 있으면 터진다
            boolean[][] visited = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                   if(map[i][j] == '.' || visited[i][j]) continue;
                   map = bfs(map, visited, i, j); // BFS로 map의 상태와 visited를 체크한다
                }
            }
            if(!isChanged) break; // 터지는 뿌요뿌요가 없다면 게임은 멈춘다
            
            // step 2: 뿌요뿌요가 아래로 내려간다
            for(int x = 0; x < 6; x++){
                int idx = 11; // 아래서 올라오면서 뿌요뿌요가 있는 부분만 읽는다.
                for(int y = 11; y > -1; y--){
                    if(map[y][x] != '.') {
                        map[idx--][x] = map[y][x];
                    }
                }
                for(int y = idx; y > -1; y--) { // 나머지 칸들은 '.'로 초기화한다
                    map[y][x] = '.';
                }
            }
            count++; // 연쇄를 카운트한다

        }

        bw.write(count + "");
        bw.flush();
        br.close();
        bw.close();
    }
}


