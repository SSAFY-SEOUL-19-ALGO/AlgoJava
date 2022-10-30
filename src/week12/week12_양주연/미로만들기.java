package week12.week12_양주연;

import java.io.*;
import java.util.*;
public class 미로만들기
{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		String note = br.readLine();
		char[][] map = new char[100][100];
		for(int i=0; i<100; i++){
		    for(int j=0; j<100; j++){
		        Arrays.fill(map[i], '#');
		    }
		}
		int curX, curY;
		curX = curY = 50;
		map[curX][curY]='.';
		int curDirIdx = 0;
		int[] dx={1,0,-1,0}, dy={0,-1,0,1};
		int minX, minY, maxX, maxY;
		minX = minY = maxX = maxY = 50;
		for(char c : note.toCharArray()){
		    if(c=='R'){
		        curDirIdx = (curDirIdx+1)%4;
		    }else if(c=='L'){
		        curDirIdx = (curDirIdx-1+4)%4;
		    }else{ //F
		        curX = curX+dx[curDirIdx];
		        curY = curY+dy[curDirIdx];
		        map[curX][curY]='.';
		        minX = Math.min(minX, curX);
		        minY = Math.min(minY, curY);
		        maxX = Math.max(maxX, curX);
		        maxY = Math.max(maxY, curY);
		    }
		    
		}
		for(int i=minX; i<=maxX; i++){
		    for(int j=minY; j<=maxY; j++){
		        bw.write(map[i][j]+"");
		    }
		    bw.write("\n");
		}
		br.close();
		bw.flush();
		bw.close();
	}
	
}