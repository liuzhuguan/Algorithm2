import utils.Utils;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    Utils.swap(arr, j,j+1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
         bubbleSort(arr);
         for (int a : arr) {
             System.out.print(a + " ");
         }
    }
}
