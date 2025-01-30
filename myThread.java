// Author: Tytus Felbor

public class myThread implements Runnable{

	public void run() {
		for (int j = 1; j <= 5; j++) {
			// Section of code to be run 5 times
			System.out.println("Thread " + Thread.currentThread().getId() + ": Loop " + j);
			int[] arr = new int[80000];

			for (int i = 0; i < arr.length; i++) {
				arr[i] = (int) (Math.random() * 1000000);
			}



			long begin = System.currentTimeMillis();
			ManySorts.insertion(arr);
			//ManySorts.bubble(arr);
			//ManySorts.selection(arr);
			//ManySorts.merge(arr);
			//ManySorts.quick(arr);
			//ManySorts.stooge(arr);
			long end = System.currentTimeMillis();
			System.out.printf("Sort complete: %d, %d, %d, ...\n", arr[0], arr[1], arr[2]);
			System.out.printf("Run time: %fs", (end-begin)/1000.0);
		}
	}

}
