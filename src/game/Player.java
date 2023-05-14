package game;

import cards.Card;
import cards.Hand;

/**
 * Player class, which includes the number of credits and the player's hand. Also includes methods for manipulating those attributes.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class Player{
	
	/**
	 * Number of credits the player has
	 */
	private int credits;
	
	/**
	 *  Player's hand
	 */
	private Hand hand;
	
	/**
	 * Constructor method initializes credit amount and hand
	 * @param credits Initial amount of credits
	 */
	public Player(int credits) {
		this.credits = credits;
		this.hand = new Hand(5);
	}
	
	/**
	 * Obtain the player's credit number
	 * @return int - Player's credits
	 */
	public int getCredits() {
		return this.credits;
	}
	
	/**
	 * Add credits to the player
	 * @param amount Credits to add
	 */
	public void addCredits(int amount) {
		this.credits += amount;
	}
	
	/**
	 * Remove credits from the player
	 * @param amount Credits to remove
	 */
	public void removeCredits(int amount) {
		this.credits -= amount;
	}
	
	/**
	 * Fetch the player's hand
	 * @return Hand - Player's hand
	 */
	public Hand getHand() {
		return this.hand;
	}

	/**
	 * Place card in player's hand
	 * @param card Card to add to the player's deck
	 */
	public void placeCard(Card card) {
		this.hand.placeCard(card);
	}
	
	/**
	 * Remove card from the player's hand
	 * @param index Position of the card in the player's hand
	 * @return Card - Card that was removed from the hand
	 */
	public Card removeCard(int index) {
		return this.hand.removeCard(index);
	}

	/**
	 * Generate string representation of a player
	 */
	@Override
	public String toString() {
		String string = "";
		string += "\nCredits: " + credits;
		string += "\nHand: " + hand;
		return string;
	}
}
