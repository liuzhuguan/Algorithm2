import java.util.HashMap;
import java.util.Map;

/*
        给定一个数组，可以将其划分为任意个不相容的子数组，求分出来的子数组
    异或和为0的子数组数量最多
        动态规划 + 上一个算法原型
 */
public class MostEOR {

    public static int EOR(int[] arr) {
        int ans = 0;
        int xor = 0;
        int[] dp = new int[arr.length];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                int pre = map.get(xor);
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }
            map.put(xor,i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1};
        int eor = EOR(arr);
        System.out.println(eor);
    }
}
