package game;


/**
 * Game mode interface that specifies a required method for a game mode implementation.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public interface IMode {
	
	/**
	 * Play method that contains the actions taken by a game mode implementation (for example, the simulation game mode specifies a number of deals and runs a typical 
	 * bet, deal, hold, evaluate sequence for each deal) 
	 */
	public void play();
}
