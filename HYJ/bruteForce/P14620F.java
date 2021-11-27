import java.util.Scanner;

public class P14620F {
    static int[][] map;
    static int N;
    static boolean[][] visit;
    static int minValue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        visit = new boolean[N][N];
        map = new int[N][N];
        minValue = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int y=1; y<N-1; y++){
            for(int x=1; x<N-1; x++){
                dfs(y,x,0,0);
            }
        }

        System.out.println(minValue);
    }
    static void dfs(int y, int x, int count, int sum){
        //선택치에 왔으니 점검합시다~
        // a. count가 3이면 돌아가야 하고

        if(count == 3){
            if(sum == 12) System.out.println("y : "+y+", x : "+x);
            if(sum < minValue) minValue = sum;
            return;
        }
        // b. 마지막까지 왔으면 돌아가야하고
        if(y >= N-1) return;

        //전체를 돌껀데
        for(int nextY = y; nextY<N-1; nextY++){
            for(int nexyX = x; nexyX<N-1; nexyX++){

                if(!able(nextY,nexyX)) continue;

                //꽃밭 체크 후 sum 계산
                check(nextY, nexyX);
                //이동
                dfs(nextY,nexyX,count+1,sum + flower(nextY,nexyX));
                remove(nextY,nexyX);
            }
        }
    }

    static int flower (int y,int x){
        return map[y+1][x] +
        map[y][x+1] +
        map[y-1][x] +
        map[y][x-1] +
        map[y][x];
    }

    static boolean able (int y,int x){
       return (!visit[y][x] &&
        !visit[y][x+1] &&
        !visit[y-1][x] &&
        !visit[y][x-1] &&
               !visit[y+1][x]);
    }


    static void check (int y,int x){
        visit[y+1][x]  = true;
        visit[y][x+1]  = true;
        visit[y-1][x]  = true;
        visit[y][x-1] = true;
        visit[y][x] = true;
    }

    static void remove (int y,int x){
        visit[y+1][x]  = false;
        visit[y][x+1]  = false;
        visit[y-1][x]  = false;
        visit[y][x-1] = false;
        visit[y][x] = false;
    }
}
