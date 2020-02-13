import utils.Utils;

public class QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr,0,arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            Utils.swap(arr,left + (int) (Math.random() * (right + 1 - left)),right);
            int[] p = partition(arr,left,right);
            quickSort(arr,left,p[0] - 1);
            quickSort(arr,p[1] + 1, right);
        }
    }

    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                Utils.swap(arr,++less,l++);
            } else if (arr[l] > arr[r]) {
                Utils.swap(arr,--more,l);
            }
            else {
                l++;
            }
        }
        Utils.swap(arr,more,r);

        return new int[] {less + 1, more};
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        quickSort(arr);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
}
