import java.util.Scanner;

/**
 * Four Squares
 * 참조 : https://cocoon1787.tistory.com/401
 */
public class P17626 {
    static int N;
    static int[] dp; //dp[n] = 제곱의 합이 n을 만족하는 최소 제곱수의 개수
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N+1];
        dp[0] = 0;
        for(int i=1; i<=N; i++){
            int min = Integer.MAX_VALUE;
            for(int j=1; j*j<=i; j++){
                min = Math.min(dp[i-j*j]+1,min);
            }
            dp[i] = min;
        }
        System.out.println(dp[N]);
    }
}
