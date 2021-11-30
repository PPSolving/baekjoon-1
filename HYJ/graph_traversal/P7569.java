import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * P7569 토마토
 */
public class P7569 {
    static int N, M, H;
    static int[][][] map;
    static int unripeTomato; //익어야하는 토마토
    static int ripeTomato;//현재까지 익은 토마토수
    static int day; //모든 토마토가 익는 일 수
    static Queue<RipePosition> queue; //주변 익힐 토마토

    static class RipePosition {
        int y;
        int x;
        int h;
        int day;

        public RipePosition(int y, int x, int h, int day) {
            this.y = y;
            this.x = x;
            this.h = h;
            this.day = day;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        H = sc.nextInt();
        //init
        map = new int[N][M][H];
        unripeTomato = 0;
        ripeTomato = 0;
        day = 0;

        queue = new LinkedList<>();
        for (int h = 0; h < H; h++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    map[y][x][h] = sc.nextInt();
                    if (map[y][x][h] == 0) unripeTomato++;
                    else if (map[y][x][h] == 1) {
                        queue.add(new RipePosition(y, x, h, 0));
                    }
                }
            }
        }

        //저장될때 부터 덜익은게 하나도 없다면
        if (unripeTomato == 0) System.out.println("0");
        else {
            day = bfs();
            //덜익은것과 익은것이 같지 않다면
            if (unripeTomato != ripeTomato) System.out.println("-1");
            else System.out.println(day);
        }

    }

    //주변 탐색만
    static int bfs() {
        int lastDay = 0;

        while (!queue.isEmpty()) {
            RipePosition cur = queue.poll();

            //day 갱신
            lastDay = cur.day;

            // 6개의 방향 [위, 아래, 동, 남, 서, 북], 3개 좌표 [y,x,h]
            int[][] directions = new int[][]{ //[6][3]
                    {0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}
            };

            for (int i = 0; i < directions.length; i++) {
                int nextY = cur.y+ directions[i][0];
                int nextX = cur.x+ directions[i][1];
                int nextH = cur.h+ directions[i][2];

                if(0 <= nextY && nextY < N
                && 0 <= nextX && nextX < M
                && 0 <= nextH && nextH < H
                && map[nextY][nextX][nextH] == 0){
                    map[nextY][nextX][nextH] = 1;
                    ripeTomato++;
                    queue.add(new RipePosition(nextY,nextX,nextH,cur.day+1));
                }
            }
        }
        return lastDay;
    }
}
