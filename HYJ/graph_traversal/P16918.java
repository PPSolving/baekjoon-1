import java.util.LinkedList;
import java.util.Scanner;

public class P16918 {

    static int R, C, N;
    static int[][] map;
    static LinkedList<Position> list; //터트려야할 폭탄 위치를 담은 리스트

    static class Position {
        int y;
        int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
        @Override
        public boolean equals(Object o){
            Position p = (Position) o;
            if(this.y == p.y && this.x == p.x) return true;
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        N = sc.nextInt();

        //init
        map = new int[R][C];
        list = new LinkedList<>();
        for (int y = 0; y < R; y++) {
            String[] str = sc.next().split("");
            for (int x = 0; x < C; x++) {
                if (str[x].equals("O")) {
                    map[y][x] = 0; //0은 폭탄 위치로 2일 부터 시작
                } else map[y][x] = -1; //-1은 빈공간
            }
        }

        //2초 부터 시작
        for (int d = 2; d <= N; d++) {
            bfs(d);
        }
        StringBuffer buf = new StringBuffer();
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (map[y][x] != -1) buf.append("O");
                else buf.append(".");
            }
            buf.append("\n");
        }
        System.out.println(buf.toString());
    }

    static void bfs(int day) {
        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        //짝수일 -> 완전 탐색해야하므로 빈공간이면 0일로 두고, 0일인건 2일로 바꾼다.
        if (day % 2 == 0) {
            list.clear();
            for (int y = 0; y < R; y++) {
                for (int x = 0; x < C; x++) {
                    if (map[y][x] == -1) {
                        map[y][x] = 0; //0 일로 넣기
                    }else if(map[y][x] == 0){ 
                        //0일은 터져야 하므로 2로(2일을 의미) 변경
                        map[y][x] = 2;
                        list.add(new Position(y,x));
                    }
                }
            }
        } else { //홀수인 -> 제거 되야할 2일, 주변을 모두 터트린다. (-1) 값으로 변경
            while(!list.isEmpty()){
                Position cur = list.pollFirst();
                map[cur.y][cur.x] = -1;
                for(int i=0; i<directions.length; i++){
                    int nextY = cur.y+directions[i][0];
                    int nextX = cur.x+directions[i][1];

                    if(0<=nextY && nextY < R
                            && 0<= nextX && nextX < C){
                        map[nextY][nextX] = -1;
                    }
                }
            }
        }
    }
}
