package game;


/**
 * Class that extends the AbstractDoubleBonus class. Implements the Double Bonus 10/7 variant by specifying its payout table.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class DoubleBonus107 extends AbstractDoubleBonus {
	
	
	/**
	 * Constructor class for the Double Bonus 10/7 variant which initializes the payout table
	 */
	public DoubleBonus107() {
		
		payTable.put("Royal Flush", 	new int[]{250, 500, 750, 1000, 4000});
		payTable.put("Straight Flush", 	new int[]{ 50, 100, 150,  200,  250});
		payTable.put("Four Aces", 		new int[]{160, 320, 480,  640,  800});
		payTable.put("Four 2-4", 		new int[]{ 80, 160, 240,  320,  400});
		payTable.put("Four 5-K", 		new int[]{ 50, 100, 150,  200,  250});
		payTable.put("Full House", 		new int[]{ 10,  20,  30,   40,   50});
		payTable.put("Flush", 			new int[]{  7,  14,  21,   28,   35});
		payTable.put("Straight", 		new int[]{  5,  10,  15,   20,   25});
		payTable.put("Three of a Kind", new int[]{  3,   6,   9,   12,   15});
		payTable.put("Two Pair", 		new int[]{  1,   2,   3,    4,    5});
		payTable.put("Jacks or Better", new int[]{  1,   2,   3,    4,    5});
		
	}
}