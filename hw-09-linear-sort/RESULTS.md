| Алгоритм     | 100, ms | 1000, ms | 10_000, ms | 100_000, ms | 1000_000, ms | 10_000_000 | 100_000_000   |
|--------------|---------|----------|------------|-------------|--------------|------------|---------------|
| BucketSort   | 1       | 1        | 9          | 2467        | timeout      | timeout    | timeout       |
| CountingSort | 0       | 0        | 0          | 1           | 8            | 44         | 391           |
| RadixSort    | 0       | 0        | 3          | 12          | 53           | 441        | out of memory |
| Shell        | 0       | 0        | 4          | 15          | 96           | 1019       | 12389         |
