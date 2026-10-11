/*************************************************************************
 * Project: Super Mario Bros Arcade
 * Programmer: Maria Akhtar 
 * Date: June 6th, 2023
 * Program name: WhackAGoombaGame.java
 * Description: Class for the whack-a-goomba game 
 *************************************************************************/
package supermario;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class WhackAGoombaGame {
public static Clip clip; 
   
    private String playerName;
    private int score;
    private int chances;
    private boolean gameRunning;
    
    //constructor 
    WhackAGoombaGame()
    {
        score = 0;
        chances = 5;
        gameRunning = true;
    
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getChances()
    {
        return chances;
    }
    
    public void setPlayerName(String pN) {
        playerName = pN;
    }
    
   
         
    public void playGame()
    {
        //start playing background music 
        
        //Initialize scanner/variables/random number generator
        playMusic("Music/whackAGoomba.wav");
        Scanner scanN = new Scanner (System.in);
        Random random =  new Random();
        int move = 0;
        
        //generate a random position for the goomba
        int goombaPosition = random.nextInt(5) + 1;
      
        
        //Display game instructions 
        System.out.println("\n--------WELCOME TO WHACK-A-GOOMBA!--------"
                + "\nInstructions: Whack as many Goombas as you can. You "
                + "have 5 chances to miss.");
        
        do
        { 
            //Display game board 
            displayGameGraphics();
            
            //Display current score and remaining chances 
            System.out.println(toString());
            
            //Prompt user for their move 
            System.out.print("Enter your move (1-5): ");
            do
            {
                try{
                
                    move = scanN.nextInt(); //get user input 
                
                    if (move < 1 || move > 5)
                    {
                        System.out.print("Invalid move. Please enter a "
                                + "number from 1-5: ");
                    }
                
                }catch(InputMismatchException e)
                {
                    System.out.print("Invalid input. please enter an integer "
                            + "value: ");
                    scanN.nextLine();
                }
            
            }while(move < 1 || move > 5);
            
                //Check if the user's move matches the Goomba's position 
                if (goombaPosition == move)
                {
                    //If the user hit the gommba, increment score 
                    score++;
                    System.out.println("You whacked a Goomba! Score +1");
                }
                else if (goombaPosition != move)
                {
                    //If the user missed, decrement their chances 
                    chances--;
                    System.out.println("You missed!");
                } 
            
                if (chances <= 0)
                {
                    gameRunning = false;
                }
       
        } while (gameRunning); //run until the player runs out of chancea 
        
        
        //Game over sound effect
        clip.stop();
        playMusic("SoundEffects/LoseGame.wav");
        System.out.println("\nGAME OVER");
        pause(4000);
        
        //Print the player's final score 
        System.out.println("Final score: " + score);
        
        //Determine if the player made it to the leaderboard 
        boolean madeLeaderboard = checkIfMadeLeaderboard(score);
        

        //If the player made it to the leaderboard 
        if (madeLeaderboard)
        {
            playMusic("SoundEffects/NewHighScore.wav");
            saveHighScore(playerName, score);
            System.out.println("\nCongratulations! You're on the leaderboard");
            
            pause(4000);
        }
        
        //Display the leaderboard 
        displayTopHighScores();
   
          
        
    }//end of playGame() method
    
    
    @Override
    public String toString()
    {
        return "Score " + score + "\nChances remaining: " + chances + "\n";
        
    }//end of toString method
    
    
    /*****************************************************************
     * Method: l
     * Description: loads the player's name/score from the file    
    ******************************************************************/
    private void displayGameGraphics()
    {
        System.out.println(" _______ _______ _______ _______ _______"
                + "\n|       |       |       |       |       |"
                + "\n|   1   |   2   |   3   |   4   |   5   |"
                + "\n|_______|_______|_______|_______|_______|");

    }//end of displayGameGraphics method 
    
    /*****************************************************************
     * Method: loadScores
     * Description: loads the player's name/score from the file    
    ******************************************************************/
    private ArrayList<String> loadScores() 
    {
        ArrayList<String> nameAndScores = new ArrayList<>();

        
        try {
            //Create a File object representing the file "goombaHighScore.txt"
            File file = new File("goombaHighScore.txt");
        
            //Check if the file doesn't exist
            if (!file.exists()) {
                return nameAndScores;
            }
        
            //Create a scanner to read from the file
            Scanner scanner = new Scanner(file);
        
            //Read the file line by line until there are no more lines
            while (scanner.hasNextLine()) 
            {
                //Read the current line from the file
                String scoreInfo = scanner.nextLine();
                
                //Split the line by comma (format should be "playerName,score")
                String[] scoreParts = scoreInfo.split(",");
            
                //Check if the line has the expected format with two parts
                if (scoreParts.length == 2) 
                {
                    // Extract the player name and score from the parts
                    String userName = scoreParts[0];
                    int userScore = Integer.parseInt(scoreParts[1]);
                    
                    //Create a formatted string representing the player name 
                    //and score, and add it to the list of high scores
                    nameAndScores.add(userName + " - " + userScore);
                }
            }
        
            //close the scanner
            scanner.close();
            
        } catch (IOException e) 
        {
            System.out.println("Error" + e.getMessage());
        }

        //return the list of scores
        return nameAndScores;
        
    }//end of loadScores method 

    
    /*****************************************************************
     * Method: saveHighScore
     * Description: Saves the player's name/score to the file
     * @param playerName - The player's name 
     * @param score1 - The player's score 
    ******************************************************************/
    private void saveHighScore(String playerName, int score1) 
    {
        try {
            //Create a FileWriter to write to the file "goombaHighScore.txt"
            FileWriter writer = new FileWriter("goombaHighScore.txt", true);
            
            //Write the player name/high score to the file, separated by a comma
            writer.write(playerName + "," + score1 + "\n");
            
            //close the filwriter
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
        
    }//end of saveHighscore method 
    
    /*****************************************************************
     * Method: displayTopHighScores
     * Description: display the leaderboard    
    ******************************************************************/
    private void displayTopHighScores() 
    {
        
        ArrayList<String> nameAndScores = loadScores();

        //Extract the scores from the high score entries
        ArrayList<Integer> scores = extractScores(nameAndScores);

        //Sort the scores in descending order
        Collections.sort(scores, Collections.reverseOrder());

        //Print the top 3 high scores
        System.out.println("\n======LEADERBOARD======");

        //Determine the number of scores to display (minimum of 3 or the actual 
        //number of scores)
        int count = Math.min(3, scores.size());
        int printedCount = 0;
        for (int i = 0; i < count && printedCount < 3; i++) 
        {
            int currentScore = scores.get(i);
            for (int j = 0; j < nameAndScores.size(); j++) 
            {
                String highScore = nameAndScores.get(j);
                String[] parts = highScore.split(" - ");

                // Check if the entry has the expected format and matches the 
                //current score
                if (parts.length == 2 && Integer.parseInt(parts[1]) == 
                        currentScore) 
                {
                    // Print the high score entry
                    System.out.println((printedCount + 1) + ". " + parts[0] + 
                            " - " + currentScore);

                    // Increment the printed count
                    printedCount++;

                    // Remove the printed entry from the highScores list
                    nameAndScores.remove(j);
                    break;
                }
            }
        }
       
    }//end of displayTopHighScores method 

    /*****************************************************************
     * Method: extractScores
     * Description: Extracts the numerical score from the ArrayList   
     * @param nameAndScores1 - the list of the player's name and scores 
     * @return scores - the extracted score 
    ******************************************************************/
    private ArrayList<Integer> extractScores(ArrayList<String> nameAndScores1) 
    {
        //Create a new ArrayList to store the extracted scores
        ArrayList<Integer> scores = new ArrayList<>();
        
        for (String highScore : nameAndScores1) {
            String[] parts = highScore.split(" - ");
            
            //Check if the entry has the expected format with two parts
            if (parts.length == 2) {
                //Extract and parse the score, then add it to the scores list
                scores.add(Integer.parseInt(parts[1]));
            }
        }
        return scores;
        
    }//end of extractScores method 

    /*****************************************************************
     * Method: checkIfMadeLeaderboard
     * Description: Checks if the player made the leaderboard
     * @param score - the player's score
     * @return true or false depending on if the user is on leaderboard
    ******************************************************************/
    private boolean checkIfMadeLeaderboard(int score) 
    {
        ArrayList<Integer> scores = extractScores(loadScores());

        //Sort the scores in descending order
        Collections.sort(scores, Collections.reverseOrder());

        //Check if the user's score is higher than any of the scores in the 
        //leaderboard
        for (int i = 0; i < Math.min(3, 
                scores.size()); i++) {
            if (score > scores.get(i)) {
                return true;
            }
        }

        //Otherwise, return false
        return false;
        
    }//end of checkIfMadeLeaderboard method 

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
    public void playMusic(String fileName)
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
                 UnsupportedAudioFileException ex) {
       
         //If file not found
         System.out.println("Error with playing sound.");
        }
        
    }//end of playMusic method

   
}//end of class
