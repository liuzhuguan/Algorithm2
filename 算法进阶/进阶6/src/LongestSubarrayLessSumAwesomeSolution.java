/*
    给定一个数组arr，值可正，可负，可0；一个整数aim，求累加 和小于等于aim的，最长子数组，要求时间复杂度O(N)
 */
public class LongestSubarrayLessSumAwesomeSolution {

    public static int maxLengthAwesome(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] nums = new int[arr.length];
        int[] ends = new int[arr.length];
        nums[arr.length - 1] = arr[arr.length - 1];
        ends[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (nums[i + 1] < 0) {
                nums[i] = nums[i + 1] + arr[i];
                ends[i] = ends[i + 1];
            } else {
                nums[i] = arr[i];
                ends[i] = i;
            }
        }

        int sum = 0;
        int end = 0;
        int len = 0;
        for (int start = 0; start < arr.length; start++) {
            while (end < arr.length && nums[end] + sum <= aim) {
                sum += nums[end];
                end = ends[end] + 1;
            }
            sum -= end > start ? arr[start] : 0;
            len = Math.max(len, end - start);
            end = Math.max(end, start + 1);
        }
        return len;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("oops!");
            }
        }
    }
}
