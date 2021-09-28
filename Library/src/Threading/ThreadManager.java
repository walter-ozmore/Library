package Threading;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
	class ThreadWraper {
		long id = 0;
		Thread thread;
		
		public ThreadWraper(Thread thread) {
			this.thread = thread;
			id = tid++;
		}
		public void syncOutput() {}
		public boolean isAlive() { return thread.isAlive(); }
		public void start() { thread.start(); }
	}
	
	int maxThreads;
	static long tid = 0;
	ArrayList<ThreadWraper> threads;
	ArrayList<ThreadWraper> threadsInQueue;
	
	public ThreadManager(int maxThreads) {
		this.maxThreads = maxThreads;
		init();
	}
	void init() {
		threads = new ArrayList<ThreadWraper>();
		threadsInQueue = new ArrayList<ThreadWraper>();
	}
	public void addThread(Thread thread) {
		removeDeadThreads();
		threadsInQueue.add(new ThreadWraper(thread));
	}
	public void run() {
		while(threads.size() != 0 || threadsInQueue.size() != 0) {
			removeDeadThreads();
			if(threads.size() < threadsInQueue.size() && threads.size() < maxThreads) {
				ThreadWraper tr = threadsInQueue.remove(0);
				tr.start();
				threads.add(tr);
			}
		}
	}
	void removeDeadThreads() {
		for(int x=0; x<threads.size(); x++)
			if(!threads.get(x).isAlive())
				threads.remove(x--);
	}
}

