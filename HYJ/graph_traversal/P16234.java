package baecjoon_collection.graphtraversal;

import java.util.*;

public class P16234 {
    static int[] parent;
    static int N, MIN, MAX;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        MIN = sc.nextInt();
        MAX = sc.nextInt();
        parent = new int[N*N+1];
        map = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        int count = 0;

        while(check()){
            count++;
            distribute();
        }

        System.out.println(count);
    }
    static boolean check(){
        boolean takePlace = false;

        //parent Reset
        for(int i=1; i<=N*N; i++){
            parent[i] = i;
        }

        //모든 원소들의 대해 주변 확인
        //동남서북
        int[][] direction = new int[][]{{0,1},{1,0},{0,-1},{0,-1}};
        for(int y=1; y<=N; y++){
            for(int x=1; x<=N; x++){
                //자신은 체크
                for(int i=0; i< direction.length; i++){
                    int nextY = y + direction[i][0];
                    int nextX = x + direction[i][1];
                    if(0<nextY && nextY <=N && 0<nextX && nextX <=N){
                        //차이값 계산
                        int diff = Math.abs((map[y][x])-map[nextY][nextX]);
                        if(MIN <= diff && diff <=MAX){ //해당 범위 안에 들어있다면 집합 하나 생김
                            takePlace = true;
                            int myNum = (y-1)*N+x;
                            int nextNum = (nextY-1)*N+nextX;
                            if(find(myNum) != find(nextNum)){
                                union(myNum,nextNum);
                            }
                        }
                    }
                }
            }
        }
        return takePlace;
    }

    static void distribute(){
        //부모가 같은것들만 더해서 분배
        //부모 Number, 총합
        Map<Integer, Integer> sumNum = new HashMap<>();
        //부모 Number, 명수
        Map<Integer, Integer> sumPeople = new HashMap<>();

        //합 구하기
        for(int y=1; y<=N; y++){
            for(int x=1; x<=N; x++){
                int myNum = (y-1)*N+x;
                int parentNum = find(myNum);
                sumNum.put(parentNum,sumNum.getOrDefault(parentNum,0)+map[y][x]);
                sumPeople.put(parentNum,sumPeople.getOrDefault(parentNum,0)+1);
            }
        }

        //자신의 부모를 찾아서 분배
        for(int y=1; y<=N; y++){
            for(int x=1; x<=N; x++){
                int myNum = (y-1)*N+x;
                int parentNum = find(myNum);
                map[y][x] = sumNum.get(parentNum)/sumPeople.get(parentNum);
            }
        }
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        parent[pb] = pa;
    }

    static int find(int a){
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

}
