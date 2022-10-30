package week12.week12_양주연;

import java.io.*;
public class 카드섞기
{
    public static int[] copy(int[] arr){ //깊은복사 메서드
        int[] temp = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            temp[i] = arr[i];
        }
        return temp;
    }
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] card = new int[N];
		int[] P = new int[N];
		int[] S = new int[N];
		int[] temp = new int[N];
		for(int i=0; i<N; i++){
		    card[i] = i%3; //0번째 위치에 있던 카드 플레이어 0에게, 1번째 위치에 있던 카드 플레이어 1에게, 2번째 위치에 있던 카드 플레이어 2에게, ...
		}
		String[] input = br.readLine().split(" ");
		for(int i=0; i<N; i++){
		    P[i] = Integer.parseInt(input[i]);
		}
		input = br.readLine().split(" ");
		for(int i=0; i<N; i++){
		    S[i] = Integer.parseInt(input[i]);
		}
		int shuffleCnt = 0;
		while(true){
		    //카드가 최종적으로 보내야하는 플레이어에게 보내졌는지 확인
		    boolean match = true;
		    for(int i=0; i<N; i++){
		        if(card[i]!=P[i]){
		            match=false;
		            break;
		        }
		    }
		    if(match){ //카드를 최종적으로 보내야하는 플레이어에게 보내는데 성공했다면,
		        bw.write(shuffleCnt+"\n"); //출력
		        break; //while문에서 빠져나옴
		    }else{ //카드가 아직 최종적으로 보내야하는 플레이어에게 보내지지 않았다면,
		        //카드 섞음
		        for(int i=0; i<N; i++){
		            temp[i] = card[S[i]]; //카드가 섞이면서 0 ~ N-1번 카드가 어떤 플레이어에게 가게되는지 저장.
		        }
		        card = copy(temp);
		        shuffleCnt++;
		        
		        //섞어도 섞어도 카드를 해당하는 플레이어에게 줄 수 없는지 확인
		        //초기상태로 카드가 돌아왔다 == 섞어도 섞어도 카드를 해당하는 플레이어에게 줄 수 없다
		        boolean impossible = true;
		        for(int i=0; i<N; i++){
		            if(card[i]!=i%3){
		                impossible = false;
		                break; 
		            }
		        }
		        if(impossible){
		            bw.write("-1\n"); //-1 출력
		            break; //while문에서 빠져나옴
		        }
		    }
		}
		br.close();
		bw.flush();
		bw.close();
	}
}