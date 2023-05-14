package game;

import cards.Deck;

/**
 * Abstract class that implements the IMode interface. Specifies methods that are utilized for a game mode.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public abstract class AbstractMode implements IMode {
	
	/**
	 * Player object which includes credits and a hand of cards
	 */
	protected Player player;
	
	/**
	 * Deck to be used for the game
	 */
	protected Deck deck;
	
	/**
	 * Poker variant to use for the game
	 */
	protected IVariant variant;
	
	/**
	 * Number of credits the player started with
	 */
	protected int initialCredits;
	
	/**
	 * Total amount of credits spent betting
	 */
	protected int sumOfAllBets;
	

	/**
	 * Constructor class which initializes player, deck and variant fields
	 * @param player Player object
	 * @param deck Deck to utilize
	 * @param variant Variant to utilize
	 */
	public AbstractMode(Player player, Deck deck, IVariant variant) {
		this.player = player;
		this.deck = deck;
		this.variant = variant;
		this.sumOfAllBets = 0;
		this.initialCredits = player.getCredits();
	}
	
	
	/**
	 * Betting method which removes the betting amount from the player's credit total
	 * @param bet_amount Amount to bet
	 * @param bet_specified Checks whether a betting amount was specified 
	 * @return boolean - True if bet was successful, false if not
	 */
	public abstract boolean bet(int bet_amount, boolean bet_specified);
	
	/**
	 * Dealing method which draws cards from the deck and places them into player's hand
	 * @return boolean - True if deal was successful, false if not
	 */
	public abstract boolean deal();
	
	/**
	 * Holding method which keeps specified cards in the player's hand and discards the rest
	 * @param indexes Positions to hold in player's hand
	 * @return boolean - True if hold was successful, false if not
	 */
	public abstract boolean hold(int[] indexes);
	
	/**
	 * Returns the number of credits the player has
	 * @return int - Number of credits
	 */
	public abstract int credit();
	
	/**
	 * Advice method which, based on the player's hand, determines which cards should be held
	 * @return int[] - Array of positions to hold
	 */
	public abstract int[] advice();
	
	/**
	 * Method which checks the player's final hand and whether the player lost or won
	 * @param bet Amount the player bet
	 */
	public abstract void evaluate(int bet);
	
	
	/**
	 * Method that prints the statistics table
	 */
	public abstract void statistics();
	
	
	/**
	 * Places the player's cards back into the deck
	 */
	public void restoreDeck() {
		for(int i = 0; i < 5; i++) {
			deck.addCard(player.removeCard(i));
		}
	}
	
	/**
	 * Gets the string that represent's the player's hand
	 * @return String - String that represent's the player's hand
	 */
	public String getHandKey() {
		return variant.getHandKey(player.getHand());
	}
	
	/**
	 * Gets the payout for the player's hand
	 * @param bet Amount the player bet
	 * @return int - Payout amount
	 */
	public int getHandPayout(int bet) {
		return variant.getHandPayout(getHandKey(), bet);
	}

}
