import java.io.*;
import java.util.*;

/**
 * 효율적인 해킹
 */
public class P1325 {
    // 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 출력
    static ArrayList<Integer>[] graph;
    static int N,M;
    static int[] countNum;
    static int MAX;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        countNum = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        //단방향
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());;
            int v = Integer.parseInt(st.nextToken());;
            graph[v].add(u); //u가 v를 신뢰 = v를 잡으면 u는 바로 잡힌다.
        }
        MAX = 0;
        for(int i=1; i<=N; i++){
            bfs(i);
        }
        StringBuffer buf = new StringBuffer();
        for(int i=1; i<=N; i++){
            if(MAX == countNum[i]){
                buf.append(i+" ");
            }
        }
        System.out.println(buf.toString());
        bw.flush();
        bw.close();
    }

    static void bfs(int start){
        boolean[] visit = new boolean[N+1];
        visit[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        int count = 0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            count ++;
            for(int next : graph[current]){
                if(!visit[next]){
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
        if(count >= MAX){
            MAX = count;
        }
        countNum[start] = count;
    }
}
