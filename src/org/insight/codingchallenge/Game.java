package org.insight.codingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Game Class. Main class of jBlackJack
 * @author Chandravadan
 *
 */
public class Game {

	/**
	 * The name of the player
	 */
	static String playerName;
	
	/**
	 * Amount of money the player has
	 */
	static int playerPurse;
	
	/**
	 * Initial Amount the player has
	 */
	static final int INITIAL_PURSE=100;
	
	/**
	 * No. of chips the player wishes to bet on this hand
	 */
	static int betAmount;            
	
	/**
	 * Did the player win this hand?
	 */
	static boolean playerWon=false;
	
	/**
	 * To read the player's responses from the console
	 */
	static BufferedReader input;
	
	/**
	 * Play the next hand?
	 */
	static boolean playNextHand=true;


	/**
	 * The main function to be started
	 * @param args unused
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		System.out.print("Welcome to BlackJack! \n Please enter your name : ");
		input=new BufferedReader(new InputStreamReader(System.in));

		playerName=input.readLine();

		playerPurse = INITIAL_PURSE;

		while (playNextHand) {
			
			// A single hand of BlackJack
			
			System.out.println("Welcome "+playerName+", you currently have " + playerPurse+ " chips");
			do {
				System.out.println("Place your bet  (Enter -1 to leave the table): ");
				System.out.print("> ");

				String possibleBetAmount=input.readLine();

				try{
					betAmount=Integer.parseInt(possibleBetAmount);
				}
				catch(Exception e){
					System.out.println("Sorry, that wasn't a valid amount. You must bet atleat 1 and atmost "+playerPurse+" chips");
					betAmount=Integer.MIN_VALUE;
				}

				if(betAmount<-1 && betAmount!=Integer.MIN_VALUE)
					System.out.println("Sorry, negative amounts aren't allowed");
				else if (betAmount == 0)
					System.out.println("Sorry, you need to bet atleast 1 chip");
				else if(betAmount > playerPurse)
					System.out.println("Sorry, you can bet atmost "+playerPurse+" chips");
				

			} while ((betAmount < 0 || betAmount > playerPurse) && (betAmount!=-1));

			if (betAmount == -1)
				break;

			playAHand();

			if (playerWon)
				playerPurse = playerPurse + betAmount;
			else
				playerPurse = playerPurse - betAmount;

			if (playerPurse == 0) {
				System.out.println("You've exhausted your purse, "+playerName+". Thank you for playing.");
				input.close();
				System.exit(0);
			}
			
			System.out.println("Your purse has "+playerPurse+" chips.Play another hand? [Y/n] ");
			String answer=input.readLine();
			if(answer.equalsIgnoreCase("n"))
				break;
		}

		System.out.println(" Thank you for playing blackjack, "+playerName+". Your final purse is " + playerPurse + " chips");
		input.close();

	} 


	/**
	 * Plays one hand of BlackJack
	 * @throws IOException
	 */
	static void playAHand() throws IOException {

		// Create a deck
		Deck deck= new Deck();
		deck.shuffle();
		
		//Deal the dealer 2 cards
		Hand dealerHand = new Hand();
		dealerHand.addCard( deck.dealCard() );
		dealerHand.addCard( deck.dealCard() );
		
		//Deal the player 2 cards
		Hand playerHand = new Hand();
		playerHand.addCard( deck.dealCard() );
		playerHand.addCard( deck.dealCard() );

		System.out.println("Dealing cards...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Dealing interrupted");
			playerWon=false;
			return;
		}
		
		//Check if either the player or the dealer have a blackjack		
		if (dealerHand.getHandValue() == 21) {
			
			System.out.print("Dealer : ");
			displayHand(dealerHand);
			
			System.out.print(playerName+" : ");
			displayHand(playerHand);
			
			System.out.println("Dealer has the Blackjack.  Dealer wins.");
			playerWon=false;
			return;
		}

		if (playerHand.getHandValue() == 21) {
			
			System.out.print("Dealer : ");
			displayHand(dealerHand);
			
			System.out.print(playerName+" : ");
			displayHand(playerHand);
			
			System.out.println(playerName+" has the Blackjack!"+playerName+" wins $"+betAmount+"!!");
			playerWon=true;
			return;
		}

		// Allow the player to "Hit" or "Stand".
		// Crossing over 21 results in a bust 
		while (true) {

			System.out.println("\n");
			
			System.out.print("Dealer : ");
			System.out.println(dealerHand.getCard(0)+", X");
			
			System.out.print(playerName+" : ");
			displayHand(playerHand);
			
			System.out.print("Hit (H) or Stand (S)? ");
			String playerChoice;  
			
			playerChoice=input.readLine();
			
					
			while(!playerChoice.equalsIgnoreCase("H") && !playerChoice.equalsIgnoreCase("S"))
			{
				playerChoice = input.readLine();
				if (!playerChoice.equalsIgnoreCase("H") && !playerChoice.equalsIgnoreCase("S"))
					System.out.print("Please type 'h' if you want to HIT, and 's' if you want to STAND ");
			} 

			// If player decides to stand, the hand is done and
			// dealer has to pick his cards
			if ( playerChoice.equalsIgnoreCase("s")) {
				break;
			}
			else {
				
				// If the player HITs, he is dealt with a new card
				Card newCard = deck.dealCard();
				playerHand.addCard(newCard);
							
				System.out.println("<Dealt " + newCard+">");
				
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				
				}
				
				if (playerHand.getHandValue() > 21) {
					
					System.out.println("Your score: "+playerHand.getHandValue()+"\nYou have been busted! ");
				
					try {
						Thread.sleep(250);
					} catch (InterruptedException e) {
					
					}
					
					System.out.println("Final hands: ");
					System.out.print("Dealer: ");
					displayHand(dealerHand);
					
					System.out.print(playerName+":");
					displayHand(playerHand);
					
					playerWon=false;
					return;
				}
			}

		} 

		// If player stands, dealer HITs till he crosses 16. If dealer crosses 21, its a bust
		
		System.out.println("You opted to STAND. Your final score : "+playerHand.getHandValue());
		
		try {
			Thread.sleep(250);
		} catch (InterruptedException e1) {
		
		}
		System.out.println("Dealer hits till he's less than 17");
		
		System.out.print("Dealer: ");
		displayHand(dealerHand);
		
		while (dealerHand.getHandValue() <= 16) {
			Card newCard = deck.dealCard();
			
			System.out.println("<Dealt " + newCard+">");
			dealerHand.addCard(newCard);
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e1) {
			}
			
			if (dealerHand.getHandValue() > 21) {
				System.out.println("Dealer busted with score : "+dealerHand.getHandValue()+". You win!");
			
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println("Interrupted!");
				}
				
				playerWon=true;
				return;
			}
		}
		

		//Nobody got a blackjack. Compare scores and decide winner.
		// A tie means dealer has won

		System.out.println("Final Hands: ");
		System.out.print("Dealer: ");
		displayHand(dealerHand);
		System.out.print("Dealer score: ");
		System.out.println(dealerHand.getHandValue());
		
		System.out.println();
		
		System.out.print(playerName+" : ");
		displayHand(playerHand);
		System.out.println(playerName+"'s score: "+playerHand.getHandValue());
		
		System.out.println();
		
		if (dealerHand.getHandValue() == playerHand.getHandValue()) {
			System.out.println("Tie. Advantage to dealer.  You lose.");
			playerWon=false;
			return;
		}
		else if (dealerHand.getHandValue() > playerHand.getHandValue()) {
			System.out.println("Dealer wins! ");
			playerWon=false;
			return;
		}
		else {
			System.out.println("You win "+betAmount+" chips!");
			playerWon=true;
			return;
		}

	}  
	
	/**
	 * Displays a hand of cards
	 * @param hand The hand to display
	 */
	static void displayHand(Hand hand){
		
		int numberOfCards=hand.getCardCount();
		for(int count=0;count < numberOfCards; count++)
			System.out.print(hand.getCard(count).toString()+" , ");
		System.out.println();
	}

}
