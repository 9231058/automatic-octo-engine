import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import multithreading.Buffer;
import multithreading.Consumer;
import multithreading.Producer;
import multithreading.SynchronizedBuffer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Buffer buffer = new SynchronizedBuffer();
		executorService.execute(new Producer(buffer));
		executorService.execute(new Consumer(buffer));
		executorService.shutdown();
	}

}
