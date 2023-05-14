package game;

import java.util.HashMap;
import java.util.Map;


/**
 * Abstract class that implements the IVvariant interface. Contains required attributes for a poker variant
 * and implements a method for getting a hand's payout value.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public abstract class AbstractVariant implements IVariant {
	
	/**
	 * Number of hands played
	 */
	protected int totalHands;
	
	/**
	 * Table that contains the payout amounts
	 */
	protected Map<String, int[]> payTable;
	
	/**
	 * Table that contains statistics (how many times each hand occurred)
	 */
	protected Map<String, Integer> statsTable;
	
	
	/**
	 * Constructor class which creates the payout and statistics tables
	 */
	public AbstractVariant() {
		payTable = new HashMap<>();
		statsTable = new HashMap<>();
	}
	
	
	/**
	 * Method that updates the statistics table
	 * @param handKey String that corresponds to the player's hand 
	 */
	public abstract void updateStats(String handKey);
	
	
	/**
	 * Method that increments the value of an entry of the statistics table specified by the string that represents the player's hand
	 * @param handKey String that corresponds to the player's hand 
	 */
	public void incrementKey(String handKey) {
		if (statsTable.put(handKey, statsTable.get(handKey)+1) == null)
			statsTable.put("Other", statsTable.get("Other")+1);
	}
	
	
	@Override
	public int getHandPayout(String handKey, int bet) {
		int[] payoutArray;
		
		payoutArray = payTable.get(handKey);
		
		updateStats(handKey);
		
		if (payoutArray == null)
			return 0;
		
		return payoutArray[bet-1];
	}
	
}
