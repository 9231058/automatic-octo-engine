package tee.domain;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Task implements Runnable, Comparable<Task> {

	private String id;
	private String packageName = "";
	private String className;
	private String methodName;
	private String afterMehtodName = "";
	private String beforeMethodName = "";
	private ArrayList<String> depends;

	public Task() {
	}

	@Override
	public void run() {
	}

	@Override
	public int compareTo(Task task) {
		return 0;
	}

}
