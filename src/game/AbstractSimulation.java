package game;

import java.util.LinkedList;
import java.util.List;

import cards.Card;
import cards.Deck;


/**
 * Abstract class that extends the AbstractMode class. Contains typical methods utilized for simulation purposes.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public abstract class AbstractSimulation extends AbstractMode {
	
	/**
	 * Number of deals to run the simulation
	 */
	protected int nbdeals;
	
	/**
	 * Amount of credits to bet
	 */
	protected int bet;

	
	/**
	 * Constructor method that initializes the player, deck, variant and number of deals and bet amount
	 * @param player Player object
	 * @param deck Deck to use in simulation
	 * @param variant Variant to use for simulation
	 * @param nbdeals Number of deals to perform
	 * @param bet Amount to bet for each deal
	 */
	public AbstractSimulation(Player player, Deck deck, IVariant variant, int nbdeals, int bet) {
		super(player, deck, variant);
		this.nbdeals = nbdeals;
		this.bet = bet;
	}
	
	
	/**
	 * {@inheritDoc}
	 * (No verbose)
	 */
	@Override
	public boolean bet(int bet_amount, boolean bet_specified) {
		
		if (bet_amount < 1 || bet_amount > 5 || bet_amount > player.getCredits()) {
			return false;
		}
		else {
			player.removeCredits(bet_amount);
			sumOfAllBets += bet_amount;
			return true;
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 * (Deck is shuffled before dealing)
	 */
	@Override
	public boolean deal() {
		
		if (deck.getNumCards() < 5)
			return false;
		
		deck.shuffle();
		
		for(int i = 0; i < 5; i++) {
			player.placeCard(deck.drawCard());
		}
		
		return true;
	}
	
	
	/**
	 * {@inheritDoc}
	 * (No verbose)
	 */
	@Override
	public boolean hold(int[] indexes) {
		
		List<Card> discarded_cards = new LinkedList<Card>();
		
		int[] discard = new int[5 - indexes.length];
		int k = 0;		
		int flag = 0;
		
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
		    
			for(int i = 0; i < discard.length; i++) {
				discarded_cards.add(player.removeCard(discard[i]));
				player.placeCard(deck.drawCard());
				deck.addCard(discarded_cards.remove(0));
			}
				
		}
		
		return true;
	   
	}
	
	
	/**
	 * {@inheritDoc}
	 * (No verbose)
	 */
	@Override
	public int credit() {
		return player.getCredits();
	}
	
	/**
	 * {@inheritDoc}
	 * (No verbose)
	 */
	@Override
	public int[] advice() {
		return variant.advice(player.getHand(), false);
	}
	
	/**
	 * {@inheritDoc}
	 * (No verbose)
	 */
	@Override
	public void statistics() {
		
		variant.statistics();
		
		double theoreticalReturn = (player.getCredits() - initialCredits + sumOfAllBets) / ((float) sumOfAllBets) * 100;
		System.out.println(" Credit            | " + player.getCredits() + " ("+ String.format("%.3f", theoreticalReturn) +"%)");
		System.out.println("------------------------------------");
	}
	
	/**
	 * {@inheritDoc}
	 * (No verbose)
	 */
	@Override
	public void evaluate(int bet) {
		int payout = getHandPayout(bet);
		player.addCredits(payout);
	}

}
