/*************************************************************************
 * Project: Super MarioBros Arcade
 * Programmer: Maria Akhtar, Ruchir Lalwani
 * Date: June 6th, 2023
 * Program name: Blackjack.java
 * Description: Class for the Blackjack game 
 *************************************************************************/
package supermario;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Blackjack {
    public static Clip clip;
    private static Clip backgroundMusicClip;
    
    private String playerName;
    private ArrayList<String> drawDeck; //List to store the deck of cards
    private ArrayList<String> playerHand; //List to store the player's hand
    private ArrayList<String> dealerHand; //List to store Luigi's hand 
    private int playerWins; // Counter for player wins
    private int dealerWins; // Counter for dealer wins
    

    //contructor 
    public void setPlayerName(String pN) 
    {
        playerName = pN;
    }
    
    
    public Blackjack() 
    {
        //initialize the card deck, player's hand, and Luigi's hand lists
        drawDeck = new ArrayList<>(Arrays.asList("2♠", "3♠", "4♠", "5♠", 
                "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "Q♠", "K♠",
                "A♠", "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", 
                "10♥", "J♥", "K♥", "Q♥", "A♥", "2♣", "3♣", "4♣",
                "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "Q♣", "K♣",
                "A♣", "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦",
                "9♦", "10♦", "J♦", "Q♦", "K♦", "A♦"));
        
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        
        //Keep track of each player's wins 
        playerWins = 0;
        dealerWins = 0;
    }
    
 
    /*****************************************************************
     * Method: dealInitialCards
     * Description: deals 2 cards to each player's hand 
    ******************************************************************/
    private void dealInitialCards() 
    {
        //Deal the 2 inital cards to the player and Luigi (dealer)
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        playerHand.add(drawCard());
        dealerHand.add(drawCard());
        
    }//end of dealInitial cards method 
    
    
    /*****************************************************************
     * Method: drawCard
     * Description: draws a card from the top of the deck   
     * @return - the first card from the deck 
    ******************************************************************/
    private String drawCard() 
    {
        //check if the drawDeck list is empty
        if (drawDeck.isEmpty()) {
            return null;
        }
        
        //remove the first card from the card deck
        return drawDeck.remove(0);
        
    }//end of drawCard method 
    
    
    /*****************************************************************
     * Method: getHandValue 
     * Description: get the numeric value of each player's hand  
     * @param hand - the player's hand 
     * @return - the numeric value of the player's hand 
    ******************************************************************/
    private int getHandValue(List<String> hand) 
    {
        int value = 0; //initalize the total hand value to 0
        int aceCount = 0; //keep track of the number of Aces in the hand
        
        //Iterate through each card in the hand
        for (String card : hand) 
        {
            char rank = card.charAt(0); //Extract the rank of the card
            
            switch (rank) {
                case 'A':
                    //If rank is an Ace, add 11 to the hand value/increment
                    //the Ace count
                    value += 11;
                    aceCount++;
                    break;
                case '1':
                case 'J':
                case 'Q':
                case 'K':
                    //For other ranks (10, J, Q, K), add 10 to the hand value
                    value += 10;
                    break;
                default:
                    // For all other ranks (2-9), add the numeric value to hand
                    value += Character.getNumericValue(rank);
                    break;
            }
            
        }
         //Adjust the value if the hand value exceeds 21 & there are Aces present
        while (value > 21 && aceCount > 0) 
        {
            value -= 10; //subtract 10 from the value
            aceCount--; //Decrement the ace count
        }
        
        return value; //return the final hand value
        
    }//end of getHandValue method 
    
    
    /*****************************************************************
     * Method: playGame
     * Description: runs 1 round of the game    
    ******************************************************************/
    public void playGame() 
    {
        Collections.shuffle(drawDeck); //shuffle the deck of cards
        dealInitialCards(); //deal the initial cards to the player and dealer
        
        System.out.println(playerName + "'s hand: " + playerHand.get(0) + ", " 
                + playerHand.get(1));
        System.out.println("Luigi's hand: " + dealerHand.get(0) + ", [Hidden]");

        //check if the player has a winning hand right from the start
        if (getHandValue(playerHand) == 21) 
        {
            playMusic("SoundEffects/blackjackWinRound.wav");
            System.out.println("Blackjack! You win!");
            playerWins++; //increment player wins counter
            return;
        }

        while (true) 
        {
            System.out.print("\nDo you want to hit or stand? (h/s): ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            //if the player chooses to hit
            if (choice.equalsIgnoreCase("h")) 
            {
                //Draw a card and add it to the player's hand
                String drawnCard = drawCard();
                playerHand.add(drawnCard);
                System.out.println("\n" + playerName + "'s hand: " + playerHand);

                //if player's hand value exceeds 21, they lose
                if (getHandValue(playerHand) > 21) 
                {
                    playMusic("SoundEffects/blackjackLoseRound.wav");
                    System.out.println("Bust! You lose!");
                    dealerWins++; //increment dealer wins counter
                    break;
                }
            } 
            
            //if player chooses to stand
            else if (choice.equalsIgnoreCase("s")) 
            {
                System.out.println(playerName + " stands.");

                //display Luigi's hand 
                System.out.println("\nLuigi's hand: " + dealerHand);

                //Luigi draws cards until his hand reaches 17 or higher
                while (getHandValue(dealerHand) < 17) 
                {
                    String drawnCard = drawCard();
                    dealerHand.add(drawnCard);
                    System.out.println("Luigi draws: " + drawnCard);
                }

                //Get the hand value of both player's decks and display them 
                int playerValue = getHandValue(playerHand);
                int dealerValue = getHandValue(dealerHand);

                System.out.println("\n" + playerName + "'s hand value: " + 
                        playerValue);
                System.out.println("Luigi's hand value: " + dealerValue);

                //determine winner of round 
                if (dealerValue > 21) 
                {
                    //Luigi's hand value exceeds 21, player wins
                    playMusic("SoundEffects/blackjackWinRound.wav");
                    System.out.println("\nLuigi busts! You win!");
                    playerWins++; //increment player wins counter
                    
                } 
                else if (dealerValue > playerValue) 
                {
                    //Luigi's hand value is higher than the player's, Luigi wins
                    playMusic("SoundEffects/blackjackLoseRound.wav");
                    System.out.println("\nLuigi wins!");
                    dealerWins++; //increment dealer wins counter
                    
                } else if (dealerValue < playerValue) 
                {
                    //Player's hand value is higher than Luigi's, player wins
                
                    playMusic("SoundEffects/blackjackWinRound.wav");
                    System.out.println("\nYou win!");
                    playerWins++; //increment player wins counter
                    
                } else 
                {
                    //Both player and Luigi have the same hand value, it's a tie
                    playMusic("SoundEffects/blackjackLoseRound.wav");
                    System.out.println("\nIt's a tie!");
                }

                break;   
            } 
            else 
            {
                System.out.println("Invalid input. Please try again.");
            }   
        }
        
        // Print the current score
        System.out.println("\n--- Current Score ---");
        System.out.println(playerName + ": " + playerWins);
        System.out.println("Luigi: " + dealerWins);
        
        playerHand.clear();
        dealerHand.clear();
        
        pause(3000);
   
    }//end of playGame method
    
    
    /*****************************************************************
     * Method: playBestOfThree
     * Description: loops the game 3 times/determines final winner   
    ******************************************************************/
    public void playBestOfThree() {
        
        playMusic("Music/blackjack.wav");
        for (int i = 0; i < 3; i++) 
        {
            
            System.out.println("\n--- Round " + (i + 1) + " ---");
            playGame();
        }
        pause(3000);
        
        stopBackgroundMusic();
        
        
        //if the player wins
        if (playerWins > dealerWins) 
        {
            playMusic("SoundEffects/blackjackWin.wav");
            System.out.println("\nCongratulations! You won the best out of "
                    + "three games."); 
        } 
        //if Luigi (dealer) wins 
        else if (dealerWins > playerWins) 
        {
            playMusic("SoundEffects/blackjackLose.wav");
            System.out.println("\nBetter luck next time! Luigi won the best "
                    + "out of three games.");
        } 
        //if it's a draw 
        else 
        {
            playMusic("SoundEffects/blackjackLose.wav");
            System.out.println("\nIt's a tie! Both player and Luigi won an "
                    + "equal number of games.");
        }
        
        System.out.println("\n♦♣♥♠♦♣♥♠♦♣♥♠♦♣♥♠♦♣♥♠♦♣♥♠♦♣♥♠♦♣♥♠"
                + "♦♣♥♠♦♣♥♠");
        
    }//end of playBestOfThree method 
    
    
    /*****************************************************************
     * Method: blackjackMenu
     * Description: Displays the blackjack menu 
    ******************************************************************/
    private void blackjackMenu() {
        System.out.println("\n---- BLACKJACK MENU ----");
        System.out.println("1. Play");
        System.out.println("2. Rules");
        System.out.println("3. Quit");
        System.out.print("\nEnter your choice: ");
        
    }//end of blackjackMenu method 
    
    
    /*****************************************************************
     * Method: displayRules
     * Description: Displays the rules of the game    
    ******************************************************************/
    private void displayRules() {
        System.out.println("\n\t\t------ RULES OF BLACKJACK ------"
                + "\n1. The goal of the game is to have a hand value as close "
                + "to 21 \n   as possible without exceeding it."
                + "\n\n2. Numbered cards (2-10) have their face value, face "
                + "cards\n   (J, Q, K) are worth 10, and Ace can be worth 1 "
                + "or 11\n   depending on what helps the player's hand."
                + "\n\n3. The player is initially dealt two cards and can "
                + "choose to\n   'hit' (draw another card) or 'stand' "
                + "(end their turn)."
                + "\n\n4. The player wins if their hand value is closer to "
                + "21\n   than the dealer's hand value, without exceeding 21."
                + "\n\n5. If the player's hand value exceeds 21, they bust "
                + "and lose the game."
                + "\n\n6. If the player chooses to stand, the dealer reveals "
                + "their\n   hidden card and draws additional cards until "
                + "their hand value is 17 or higher."
                + "\n\n7. If the dealer's hand value exceeds 21, they bust "
                + "and the player wins."
                + "\n\n8. If the dealer's hand value is closer to 21 than the"
                + " player's\n   hand value without exceeding 21, the "
                + "dealer wins."
                + "\n\n9. If both the player and dealer have the same hand "
                + "value,\n   it's a tie (push) and no one wins.");
    
    }//end of display rules method 
    
    
    /*****************************************************************
     * Method: run
     * Description: runs the full Blackjack code - menu + options    
    ******************************************************************/
    public void run() 
    {
        //Initalize variables/scanner 
        Scanner scanN = new Scanner(System.in);
        boolean quit = false;
        int choice = 0;
        
        //Welcome user 
        System.out.println("\n♦♣♥♠*******WELCOME TO LUIGI'S CASINO*******"
                + "♦♣♥♠");
        
        while (!quit) 
        {
            blackjackMenu(); //Display menu 
            do
            {
                try
                {   
                    choice = scanN.nextInt();
                    
                    //If the user's choice is invalid 
                    if(choice < 1 || choice > 3)
                    System.out.print("Invalid input. please enter a 1, 2, or 3 "
                            + "only: ");
                }
                catch (InputMismatchException e)
                {
                    System.out.print("Invalid input. Please enter an integer "
                            + "only: ");
                    scanN.nextLine(); //reset the scanner
                }
        
            }while (choice < 1 || choice > 5);
 
            //perform the chosen game or action based on the menu choice 
            switch (choice) {
                case 1:
                    //Reset the # of wins for each player 
                    playerWins = 0;
                    dealerWins = 0;
                    playBestOfThree(); //run the game 
                    break;
                case 2:
                    displayRules();
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    break;
            }
        }
    }//end of run method 
    
    
    /*****************************************************************
     * Method: pause
     * Description: Pauses the program   
     * @param milliseconds - the length of the pause 
    ******************************************************************/
    public void pause(int milliseconds) 
    {
        try 
        {
            //Pause the program the specified number of milliseconds
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) 
        {
            System.out.println("Error" + e.getMessage());
        }
    }//end of pause method 
    
    
    /*****************************************************************
     * Method: playMusic 
     * Description: Handles playing music/sound effects  
     * @param fileName - the name of the music file 
    ******************************************************************/
    public static void playMusic(String fileName)
    {
        try
        {
            //Find music file and start slip
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
            (new File(fileName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
       
            //Assign the Clip to the appropriate variable
            if (fileName.contains("blackjack.wav")) 
            {
                backgroundMusicClip = clip;
            } 
        } 
        catch (IOException | LineUnavailableException | 
                 UnsupportedAudioFileException ex) 
        {
       
         //If file not found
         System.out.println("Error with playing sound.");
     
        }
    }//end of playMusic method
    
    
    /*****************************************************************
     * Method: stopBackgroundMusic
     * Description: Stops the background casino music 
    ******************************************************************/
    public void stopBackgroundMusic() 
    {
        
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
        }
   
    }//end of stopBackgroundMusic method 
    
   
    
}//end of class
   
    
    
    
