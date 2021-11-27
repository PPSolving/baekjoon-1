import java.util.Scanner;

public class P14620S {
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
                //System.out.println("start Y : "+y+", X : "+x);
                dfs(y,x,0,0);
                //System.out.println("minValue"+minValue);
            }
        }

        System.out.println(minValue);
    }
    static void dfs(int y, int x, int count, int sum){


        //1. 결과 도착?
        if(count == 3){
            //if(sum == 12) System.out.println("y : "+y+", x : "+x);
            if(sum < minValue) minValue = sum;
            return;
        }


        //2. 전체를 돌껀데
        for(int nextY = y; nextY<N-1; nextY++){
            for(int nextX = 1; nextX<N-1; nextX++){
                //왔던길은 돌아가지X
                if(nextY == y && nextX <x) continue;

                //3. 갈수 없으면 pass
                if(!able(nextY,nextX)) continue;

                //4. 갈 수 있다면 방문체크
                check(nextY, nextX);
                //5. 이동
                dfs(nextY,nextX,count+1,sum + flower(nextY,nextX));
                //6. 체크 아웃
                remove(nextY,nextX);
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
