import java.util.Stack;

/*
    一个二维整型数组 0 1 表示，求最大矩阵区域为1的数量
 */
public class MaxRectangle {

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height),maxArea);
        }
        return maxArea;
    }

    public static int maxRecFromBottom(int[] height) {
        if (height.length == 0 || height == null) {
            return 0;
        }
        int maxArea = 0;
        //使用单调栈解答
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(curArea, maxArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {0,0,0,0},
                {0,0,1,0},
                {0,1,1,0},
                {1,1,1,1}
        };

        int i = maxRecSize(arr);
        System.out.println(i);
    }
}
