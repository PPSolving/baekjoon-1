import java.util.*;

/**
 * 한윤정이 이탈리아에 가서 아이스크림을 사먹는데
 */
public class P2422 {

    static int N, M;
    /**
     * **새로알게된 점**
     * 피해야할 조합을 map[][] == 1 으로 표현 할 수 있다!!
     */
    static int[][] map;
    static int[] arr;
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N + 1];
        map = new int[N+1][N+1];
        answer = 0;
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a][b] = 1;
            map[b][a] = 1;
        }

        combination(3, N, arr,1, new int[3],0);
        System.out.println(answer);
    }

    /**
     * nCk = n-1Ck-1 + n-1Ck 공식 적용
     * @param k : nCk 의 k로 현재 뽑아야 하는 숫자의 개수를 의미
     * @param n : nCk 의 n으로 현재 뽑을 수 있는 숫자의 개수를 의미
     * @param arr : 선택할 수 있는 숫자가 담긴 배열
     * @param arrIdx : arr 배열에서 현재 선택 할 index로 (arrIdx 미만의 숫자는 선택 불가한 상황)
     * @param cur : 현재까지 조합된 배열
     * @param curIdx : 현재까지 조합된 배열에 들어갈 index
     */
    static void combination(int k,int n, int[] arr, int arrIdx, int[] cur, int curIdx) {
        if (k == 0) { //조합완료
            if(map[cur[0]][cur[1]] == 1 ||
                    map[cur[0]][cur[2]] == 1 ||
                    map[cur[1]][cur[2]] == 1) return;
            answer++;
            return;
            /**
             * 조합된 결과를 검사할 코드 작성
             */
        }

        /**
         * 뽑을 수 있는 숫자 n개 보다 뽑아야하는 숫자(빈 공간)이
         * 더 크다면 조합 불가한 상황
         */
        if (n < k ) return;

        cur[curIdx] = arr[arrIdx];
        combination(k - 1, n-1, arr,arrIdx + 1, cur, curIdx+1); //선택
        combination(k,n-1, arr,arrIdx + 1, cur, curIdx); //미선택
    }
}
