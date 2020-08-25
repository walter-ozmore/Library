package Threading;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
	int maxThreads;
	ArrayList<ThreadWraper> threads;
	
	public ThreadManager(int maxThreads) {
		this.maxThreads = maxThreads;
		init();
	}
	void init() {
		threads = new ArrayList<ThreadWraper>();
	}
	public boolean addThread(Thread thread) {
		removeDeadThreads();
		if(threads.size()<maxThreads) {
			thread.start();
			threads.add(new DefaultThreadWraper(thread));
			return true;
		}
		return false;
	}
	public boolean addThread(ThreadWraper thread) {
		removeDeadThreads();
		if(threads.size()<maxThreads) {
			thread.start();
			threads.add(thread);
			return true;
		}
		return false;
	}
	void removeDeadThreads() {
		for(int x=0; x<threads.size(); x++)
			if(!threads.get(x).isAlive())
				threads.remove(x--);
	}
	public boolean isAllThreadsDead() {
		removeDeadThreads();
		if(threads.size()<=0)
			return true;
		return false;
	}
	public boolean isOpenThread() {
		removeDeadThreads();
		if(threads.size()<maxThreads)
			return true;
		return false;
	}
}

