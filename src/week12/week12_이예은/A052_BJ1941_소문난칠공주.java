package week12.week12_이예은;

import java.io.*;
import java.util.*;

public class A052_BJ1941_소문난칠공주 {

    static int count, last_y, last_x;
    static boolean[][] visited;
    static char[][] map;
    static long z = 0;

    public static class Pos {
        int y, x;

        public Pos(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static void comb(int idx, int cnt, int s) { // 2차원 배열의 조합 구하기
    	// idx : 조합에서 처음 시작되는 idx값
    	// cnt: 지금 까지 고른 학생의 수
    	// s : 이다솜파의 수
    	
        int x = idx % 5; // 열
        int y = idx / 5; // 행

        if(cnt - s >= 4) return; // 이다솜파가 4명이상 나올 수 없는 경우 백트레킹으로 돌아간다

        if(cnt == 7){ // 7명의 공주를 모았을때
            idx--; // 이전 상황으로 만들어주고
            x = idx % 5; // 마지막으로 추가된 공주의 좌쵸를 가져온다
            y = idx / 5;
            if(s >= 4 && isConnected(y, x)) { // 마지막 으로 추가된 공주의 좌표부터 시작해서 모든 자리가 연결되어있는 지 확인한다
                count++; // 조건을 만족한다면 7공주를 만드는 경우의수를 하나 늘려준다
            }
            return;
        }


        for(int i = idx; i < 25; i++){

            x = i % 5; // 열 Column
            y = i / 5; // 행 Row

            visited[y][x] = true; // 조합에서는 방문체크가 필요업지만 나중에 BFS 탐색을 하기위해 방문체크를 해준다
            if(map[y][x] == 'S') { // 추가된 학생이 이다솜 파이면 s의 카운트를 올려준다
                comb(i + 1, cnt + 1, s + 1); 
            } else {
                comb(i + 1, cnt + 1, s);
            }
            visited[y][x] = false; // 백트레킹을 하기 때문에 돌아왔을 때는 방문체크를 false로 초기화한다

        }
    }
    public static boolean isConnected(int y, int x) { // Visited에 저장된 정보를 통해 BFS 탐색을 한다
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] checked = new boolean[5][5]; // inConnected 함수안에서 갔던 곳에 다시 방문하지 않기 위해 체크를 해준다

        queue.add(new Pos(y, x));
        checked[y][x] = true;
        int cnt = 1; // 연결된 자리수의 총길이

        while(!queue.isEmpty()) {
            Pos p = queue.poll();
            y = p.y;
            x = p.x;

            for(int i = 0; i < 4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(ny < 0 || ny >= 5 || nx < 0 || nx >= 5 ) continue; // 범위를 넘어가면 다음 탐색으로 간다

                if(!checked[ny][nx] && visited[ny][nx]) { // 아직 inConnected 함수 안에서 방문한 적이 없고 조합에서 고른 7명중 하나라면
                    checked[ny][nx] = true; // 방문한다
                    queue.add(new Pos(ny, nx));
                    cnt++;
                }
            }
        }

        boolean result = cnt == 7 ? true : false;
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map = new char[5][5];


        for(int i = 0; i < 5; i++){
            String inputs = br.readLine();
            for(int j = 0; j < 5; j++){
                map[i][j] = inputs.charAt(j);
            }
        }

        //step 1:  7명 공주를 고른다
        //step 2 : 연결되어 있는지  4명이 이다솜 파인지 확인한다
        visited = new boolean[5][5];
        comb(0,0, 0);
  
        bw.write(count + "");
        bw.flush();
        br.close();
        bw.close();
    }
}


