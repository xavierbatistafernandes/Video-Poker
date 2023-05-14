package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import cards.Card;
import cards.Deck;
import cards.Rank;
import cards.Suit;

import game.Debug;
import game.DoubleBonus107;
import game.IMode;
import game.IVariant;
import game.Player;
import game.Simulation;

/**
 * Main class which runs simulation or debug game modes depending on the input arguments specified.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class Main {

	/**
	 * Main method
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		
		List<Card> cards = new LinkedList<>();
		List<String> commands = new LinkedList<>();
		
		if(args.length < 1 || args[0].length() != 2 || args[0].charAt(0) != '-')
			correctUsage();
		
		Player player;
		Deck deck;
		IVariant variant;
		
		IMode game = null;
		
		String[] tokens = null;
		String new_command;
		String tmpString = "";
		
		int tmp;
		int credits = 0;

		Rank rank = null;
		Suit suit = null;
		
		/* Checking the invoked game mode */
		switch(args[0].charAt(1)) {
			
			case 'd':
				
				if(args.length != 4)
					correctUsage();
				
				System.out.println("===== Video Poker - Debug =====");
				
				tmp = -1;
				tokens = null;
				
				try {
					
					credits = Integer.parseInt(args[1]);
					
					if(credits <= 0) {
						System.out.println("error: invalid number of credits");
						System.exit(-1);
					}
					
					tokens = null;
					tmp = -1;
					
					/* Reading command file */
					File cmd_file = new File(args[2]);
					FileReader cmd_fr = new FileReader(cmd_file);
					BufferedReader cmd_br = new BufferedReader(cmd_fr);		
					
					while((tmp = cmd_br.read()) != -1) {
						if (tmp == ' ' || tmp == '\t' || tmp == '\n' || tmp == '\r')
							continue;
						
						tmpString += "" + (char) tmp;
					}
					
					
					tokens = tmpString.split("");

                    if (tokens[0].equals("")) {
                        System.out.println("error: command file has no commands");
                        System.exit(-1);
                    }
                    
					for (int i = 0; i < tokens.length; i++) {
						new_command = "";
						
						switch(tokens[i]) {
							
							case "b":
							case "$":
							case "d":
							case "h":
							case "a":
							case "s":
								new_command += tokens[i];
								for (int j = i+1; j < tokens.length; j++) {
									try {
										tmp = Integer.parseInt(tokens[j]);
										new_command += tmp;
										i++;
									}
									catch (NumberFormatException e) {
										break;
									}
								}
								
								commands.add(new_command);
								break;
								
							default:
								System.out.println("error: invalid command file format");
								System.exit(-1);
							
						}
					}
					
					cmd_fr.close();
					cmd_br.close();
								
					/* Reading card-file */
					tmp = -1;
					tmpString = "";
					tokens = null;

					
					File card_file = new File(args[3]);
					FileReader card_fr = new FileReader(card_file);
					BufferedReader card_br = new BufferedReader(card_fr);
					
					
					while((tmp = card_br.read()) != -1) {
						if (tmp == ' ' || tmp == '\t' || tmp == '\n' || tmp == '\r')
							continue;
						
						tmpString += "" + (char) tmp;
					}
					
					tokens = tmpString.split("");
                    if (tokens[0].equals("")) {
                        System.out.println("error: card file has no cards");
                        System.exit(-1);
                    }
                    
					for (int i = 0; i < tokens.length; i+=2) {
						
						try {
					    	switch(tokens[i]) {
								case "2": rank = Rank.TWO;		break;
								case "3": rank = Rank.THREE;	break;
								case "4": rank = Rank.FOUR;		break;
								case "5": rank = Rank.FIVE;		break;
								case "6": rank = Rank.SIX;		break;
								case "7": rank = Rank.SEVEN;	break;
								case "8": rank = Rank.EIGHT;	break;
								case "9": rank = Rank.NINE;		break;
								case "T": rank = Rank.TEN;		break;
								case "J": rank = Rank.JACK;		break;
								case "Q": rank = Rank.QUEEN;	break;
								case "K": rank = Rank.KING;		break;
								case "A": rank = Rank.ACE;		break;
								default:
									System.out.println("error: invalid card file format");
									System.exit(-1);
					    	}
					    	
					    	switch(tokens[i+1]) {
								case "H": suit = Suit.HEARTS; 	 break;
								case "D": suit = Suit.DIAMONDS;  break;
								case "C": suit = Suit.CLUBS; 	 break;
								case "S": suit = Suit.SPADES; 	 break;
								default:
									System.out.println("error: invalid card file format");
									System.exit(-1);
					    	}
					    	
					    	cards.add(new Card(rank, suit));
						}
						catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("error: invalid card file format");
							System.exit(-1);
						}
					       
					}
					
					
					card_fr.close();
					card_br.close();
					
					
				}
				catch(NullPointerException | IOException e) {
					System.out.println("error: could not read input files");
					correctUsage();
					System.exit(-1);
				}
				catch(NumberFormatException e) {
					System.out.println("error: wrong parsing of inputs");
					System.exit(-1);
				}
				
				
				player = new Player(credits);
				deck = new Deck(cards);
				variant = new DoubleBonus107();
				
				game = new Debug(player, deck, variant, commands);
				
				break;
				
			case 's':
				
				if(args.length != 4) {
					correctUsage();
				}
				
				System.out.println("===== Video Poker - Simulation =====");
				
				int bet_amount = 0;
				int nb_deals = 0;
				
				try {
					credits = Integer.parseInt(args[1]);
					bet_amount = Integer.parseInt(args[2]);
					nb_deals = Integer.parseInt(args[3]);
				}
				catch(NumberFormatException e) {
					System.out.println("error: wrong parsing of inputs");
					validValues();
					System.exit(-1);
				}
				
				if(credits <= 0 || bet_amount < 1 || bet_amount > 5 || nb_deals <= 0) {
					System.out.println("error: invalid values of inputs");
					validValues();
					System.exit(-1);
				}

				player = new Player(credits);
				deck = new Deck();
				variant = new DoubleBonus107();
				
				game = new Simulation(player, deck, variant, nb_deals, bet_amount);
				
				break;
		}
		
		game.play();
		
		System.out.println("exit success");
	}
	
	/**
	 * Prints the correct usage of the program
	 */
	private static void correctUsage() {
		System.out.println("*********************************************************");
		System.out.println("* Incorrect input format. Usage:                        *");
		System.out.println("* java -jar videopoker.jar -d credit cmd-file card-file *");
		System.out.println("* or                                                    *");
		System.out.println("* java -jar videopoker.jar -s credit bet nbdeals        *");
		System.out.println("*                                                       *");
		System.out.println("* 'credit' must be an integer > 0                       *");
		System.out.println("* 'bet' must be an integer >= 1 and <= 5                *");
		System.out.println("* 'nbdeals' must be an integer > 0                      *");
		System.out.println("*********************************************************");
		System.exit(-1);
	}
	
	/**
	 * Prints what the valid values are for the player's credits, betting amount and number of deals
	 */
	private static void validValues() {
		System.out.println("'credit' must be an integer greater than 0.");
		System.out.println("'bet' must be an integer between 1 and 5");
		System.out.println("'nbdeals must be an integer greater than 0");
		System.out.println("Exiting...");
	}

}