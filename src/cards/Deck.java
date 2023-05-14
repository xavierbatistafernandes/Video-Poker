package cards;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that represents a deck of cards.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class Deck {
	
	/**
	 * List of cards that together make the deck
	 */
	private LinkedList<Card> deck;
	
	/**
	 * Constructor class that creates a standard 52 card deck
	 */
	public Deck() {
		deck = new LinkedList<Card>();
		this.add52Cards();
	}
	
	/**
	 * Constructor class that creates a deck with cards from a linked list
	 * @param loaded_deck - cards to be placed in the deck
	 */
	public Deck(List<Card> loaded_deck) {
		deck = (LinkedList<Card>) loaded_deck;
	}
	
	/**
	 * Add 52 unique cards to the deck
	 */
	public void add52Cards() {
		for(Suit aux_suit: Suit.values()) {
			for(Rank aux_rank: Rank.values()) {
				Card aux_card = new Card(aux_rank,aux_suit);
				deck.add(aux_card);
			}
		}
	}

	/**
	 * Add a single card to the deck
	 * @param card - card to be added
	 */
	public void addCard(Card card) {
		deck.addLast(card);
	}
	
	/**
	 * Get the number of cards in the deck
	 * @return int - amount of cards in the deck
	 */
	public int getNumCards() {
		return deck.size();
	}
	
	/**
	 * Draw a card from the deck
	 * @return card - Drawn card
	 */
	public Card drawCard() {
		return deck.removeFirst();
	}
	
	/**
	 * Shuffle the deck
	 */
	public void shuffle() {
		Collections.shuffle(deck);
	}

	/**
	 * Defines string that represents the deck
	 */
	@Override
	public String toString() {
		String cards = "";
		for (int i = 0; i < deck.size(); i++)
			cards += deck.get(i) + " ";
		
		return cards;
	}

}
