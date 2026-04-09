public class QuickSort {

    public static void sort(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    static void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int par = partition(arr, low, high);
            quickSort(arr, low, par - 1);
            quickSort(arr, par + 1, high);
        }
    }

    // partitions around arr[high] as pivot, returns final pivot index
    static int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
