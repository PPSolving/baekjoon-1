import java.util.Scanner;

/**
 * 숫자야구
 */
public class P2503 {
    static class Condition{
        int num;
        int strike;
        int ball;
        public Condition(int num, int strike, int ball){
            this.num = num;
            this.strike = strike;
            this.ball = ball;
        }
    }

    static Condition[] cons;
    static int answer;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        //init
        answer = 0;
        cons = new Condition[N];
        for(int i=0; i<N; i++){
            cons[i] = new Condition(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        // 123 부터 987 까지 조건이 맞는지 탐색
        permutation(0, new int[]{1,2,3,4,5,6,7,8,9}, new boolean[9],new int[3]);

        System.out.println(answer);
    }

    //permutation
    static void permutation(int curIdx, int[] nums,boolean[] used , int[] curs ){
        if(curIdx == 3){ //조합완료
            int testNum = curs[0]*100+curs[1]*10+curs[2];
            //검사가 맞으면 answer++
            if(check(testNum)) answer++;
            return;
        }

        for(int i=0; i<nums.length; i++){
            if(!used[i]){
                curs[curIdx] = nums[i];
                used[i] = true;
                permutation(curIdx+1,nums,used,curs);
                used[i] = false;
            }
        }
    }

    //num과 주어진 Condition이 일치 한다면 true
    static boolean check(int num){
        int[] nums = new int[]{num/100,(num%100)/10,(num%10)};
        for(int i=0; i< cons.length; i++){
            int strike  = 0;
            int ball = 0;
            Condition curCon = cons[i];
            int[] peerNums  = new int[]{curCon.num/100,(curCon.num%100)/10,(curCon.num%10)};
            for(int j=0; j<3; j++){
                if(nums[j] == peerNums[0] || nums[j] == peerNums[1] || nums[j] == peerNums[2]){
                    if(nums[j] == peerNums[j]) strike++;
                    else ball++;
                }
            }
            if(curCon.strike != strike || curCon.ball != ball) return false;
        }

        return true;
    }

}
