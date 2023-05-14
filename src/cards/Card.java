package cards;

import java.util.Objects;

/**
 * Class that represents a card from the deck, a card must have a rank and a suit.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public class Card {
	
	/**
	 * Card's rank
	 */
	private final Rank rank;
	
	/**
	 * Card's suit
	 */
	private final Suit suit;
	
	/**
	 * Constructor for a card, a rank and suit is required, two enumerated types are received in order to ensure
	 * that the rank and suit of the card are valid
	 * @param rank Rank of the card
	 * @param suit Suit of the card
	 */
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	/**
	 * Obtains the card's rank
	 * @return Rank Card's rank
	 */
	public Rank getRank() {
		return this.rank;
	}
	
	/**
	 * Obtains the card's suit
	 * @return Suit Card's suit
	 */
	public Suit getSuit() {
		return this.suit;
	}
	
	/**
	 * Determines the string to be printed for a card
	 * @return String String that represents the card
	 */
	@Override
	public String toString() {
		String card = new String("");
		
		switch(rank) {
			case ACE: 	card += "A"; break;
			case TWO: 	card += "2"; break;
			case THREE: card += "3"; break;
			case FOUR: 	card += "4"; break;
			case FIVE: 	card += "5"; break;
			case SIX: 	card += "6"; break;
			case SEVEN: card += "7"; break;
			case EIGHT: card += "8"; break;
			case NINE: 	card += "9"; break;
			case TEN: 	card += "T"; break;
			case JACK: 	card += "J"; break;
			case QUEEN: card += "Q"; break;
			case KING: 	card += "K"; break;
			default:
				System.out.println("error: invalid card rank");
				System.exit(-1);
		}
		
		switch(suit) {
			case SPADES:   card += "S"; break;
			case CLUBS:    card += "C"; break;
			case HEARTS:   card += "H"; break;
			case DIAMONDS: card += "D"; break;
			default:
				System.out.println("error: invalid card suit");
				System.exit(-1);
		}
		
		return card;
	}
	
	/**
	 * Override hash function
	 */
	@Override
	public int hashCode() {
		return Objects.hash(rank, suit);
	}

	/**
	 * Override the equals method to ensure cards are compared correctly (i.e., equal cards have the same suit and rank)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return rank == other.rank && suit == other.suit;
	}
}
