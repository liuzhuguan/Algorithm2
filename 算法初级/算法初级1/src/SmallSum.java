/*
    在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组 的小和。
 */
public class SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return mergeSort(arr,0,arr.length-1);
    }

    public static int mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l+r)/2;

        return mergeSort(arr,l,mid) + mergeSort(arr,mid+1,r) + merge(arr,l,mid,r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r-l+1];
        int p1 = l;
        int p2 = m+1;
        int i = 0;
        int res = 0;

        while (p1 <= m && p2 <= r) {
            res = res + (arr[p1] < arr[p2] ? (arr[p1] * (r - p2 + 1)) : 0);
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            help[i++] = arr[p1++];
        }

        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l+j] = help[j];
        }

        return res;
    }


    public static void main(String[] args) {
        int[] arr = {1,3,5,6,2};
        int i = smallSum(arr);

        System.out.println(i);
    }
}
