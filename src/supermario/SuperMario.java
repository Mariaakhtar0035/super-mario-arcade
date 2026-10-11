/***************************************************************************
 * Project Name: Super Mario Bros Arcade 
 * Programmers: Maria Akhtar & Ruchir Lalwani 
 * Date: May 20th, 2023
 * Program name: SuperMario.java
 * Description: Create an arcade based on New Super Mario Bros DS minigames
 **************************************************************************/
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



public class SuperMario {
public static Clip clip; 
  
    public static void main(String[] args) {
 
        //initalize scanners/variables
        Scanner scanN = new Scanner(System.in); 
        Scanner scanS = new Scanner(System.in);
        int menuChoice = 0;
        
        //play Mario's voice/display title screen
        playMusic("SoundEffects/mario.wav");
        title();
       
        //Prompt the user to enter their name 
        System.out.print("\nEnter player name: ");
        String playerName = scanS.nextLine();
        
        do
        {
            //main menu music 
            playMusic("Music/marioTheme.wav");
            
            //Display the main menu and prompt the user for a choice
            System.out.println("\n\t====== WELCOME TO MARIO PARTY ======");
            menu();
            
            do
            {
                try
                {
                    menuChoice = scanN.nextInt(); //get input
                    
                    //If the user's choice is not between 1-6
                    if(menuChoice < 1 || menuChoice > 6)
                    System.out.print("Invalid input. please enter an integer "
                            + "between 1-6 only: ");
                }
                catch (InputMismatchException e)
                {
                    System.out.print("Invalid input: Please enter an integer "
                            + "only: ");
                    scanN.nextLine(); //reset the scanner
                }
        
            }while (menuChoice < 1 || menuChoice > 6);
    
            clip.stop();
            
            //perform the chosen game or action based on the menu choice 
            switch (menuChoice) {
                case 1:
                    whackAGoombaGame(playerName);
                    break;
                case 2:
                    adventureGame();
                    break;
                case 3:
                    puzzleGame(playerName);
                    break;
                case 4:
                    blackjackGame(playerName);
                    break;
                case 5:
                    ticTacToe();
                    break;
                default:
                    break;
            }
            
        
        }while(menuChoice != 6); //loop until the user chooses 6
  
        //Thank the user for playing and exit program 
        playMusic("Soundeffects/exitGame.wav");
        System.out.println("\nThank you for playing! Exiting program...");
        pause(3000);

        
    }//end of main method
    
    /******************************************************
     * Method: menu
     * Description: Displays the main menu  
     *****************************************************/
    public static void menu()
    {
        //create menu
        System.out.println("\n-----------------------------------------------"
                + "--\n|\t\t  Main Menu\t\t\t|"
                + "\n|\t[1] Whack-A-Goomba\t\t\t|"
                + "\n|\t[2] Mario's Dilemma\t\t\t|"
                + "\n|\t[3] Phrase Frenzy\t\t\t|"
                + "\n|\t[4] Luigi's Casino - Blackjack\t\t|"
                + "\n|\t[5] Bowser's Tic-Tac Takeover (2 Player)|"
                + "\n|\t[6] Exit\t\t\t\t|"
                + "\n|\t\t\t\t\t\t|"
                + "\n-------------------------------------------------");
        
        //ask the user which number they would like to enter
        System.out.print("What would you like to do? - Enter a number: ");
        
    }//end of menu method 
    
    
    /******************************************************
     * Method: title
     * Description: Displays the title screen  
     *****************************************************/
    public static void title()
    {
        //create the title screen
        String[] s = {
                "  _______ ",
                " /  _____\\",
                "|  |____  ",
                " \\_____ \\ ",
                " _____) |",
                "|______/ "
        };
        
        String[] m = {
                "_       _",
                "|\\     /|",
                "| \\ _ / |",
                "|  \\_/  |",
                " |       |",
                " |       |"
        };
        
        String[] b = {
                " _______ ",
                "|  __   \\",
                "| |__| _/",
                "|  __ |  ",
                "| |__| | ",
                "|______/"
        };
        
        String[] mysteryBlock = {
                "         ",
                " _______ ",
                "|       |",
                "|   ?   |",
                "|_______|",
                "         "
                
        };
        
    
        for (int i = 0; i < s.length; i++) 
        {
            System.out.println(mysteryBlock[i] + " " + s[i] + " " + m[i] + 
                    " " + b[i] + " " + mysteryBlock[i]);
        }
          
    }//end of title method 
    
    
    /******************************************************
     * Method: whackAGoombaGame
     * Description: Runs the Whack-A-Goomba game  
     * @param pN - The player's name 
     *****************************************************/
    public static void whackAGoombaGame(String pN)
    {

        WhackAGoombaGame goombaGame = new WhackAGoombaGame();
        goombaGame.setPlayerName(pN);
        goombaGame.playGame();
        
        
    }//end of method
    
    
    /******************************************************
     * Method: adventureGame
     * Description: Runs the 'Mario's Dilemma' game 
     *****************************************************/
    public static void adventureGame()
    {
        adventureGame advGame = new adventureGame();
        advGame.playGame();
        
    }//end of adventureGame method
    
    
    /******************************************************
     * Method: puzzleGame 
     * Description: Runs the Phrase Frenzy game  
     * @param pN - The player's name 
     *****************************************************/
    public static void puzzleGame(String pN)
    {
        puzzleGame puzzle = new puzzleGame();
        puzzle.setPlayerName(pN);
        puzzle.playGame();
        
    }//end of puzzleGame method
    
    /******************************************************
     * Method: blackjackGame
     * Description: Runs the blackjack game 
     * @param pN - The player's name 
     *****************************************************/
    public static void blackjackGame(String pN)
    {
        Blackjack game = new Blackjack();
        game.setPlayerName(pN);
        game.run();
        
    }//end of blackjackGame method
    
    /******************************************************
     * Method: ticTacToe
     * Description: Runs the Tic-Tac-Toe game 
     *****************************************************/
    public static void ticTacToe()
    {
        TicTacToe game = new TicTacToe();
        game.playGame();
        
    }//end of ticTacToe method 
    
    /******************************************************
     * Method: playMusic
     * Description: plays music/sound effects  
     * @param fileName - the name of the music file 
     *****************************************************/
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
            
         } 
        catch (IOException | LineUnavailableException | 
                 UnsupportedAudioFileException ex) 
        {
       
         //If file not found
         System.out.println("Error with playing sound.");
     
        }
        
    }//end of playMusic method
    
    
    /******************************************************
     * Method: pause 
     * Description: Pauses the program  
     * @param milliseconds - length of pause 
     *****************************************************/
    public static void pause(int milliseconds) 
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
    
}//end of main class
