import java.util.Stack;

/*
        一个数组围成环，两个数之间的数比两数中小的那一个数还小，则它们可以相见，
    问整个数组能相见的有多少对
 */
public class MeetTimes {

    public static class Pair {
        private int value;
        private int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    public static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    //求排列组合
    public static long getInternalSum(int n) {
        return n == 1l ? 0l : (long) n * (long) (n - 1) / 2l;
    }

    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0l;
        }
        int size = arr.length;
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        int value = arr[maxIndex];
        int index = nextIndex(size,maxIndex);
        long res = 0l;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(value));
        while (index != maxIndex) {
            value = arr[index];
            while (!stack.isEmpty() && stack.peek().value < value) {
                int times = stack.pop().times;
                res = res + getInternalSum(times) + 2 * times;
            }
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().times++;
            } else {
                stack.push(new Pair(value));
            }
            index = nextIndex(size,index);
        }
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            res = res + getInternalSum(times);
            if (!stack.isEmpty()) {
                res += times;
                if (stack.size() > 1) {
                    res += times;
                } else {
                    res += stack.peek().times > 1 ? times : 0;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2};
        long communications = communications(arr);
        System.out.println(communications);
    }
}
