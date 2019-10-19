package beerregister;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class BeerRegister {
	static boolean exit = false;
	public ArrayList<Beer> beer_list = new ArrayList<Beer>();


	public void read() throws IOException {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		while (!exit) {
			String command = br.readLine();
			String[] cmd = command.split(" ");
			
			if (cmd[0].equals("add")) {
				add(cmd);
			}
			else if (cmd[0].equals("list")) {
				list(cmd);
			}
			else if (cmd[0].equals("load")) {
				load(cmd);
			}
			else if (cmd[0].equals("save")) {
				save(cmd);
			}
			else if (cmd[0].equals("delete")) {
				delete(cmd);
			} 
			else if (cmd[0].equals("exit")) {
				System.exit(0);
			}
		}
		br.close();
	}
	
	protected void add(String[] cmd) {
		beer_list.add(new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3])));
	}
	
	protected void list(String[] cmd) {
		if (cmd.length > 1) {
			if (cmd[1].equals("name")) {
				Collections.sort(beer_list, new NameComparator());
			}
			else if (cmd[1].equals("style")) {
				Collections.sort(beer_list, new StyleComparator());
			}
			else if (cmd[1].equals("strength")) {
				Collections.sort(beer_list, new StrengthComparator());
			}
		}
		for (Beer beer: beer_list) {
			System.out.println(beer);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void load(String[] cmd) throws IOException {
		FileInputStream f = new FileInputStream(cmd[1]);
		ObjectInputStream is = new ObjectInputStream(f);
		try {
			beer_list = (ArrayList<Beer>)is.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to read object.");
		}
		is.close();
	}
	
	protected void save(String[] cmd) throws IOException {
		FileOutputStream fo = new FileOutputStream(cmd[1]);
		ObjectOutputStream out = new ObjectOutputStream(fo);
		out.writeObject(beer_list);
		out.close();
	}
	
	protected void delete(String[] cmd) {
		try {
		Collections.sort(beer_list, new NameComparator());
		int i = Collections.binarySearch(beer_list, new Beer(cmd[1], null, null), new NameComparator());
		beer_list.remove(i);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("szar");
		}
	}
}