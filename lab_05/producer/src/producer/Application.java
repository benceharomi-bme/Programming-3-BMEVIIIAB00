package producer;

public class Application {
	static public void main(String[] args) {
		Fifo fifo = new Fifo();
		Producer p = new Producer(fifo, "producer", 100);
		Consumer c = new Consumer(fifo, "consumer", 200);
		p.start();
		c.start();
	}
}
