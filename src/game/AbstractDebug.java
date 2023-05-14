package game;


import java.util.LinkedList;
import java.util.List;

import cards.Card;
import cards.Deck;


/**
 * Abstract class that extends the AbstractMode class. Contains typical methods utilized for debugging.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public abstract class AbstractDebug extends AbstractMode {
	
	
	/**
	 * Constructor method that initializes the player, deck and variant
	 * @param player Player object
	 * @param deck deck Deck utilized for debugging
	 * @param variant Poker variant to use
	 */
	public AbstractDebug(Player player, Deck deck, IVariant variant) {
		super(player, deck, variant);
	}
	
	
	/**
	 * {@inheritDoc}
	 * (Verbose)
	 */
	@Override
	public boolean bet(int bet_amount, boolean bet_specified) {
		
		System.out.print("\n-cmd ");
		if (bet_specified == true) System.out.println("b " + bet_amount);
		else System.out.println("b");
		
		if (bet_amount < 1 || bet_amount > 5 || bet_amount > player.getCredits()) {
			System.out.println("b: illegal amount");
			return false;
		}
		else {
			player.removeCredits(bet_amount);
			
			sumOfAllBets += bet_amount;
			System.out.println("player is betting " + bet_amount);
			return true;
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 * (No shuffling)
	 */
	public boolean deal() {
		System.out.println("\n-cmd d");
		
		/* Checking if there are at least 5 cards in the deck to deal */
		if (deck.getNumCards() < 5) {
			System.out.println("d: not enough cards in the deck");
			return false;
		}
		
		for(int i = 0; i < 5; i++) {
			player.placeCard(deck.drawCard());
		}
		
		System.out.println("player's hand " + player.getHand());
		
		return true;
	}
	
	
	/**
	 * {@inheritDoc}
	 * (Verbose)
	 */
	@Override
	public boolean hold(int[] indexes) {

		List<Card> discarded_cards = new LinkedList<Card>();

		int k = 0;		
		int flag = 0;
		
		System.out.print("\n-cmd h");
		
		/* Printing indexes of the cards to hold */
		for (int i = 0; i < indexes.length; i++) {
			System.out.print(" " + (indexes[i]+1));
		}
		
		/* Checking if more than 5 indexes were specified */
		if (indexes.length > 5) {
			System.out.println("\nh: too many indexes");
			return false;
		}
		
		/* Checking if there are repeated or invalid indexes */
		int[] mask = new int[indexes.length];
		for (int i = 0; i < indexes.length; i++) {
			if (indexes[i] < 0 || indexes [i] > 4) {
				System.out.println("\nh: illegal card index");
				return false;
			}
			
			if (mask[i] == 0) {
				mask[i] = 1;
			}
			else {
				System.out.println("\nh: repeated card index");
				return false;
			}
		}
		
	
		int[] discard = new int[5 - indexes.length];
		if(discard.length != 0) {
			for(int i = 0; i < 5; i++) {
		    	flag = 0;
				for(int j = 0; j < indexes.length; j++) {
					if(i == indexes[j]) {
						flag = 1;
					}	
				}
				if(flag == 0) {
					discard[k] = i;
					k++;
				}
			}
		    
			/* Checking if there are enough cards in the deck for replacing */
			if (discard.length > deck.getNumCards()) {
				System.out.println("\nh: not enough cards in the deck");
				return false;
			}
			
			for(int i = 0; i < discard.length; i++) {
				discarded_cards.add(player.removeCard(discard[i]));
				player.placeCard(deck.drawCard());
				deck.addCard(discarded_cards.remove(0));
			}
				
		}
		
		System.out.println("\nplayer's hand " + player.getHand());
		
		return true;
	}
	
	
	/**
	 * {@inheritDoc}
	 * (Verbose)
	 */
	@Override
	public int credit() {
		System.out.println("\n-cmd $");
		System.out.println("player's credit is " + player.getCredits());
		return player.getCredits();
	}
	
	
	/**
	 * {@inheritDoc}
	 * (Verbose)
	 */
	@Override
	public int[] advice() {
		System.out.println("\n-cmd a");
		
		int[] indexes = variant.advice(player.getHand(), true);
		
		if (indexes.length == 0) {
			System.out.println("player should discard all cards");
		}
		else {
			System.out.print("player should hold cards");
			for (int i = 0; i < indexes.length; i++)
				System.out.print(" " + (indexes[i] + 1));
			System.out.println();
		}
		return indexes;
	}
	
	
	@Override
	public void statistics() {
		System.out.println("\n-cmd s");
		
		double theoreticalReturn = (player.getCredits() - initialCredits + sumOfAllBets) / (float) sumOfAllBets * 100;
		variant.statistics();
		
		System.out.println(" Credit            | " + player.getCredits() + " ("+ String.format("%.3f", theoreticalReturn) +"%)");
		System.out.println("-------------------------------------");
	}
	
	
	/**
	 * {@inheritDoc}
	 * (Verbose)
	 */
	@Override
	public void evaluate(int bet) {
		String handKey = getHandKey();
		
		player.addCredits(getHandPayout(bet));
		
		if (!handKey.equals("Other")) {
			System.out.println("player wins with a " + handKey.toUpperCase() + " and his credit is " + player.getCredits());
		}
		else {
			System.out.println("player loses and his credit is " + player.getCredits());
		}
	}
	
	
}
