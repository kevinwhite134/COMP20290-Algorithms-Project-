import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Benchmark {

    static final int[] SIZES = {1_000, 5_000, 10_000, 50_000, 100_000};
    static final int RUNS = 5;

    public static void main(String[] args) throws IOException {
        PrintWriter csv = new PrintWriter(new FileWriter("results.csv"));
        csv.println("algorithm,n,time_ms");

        for (int size : SIZES) {
            System.out.println("\nn = " + size);
            Integer[] base = randomArray(size);

            run("MergeSort",    base, MergeSort::sort,    csv);
            run("QuickSort",    base, QuickSort::sort,    csv);
            run("CountingSort", base, CountingSort::sort, csv);
            run("RadixSort",    base, RadixSort::sort,    csv);
            // run("PaperAlgorithm", base, PaperAlgorithm::sort, csv);
        }

        csv.close();
        System.out.println("\nResults written to results.csv");
    }

    static void run(String name, Integer[] base, Consumer<Integer[]> algo, PrintWriter csv) {
        long ns = time(base, algo);
        double ms = ns / 1e6;
        System.out.printf("%-16s %.2f ms%n", name, ms);
        csv.printf("%s,%d,%.4f%n", name, base.length, ms);
    }

    static long time(Integer[] base, Consumer<Integer[]> algo) {
        // warmup
        algo.accept(Arrays.copyOf(base, base.length));

        long total = 0;
        for (int i = 0; i < RUNS; i++) {
            Integer[] copy = Arrays.copyOf(base, base.length);
            long start = System.nanoTime();
            algo.accept(copy);
            total += System.nanoTime() - start;
        }
        return total / RUNS;
    }

    static Integer[] randomArray(int size) {
        Random rng = new Random();
        Integer[] arr = new Integer[size];
        for (int i = 0; i < size; i++) arr[i] = rng.nextInt(size);
        return arr;
    }
}
