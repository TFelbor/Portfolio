  # Sorting Algorithms Runtime Analysis
  
  This project implements and analyzes the runtime performance of various sorting algorithms. It includes both classic and uncommon sorting   algorithms, with functionality to measure and compare their performance under different conditions, including multi-threaded execution      and various input types.
  
  
  ## Table of Contents

  1. Implemented Algorithms
  2. Project Structure
  3. Features
  4. Algorithm Complexities
  5. Usage
  6. Testing Scenarios
  7. Performance Analysis
  8. Implementation Details
  9. Requirements
  10. Potential Improvements
  
  ## 1. Implemented Algorithms
  
  1. Insertion Sort
  2. Bubble Sort
  3. Selection Sort
  4. Merge Sort
  5. Quick Sort
  6. Stooge Sort
  7. Reverse Bubble Sort (for testing purposes)
  
  
  ## 2. Project Structure
  
  - `ManySorts.java` → Contains implementations of all main sorting algorithms
  - `myThread.java` → Implements threading functionality for concurrent sorting analysis
  - `Project3Main.java` → Main driver class with testing implementations and reverse sorting
  
  
  ## 3. Features
  
  - Multiple sorting algorithm implementations
  - Multi-threaded testing capability
  - Runtime measurement functionality
  - Support for different input types:
    - Random arrays
    - Identical value arrays
    - Various array sizes (10,000 to 80,000 elements)
  
  
  ## 4. Algorithm Complexities
  
  - Insertion Sort → O(n²)
  - Bubble Sort → O(n²)
  - Selection Sort → O(n²)
  - Merge Sort → O(n log n)
  - Quick Sort → O(n log n)
  - Stooge Sort → O(n^(log 3/log 1.5)) ≈ O(n^2.7095)
  
  
  ## 5. Usage
  
  ### Basic Usage
  ```
  // Fill array with data
  int[] arr = new int[10000];
  
  // Time and run a sorting algorithm
  long begin = System.currentTimeMillis();
  ManySorts.insertion(arr);  // Or any other sorting method
  long end = System.currentTimeMillis();
  
  System.out.printf("Run time: %fs", (end-begin)/1000.0);
  ```
  
  ### Thread-based Testing
  ```
  // Create and run multiple threads for concurrent testing
  Thread t1 = new Thread(new myThread());
  t1.start();
  ```
  
  
  ## 6. Testing Scenarios
  
  1. **Random Data**
     - Arrays filled with random integers
     - Tests general-case performance
  
  2. **Identical Values**
     - Arrays filled with the same value
     - Tests best-case scenarios
  
  3. **Multi-threaded Testing**
     - Concurrent execution of sorting algorithms
     - Tests performance under parallel conditions
  
  
  ## 7. Performance Analysis
  
  The project allows for performance analysis of sorting algorithms under different conditions:
  - Different input sizes
  - Different input patterns
  - Single vs Multi-threaded execution
  
  
  ## 8. Implementation Details
  
  - All sorting algorithms are implemented as static methods
  - Thread implementation uses the Runnable interface
  - Runtime measurements use System.currentTimeMillis()
  - Array sizes are configurable for different test scenarios
  
  
  ## 9. Requirements
  
  - Java Development Kit (JDK) 8 or higher
  - Sufficient memory to handle large arrays (up to 80,000 elements)
  
  
  ## 10. Potential Improvements
  
  - Additional sorting algorithms
  - Visualization of sorting process
  - More detailed performance metrics
  - CSV output for data analysis
  - Memory usage analysis
  - GUI for interactive testing
  
