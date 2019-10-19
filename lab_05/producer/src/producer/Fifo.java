package producer;

import java.util.ArrayList;

public class Fifo {
	protected ArrayList<String> string_array = new ArrayList<String>();
	
	public synchronized void put(String e) throws InterruptedException {
		while(string_array.size()>=10) {
			this.wait();
		}
		this.notify();
		string_array.add(e);
	}
	public synchronized String get() throws InterruptedException {
		while(string_array.size()<=0) {
			this.wait();
		}
		this.notify();
		String string = string_array.get(0);
		string_array.remove(0);
		return string;
	}
}
