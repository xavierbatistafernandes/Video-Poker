package cards;

import java.util.Arrays;

/**
 * Class that represents a player's hand.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class Hand {
	
	/**
	 * Array of hand that make the player's hand
	 */
	private Card[] hand;
	
	/**
	 * Constructor class that creates a player's hand which is an array of size hand_size
	 * @param hand_size - number of cards in the player's hand
	 */
	public Hand(int hand_size) {
		this.hand = new Card[hand_size];
	}

	/**
	 * Place a card in the player's hand
	 * @param card - card to be placed into the player's hand
	 */
	public void placeCard(Card card) {
		for(int i = 0; i < hand.length; i++) {
			if(hand[i] == null) {
				hand[i] = card;
				break;
			}
		}
	}
	
	/**
	 * Remove card from player's hand
	 * @param index - position of the card to be removed
	 * @return Card - card removed from the player's hand
	 */
	public Card removeCard(int index) {
		Card card = hand[index];
		hand[index] = null;
		return card;
	}
	
	/**
	 * Check the card in a certain position of the player's hand, without removing it
	 * @param index - position to check
	 * @return Card - card in position index
	 */
	public Card getCard(int index) {
		return hand[index];
	}
	
	/**
	 * Get the suit of a specific card of the player's hand
	 * @param index - position to check
	 * @return Suit - suit of the card 
	 */
	public Suit getSuitAt(int index) {
		return hand[index].getSuit();
	}
	
	/**
	 * Get the rank of a specific card of the player's hand
	 * @param index - position to check
	 * @return Rank - rank of the card 
	 */
	public Rank getRankAt(int index) {
		return hand[index].getRank();
	}
	
	/**
	 * Sort the player's hand by the cards' rank
	 */
	public void sortByRank() {
		Arrays.sort(hand, new ComparatorByRank());
	}
	
	/**
	 * Sort the player's hand by the cards' suit
	 */
	public void sortBySuit() {
		Arrays.sort(hand, new ComparatorBySuit());
	}

	/**
	 * Define string that represents player's hand
	 */
	@Override
	public String toString() {
		String cards = "";
		
		if (hand[0] == null)
			return "player has no cards";
		
		for (int i = 0; i < hand.length; i++)
			cards += hand[i] + " ";
		
		return cards;
	}
}
