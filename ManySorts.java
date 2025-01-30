// Author: Tytus Felbor

public class ManySorts {
	
	public static void insertion(int[] arr) {
		
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			
			int key = arr[i];
			int j = i - 1;
			
			/* Move elements of arr[0..i-1], that are greater than key, to one position ahead of their current position */
			while (j >= 0 && arr[j] > key) {
				
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			
			arr[j + 1] = key;
		}
	}
	
	public static void bubble(int[] arr) {
		
		int n = arr.length;
		for (int i = 0; i < n - 1; i++) {
			
			for (int j = 0; j < n - i - 1; j++) {
				
				if (arr[j] > arr[j + 1]) {
					
					// swap arr[j+1] and arr[j]
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	
	public static void selection(int[] arr) {
		
		int n = arr.length;
		
		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n-1; i++){
			
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i+1; j < n; j++) {
				
				if (arr[j] < arr[min_idx]) min_idx = j;
				
				// Swap the found minimum element with the first element
				int temp = arr[min_idx];
				arr[min_idx] = arr[i];
				arr[i] = temp;
			}
		}
	}
	
	public static void merge(int arr[]) {
		
		mergeSort(arr, 0, arr.length - 1);
	}
	
	private static void mergeArr(int arr[], int l, int m, int r){
		
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;
		
		// Create temp arrays
		int L[] = new int[n1];
		int R[] = new int[n2];
		
		// Copy data to temp arrays
		for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];
		
		// Merge the temp arrays
		// Initial indexes of first and second subarrays
		int i = 0, j = 0;
		
		// Initial index of merged subarray array
		int k = l;
		while (i < n1 && j < n2) {
			
			if (L[i] <= R[j]) {
				
				arr[k] = L[i];
				i++;
			}
			
			else {
				
				arr[k] = R[j];
				j++;
			}
			
			k++;
		}
		
		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			
			arr[k] = L[i];
			i++;
			k++;
		}
		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			
			arr[k] = R[j];
			j++;
			k++;
		}
	}
	
	// Main function that sorts arr[l..r] using merge()
	private static void mergeSort(int arr[], int l, int r){
		
		if (l < r) {
			
			// Find the middle point
			int m = l + (r - l) / 2;
			// Sort first and second halves
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);
			// Merge the sorted halves
			mergeArr(arr, l, m, r);
		}
	}
	
	public static void quick(int[] arr) {
		
		quickSort(arr, 0, arr.length - 1);
	}
	
	private static void swap(int[] arr, int i, int j){
		
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/* 	This function takes last element as pivot, places
		the pivot element at its correct position in sorted
		array, and places all smaller (smaller than pivot)
		to left of pivot and all greater elements to right
		of pivot */
	
	private static int partition(int[] arr, int low, int high) {
		
		// pivot
		int pivot = arr[high];
		// Index of smaller element and
		// indicates the right position
		// of pivot found so far
		int i = (low - 1);
		
		for (int j = low; j <= high - 1; j++) {
			
			// If current element is smaller
			// than the pivot
			if (arr[j] < pivot) {
				
				// Increment index of
				// smaller element
				i++;
				swap(arr, i, j);
			}
		}
		
		swap(arr, i + 1, high);
		return (i + 1);
	}
	/* The main function that implements QuickSort
		arr[] --> Array to be sorted,
		low --> Starting index,
		high --> Ending index */
	
	private static void quickSort(int[] arr, int low, int high){
		
		if (low < high) {
			
			// pi is partitioning index, arr[p]
			// is now at right place
			int pi = partition(arr, low, high);
			// Separately sort elements before
			// partition and after partition
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	
	public static void stooge(int arr[]) {
		stoogesort(arr, 0, arr.length - 1);
	}
	
	private static void stoogesort(int arr[], int l, int h){
		
		if (l >= h)
			return;
		
		// If first element is smaller
		// than last, swap them
		if (arr[l] > arr[h]) {
			
			int t = arr[l];
			arr[l] = arr[h];
			arr[h] = t;
		}
		
		// If there are more than 2 elements in
		// the array
		if (h - l + 1 > 2) {
			
			int t = (h - l + 1) / 3;
			// Recursively sort first 2/3 elements
			stoogesort(arr, l, h - t);
			// Recursively sort last 2/3 elements
			stoogesort(arr, l + t, h);
			// Recursively sort first 2/3 elements
			// again to confirm
			stoogesort(arr, l, h - t);
		}
	}
	
	public static void reverseBubbleSort(int[] arr) {
		int n = arr.length;

		for (int i = 0; i < n - 1; i++) {
			
			for (int j = 0; j < n - i - 1; j++) {
				
				if (arr[j] < arr[j + 1]) {
					
					// Swap elements if they are in the wrong order
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	
}
