
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class 미로만들기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(bufferedReader.readLine());
		int minx=0;	//가장작은 x
		int miny=0; //가장작은 y
		int maxx=0; //가장큰 x
		int maxy=0; //가장 작은 y
		int[][] vector= {{1,0},{0,-1},{-1,0},{0,1}};	// 방향을 바라보았을때 값
		int a=0;	//바라보는 방향은 남쪽
		String[] s=bufferedReader.readLine().split("");
		Stack<int[]> stack=new Stack<>();	// 방문한 노드를 스택에 넣는다.
		stack.add(new int[] {0,0});	//가장먼저 0,0을 넣음
		for(String act:s) {
			if(act.equals("R")) {	//r이면 1증가
				if(a==3) {
					a=0;
				}
				else a++;
			}
			else if(act.equals("L")) {	//l이면 1 감소
				if(a==0) {
					a=3;
				}
				else a--;
			}else if(act.equals("F")) {	//f이면
				int[] tmp=stack.peek();	//가장 최근 좌표에서 벡터값을 더한다.
				int x=tmp[0]+vector[a][0];
				int y=tmp[1]+vector[a][1];
				minx=Math.min(x, minx);
				miny=Math.min(y, miny);
				maxx=Math.max(maxx, x);
				maxy=Math.max(maxy, y);
				stack.add(new int[] {x,y});
			}
		}
		//System.out.println(maxx+" "+maxy);
		//System.out.println(minx+" "+miny);
		String[][] map=new String[maxx+Math.abs(minx)+1][maxy+Math.abs(miny)+1]; //맵의 크기는 minx+maxx,miny+maxy
		for(int i=0;i<map.length;i++) {
			Arrays.fill(map[i], "#");	//전부 벽으로 메꿈
		}
		for(int[] tmp:stack) {
			map[tmp[0]+Math.abs(minx)][tmp[1]+Math.abs(miny)]=".";	//스택에서 하나씩 뽑으면서 .으로 바꿈
		}
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
