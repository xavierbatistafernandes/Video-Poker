package game;


import java.util.Iterator;
import java.util.List;

import cards.Deck;

/**
 * Debugging class that extends the AbstractDebug class. Runs a series of commands read from a text file for a deck that is also loaded from a text file.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class Debug extends AbstractDebug {
	
	/**
	 * List of commands to run
	 */
	private List<String> commands;
	
	/**
	 * Constructor class, which initializes the player, deck, variant and command list
	 * @param player Player object
	 * @param deck Deck to utilize
	 * @param variant Variant of poker
	 * @param commands Command list
	 */
	public Debug(Player player, Deck deck, IVariant variant, List<String> commands) {
		super(player, deck, variant);
		this.commands = commands;
	}
	
	
	@Override
	public void play() {
		Iterator<String> itr = commands.iterator();
		char nextState = '\0';
		char prevState = '\0';
		
		String tmp = "";
		int amount = 0;
		
		boolean isBetSpecified = false;
		boolean isPrevStateValid = false;
		
		while(itr.hasNext()) {
	
			tmp = itr.next();
			nextState = tmp.charAt(0);
			
			switch (nextState) {
				
				case 'b':
					
					if (prevState != 'b' && prevState != 'h' && prevState != '\0') {
						System.out.println("\n-cmd b");
						System.out.println("b: bet is only allowed at the start of the round");
						return;
					}
					
					if (prevState == 'b' && isPrevStateValid == true) {
						System.out.println("\n-cmd b");
						System.out.println("b: cannot bet twice in a row");
						return;
					}
					
					try {
						tmp = tmp.substring(1);
						amount = Integer.parseInt(tmp);
						isBetSpecified = true;
					}
					catch (NumberFormatException | StringIndexOutOfBoundsException e) {
						amount = 5;
						isBetSpecified = false;
						
					}
					
					isPrevStateValid = bet(amount, isBetSpecified);
					
					break;
					
					
				case '$':
					credit();
					break;
					
					
				case 'd':
					if (isPrevStateValid == false) {
						System.out.println("\n-cmd d");
						System.out.println("d: cannot deal after an invalid bet");
						return;
					}
					
					if (prevState == 'd') {
						System.out.println("\n-cmd d");
						System.out.println("d: cannot deal twice in a row");
						return;
					}
					
					if (prevState != 'b') {
						System.out.println("\n-cmd d");
						System.out.println("d: deal is only allowed after bet");
						return;
					}
					
					if (deal() == false) {
						return;
					}
					
					break;
					
					
				case 'h':
					if (prevState == 'h') {
						System.out.println("\n-cmd h");
						System.out.println("h: cannot hold twice in a row");
						return;
					}
					
					if (prevState != 'd') {
						System.out.println("\n-cmd h");
						System.out.println("h: hold is only allowed after deal");
						return;
					}
					
					int num_holds = tmp.length()-1;
					int[] indexes = new int[num_holds];
				
					for (int i = 0; i < num_holds; i++) {
						try {
							indexes[i] = Integer.parseInt("" + tmp.charAt(i+1)) - 1;
						}
						catch (NumberFormatException e) {
							System.out.println("h: cards to hold must be integers");
							return;
						}
					}
					
					
					if (hold(indexes) == false) {
						return;
					}
					
					evaluate(amount);
					
					restoreDeck();
					
					break;
				
					
				case 'a':
					
					if (prevState != 'd') {
						System.out.println("\n-cmd a");
						System.out.println("a: advice is only allower after deal");
						return;
					}
						
					advice();
					break;
						
					
				case 's':
					statistics();
					break;
			}
			
			if (nextState != '$' && nextState != 'a' && nextState != 's')
				prevState = nextState;
		}
	}


}
