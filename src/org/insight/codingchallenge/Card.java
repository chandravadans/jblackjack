package org.insight.codingchallenge;

/**
 * A Card object. Each card has a suit and a value
 * @author Chandravadan
 */
public class Card {

	/**
	 * Value of a card. 1 to 13
	 */
	private final int value;

	/*
	 * Codes for face cards
	 */
	public final static int ACE = 1, JACK = 11,	QUEEN = 12, KING = 13;

	/**
	 * Suit of the card
	 */
	private final int suit;

	/**
	 * Codes for suits
	 */
	public final static int CLUBS = 0, DIAMONDS = 1, HEARTS = 2, SPADES = 3;


	/**
	 * Constructs a Card given a value and a suit
	 * @param value Must be between 1 and 13
	 * @param suit Must be between 0 and 3
	 */
	public Card(int value, int suit) {
		this.value = value;
		this.suit = suit;
	}

	/**
	 * Gets the suit of this card
	 * @return A number between 0 and 3, the code for the suit 
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * Gets the value of the card, between 1 to 13
	 * @return A number between 1 and 13, the value of the card
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Return the suit as a String. Unicode support required
	 * @return A String that gives the value of the suit
	 */
	public String getSuitAsString() {

		switch ( suit ) {
		case SPADES:   return  "\u2660";
		case HEARTS:   return "\u2665";
		case DIAMONDS: return "\u2666";
		case CLUBS:    return "\u2663";
		default:       return "x";
		}
	}

	/**
	 * Get the value of the card as a String.
	 * @return A String that denotes the value of the card
	 */
	public String getValueAsString() {
		switch ( value ) {
		case 1:   return "A";
		case 2:   return "2";
		case 3:   return "3";
		case 4:   return "4";
		case 5:   return "5";
		case 6:   return "6";
		case 7:   return "7";
		case 8:   return "8";
		case 9:   return "9";
		case 10:  return "10";
		case 11:  return "J";
		case 12:  return "Q";
		case 13:  return "K";
		default:  return "x";
		}
	}

	/**
	 * Returns the string representation of the card object
	 */
	public String toString() {
		return getValueAsString() + getSuitAsString();
	}
}
