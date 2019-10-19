package beerregister;

import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer>{
	@Override
	public int compare(Beer b1, Beer b2) {
		return b1.strength.compareTo(b2.strength);
	}
}