/*************************************************************************
 * Project: Super Mario Bros Arcade
 * Programmer: Ruchir Lalwani, Maria Akhtar 
 * Date: June 6th, 2023
 * Program name: puzzleGame.java
 * Description: Class for the word game 
 *************************************************************************/
package supermario;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class puzzleGame {
public static Clip clip; 

    private String playerName;
    private int score;
    
    public void setPlayerName(String pN) 
    {
        playerName = pN;
    }
    
    
    /*****************************************************************
     * Method: playGame
     * Description: Runs the game 
    ******************************************************************/
    public void playGame()
    {
        //Create ArrayLits to store all possible words and words that the user 
        //has already entered 
        ArrayList<String> allWords = new ArrayList<>();
        ArrayList<String> usedWords = new ArrayList<>();
        
        //Create a new file for all the possible words
        File file = new File("words.txt");
        
        try
        {
            Scanner scanS = new Scanner(file);
            while(scanS.hasNext())
            {
                String word = scanS.nextLine();
                allWords.add(word);
            }
            
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        
        //Create a timer to limit the game duration 
        Timer timer = new Timer();
        
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                playMusic("soundEffects/timesUp.wav");
                System.out.println("\n\nTime's up! Game over.");

                System.out.println("\nPress Enter to view score");
                timer.cancel();
                timer.purge();
  
            }
        };
        
        
        //Display the instructions 
        System.out.println("\nWELCOME TO PHRASE FRENZY!"
                + "\n\n**********RULES**********"
                + "\n'Super Mario Bros'"
                + "\nThe letters in this phrase will be used to make english "
                + "words\nTry to come up with as many combinations as you can "
                + "within 45 seconds\n(not case sensitive)"
                + "\n\nGood luck!");
        
        Scanner scanner = new Scanner(System.in); //scanner for user input
        
        
        //Prompt the user to start the game
        System.out.print("\nEnter 's' to begin the game: ");
        
        String startCommand;
        
        do
        {
            startCommand = scanner.nextLine();
            if (!startCommand.equalsIgnoreCase("s")) 
            {
                System.out.print("Invalid input. Please try again: ");
            }
        }while(!startCommand.equalsIgnoreCase("s"));
            
        
        //Schedule the timer for 45 seconds
        timer.schedule(task, 45 * 1000);
        playMusic("Music/wordGame.wav");
        
        do
        {
            //prompt the user to enter a word
            System.out.print("\nEnter a word: ");
            String userWord = scanner.nextLine();
            
            //If the user re-enters a used word
            if (usedWords.contains(userWord.toLowerCase())) 
            {
                playMusic("SoundEffects/invalid.wav");
                System.out.println("You've already entered this word. Try "
                        + "again.");
            }
            //if the user enters a valid word
            else if (allWords.contains(userWord.toLowerCase()))
            {
                playMusic("SoundEffects/valid.wav");
                score += userWord.length() + 1;
                System.out.println("Valid word! Score: " + score);
                usedWords.add(userWord.toLowerCase());
            }
            else if (userWord.equals (""))
            {
                break;
            }
            //if the user enters an invalid word 
            else 
            {
                playMusic("SoundEffects/invalid.wav");
                System.out.println("Sorry, this is not a valid Word! Try "
                        + "again");
            }
        
        }while(System.currentTimeMillis() < task.scheduledExecutionTime());
        
        //display final score
        System.out.println("Final score: " + score); // Display final score
        
        
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
        
    }//end of playGamemethod
    
    
    /*****************************************************************
     * Method: loadScores
     * Description: loads the player's name/score from the file    
    ******************************************************************/
    private ArrayList<String> loadScores() 
    {
        //Create an ArrayList to store the player names/scores 
        ArrayList<String> nameAndScores = new ArrayList<>();

        
        try {
            //Create a File object representing the file "goombaHighScore.txt"
            File file = new File("wordHighScore.txt");
        
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
            FileWriter writer = new FileWriter("wordHighScore.txt", true);
            
            //Write the player name/high score to the file, separated by a comma
            writer.write(playerName + "," + score1 + "\n");
            
            //close the filwriter
            writer.close();
            
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
        
    }//end of saveHighScore method 

    
    /*****************************************************************
     * Method: displayTopHighScores
     * Description: display the leaderboard    
    ******************************************************************/
    private void displayTopHighScores() 
    {
        //ArrayList that stores player names/scores 
        ArrayList<String> nameAndScores = loadScores();

        //Extract the scores from the high score entries
        ArrayList<Integer> scores = extractScores(nameAndScores);

        //Sort the scores in descending order
        Collections.sort(scores, Collections.reverseOrder());

        //Print the top 3 high scores
        System.out.println("\n====== Leaderboard ======");

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
       
    }//end of displayTopHighscores method 

    
    /*****************************************************************
     * Method: extractScores
     * Description: Extracts the numerical score from the ArrayList   
     * @param milliseconds - the length of the pause 
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
     * @param milliseconds - the length of the pause 
    ******************************************************************/
    private boolean checkIfMadeLeaderboard(int score) 
    {
        //ArrayList that stores the extracted scores 
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
         }
        catch (IOException | LineUnavailableException | 
                 UnsupportedAudioFileException ex) 
        {
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
        } catch (InterruptedException e) 
        {
            System.out.println("Error" + e.getMessage());
        }
        
    }//end of pause method
    
    
}//end of class
