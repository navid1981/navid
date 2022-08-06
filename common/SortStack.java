import java.util.Stack;

public class SortStack<Integer> {
    Stack<Integer> min=new Stack<>();
    Stack<Integer> max=new Stack<>();

    public void push(Integer n){
        if(min.isEmpty()){
            min.push(n);
        } else{
            while(!min.isEmpty() && (int)n>(int)min.peek()){
                max.push(min.pop());
            }
            min.push(n);
            while(!max.isEmpty()){
                min.push(max.pop());
            }
        }
    }

    public Integer pop() throws Exception {
        if(min.isEmpty()){
            throw new Exception();
        }else{
            return min.pop();
        }
    }

    public void showStack() {
        while(!min.isEmpty()){
            System.out.print(min.peek()+", ");
            max.push(min.pop());
        }
        while(!max.isEmpty()){
            min.push(max.pop());
        }
    }
}
