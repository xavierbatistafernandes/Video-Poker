package game;

import cards.Hand;


/**
 * Interface that specifies the required methods for variants of poker.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public interface IVariant {
	
	/**
	 * Determines which cards the player should hold
	 * @param hand player's hand
	 * @param sort boolean value that decides whether a copy of the hand should be made so it can be sorted without messing with the original hand's indices
	 * @return int[] - positions to hold
	 */
	public int[] advice(Hand hand, boolean sort);
	
	
	/**
	 * Prints the statistics table
	 */
	public void statistics();
	
	
	/**
	 * Gets the payout from the player's hand
	 * @param handKey String that corresponds to the player's hands, used to access payout table
	 * @param bet bet amount
	 * @return int - payout
	 */
	public int getHandPayout(String handKey, int bet);
	
	
	/**
	 * Gets the string that corresponds to the player's hand
	 * @param hand player's hand
	 * @return String - String that corresponds to the player's hands
	 */
	public String getHandKey(Hand hand);

}
