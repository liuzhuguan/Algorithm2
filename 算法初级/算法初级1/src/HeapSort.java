import utils.Utils;

public class HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0 ; i < arr.length; i++) {
            heapInsert(arr,i);
        }
        int size = arr.length;
        Utils.swap(arr,0,--size);
        while (size > 0) {
            heapify(arr,0,size);
            Utils.swap(arr,0,--size);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1)/2]) {
            Utils.swap(arr,index,(index-1)/2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            Utils.swap(arr,largest,index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        heapSort(arr);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
}
