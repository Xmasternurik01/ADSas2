public class Main {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(12);
        stack.push(10);
        stack.push(8);
        System.out.println(stack.pop());
        System.out.println(stack.peek());

    }

}