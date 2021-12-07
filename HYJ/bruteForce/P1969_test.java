import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * DNA - 문제이해 잘못해서 품 - s1~sn 중에서 고르는것으로 착각
 */
public class P1969_test {
    static char[] dna = new char[]{'T','A','G','C'}; //DNA
    static int N,M;
    static ArrayList<Dna> strList;

    static class Dna{
        String str;
        String strArr[];
        int num;
        public Dna(String str){
            this.str = str;
            this.strArr = str.split("");
            this.num = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        strList = new ArrayList<>();
        for(int i=0; i<N; i++){
            strList.add(new Dna(sc.next()));
        }
        //비교 = O(N^2*M)
        for(int i=0; i<N-1; i++){
            Dna cur = strList.get(i);
            for(int j=i+1; j<N; j++){
                Dna peer = strList.get(j);
                for(int k=0; k<M; k++){
                    if(cur.strArr[k] != peer.strArr[k]){
                        cur.num++;
                        peer.num++;
                    }
                }
            }
        }

        //정렬
        Collections.sort(strList, new Comparator<Dna>(){
            @Override
            public int compare(Dna a, Dna b){
                if(a.num == b.num) return a.str.compareTo(b.str);
                return a.num- b.num;
            }
        });
        Dna answer = strList.get(0);
        System.out.println(answer.str+"\n"+ answer.num);

        answer = strList.get(N-1);
        System.out.println(answer.str+"\n"+ answer.num);

    }
}
