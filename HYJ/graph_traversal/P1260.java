import java.util.*;

/**
 * DFS와 BFS
 */
public class P1260 {

    static int N,M,V;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 1번 부터
        M = sc.nextInt();
        V = sc.nextInt();
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=1; i<=M; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        //sort
        for(int i=1; i<=N; i++){
            Collections.sort(graph[i]);
        }

        visit = new boolean[N+1];
        dfs(V);
        System.out.println();
        visit = new boolean[N+1];
        bfs(V);

    }

    public static void dfs(int start){
        visit[start] = true;
        System.out.print(start+" ");
        for(int next : graph[start]){
            if(!visit[next]){
                dfs(next);
            }
        }
    }
    public static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        visit[start] = true;
        queue.add(start);
        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.print(current+" ");
            for(int next : graph[current]){
                if(!visit[next]){
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}
