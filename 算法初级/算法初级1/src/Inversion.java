/*
    在一个数组中，左边的数如果比右边的数大，则折两个数构成一个逆序对，请打印所有逆序 对。
 */

public class Inversion {

    public static void printReverse(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        mergeSort(arr,0,arr.length - 1);
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = (l+r)/2;

        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int length = r-l+1;
        int[] help = new int[length];
        int p1 = m;
        int p2 = r;

        while (p1 >= l && p2 >= m+1) {
            if (arr[p1] > arr[p2]) {
                for (int j = p2 ; j > m ; j--) {
                    System.out.println("(" + arr[p1] + "," + arr[p2] + ")");
                }
            }
            help[--length] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }

        while (p1 >= l) {
            help[--length] = arr[p1--];
        }

        while (p2 >= m+1) {
            help[--length] = arr[p2--];
        }

        for (int i = 0; i < help.length; i++) {
            arr[l+i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,3,2,6,5};
        printReverse(arr);
    }
}
