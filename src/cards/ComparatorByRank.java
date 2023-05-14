package cards;

import java.util.Comparator;

/**
 * Comparator class used for sorting cards by rank.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class ComparatorByRank implements Comparator<Card> {
	
	/**
	 * Compare two cards' rank
	 */
	public int compare(Card c1, Card c2) {
		Rank rank1 = c1.getRank();
		Rank rank2 = c2.getRank();
		
		return rank1.compareTo(rank2);
	}

}
