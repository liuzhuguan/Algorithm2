/*
        给定一个数组arr，全是正数；一个整数aim，求累加和等 于aim的，最长子数组，
    要求额外空间复杂度O(1)，时间 复杂度O(N)
 */
public class LongestSumSubArrayLengthInPositiveArray {

    public static int getMaxLong(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim <= 0) {
            return 0;
        }
        int L = 0;
        int R = 0;
        int max = 0;
        int sum = arr[0];
        while (R < arr.length && L <= R) {
            if (sum == aim) {
                max = Math.max(max, R - L + 1);
                sum -= arr[L++];
            } else if (sum < aim) {
                R++;
                if (R == arr.length) {
                    break;
                }
                sum += arr[R];
            } else if (sum > aim) {
                sum -= arr[L++];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,1,1,1,1,1,1};
        int maxLong = getMaxLong(arr, 6);
        System.out.println(maxLong);
    }
}
