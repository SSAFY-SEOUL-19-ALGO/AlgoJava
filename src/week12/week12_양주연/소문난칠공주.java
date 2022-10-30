package week12.week12_양주연;

import java.io.*;
import java.util.*;

public class 소문난칠공주 {
    
    static boolean[] isSelected;
    static Seat[] seatArray;
    static char[][] map;
    static int[] dx={1, 0, -1, 0}, dy={0, -1, 0, 1};
    static int answer=0;
    
    public static class Seat{
        int x, y;
        public Seat(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    
    public static boolean chkConditions(int[] selectedSeats){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] chk = new boolean[7];
        chk[0] = true;
        int leftCnt=6;
        q.offer(selectedSeats[0]);
        while(!q.isEmpty()){
            Seat curSeat = seatArray[q.poll()];
            for(int i=0; i<4; i++){
                int nx = curSeat.x+dx[i];
                int ny = curSeat.y+dy[i];
                for(int j=1; j<7; j++){
                    Seat tmpSeat = seatArray[selectedSeats[j]];
                    if(!chk[j] && nx==tmpSeat.x && ny==tmpSeat.y){
                        chk[j] = true;
                        leftCnt--;
                        q.offer(selectedSeats[j]);
                        break;
                    }
                }
            }
        }
        if(leftCnt!=0) return false;
        int sCnt = 0;
        for(int i : selectedSeats){
            if(map[seatArray[i].x][seatArray[i].y]=='S') sCnt++;
        }
        if(sCnt<4) return false;
        return true;
    }
    
    public static void comb(int lev, int start){
        if(lev==7){
            int[] selectedSeats = new int[7];
            int idx=0;
            for(int i=0; i<25; i++){
                if(isSelected[i]) selectedSeats[idx++]=i;
            }
            if(chkConditions(selectedSeats)) answer++;
        }
        else{
            for(int i=start; i<25; i++){
                isSelected[i] = true;
                comb(lev+1, i+1);
                isSelected[i] = false;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        map = new char[5][5];
        String line;
        for(int i=0; i<5; i++){
            line = br.readLine();
            for(int j=0; j<5; j++){
                map[i][j] = line.charAt(j);
            }
        }
        seatArray = new Seat[25];
        int seatIdx=0;
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                seatArray[seatIdx++] = new Seat(i, j);
            }
        }
        isSelected = new boolean[25];
        comb(0, 0);
        bw.write(answer+"\n");
        br.close();
        bw.flush();
        bw.close();
    }
}