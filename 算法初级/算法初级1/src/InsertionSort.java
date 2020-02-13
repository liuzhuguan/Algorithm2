import utils.Utils;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0  &&  arr[j] > arr[j+1]; j--) {
                Utils.swap(arr,j,j+1);
            }
         }
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        insertionSort(arr);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
}
