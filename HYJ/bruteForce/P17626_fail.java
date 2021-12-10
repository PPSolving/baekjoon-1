import java.util.Arrays;
import java.util.Scanner;

/**
 * Four Squares
 */
public class P17626_fail {
    static int N ;
    static int[][] nums;
    static int max;
    static boolean answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        //1개 부터 Max까지가 조합이 가능한 숫자이다.
        max = (int) Math.sqrt(N);
        if(N == max*max){
            System.out.println(1);
            return;
        }
        nums = new int[4][];

        for(int i=0; i<4; i++){
            nums[i] = new int[(max*(i+1))+1];
        }
        for(int i=1; i<=max; i++){
            nums[0][i] = i;
            nums[1][i*2-1] = i;
            nums[1][i*2] = i;
            nums[2][i*3-2] = i;
            nums[2][i*3-1] = i;
            nums[2][i*3] = i;
            nums[3][i*4-3] = i;
            nums[3][i*4-2] = i;
            nums[3][i*4-1] = i;
            nums[3][i*4] = i;
        }
        answer = false;
        for(int i=2; i<=4; i++){ //i개 조합으로 가능하면 바로 return
            permutation(i, new int[i],0, nums[i-1], new boolean[nums[i-1].length]);
            if(answer){
                System.out.println(i);
                break;
            }
        }
    }

    static void permutation(int resultLen,int[] curs, int curIdx, int[] nums, boolean[] used){
        if(answer) return;

        //조합 완료
            if(curIdx == resultLen){ //해당 개수의 도달하면 종료
                int sum = 0;
                for(int i=0; i<curs.length; i++){
                    sum += curs[i]*curs[i];
                }
                if(sum == N){
                    System.out.println(Arrays.toString(curs));
                    answer = true;
                }
                return;
            }

            for(int i=1; i<nums.length; i++){
                if(!used[i] && !answer){
                used[i] = true;
                curs[curIdx] = nums[i];
                permutation(resultLen, curs, curIdx+1,nums, used);
                used[i] = false;
            }
        }

    }

}
