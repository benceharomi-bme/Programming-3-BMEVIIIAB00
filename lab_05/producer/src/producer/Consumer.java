package producer;

public class Consumer extends Thread {
	private Fifo fifo;
	private String string;
	private int sleeptime;
	
	public Consumer(Fifo F, String S, int N) {
		fifo = F;
		string = S;
		sleeptime = N;
	}

	public void run() {
		while(true) {
			try {
				System.out.println("consumed " + string + " " + fifo.get() + " " + (System.currentTimeMillis()%100000));
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
