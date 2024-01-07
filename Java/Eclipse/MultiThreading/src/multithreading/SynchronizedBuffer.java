package multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBuffer implements Buffer {

	private final Lock accessLock = new ReentrantLock();
	private final Condition canWrite = accessLock.newCondition();
	private final Condition canRead = accessLock.newCondition();
	private int buffer = -1;
	private boolean occupied = false;

	@Override
	public void set(int value) {
		accessLock.lock();
		try {
			while (occupied) {
				System.out.println("Producer tries to write.");
				canWrite.await();
			}
			buffer = value;
			occupied = true;
			canRead.signalAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			accessLock.unlock();
		}
	}

	@Override
	public int get() {
		accessLock.lock();
		int readValue = 0;
		try {
			while (!occupied) {
				System.out.println("Consumer tries to read.");
				canRead.await();
			}
			readValue = buffer;
			occupied = false;
			canWrite.signalAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			accessLock.unlock();
		}
		return readValue;
	}

}
