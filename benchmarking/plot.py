import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv("results.csv")

plt.figure(figsize=(10, 6))
for algo in df["algorithm"].unique():
    subset = df[df["algorithm"] == algo]
    plt.plot(subset["n"], subset["time_ms"], marker="o", label=algo)

plt.xlabel("Input Size (n)")
plt.ylabel("Time (ms)")
plt.title("Sorting Algorithm Comparison")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig("results.png")
plt.show()
