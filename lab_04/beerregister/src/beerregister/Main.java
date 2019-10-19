package beerregister;

public class Main {
	public static void main(String[] args) {
		BeerRegister register = new BeerRegister();
		try {
			register.read();
		} catch (Throwable e) {
			System.out.println(e);
		}
		
	}

}
