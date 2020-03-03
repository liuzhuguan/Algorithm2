import java.util.HashMap;
import java.util.Map;

/*
    求一个数组中最大的子数组，它的元素相加等于aim  输入数组为整数
 */
public class MaxSubArrayToAim {

    public static int maxLength(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);     //不错过起始值
        int len = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum - aim)) {
                len = Math.max(i - map.get(sum - aim), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {7,5,7,2,3,1,1,7,5};
        int i = maxLength(arr, 7);
        System.out.println(i);
    }
}
