package beerregister;

import java.util.Comparator;

public class StyleComparator implements Comparator<Beer>{
	@Override
	public int compare(Beer b1, Beer b2) {
		return b1.style.compareTo(b2.style);
	}
}