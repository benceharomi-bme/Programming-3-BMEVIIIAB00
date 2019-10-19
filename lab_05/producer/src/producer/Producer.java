package producer;

public class Producer extends Thread {
	private Fifo fifo;
	private String string;
	private int sleeptime;
	
	public Producer(Fifo F, String S, int N) {
		fifo = F;
		string = S;
		sleeptime = N;
	}
	public void go() throws InterruptedException {
		int i = 0;
		while(true) {
			fifo.put(string + " " + i);
			Thread.sleep(sleeptime);
			System.out.println("produced" + " " + string + " " + i + " " + (System.currentTimeMillis()%100000));
			i++;
		}
	}
	
	public void run() {
			try {
				this.go();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
