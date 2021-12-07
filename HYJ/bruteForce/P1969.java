import java.util.*;

/**
 * DNA
 */
public class P1969 {
    static Map<String,Integer>[] map;
    static int N,M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new HashMap[M];

        //비교대상 개수 N
        String[] strArr = new String[N];
        for(int i=0; i<N; i++) {
            strArr[i] = sc.next();
        }
        for(int i=0; i<M; i++){
            map[i] = new HashMap<>();
        }

        //map[M] = M개의 자리수 중 빈도 수 저장
        for(int i=0; i<N; i++) {
            String[] temArr = strArr[i].split("");
            for(int j=0; j<M; j++) {
                map[j].put(temArr[j],map[j].getOrDefault(temArr[j],0)+1);
            }
        }

        char[] answer = new char[M];
        int [] numArr = new int[M];
        for(int i=0; i<M; i++){
            int max = 0;
            //오름차순 정렬하고 앞에서 부터 빈도 개수 파악한다.
            //max값 같을 경우 갱신안되므로 자동적으로 사전순으로 된다.
            ArrayList<String> keyList = new ArrayList<>(map[i].keySet());
            Collections.sort(keyList);
            for(String key : keyList){ //4개로 고정
                int num = map[i].get(key);
                if(max < num) {
                    max = num;
                    answer[i] = key.charAt(0);
                    numArr[i] = N-num; //DNA개수 - 빈도개수
                }
            }
        }
        int sum = 0;
        for(int num : numArr){
            sum+=num;
        }
        System.out.println(new String(answer)+"\n"+sum);
    }
}
