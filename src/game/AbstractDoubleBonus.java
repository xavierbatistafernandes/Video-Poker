package game;

import java.util.Arrays;

import cards.Card;
import cards.Hand;
import cards.Rank;
import cards.Suit;

/**
 * Abstract class that extends the AbstractVariant class. Contains useful methods for implementing poker variants of the Double Bonus type, for example, checking what cards the player should hold and
 * updating statistics. Different Double Bonus variants just have to implement their own payout table.
 * @author Diogo Martins, Jose Brito, Xavier Fernandes
 *
 */
public abstract class AbstractDoubleBonus extends AbstractVariant {
	

	/**
	 * Constructor class which initializes statistics table
	 */
	public AbstractDoubleBonus() {
		statsTable.put("Jacks or Better", 0);
		statsTable.put("Two Pair", 		  0);
		statsTable.put("Three of a Kind", 0);
		statsTable.put("Straight", 		  0);
		statsTable.put("Flush", 		  0);
		statsTable.put("Full House", 	  0);
		statsTable.put("Four of a Kind",  0);
		statsTable.put("Straight Flush",  0);
		statsTable.put("Royal Flush", 	  0);
		statsTable.put("Other", 		  0);
	}
	
	
	@Override
	public void updateStats(String handKey) {
		
		if (handKey.equals("Four Aces") || handKey.equals("Four 2-4") || handKey.equals("Four 5-K"))
			handKey = "Four of a Kind";
		
		incrementKey(handKey);
		
		totalHands++;
	}
	
	
	/**
	 * Prints the statistics table
	 */
	@Override
	public void statistics() {
		
		System.out.println("-------------------------------------");
		System.out.println(" Hand              | Nb             ");
		System.out.println("-------------------------------------");
		System.out.println(" Jacks or Better   | " + statsTable.get("Jacks or Better"));
		System.out.println(" Two Pair          | " + statsTable.get("Two Pair"));
		System.out.println(" Three of a Kind   | " + statsTable.get("Three of a Kind"));
		System.out.println(" Straight          | " + statsTable.get("Straight"));
		System.out.println(" Flush             | " + statsTable.get("Flush"));
		System.out.println(" Full House        | " + statsTable.get("Full House"));
		System.out.println(" Four of a Kind    | " + statsTable.get("Four of a Kind"));
		System.out.println(" Straight Flush    | " + statsTable.get("Straight Flush"));
		System.out.println(" Royal Flush       | " + statsTable.get("Royal Flush"));
		System.out.println(" Other             | " + statsTable.get("Other"));
		System.out.println("-------------------------------------");
		System.out.println(" Total             | " + totalHands);
		System.out.println("-------------------------------------");

	}
	
	
	
	/**
	 * Gets the string that corresponds to the player's hand
	 * @param hand Player's hand
	 */
	@Override
	public String getHandKey(Hand hand) {
		
		if (isRoyalFlush(hand).length 	 != 0) 	return "Royal Flush";
		if (isStraightFlush(hand).length != 0) 	return "Straight Flush";
		if (isFourAces(hand).length 	 != 0) 	return "Four Aces";
		if (isFour24(hand).length 		 != 0) 	return "Four 2-4";
		if (isFour5K(hand).length 		 != 0) 	return "Four 5-K";
		if (isFullHouse(hand).length 	 != 0) 	return "Full House";
		if (isFlush(hand).length 		 != 0) 	return "Flush";
		if (isStraight(hand).length 	 != 0) 	return "Straight";
		if (isThreeOfAKind(hand).length  != 0) 	return "Three of a Kind";
		if (isTwoPair(hand).length 		 != 0) 	return "Two Pair";
		if (isJacksOrBetter(hand).length != 0) 	return "Jacks or Better";
		
		return "Other";
		
	}
	
	
	
	/**
	 * Determines which cards the player should hold
	 * @param originalHand Player's hand
	 * @param makeCopy Boolean value that decides whether a copy of the hand should be it can be sorted without messing with the original hand's indices
	 */
	@Override
	public int[] advice(Hand originalHand, boolean makeCopy) {
		
		Hand hand;
		int[] hold;
		
		if (makeCopy == true) {
			hand = new Hand(5);
			for (int i = 0; i < 5; i++)
				hand.placeCard(originalHand.getCard(i));
		}
		else {
			hand = originalHand;
		}
		
		while (true) {
			
				 if((hold = isRoyalFlush(hand)).length 									!= 0) break;
			else if((hold = isStraightFlush(hand)).length 								!= 0) break;
			else if((hold = isFourOfAKind(hand)).length 								!= 0) break;
			else if((hold = isFourToARoyalFlush(hand)).length 							!= 0) break;
			else if((hold = isThreeAces(hand)).length 									!= 0) break;
			else if((hold = isStraight(hand)).length 									!= 0) break;
			else if((hold = isFlush(hand)).length 										!= 0) break;
			else if((hold = isFullHouse(hand)).length 									!= 0) break;
			else if((hold = isThreeOfAKind(hand)).length 								!= 0) break;
			else if((hold = isFourToAStraightFlush(hand)).length 						!= 0) break;
			else if((hold = isTwoPair(hand)).length 									!= 0) break;
			else if((hold = isHighPair(hand)).length 									!= 0) break;
			else if((hold = isFourToAFlush(hand)).length 								!= 0) break;
			else if((hold = isThreeToARoyalFlush(hand)).length 							!= 0) break;
			else if((hold = isFourToAnOutsideStraight(hand)).length 					!= 0) break;
			else if((hold = isLowPair(hand)).length 									!= 0) break;
			else if((hold = isAKQJUnsuited(hand)).length 								!= 0) break;
			else if((hold = isThreeToAStraightFlushTypeOne(hand)).length 				!= 0) break;
			else if((hold = isFourToAnInsideStraightWithThreeHighCards(hand)).length 	!= 0) break;
			else if((hold = isQJSuited(hand)).length 									!= 0) break;
			else if((hold = isThreeToAFlushWithTwoHighCards(hand)).length 				!= 0) break;
			else if((hold = isTwoSuitedHighCards(hand)).length 							!= 0) break;
			else if((hold = isFourToAnInsideStraightWithTwoHighCards(hand)).length 		!= 0) break;
			else if((hold = isThreeToAStraightFlushTypeTwo(hand)).length 				!= 0) break;
			else if((hold = isFourToAnInsideStraightWithOneHighCard(hand)).length 		!= 0) break;
			else if((hold = isKQJUnsuited(hand)).length 								!= 0) break;
			else if((hold = isJTSuited(hand)).length 									!= 0) break;
			else if((hold = isQJUnsuited(hand)).length 									!= 0) break;
			else if((hold = isThreeToAFlushWithOneHighCard(hand)).length 				!= 0) break;
			else if((hold = isQTSuited(hand)).length 									!= 0) break;
			else if((hold = isThreeToAStraightFlushTypeThree(hand)).length 				!= 0) break;
			else if((hold = isKQorKJUnsuited(hand)).length 								!= 0) break;
			else if((hold = isAce(hand)).length 										!= 0) break;
			else if((hold = isKTSuited(hand)).length 									!= 0) break;
			else if((hold = isJackQueenOrKing(hand)).length 							!= 0) break;
			else if((hold = isFourToAnInsideStraightWithNoHighCards(hand)).length 		!= 0) break;
			else if((hold = isThreeToAFlushWithNoHighCards(hand)).length 				!= 0) break;
				 
			break;
			
		}
		
		
		
		
		if (hold.length != 0) {
			if (makeCopy == true) return getOriginalIndexes(hand, originalHand, hold);
			
			return hold;
		}
		else {
			return new int[0];
		}
	}	
	
	
	/**
	 * Checks if the player's hand is a Royal Flush
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isRoyalFlush(Hand hand) {
		return (isStraightFlush(hand).length != 0 && hand.getRankAt(0) == Rank.TEN)? new int[] {0,1,2,3,4} : new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Straight Flush
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isStraightFlush(Hand hand) {
		return (isStraight(hand).length != 0 && isFlush(hand).length != 0)? new int[] {0,1,2,3,4} : new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Four Of A Kind
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourOfAKind(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[0] == rank[3]) {
			return new int[] {0,1,2,3};
		}
		else if(rank[1] == rank[4]){
			return new int[] {1,2,3,4};
		}
			return new int[0];
		}
	
	/**
	 * Checks if the player's hand is Four Aces
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourAces(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		return (rank[1] == Rank.ACE && rank[4] == Rank.ACE)? new int[] {1,2,3,4}: new int[0];
	}
	
	/**
	 * Checks if the player's hand is Four 2-4
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFour24(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(isFourOfAKind(hand).length != 0 && rank[2].ordinal() >= Rank.TWO.ordinal() && rank[2].ordinal() <= Rank.FOUR.ordinal()) return isFourOfAKind(hand);
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is Four 5-K
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFour5K(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(isFourOfAKind(hand).length != 0 && rank[2].ordinal() >= Rank.FIVE.ordinal() && rank[2].ordinal() <= Rank.KING.ordinal()) return isFourOfAKind(hand);
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is Four To A Royal Flush
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourToARoyalFlush(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		if(suit[0] == suit [3] && rank[0].ordinal() >= Rank.TEN.ordinal()) return new int[] {0,1,2,3};
		else if(suit[1] == suit[4] && rank[1].ordinal() >= Rank.TEN.ordinal()) return new int[] {1,2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is Three Aces
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isThreeAces(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		return (rank[2] == Rank.ACE)? new int[] {2,3,4}: new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Full House
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFullHouse(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[0] == rank[2] && rank[3] == rank[4]) return new int[] {0,1,2,3,4};
		if(rank[0] == rank[1] && rank[2] == rank[4]) return new int[] {0,1,2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Flush
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFlush(Hand hand) {
		hand.sortBySuit();
		Suit[] suit = getSuit(hand);	
		return suit[0] != suit[4]? new int[0] : new int[] {0,1,2,3,4};
	}
	
	/**
	 * Checks if the player's hand is a Straight
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isStraight(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[0] == Rank.TWO && rank[1] == Rank.THREE && rank[2] == Rank.FOUR && rank[3] == Rank.FIVE && rank[4] == Rank.ACE) {
			return new int[]{0,1,2,3,4};
		}
		for(int i = 0; i < 4; i++) {
			if(rank[i+1].ordinal() != rank[i].ordinal() + 1) {
				return new int[0];
			}
		}
		return new int[]{0,1,2,3,4};
	}
	
	/**
	 * Checks if the player's hand is a Three Of A Kind
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isThreeOfAKind(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[0] == rank[2]) return new int[] {0,1,2};
		if(rank[1] == rank[3]) return new int[] {1,2,3};
		if(rank[2] == rank[4]) return new int[] {2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Four To A Straight Flush
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourToAStraightFlush(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		Boolean SameSuitFirst4 = true;
		Boolean SameSuitLast4 = true;
		
		SameSuitFirst4 = checkSameSuit(suit,0,3);
		if(SameSuitFirst4 == true) {
			if(((rank[3].ordinal() - rank[0].ordinal()) <= 4) || (rank[3] == Rank.ACE && rank[2].ordinal() <= Rank.FIVE.ordinal())) return new int[] {0,1,2,3};
		}

		SameSuitLast4 = checkSameSuit(suit,1,4);
		if(SameSuitLast4 == true) {
			if(((rank[4].ordinal() - rank[1].ordinal()) <= 4) || (rank[4] == Rank.ACE && rank[3].ordinal() <= Rank.FIVE.ordinal())) return new int[] {1,2,3,4};
		}	
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Two Pair
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isTwoPair(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[0] == rank[1] && rank[2] == rank[3]) return new int[] {0,1,2,3};
		if(rank[0] == rank[1] && rank[3] == rank[4]) return new int[] {0,1,3,4};
		if(rank[1] == rank[2] && rank[3] == rank[4]) return new int[] {1,2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a High Pair
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isHighPair(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		for(int i = 0; i < 4; i++) {
			if((rank[i] == rank[i+1]) && (rank[i].ordinal() >= Rank.JACK.ordinal())) return new int[] {i,i+1};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Four To A Flush
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourToAFlush(Hand hand) {
		hand.sortBySuit();
		Suit[] suit = getSuit(hand);
		if(checkSameSuit(suit,0,3)) return new int[] {0,1,2,3};
		if(checkSameSuit(suit,1,4)) return new int[] {1,2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Three To A Royal Flush
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isThreeToARoyalFlush(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		if(suit[0] == suit[2] && rank[0].ordinal() >= Rank.TEN.ordinal()) return new int[] {0,1,2};
		if(suit[1] == suit[3] && rank[1].ordinal() >= Rank.TEN.ordinal()) return new int[] {1,2,3};
		if(suit[2] == suit[4] && rank[2].ordinal() >= Rank.TEN.ordinal()) return new int[] {2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Four To An Outside Straight
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourToAnOutsideStraight(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		for(int i = 0; i < 3; i++) {
			if(rank[i+1].ordinal() != rank[i].ordinal() + 1) break;
			if(i == 2 && rank[3] != Rank.ACE)  return new int[] {0,1,2,3};
		}
		for(int i = 1; i < 4; i++) {
			if(rank[i+1].ordinal() != rank[i].ordinal() + 1) break;
			if(i == 3 && rank[4] != Rank.ACE)  return new int[] {1,2,3,4};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Low Pair
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isLowPair(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		for(int i = 0; i < 4; i++) {
			if((rank[i] == rank[i+1]) && (rank[i].ordinal() <= Rank.TEN.ordinal())) return new int[] {i,i+1};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a AKQJ Unsuited
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isAKQJUnsuited(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[1] == Rank.JACK) return new int[] {1,2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Three To A Straight Flush (type one)
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isThreeToAStraightFlushTypeOne(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		int numGaps = 0;
		int numHighCards = 0;
		
		for(int i = 0; i <= 2; i++) {
			if(suit[i] == suit[i+2]) {
				numGaps = (rank[i+2].ordinal() - rank[i+1].ordinal()) +  (rank[i+1].ordinal() - rank[i].ordinal()) - 2;
				numHighCards = checkHighCards(rank,i,i+2);
				if(rank[i] == Rank.TWO && rank[i+1] == Rank.THREE && rank[i+2] == Rank.FOUR) return new int[0];
				if(numGaps < 3 && numHighCards >= numGaps) return new int[] {i,i+1,i+2};
			}
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Four To An Inside Straight With Three High Cards
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourToAnInsideStraightWithThreeHighCards(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[1] == Rank.TEN && rank[4] == Rank.ACE) return new int[] {1,2,3,4};
		if(rank[1] == Rank.NINE && rank[4] == Rank.KING && checkHighCards(rank,1,4) == 3) return new int[] {1,2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a QJ Suited
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isQJSuited(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		
		for(int i = 0; i < 4; i++) {
			if(rank[i] == Rank.JACK && rank[i+1] == Rank.QUEEN && suit[i] == suit[i+1]) return new int[] {i,i+1};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Three To A Flush With Two High Cards
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isThreeToAFlushWithTwoHighCards(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		
		if(suit[0] == suit[2] && checkHighCards(rank,0,2) == 2) return new int[] {0,1,2};
		if(suit[1] == suit[3] && checkHighCards(rank,1,3) == 2) return new int[] {1,2,3};
		if(suit[2] == suit[4] && checkHighCards(rank,2,4) == 2) return new int[] {2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is Two Suited High Cards
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isTwoSuitedHighCards(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		for(int i = 0; i < 4; i++) {
			if(suit[i] == suit[i+1] && rank[i].ordinal() >= Rank.JACK.ordinal()) return new int[] {i,i+1};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Four To An Inside Straight With Two High Cards
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourToAnInsideStraightWithTwoHighCards(Hand hand){
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[1] == Rank.NINE && rank[2] == Rank.TEN && rank[4] == Rank.KING) return new int[] {1,2,3,4};
		if(rank[1] == Rank.EIGHT && rank[3] == Rank.JACK && rank[4] == Rank.QUEEN) return new int[] {1,2,3,4};
		if(rank[0] == Rank.EIGHT && rank[2] == Rank.JACK && rank[3] == Rank.QUEEN && (rank[4] == Rank.ACE || rank[4] == Rank.KING)) return new int[] {0,1,2,3};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Three To A Straight Flush (type two)
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isThreeToAStraightFlushTypeTwo(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		
		int numGaps = 0;
		int numHighCards = 0;
		
		for(int i = 0; i <= 2; i++) {
			if(suit[i] == suit[i+2]) {
				numGaps = (rank[i+2].ordinal() - rank[i+1].ordinal()) +  (rank[i+1].ordinal() - rank[i].ordinal()) - 2;
				numHighCards = checkHighCards(rank,i,i+2);
				if(rank[i] == Rank.TWO && rank[i+1] == Rank.THREE && rank[i+2] == Rank.FOUR) return new int[] {i,i+1,i+2};
				if(rank[i+1].ordinal() <= Rank.FIVE.ordinal() && rank[i+2] == Rank.ACE) return new int[] {i,i+1,i+2};
				if(numGaps == 1 || (numGaps == 2 && numHighCards == 1)) return new int[] {i,i+1,i+2};
			}
		}
		return new int[0];
	}
 	
	/**
	 * Checks if the player's hand is a Four To An Inside Straight With One High Card
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourToAnInsideStraightWithOneHighCard(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[1] == Rank.SEVEN && rank[4] == Rank.JACK) return new int[] {1,2,3,4};
		if(rank[1] == Rank.EIGHT && rank[2] == Rank.NINE && rank[3] == Rank.TEN && rank[4] == Rank.QUEEN) return new int[] {1,2,3,4};
		if(rank[0] == Rank.SEVEN && rank[3] == Rank.JACK) return new int[] {0,1,2,3};
		if(rank[0] == Rank.EIGHT && rank[1] == Rank.NINE && rank[2] == Rank.TEN && rank[3] == Rank.QUEEN) return new int[] {0,1,2,3};
		if(rank[2].ordinal() <= Rank.FIVE.ordinal() && rank[4] == Rank.ACE) return new int[] {0,1,2,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is KQJ Unsuited
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isKQJUnsuited(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		for(int i = 0; i < 3; i++) {
			if(rank[i] == Rank.JACK && rank[i+2] == Rank.KING ) return new int[] {i,i+1,i+2};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is JT Suited
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isJTSuited(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		for(int i = 0; i < 4; i++) {
			if(rank[i] == Rank.TEN && rank[i+1] == Rank.JACK && suit[i] == suit[i+1]) return new int[] {i,i+1};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is QJ Unsuited
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isQJUnsuited(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		for(int i = 0; i < 4; i++) {
			if(rank[i] == Rank.JACK && rank[i+1] == Rank.QUEEN && suit[i] != suit[i+1]) return new int[] {i,i+1};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Three To A Flush With One High Card
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isThreeToAFlushWithOneHighCard(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		if(suit[0] == suit[2] && checkHighCards(rank,0,2) == 1) return new int[] {0,1,2};
		if(suit[1] == suit[3] && checkHighCards(rank,1,3) == 1) return new int[] {1,2,3};
		if(suit[2] == suit[4] && checkHighCards(rank,2,4) == 1) return new int[] {2,3,4};	
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is QT Suited
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isQTSuited(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		for(int i = 0; i < 4; i++) {
			if(rank[i] == Rank.TEN && rank[i+1] == Rank.QUEEN && suit[i] == suit[i+1]) return new int[] {i,i+1};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Three To A Straight Flush Type Three
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isThreeToAStraightFlushTypeThree(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		int numGaps = 0;
		int numHighCards = 0;
		
		for(int i = 0; i <= 2; i++) {
			if(suit[i] == suit[i+2]) {
				numGaps = (rank[i+2].ordinal() - rank[i+1].ordinal()) +  (rank[i+1].ordinal() - rank[i].ordinal()) - 2;
				numHighCards = checkHighCards(rank,i,i+2);
				if(numGaps == 2 && numHighCards == 0) return new int[] {i,i+1,i+2};
			}
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is KQ or KJ Unsuited
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isKQorKJUnsuited(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		for(int i = 0; i < 4; i++) {
			if((rank[i] == Rank.JACK || rank[i] == Rank.QUEEN) && rank[i+1] == Rank.KING && suit[i] != suit[i+1]) return new int[] {i,i+1};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Ace
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isAce(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[4] == Rank.ACE) return new int[] {4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is KT Suited
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isKTSuited(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		for(int i = 2; i < 4; i++) {
			if(rank[i] == Rank.TEN && rank[i+1] == Rank.KING && suit[i] == suit[i+1]) return new int[] {i,i+1};
		}
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Jack Queen Or King
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isJackQueenOrKing(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[4].ordinal() >= Rank.JACK.ordinal() && rank[4].ordinal() <= Rank.KING.ordinal()) return new int[] {4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Four To An Inside Straight With No High Cards
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isFourToAnInsideStraightWithNoHighCards(Hand hand) {
		hand.sortByRank();
		Rank[] rank = getRank(hand);
		if(rank[3].ordinal() - rank[0].ordinal() == 4) return new int[] {0,1,2,3};
		if(rank[4].ordinal() - rank[1].ordinal() == 4) return new int[] {1,2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is a Three To A Flush With No High Cards
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
	private int[] isThreeToAFlushWithNoHighCards(Hand hand) {
		hand.sortBySuit();
		Rank[] rank = getRank(hand);
		Suit[] suit = getSuit(hand);
		
		if(suit[0] == suit[2] && checkHighCards(rank,0,2) == 0) return new int[] {0,1,2};
		if(suit[1] == suit[3] && checkHighCards(rank,1,3) == 0) return new int[] {1,2,3};
		if(suit[2] == suit[4] && checkHighCards(rank,2,4) == 0) return new int[] {2,3,4};
		return new int[0];
	}
	
	/**
	 * Checks if the player's hand is Jacks Or Better
	 * @param hand Player's hand
	 * @return int[] - Positions of the cards to hold
	 */
 	private int[] isJacksOrBetter(Hand hand) {
		return isHighPair(hand);
	}
 	
	/**
	 * Gets the rank of each card in the player's hand 
	 * @param hand Player's hand
	 * @return Rank[] - Card ranks
	 */
 	private Rank[] getRank(Hand hand) {
		Rank[] rank = new Rank[5]; 
		for(int i = 0; i < 5; i++) {
			rank[i] = hand.getRankAt(i);
		}
		return rank;
	}
 	
 	/**
	 * Gets the suit of each card in the player's hand 
	 * @param hand Player's hand
	 * @return Suit[] - Card suits
	 */
	private Suit[] getSuit(Hand hand) {
		Suit[] suit = new Suit[5]; 
		for(int i = 0; i < 5; i++) {
			suit[i] = hand.getSuitAt(i);
		}
		return suit;
	}
	
	
	/**
	 * Checks whether cards have the same rank within certain indices
	 * @param suit Array of the player's cards' suits, ordered by suit
	 * @param start_idx Start index 
	 * @param end_idx End index
	 * @return boolean - True if suit is same, false otheriwse
	 */
	private boolean checkSameSuit(Suit[] suit, int start_idx, int end_idx) {
		if(suit[start_idx] == suit[end_idx]) return true;
		else return false;
	}
	
	
	/**
	 * Checks the number of high cards in the player's hand between indices
	 * @param rank Array of the player's cards' ranks, ordered by rank
	 * @param start_idx Start index 
	 * @param end_idx End index
	 * @return int - Number of High Cards
	 */
	private int checkHighCards(Rank[] rank, int start_idx, int end_idx) {
		int numHighCards = 0;
		for(int i = start_idx; i <= end_idx; i++) {
			if(rank[i].ordinal() >= Rank.JACK.ordinal()) numHighCards++;
		}
		return numHighCards;
	}
	
	/**
	 * Gets original hand indices, necessary due to reordering of the player's hand
	 * @param sortedHand Hand sorted by either rank or suit
	 * @param unsortedHand Original hand before sorting
	 * @param sortedIndexes Sorted indices of the hand
	 * @return int[] - Indices of cards to hold on unsorted hand
	 */
	private int[] getOriginalIndexes(Hand sortedHand, Hand unsortedHand, int[] sortedIndexes) {
		
		int[] unsortedIndexes = new int[sortedIndexes.length];
		Card tmpCard;
		
		for (int i = 0; i < sortedIndexes.length; i++) {
			tmpCard = sortedHand.getCard(sortedIndexes[i]);
			for (int j = 0; j < 5; j++) {
				if (tmpCard.equals(unsortedHand.getCard(j)))
					unsortedIndexes[i] = j; 
			}
		}
		
		Arrays.sort(unsortedIndexes);
		
		return unsortedIndexes;
	}
}
