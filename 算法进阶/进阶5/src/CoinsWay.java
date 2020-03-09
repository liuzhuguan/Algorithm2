import java.util.HashMap;
import java.util.Map;

/*
    换钱的方法数
        【题目】 给定数组arr，arr中所有的值都为正数且不重复。每个值代表 一种面值的货币，
    每种面值的货币可以使用任意张，再给定一 个整数aim代表要找的钱数，求换钱有多少种方法。

        【举例】 arr=[5,10,25,1]，aim=0。 组成0元的方法有1种，就是所有面值的货币都不用。
     所以返回1。 arr=[5,10,25,1]，aim=15。 组成15元的方法有6种，分别为3张5元、
     1张10元+1张5元、1张 10元+5张1元、10张1元+1张5元、2张5元+5张1元和15张1元。
     所 以返回6。 arr=[3,5]，aim=2。 任何方法都无法组成2元。所以返回0。
 */
public class CoinsWay {

    public static int coins1(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    public static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            return res = aim == 0 ? 1 : 0;
        }
        for (int i = 0; i * arr[index] <= aim; i++) {
            res += process1(arr, index + 1, aim - arr[index] * i);
        }
        return res;
    }

    public static int coins2(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        HashMap<String, Integer> map = new HashMap<>();     //index_aim, times
        return process2(arr, 0, aim, map);
    }

    public static int process2(int[] arr, int index, int aim, HashMap<String, Integer> map) {
        int res = 0;
        if (index == arr.length) {
            return res = aim == 0 ? 1 : 0;
        }
        for (int i = 0; i * arr[index] <= aim ; i++) {
            int nextAim = aim - arr[index] * i;
            String key = String.valueOf(index + 1) + "_" + String.valueOf(nextAim);
            if (map.containsKey(key)) {
                res += map.get(key);
            } else {
                res += process2(arr, index + 1, nextAim, map);
            }
        }
        map.put(String.valueOf(index) + "_" + String.valueOf(aim), res);
        return res;
    }

    public static int coins3(int[] arr, int aim) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j * arr[0] <= aim ; j++) {
            dp[0][j * arr[0]] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }


    public static void main(String[] args) {
        int[] coins = {2,4};
        int aim = 8;
        int i = coins3(coins, aim);
        System.out.println(i);
    }
}
