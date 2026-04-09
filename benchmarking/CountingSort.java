// Counting sort for non-negative integers.
// Counts occurrences of each value, then uses prefix sums to place elements.
// O(n + k) time where k is the max value. Stable sort.

public class CountingSort {

    public static void sort(Integer[] arr) {
        int n = arr.length;
        int max = max(arr);

        // count[i] = how many times value i appears
        int[] count = new int[max + 1];
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }

        // prefix sum so count[i] = index of last occurrence of i in output
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // build output array walking backwards to keep it stable
        Integer[] out = new Integer[n];
        for (int i = n - 1; i >= 0; i--) {
            int v = arr[i];
            out[count[v] - 1] = v;
            count[v]--;
        }

        System.arraycopy(out, 0, arr, 0, n);
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
