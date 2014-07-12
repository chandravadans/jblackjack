package org.insight.codingchallenge;

import java.util.ArrayList;

/**
 * A Hand represents a collection of Cards
 * @author Chandravadan
 * @see Card
 *
 */
public class Hand {

	/**
	 * List of cards in the Hand
	 * @see Card
	 */
	private ArrayList<Card> hand;   

	/**
	 * Constructs an empty Hand
	 */
	public Hand() {
		hand = new ArrayList<Card>();
	}

	/**
	 * Removes all cards from hand
	 */
	public void clear() {
		hand.clear();
	}

	/**
	 * Adds a card to the hand.
	 * @param card The card to be added. If NULL, doesn't add it.
	 * @see Card
	 */
	public void addCard(Card card) {
		if (card != null)
			hand.add(card);
	}

	/**
	 * Returns number of cards in the Hand
	 * @return The total number of cards in the Hand
	 * @see Hand Card
	 */
	public int getCardCount() {
		return hand.size();
	}

	/**
	 * Gets nth card in hand
	 * @param n The index of the desired card
	 * @return Card The nth card in hand
	 */
	public Card getCard(int n) {
		if (n >= 0 && n < hand.size())
			return (Card)hand.get(n);
		else
			return null;
	}

	/**
	 * Gets the value of the Hand, scored by blackjack rules.
	 * @return The total value of all the cards in hand, scored by blackjack rules
	 */
	public int getHandValue() {
		
		//Total value of the cards in the hand
		int totalScore;      
		
		// Record the presence of an Ace, for computation of alternate score (Taking ace as 10)
		boolean acePresent;  
		
		// Number of cards in Hand
		int numberOfCards;   

		totalScore = 0;
		acePresent = false;
		numberOfCards = getCardCount();

		for(int index = 0;  index < numberOfCards;  index++ ) {
			
			Card card=getCard(index);     
			int valueOfCard=card.getValue();
			
			// Face cards are worth 10 points
			if (valueOfCard > 10) {
				valueOfCard = 10;   
			}
			
			// Recording occurrence of an Ace
			if (valueOfCard == 1) {
				acePresent = true;
			}
			totalScore = totalScore + valueOfCard;
		}

		// Check if considering Ace as 11 is favorable
		// Only first Ace is counted as 11. Rest aces are all 1 each
		// See http://www.blackjackinfo.com/bb/showthread.php?t=23539
		if ( acePresent == true  &&  totalScore + 10 <= 21 )
			totalScore = totalScore + 10;

		return totalScore;
	}  
}
