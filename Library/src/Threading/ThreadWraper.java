package Threading;

public abstract class ThreadWraper {
	static long tid = 0;
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

class DefaultThreadWraper extends ThreadWraper{

	public DefaultThreadWraper(Thread thread) {
		super(thread);
	}
	
}