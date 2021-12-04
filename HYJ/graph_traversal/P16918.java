import java.util.LinkedList;
import java.util.Scanner;

public class P16918 {

    static int R, C, N;
    static int[][] map;
    static LinkedList<Position> list;

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
        R = sc.nextByte();
        C = sc.nextByte();
        N = sc.nextInt();

        //init
        map = new int[R][C];
        list = new LinkedList<>();
        for (int y = 0; y < R; y++) {
            String[] str = sc.next().split("");
            for (int x = 0; x < C; x++) {
                if (str[x].equals("O")) {
                    map[y][x] = 2; //0은 폭탄 위치로 2일 부터 시작
                    list.add(new Position(y, x));
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

    //day = 짝수인 경우 => 짝수 초가 지난 경우 빈칸에 모두 폭탄 설치된다. -> 설치된 폭탄 모두 list로 들어간다.
    //day = 홀수인 경우 => 홀수 초가 지난 후 list에서 2초인 폭탄 다 꺼내고 주변 터트리고 0초된 폭탄만나면 나머지 모두 꺼내서 2초로 변경하여 다시 저장
    static void bfs(int day) {
        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        if (day % 2 == 0) { //짝수일
            for (int y = 0; y < R; y++) {
                for (int x = 0; x < C; x++) {
                    if (map[y][x] == -1) {
                        map[y][x] = 0; //0 일로 넣기
                        list.add(new Position(y, x));
                    }
                }
            }
        } else { //홀수일
            while(!list.isEmpty()){
                Position cur = list.pollFirst();
                if (map[cur.y][cur.x] == 0) { //아직 0일이면 나머지 다 0일 이므로 break;
                    list.addFirst(cur);
                    break;
                } else if (map[cur.y][cur.x] == 2) { //2일이면 주변 까지 폭탄 터트리기 (-1)으로
                    map[cur.y][cur.x] = -1;
                    for(int i=0; i<directions.length; i++){
                        int nextY = cur.y+directions[i][0];
                        int nextX = cur.x+directions[i][1];

                        if(0<=nextY && nextY < R
                                && 0<= nextX && nextX < C){
                            list.remove(new Position(nextY,nextX));
                            map[nextY][nextX] = -1;
                        }
                    }
                }
            }
            for (Position cur : list) { //남은건 0일뿐 이므로 일수 +2
                map[cur.y][cur.x] += 2;
            }
        }
    }
}
