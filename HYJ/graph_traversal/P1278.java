import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1278 {
    //point
    static class Position{
        int y;
        int x;
        int num;
        public Position(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }

    }

    static int N,M;
    static int result;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        result=0;
        map = new int[N][M];
        visit = new boolean[N][M];
        for(int y=0; y<N; y++){
            String[] intStr = String.valueOf(sc.next()).split("");
            for(int x=0; x<intStr.length; x++){
                map[y][x] = Integer.parseInt(intStr[x]);
            }
        }
        bfs(0,0);
        System.out.println(result);
    }

    static void bfs(int startY, int startX){
        Queue<Position> queue = new LinkedList<>();
        visit[startY][startX] = true;
        queue.add(new Position(startY,startX, 1));
        while(!queue.isEmpty()){
            Position cur = queue.poll();

            //동,남,서,북
            int[][] direction = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
            for(int i=0; i<direction.length; i++){
                int nextY = cur.y + direction[i][0];
                int nextX = cur.x + direction[i][1];

                //종료 조건
                if(nextY == N-1 && nextX == M-1){
                    result = cur.num+1;
                    return;
                }

                if(0 <= nextY && nextY < N
                && 0 <= nextX && nextX < M
                && !visit[nextY][nextX]
                && map[nextY][nextX] == 1){
                    visit[nextY][nextX] = true;
                    queue.add(new Position(nextY,nextX,cur.num+1));
                }
            }
        }
    }


}
