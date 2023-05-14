package game;

import cards.Deck;

/**
 * Simulation class which extends the AbstractSimulation class. Implements the play method (which runs the simulation).
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class Simulation extends AbstractSimulation {
	
	/**
	 * Constructor class which initializes player, deck, variant, number of deals and betting amount
	 * @param player Player object
	 * @param deck Deck to utilize
	 * @param variant Variant to utilize
	 * @param nbdeals Number of deals to perform for the simulation
	 * @param bet Betting amount
	 */
	public Simulation(Player player, Deck deck, IVariant variant, int nbdeals, int bet) {
		super(player, deck, variant, nbdeals, bet);
	}

	@Override
	public void play() {
		
		for (int i = 1; i <= nbdeals; i++) {
			
			if (bet(bet, false) == false)
				break;
				
			deal();
			
			hold(advice());
			
			evaluate(bet);
			
			restoreDeck();
		
		}
		
		statistics();
	}
}
