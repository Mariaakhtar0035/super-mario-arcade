/*************************************************************************
 * Project: Super Mario
 * Programmer: Ruchir lalwani 
 * Date: June 6th, 2023
 * Program name: TicTacToe.java
 * Description: Class for the tic tac toe game 
 *************************************************************************/
package supermario;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TicTacToe {
public static Clip clip;
    
    private char[] board; //Array to store the game board 
    private char currentPlayer; //Current player ('M'-Mario, 'B'-Bowser)
    private int moves; //Number of moves made in the game 

    public TicTacToe() 
    {
        currentPlayer = 'M'; //Set the initial player as Mario 
        moves = 0;
        board = new char[9];
        for (int i = 0; i < board.length; i++) {
            board[i] = ' '; //Initialize all board positions as empty 
        }

    }

    /*****************************************************************
     * Method: playGame
     * Description: runs the game  
    ******************************************************************/
    public void playGame() 
    {
        //Initialize variable/scanner
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);
        
        //Welcome the user and display rules 
        playMusic("Music/ticTacToe.wav");
        System.out.println("\nOXOX----WELCOME TO BOWSER'S TIC-TAC TAKEOVER"
                + "----XOXO");
        
        System.out.println("\n\t    ********RULES********"
                + "\n1. player 1 will play as Mario and player 2 as Bowser"
                + "\n2. Players take turns marking empty spaces"
                + "\n3. The objective is to get three of your symbols in a"
                + "\n   row, either horizontally, vertically, or diagonally"
                + "\n4. The game ends when one player acheives a winning"
                + "\n   combination or when all spaces on the grid are filled, "
                + "\n   resulting in a draw"
                + "\n\nGOOD LUCK!");

        while (!gameOver) 
        {
            System.out.println("\nCurrent board:");
            printBoard(); //Display the current state of the board 

            System.out.print("Player " + currentPlayer + ", enter your move "
                    + "(placement [1-9]): ");
            
            try
            {
                //Read the player's move and adjust for 0-based indexing 
                int placement = scanner.nextInt() - 1; 

                if (isValidMove(placement)) 
                {
                    board[placement] = currentPlayer; //Make the player's move 
                    moves++;

                    //Check if the current player has acheived a winning combo
                    if (moves >= 5 && checkWin()) 
                    {
                        clip.stop();
                        System.out.println("Final Board:");
                        printBoard();
                        //Dtermine which player won 
                        if (currentPlayer == 'M')
                        {
                            playMusic("SoundEffects/marioWin.wav");
                            System.out.println("\nPlayer " + currentPlayer + 
                                    " wins!\nMario defeats Bowser!");
                        }
                        else if (currentPlayer == 'B')
                        {
                            playMusic("SoundEffects/bowserWin.wav");
                            System.out.println("\nPlayer " + currentPlayer + 
                                    " wins!\nBowser defeats Mario!");
                        }
                        gameOver = true; //end game 
                    } 
                    
                    //Check if all spaces on the grid are filled (draw)
                    else if (moves == 9) 
                    {
                        clip.stop();
                        playMusic("SoundEffects/blackjackLose");
                        System.out.println("It's a draw!");
                        
                        gameOver = true; //end game 
                    } 
                    else 
                    {
                        //Switch to the next player's turn 
                        currentPlayer = getNextPlayer();
                    }
                } 
                else 
                {
                    System.out.println("Invalid move! Try again.");
                }
            
            }
            catch(InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter an integer "
                        + "value: ");
                scanner.next();
            }
        }//end of while loop 
        
        pause(3000);

    }//end of playGame method 

    /*****************************************************************
     * Method: isValidMove
     * Description: checks if the player's move is valid
     * Return - true or false depending on if the user's move is valid
    ******************************************************************/
    private boolean isValidMove(int placement) 
    {
        //Check if the placement is within the valid range (0-9), and if the 
        //corresponding position on the board is empty
        return (placement >= 0 && placement < 9 && board[placement] == ' ');
        
    }//end of isValidMoveMethod

    /*****************************************************************
     * Method: checkWin
     * Description: check;s if the current player has won
    ******************************************************************/
    private boolean checkWin() 
    {
        //Check for winning combinations in rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i] == currentPlayer && board[i + 1] == currentPlayer && 
                    board[i + 2] == currentPlayer) {
                return true;
            }
        }

        //Check for winning combinations in columns
        for (int i = 0; i < 3; i++) {
            if (board[i] == currentPlayer && board[i + 3] == currentPlayer && 
                    board[i + 6] == currentPlayer) {
                return true;
            }
        }

        //Check for winning combinations in diagonals
        if ((board[0] == currentPlayer && board[4] == currentPlayer && 
                board[8] == currentPlayer) ||
            (board[2] == currentPlayer && board[4] == currentPlayer && 
                board[6] == currentPlayer)) {
            return true;
        }

        return false;
        
    }//end of checkWin method

    /*****************************************************************
     * Method: printBoard
     * Description: Prints the game board 
    ******************************************************************/
    private void printBoard() 
    {
        //Print the current state of the board 
        System.out.println("-------------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + board[i] + " | " + board[i + 1] + " | " 
                    + board[i + 2] + " |");
            System.out.println("-------------");
        }
    }//end of printBoard method 
    
    /*****************************************************************
     * Method: getNextPlayer
     * Description: Handles when it's each player's turn 
    ******************************************************************/
    private char getNextPlayer() 
    {
        //switch to the next player's turn 
        if (currentPlayer == 'M') 
        {
            return 'B'; //If the current player is Mario, switch to Bowser
        } 
        else 
        {
            return 'M'; //If the current player is Bowser, switch to Mario 
        }
        
    }//end of getNextPlayer method 
    
    
    /*****************************************************************
     * Method: playMusic 
     * Description: Handles playing music/sound effects  
     * @param fileName - the name of the music file 
    ******************************************************************/
    public static void playMusic(String fileName)
    {
        try{
            //Find music file and start slip
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
            (new File(fileName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
         }
         catch (IOException | LineUnavailableException | 
                 UnsupportedAudioFileException ex) {
       
         //If file not found
         System.out.println("Error with playing sound.");
     
        }
        
    }//end of playMusic method 
    
    
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
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }//end of pause method



}//end of class


    
    
    

