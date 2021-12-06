import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 일루미네이션
 */
public class P5547 {
    static int W,H;
    static int[][] map;
    static int answer;
    static int[][][] direction; //[홀/짝][][]
    static class Position{
        int x;
        int y;

        public Position(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        W = sc.nextInt();
        H = sc.nextInt();
        //init
        map = new int[H+2][W+2];
        answer = 0;
        //짝수층(y%2 == 0) = 동, 남, 남서, 서, 서북, 북
        //홀수층(y%2 == 1) = 동, 동남, 남, 서, 북, 북동
        //[2][6][2] = [홀/짝][6개 방향][y/x]
        direction = new int[][][]{
                {{0,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0}},
                {{0,1},{1,1},{1,0},{0,-1},{-1,0},{-1,1}}
        };


        for(int y=1; y<=H; y++){
            for(int x=1; x<=W; x++){
                int num = sc.nextInt();
                if(num == 1) map[y][x] = 1; // 건물만 1, 나머진 0
            }
        }
        //완전탐색으로 비어있는 건물(값이 0)이 외부와 만나는 곳인지 BFS로 파악하기
        //외부와 연결된 곳이면 그대로 0, 내부로써 외부와 연결되지 않은곳이면 -1로 설정
        for(int y=1; y<=H; y++){
            for(int x=1; x<=W; x++){
                if(map[y][x] == 0) {
                    map[y][x] = bfs(y,x);
                }
            }
        }
        //건물을 돌면서 외부를 만나면 answer++;
        for(int y=1; y<=H; y++){
            for(int x=1; x<=W; x++){
                if(map[y][x] == 1) {
                    //자신이 건물인데 주변 탐색해서 외부만나면 ++1
                    int nextY = 0;
                    int nextX = 0;
                    for(int i=0; i<direction[0].length; i++){
                        nextY = y + direction[y%2][i][0];
                        nextX = x + direction[y%2][i][1];
                        if(0<=nextY && nextY<=H+1
                                && 0<=nextX && nextX<=W+1
                                && map[nextY][nextX] == 0){
                            //외부랑 닫는다면
                            answer++;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }
    //자신이 외부와 연결된 곳이라면 0으로 외부와 연결안된곳이라면 2
    static int bfs(int y, int x){
        //start는 y,x
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(y,x));
        boolean[][] visit = new boolean[H+2][W+2];
        visit[y][x] = true;
        while(!queue.isEmpty()){
            Position cur = queue.poll();
            int nextY = 0;
            int nextX = 0;
            for(int i=0; i<direction[0].length; i++){
                nextY = cur.y + direction[cur.y%2][i][0];
                nextX = cur.x + direction[cur.y%2][i][1];
                if(0<=nextY && nextY<=H+1
                && 0<=nextX && nextX<=W+1
                && map[nextY][nextX] == 0 && !visit[nextY][nextX]){
                    //외곽 라인이라면
                    if(nextY == 0 || nextY == H+1 || nextX == 0 || nextX == W+1 ){
                        return 0;
                    }
                    queue.add(new Position(nextY,nextX));
                    visit[nextY][nextX] = true;
                }
            }
        }
        return 2;//건물은 아니지만 건물안에 속하므로
    }

    //완전탐색으로 건물(값이 1)이 외부와 만날때마다 answer++
}
