package musicsorter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import musicsorter.MusicManneger;

public class Main {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(MusicManneger.getInstance());
		executorService.shutdown();
	}
}
