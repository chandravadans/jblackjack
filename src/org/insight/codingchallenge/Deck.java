package org.insight.codingchallenge;


/**
 * Represents a Deck of cards, and the set of operations that can be preformed on them
 * @author Chandravadan
 *
 */
public class Deck {

	/**
	 * Deck of cards
	 */
	private Card[] deck;

	/**
	 * Number of cards already dealt
	 */
	private int usedCards; 

	/**
	 * Constructs a deck of 52 cards
	 */
	public Deck() {

		deck = new Card[52];
		int index = 0; 
		for ( int suit = 0; suit <= 3; suit++ ) {
			for ( int value = 1; value <= 13; value++ ) {
				deck[index] = new Card(value,suit);
				index++;
			}
		}
		usedCards = 0;
	}

	/**
	 * Shuffles a full deck of cards by doing random shuffles
	 */
	public void shuffle() {

		for ( int i = 51; i > 0; i-- ) {
			int rand = (int)(Math.random()*(i+1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		usedCards = 0;
	}
	

	/**
	 * Returns number of cards left on the deck that can be dealt still
	 * @return Number of cards on the deck
	 */
	public int cardsLeft() {
		return 52 - usedCards;
	}

	/**
	 * Deals the topmost card on the deck
	 * @return The topmost card on the deck
	 */
	public Card dealCard() {
		
		//If all cards have been used, reshuffle
		if (usedCards == 52)
			shuffle();
		usedCards++;
		return deck[usedCards - 1];
	}

} 