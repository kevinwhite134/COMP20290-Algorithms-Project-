public class RadixSort {

    public static void sort(Integer[] arr) {
        int n = arr.length;
        int m = max(arr);

        // run counting sort for each digit position
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(arr, n, exp);
        }
    }

    // counting sort based on the digit at position exp (1, 10, 100, ...)
    static void countSort(Integer[] arr, int n, int exp) {
        Integer[] output = new Integer[n];
        int[] count = new int[10];

        // count occurrences of each digit
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // prefix sum
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // build output backwards
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    static int max(Integer[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
