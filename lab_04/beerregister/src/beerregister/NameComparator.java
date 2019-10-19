package beerregister;

import java.util.Comparator;

public class NameComparator implements Comparator<Beer>{
	@Override
	public int compare(Beer b1, Beer b2) {
		return b1.name.compareTo(b2.name);
	}
}