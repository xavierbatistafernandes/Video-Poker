package cards;

import java.util.Comparator;
/**
 * Comparator class used for sorting cards by suit.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class ComparatorBySuit implements Comparator<Card> {
	
	/**
	 * Compare two cards' suit
	 */
	public int compare(Card c1, Card c2) {
		Suit suit1 = c1.getSuit();
		Suit suit2 = c2.getSuit();
		
		return suit1.compareTo(suit2);
	}

}

