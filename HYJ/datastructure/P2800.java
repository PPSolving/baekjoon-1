import java.util.*;

public class P2800 {

    static String input;
    static int[] arr; //괄호 번호 붙이기
    static List<String> list;
    static Set<String> set;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        list = new ArrayList<>();
        set = new HashSet<>();
        arr = new int[input.length()];

        int count = 0;
        for(int i=0; i<input.length(); i++){
            char ch = input.charAt(i);
            if(ch == '('){
                arr[i] = ++count;
            } else if(ch == ')'){
                arr[i] = count--;
            }
        }

        dfs(0,new Stack<Integer>(), "");

        list.addAll(set);
        list.remove(input);
        Collections.sort(list);
        StringBuffer buf = new StringBuffer();
        for(String s : list){
            buf.append(s);
            buf.append("\n");
        }

        System.out.println(buf.toString());

    }

    public static void dfs(int findIdx,Stack<Integer> stack,String finalText){
        if(findIdx == input.length()){
            set.add(finalText);
            return;
        }
        char ch = input.charAt(findIdx);
        if(ch == '('){
            stack.add(arr[findIdx]);
            dfs(findIdx+1, stack, finalText+"(");
            stack.pop();
            dfs(findIdx+1, stack,finalText);
        } else if(ch == ')'){
            //add() 하고 넘어온 경우 => 마지막 괄호가 같은 번호이므로
            if(!stack.isEmpty() && stack.peek() == arr[findIdx]){
                stack.pop();
                dfs(findIdx+1, stack, finalText+")");
                stack.add(arr[findIdx]);
            } else { //pop() 하고 넘어온 경우 => 같은번호 아니므로 pass
                dfs(findIdx+1, stack, finalText);
            }
        } else{ // 그냥 이어서
            dfs(findIdx+1, stack, finalText+ch);
        }
    }
}
