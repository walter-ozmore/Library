package Examples;

import Threading.ThreadManager;
import Threading.ThreadWraper;

public class ThreadManagerExample {
	class ThreadClass extends Thread {
		String str = "";
		public ThreadClass(String str) {
			this.str = str;
		}
		public void run() {
			try {
				Thread.sleep((long) (Math.random()*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(str);
		}
	}
	public static void main(String args[]) {
		new ThreadManagerExample();
	}
	public ThreadManagerExample() {
		ThreadManager tm = new ThreadManager(2);
		tm.addThread(new ThreadClass("Test"));
		tm.addThread(new ThreadClass("Wow"));
		tm.addThread(new ThreadClass("Test"));
		tm.addThread(new ThreadClass("Wow"));
		tm.addThread(new ThreadClass("Test"));
		tm.addThread(new ThreadClass("Wow"));
		tm.addThread(new ThreadClass("Test"));
		tm.addThread(new ThreadClass("Wow"));
		tm.run();
	}
}
