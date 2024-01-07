package tee.domain.executor;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tee.domain.Task;
import tee.domain.schedule.Schedule;

public abstract class Executor extends Thread {
	protected ExecutorService executorService;
	protected PriorityQueue<Task> tasks;
	protected Schedule schedule;
	protected volatile boolean run;

	public Executor() {
		super("Executor");
		executorService = Executors.newCachedThreadPool();
		tasks = new PriorityQueue<>();
		run = false;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public void addTask(Task task) {
		if (!run)
			tasks.add(task);
	}

	public boolean isRun() {
		return run;
	}

	public void exit() {
		run = false;
	}

	public abstract void run();
}
