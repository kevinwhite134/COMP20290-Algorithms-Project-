# COMP20290 Algorithms Project - Paper 4

This project implements and benchmarks Paper 4, "A Novel Time and Space Complexity Efficient Variant of Counting-Sort Algorithm".

The paper algorithm is implemented in `src/ARUCountingSort.java`. It follows Algorithm 2 from the paper:

- `m = ceil(sqrt(k))`, where `k` is the maximum input value.
- `Q` counts quotient buckets.
- `R` counts remainder buckets.
- `B` is the temporary output array used between the stable remainder pass and the stable quotient pass.

The sorting algorithms all operate on `Integer[]` arrays. `ARUCountingSort`, `CountingSort`, and `RadixSort` reject negative values because the paper's counting-sort model assumes non-negative integer input.

## Run Correctness Checks

```text
cd src
javac *.java
java SortCorrectnessCheck
```

The check compares each sorting implementation with `Arrays.sort` on edge cases including empty input, single-item input, sorted and reverse-sorted arrays, duplicates, all-zero arrays, square-boundary values around `k = 15`, `16`, and `17`, large sparse values, and negative-input rejection.

## Run Benchmarks

```text
cd src
javac *.java
java Benchmark
```

The benchmark writes three CSV files:

```text
../results/results.csv
../results/results_runtime.csv
../results/results_space.csv
```

The combined CSV uses these columns:

```text
experiment,algorithm,n,k,time_ms,time_complexity_estimate,space_complexity_estimate,status
```

`time_ms` is the measured average runtime over five runs when the algorithm is practical to run. `time_complexity_estimate` and `space_complexity_estimate` are theoretical operation/space estimates for report graphs and paper comparison.

## Experiments

The benchmark generates deterministic random datasets that always contain both `0` and the intended maximum value `k`. This matters because the paper's analysis depends directly on the chosen `k`.

The implemented experiments are:

- `paper_fixed_n_1000_counting_vs_aru`: reproduces the paper-style comparison of traditional Counting Sort and ARU Counting Sort for fixed `n = 1,000` and increasing `k`.
- `paper_fixed_n_10000_baseline_comparison`: compares Counting, ARU, Quick, Merge, and Radix Sort at fixed `n = 10,000`.
- `paper_fixed_n_100000_baseline_comparison`: repeats the same comparison at fixed `n = 100,000`.
- `paper_fixed_n_1000000_theoretical_only`: emits theoretical rows for `n = 1,000,000` without running the algorithms, keeping the deliverable safe on ordinary lab machines.

Traditional Counting Sort is skipped for very large `k` values because allocating a `k`-sized count array is not practical. The skipped rows are still written with theoretical estimates so the report can show why ARU Counting Sort improves the `k` dependency.

## Mapping To Paper Figures

- Use `results/results_space.csv` rows from `paper_fixed_n_1000_counting_vs_aru` to reproduce the paper's memory comparison between Counting Sort and ARU Counting Sort.
- Use `results/results_runtime.csv` rows from `paper_fixed_n_1000_counting_vs_aru` to compare measured runtime for the two counting-sort variants.
- Use the `paper_fixed_n_10000_baseline_comparison` and `paper_fixed_n_100000_baseline_comparison` rows to compare ARU Counting Sort against Quick Sort, Merge Sort, Radix Sort, and traditional Counting Sort.
- Use `status` to separate measured rows from skipped or theoretical-only rows in Excel charts.

## Notes

This is a plain Java project. It does not require JavaFX, Maven, Gradle, Python, or plotting libraries. If `javac` is not found, install a JDK or add the JDK `bin` directory to `PATH`.
