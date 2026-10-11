/*************************************************************************
 * Project: Super Mario Bros Arcade
 * Programmer: Maria Akhtar, Ruchir Lalwani
 * Date: June 6th, 2023
 * Program name: adventureGame.java
 * Description: Class for the adventure game 
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


public class adventureGame {
public static Clip clip; 

    private Scanner scanner; //Scanner object to read user input 
    
    adventureGame()
    {
        scanner = new Scanner(System.in); //initialize the scanner object 
    }
    
    /*****************************************************************
     * Method: playGame
     * Description: Runs the game      
    ******************************************************************/
    public void playGame()
    {
        //Display the intro/instructions
        intro();

        int chosenPath = 0;
        
        //Perform action depending on the users chosen path 
        do
        {
            try
            {
                chosenPath = scanner.nextInt();
        
                if (chosenPath == 1)
                {
                    clip.stop();
                    handleMushroomForest();
                }
                else if (chosenPath == 2)
                {
                    clip.stop();
                    handleWarpPipeMaze();
                }
                else if (chosenPath == 3)
                {
                    clip.stop();
                    handleHauntedCastle();
                }
                else if (chosenPath > 1 || chosenPath < 3)
                {
                    System.out.print("Invalid choice! Please enter a valid "
                            + "number: ");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.print("Invalid input! Please enter an integer "
                        + "value: ");
                scanner.nextLine(); //reset the scanner
            }
    
        }while (chosenPath < 1 || chosenPath > 3);
       
       
    }//end of playGame method
       
    /*****************************************************************
     * Method: intro
     * Description: Displays the opening title and instructions   
    ******************************************************************/
    private void intro()
    {
        //Introduction scenario 
        playMusic("Music/adventureGameIntro.wav");
        System.out.println("\nOn a Serene day in the Mushroom Kingdon, Mario "
                + "and Princess Peach are reveling\nin the joy of eachother's "
                + "company");
        pause(9100); 
            
        
        System.out.println("\nOh no! Bowser, the notorious Koopa king has "
                + "arrived and kidnapped\nPrincess Peach. It is your job to "
                + "help Mario find her.");
        pause(2000);
       
        
        //Display instructions and first menu 
        System.out.print("\n********WELCOME TO 'MARIO'S DILEMMA********"
                + "\nHelp Mario find and rescue Princess Peach by making "
                + "choices\nat various crossroads and overcoming challenges "
                + "in different\nlocations. Choose wisely!"
                + "\n\nYou find Mario standing at a crossroad. Three paths lie "
                + "\nbefore him. Which path will you chose?\n"
                + "\n\t1. The Mushroom Forest"
                + "\n\t2. The Warp Pipe Maze"
                + "\n\t3. The Haunted Castle\n"
                + "\nEnter the number of your chosen path: ");
        
    }//end of intro method 
    
    
    /*****************************************************************
     * Method: handleMushroomForest
     * Description: Handles the mushroom forest path     
    ******************************************************************/
    private void handleMushroomForest()
    {
        playMusic("Music/mushroomForest.wav");
        
        System.out.println("******MUSHROOM FOREST******");
        
        //Present player with choices 
        System.out.print("\nMario ventures into the Mushroom Forest. "
                + "The lush greenery surrounds him, "
                + "\noffering a sense of tranquility. However, he encounters "
                + "a ferocious \nPiranha Plant blocking his way. What should "
                + "Mario do?\n"
                + "\n\t1. Jump over the Piranha Plant."
                + "\n\t2. Use a koopa shell to defeat it."
                + "\n\t3. Retreat and find an alternative path."
                + "\n\nEnter the number corresponding to Mario's action: ");
        
        int chosenAction = 0;
        
        //Perform action depending on the users chosen action
        do
        {
            try
            {
                chosenAction = scanner.nextInt(); //get user input 
        
                if (chosenAction == 1)
                {
                    clip.stop();
                    playMusic("SoundEffects/levelClear.wav");
                    System.out.println("\nMario skillfully leaps over the "
                            + "Piranha Plant, continuing deeper\ninto the "
                            + "Mushroom Forest. As he explores futher, he "
                            + "encounters a\ngroup of friendly Toads who offer "
                            + "him assistance. They guide him to\na hidden "
                            + "treasure chest filled with coins and power-ups. "
                            + "Mario's\njourney in the Mushroom Forest ends "
                            + "on a high note. Well done!\n"
                            + "\nEntering Warp Pipe Maze...");
                    pause(5000);
                    
                    handleWarpPipeMaze();
                
                }
                else if (chosenAction == 2)
                {
                    System.out.print("\nMario hurls a koopa shell at the "
                            + "Piranha plant, defeating\nit and clearing his "
                            + "path. As he continues through the Mushroom"
                            + "\nForest, he stubles upon a hidden Warp Pipe. "
                            + "Curiosity piques\nhis interest. Should Mario "
                            + "enter the Warp Pipe?\n"
                            + "\n\t1. Yes"
                            + "\n\t2. No"
                            + "\n\nEnter the number of your choice: ");
                    
                    int chosenPipe = 0;
                    do{
                        try
                        {
                            chosenPipe = scanner.nextInt();
            
                            
                            if (chosenPipe == 1)
                            {   clip.stop();
                                playMusic("SoundEffects/levelClear.wav");
                                System.out.println("\nMario takes a leap of "
                                        + "faith and enters the Warp Pipe, "
                                        + "transporting\nhim to a secret "
                                        + "underground cavern. The cavern is "
                                        + "filled with\ntreasures, and Mario "
                                        + "finds valuable Power-ups to aid him "
                                        + "in his journey.\nHe emerges from "
                                        + "the cavern, enriched and ready to "
                                        + "face new challenges. Well done!\n");
                                
                                System.out.println("Entering Warp Pipe Maze...");
                                pause(5000);
                                handleWarpPipeMaze();
                            }
                            else if (chosenPipe == 2)
                            {
                                clip.stop();
                                playMusic("SoundEffects/LoseGame.wav");
       
                                System.out.println("\nMario retreats from the "
                                        + "Piranha Plant, opting to find an\n"
                                        + "alternative path through the "
                                        + "Mushroom Forest. Unfortunately,"
                                        + "\nhe stumbles upon a treacherous "
                                        + "swamp and is unable to find\na way "
                                        + "across. Mario's journey in the "
                                        + "Mushroom Forest comes\nto a "
                                        + "tragic end. Game Over!\n");
                               
                                pause(3000);
                            }
                            else
                            {
                                System.out.println("Invalid choice! Please "
                                        + "enter a valid number. ");
                            }
        
                        }catch (InputMismatchException e)
                        {
                            System.out.print("Invalid input! Please enter an "
                                    + "integer value: ");
                            scanner.nextLine(); //reset the scanner
                        }
                    }while (chosenPipe < 1 || chosenPipe > 2);
        
                }//end of else if 
                
                else if (chosenAction == 3)
                {
                    System.out.print("\nMario decides to retreat from the "
                            + "Piranha Plant and find an\nalternative path in "
                            + "the Mushroom Forest. As he explores a"
                            + "\ndifferent direction, he stumbles upon a "
                            + "hidden underground tunnel."
                            + "\nCuriosity piques his interest. Should Mario "
                            + "enter the tunnel?\n"
                            + "\n\t1. Yes"
                            + "\n\t2. No"
                            + "\n\nEnter the number of your choice: ");
                    
                    int chosenPath = 0;
                    
                    do
                    {
                       try
                       {
                           chosenPath = scanner.nextInt();
                           if (chosenPath == 1)
                           {
                               clip.stop();
                               playMusic("SoundEffects/LoseGame.wav");
                               System.out.println("\nMario enters the "
                                       + "underground tunnel, discovering a "
                                       + "shortcut\nthat bypasses the Piranha "
                                       + "Plant. As he navigates through "
                                       + "the\ndimly lit passages, he suddenly "
                                       + "encounters a swarm of venomous"
                                       + "\nmushrooms. Mario's attempts to "
                                       + "defend himself fail, and he "
                                       + "succumbs\nto the poisonous spores. "
                                       + "Mario's journey in the Mushroom "
                                       + "Forest\ncomes to a tragic end. "
                                       + "Game Over!\n");
                               
                               pause(3000);
                               
                           }
                           else if (chosenPath == 2)
                           {
                               clip.stop();
                               playMusic("SoundEffects/levelClear.wav");
                               System.out.println("\nMario decides not to "
                                       + "enter the tunnel and continues "
                                       + "searching\nfor an alternative path "
                                       + "in the Mushroom Forest. He "
                                       + "explores\ndifferent areas, "
                                       + "overcoming various challenges along "
                                       + "the way.\nMario's determination "
                                       + "pays off as he eventually finds a "
                                       + "hidden\nroute, bypassing the Piranha "
                                       + "Plant. His journey to save Peach "
                                       + "continues. Well done!\n");
                               
                               System.out.println("Entering Warp Pipe Maze...");
                               pause(5000);
                               handleWarpPipeMaze();
                               
                           }
                           else 
                           {
                               System.out.println("Invalid choice! Please "
                                       + "enter a valid number ");
                           }
                       }
                       catch (InputMismatchException e)
                       {
                           System.out.println("Invalid input! Please enter "
                                   + "an integer value: ");
                           scanner.nextLine(); //reset the scanner
                       }
                       
                    }while (chosenPath < 1 || chosenPath > 3);
                }
                else 
                {
                    System.out.print("Invalid choice! Please enter a valid "
                            + "number: ");
                }
            
            }
            catch (InputMismatchException e)
            {
                System.out.print("Invalid input! Please enter an integer "
                        + "value: ");
                scanner.nextLine(); //reset the scanner
            }
            
        } while(chosenAction < 1 || chosenAction > 3);    
       
    }//end of handleMushroomForest method
    
    /*****************************************************************
     * Method: handleWarpPipeMaze 
     * Description: Handles the warp pipe maze path     
    ******************************************************************/
    private void handleWarpPipeMaze()
    {
        playMusic("Music/WarpPipeMaze.wav");
        
        //Present player with choices 
        System.out.println("\n******WARP PIPE MAZE******");
        System.out.print("\nAs Mario enters the Warp Pipe Maze, the tunnels "
                + "twist\nand turn, creating a labyrinthine puzzle. Suddenly, "
                + "a\nfork in the path appears. Which way should Mario go?\n"
                + "\n\t1. Left"
                + "\n\t2. Right"
                + "\n\t3. Retreat\n"
                + "\nEnter the number of your chosen direction: ");
        
        
        int chosenDirection = 0;
        
        //Perform action depending on the users chosen direction
        do{
            try
            {
                chosenDirection = scanner.nextInt(); //get user input 
        
        
                if (chosenDirection == 1)
                {
                    System.out.print("\nMario cautiously proceeds to the left,"
                            + " hoping to find\nan exit. However, he "
                            + "encounters a swarm of hostile Goombas\nMario "
                            + "must act quickly. What will he do?\n"
                            + "\n\t1. Jump on the Goombas to defeat them."
                            + "\n\t2. Use a Fire Flower to blast them away."
                            + "\n\t3. Attempt to run past them.\n"
                            + "\nEnter the number corresponding to Mario's "
                            + "action: ");
            
                    int chosenAction = 0;
                    
                    do{
                        try
                            {
                            chosenAction = scanner.nextInt();
            
                            if (chosenAction == 1)
                            {
                                clip.stop();
                                playMusic("SoundEffects/levelClear.wav");
                                System.out.println("\nMario jumps on the "
                                        + "Goombas, expertly defeating them "
                                        + "one by one.\nHe continues through "
                                        + "the maze, navigating its twists "
                                        + "and turns.\nMario successfully "
                                        + "emerges from the Warp Pipe Maze, "
                                        + "triumphant\nand ready for further "
                                        + "adventures. Well done!\n");
                                
                                System.out.println("Entering Haunted Castle...");
                                pause(5000);
                                handleHauntedCastle();
                
                            }
                            else if (chosenAction == 2)
                            {
                                clip.stop();
                                playMusic("SoundEffects/levelClear.wav");
                                System.out.println("\nMario activates a Fire "
                                        + "Flower and unleashes a barrage\nof "
                                        + "fireballs, eliminating the Goombas. "
                                        + "He explores the\nmaze, using his "
                                        + "newfound firepower to overcome "
                                        + "obstacles.\nMario successfully "
                                        + "emerges from the Warp Pipe Maze,"
                                        + "\ntriumphant and ready for further "
                                        + "adventures. Well done!\n"); 
                                
                                System.out.println("Entering Haunted Castle...");
                                pause(5000);
                                handleHauntedCastle();
                            }
                            else if (chosenAction == 3)
                            {
                                clip.stop();
                                playMusic("SoundEffects/LoseGame.wav");
                                System.out.println("\nMario attempts to run "
                                        + "past the Goombas, but they "
                                        + "quickly\nswarm and overpower him. "
                                        + "Despite his best efforts, Mario\nis "
                                        + "unable to escape their relentless "
                                        + "assault. His journey\nin the Warp "
                                        + "Pipe Maze comes to a tragic end. "
                                        + "Game Over!\n");
                                
                                pause(3000);
                            }
                            else 
                            {
                                System.out.print("Invalid choice!. Please "
                                        + "enter a valid number: ");
                        
                            }
                        
                        }catch (InputMismatchException e)
                        {
                            System.out.print("Invalid input! Please enter an "
                                    + "integer value: ");
                            scanner.nextLine(); //reset the scanner
                        }
                    }while (chosenAction < 1 || chosenAction > 3);
                
                
                }
                else if (chosenDirection == 2)
                {
                    System.out.print("\nMario takes the right path, hoping it "
                            + "leads him closer to\nthe exit. However, he "
                            + "encounters a group of Boos, mischievous\n"
                            + "ghostly creatures. How should Mario deal with "
                            + "this new threat?\n"
                            + "\n\t1. Face the Boos head-on."
                            + "\n\t2. Use a Starman power-up to gain "
                            + "invincibility."
                            + "\n\t3. Retreat and find an alternative route.\n"
                            + "\nEnter the number corresponding to Mario's "
                            + "action: ");
                
                    int chosenAction = 0;
                    do
                    {
                        try{
                            chosenAction = scanner.nextInt();

                            if (chosenAction == 1)
                            {
                                clip.stop();
                                playMusic("SoundEffects/levelClear.wav");
                                System.out.println("\nMario bravely faces the "
                                        + "Boos, using his precise jumps to"
                                        + "\nnavigate their haunting presence. "
                                        + "He overcomes their tricks\nand "
                                        + "traps, making his way through the "
                                        + "maze. Mario successfully\nemerges "
                                        + "from the Warp Pipe Maze, triumphant "
                                        + "and ready for\nfurther adventures. "
                                        + "Well done!\n");
                                
                                System.out.println("Entering Haunted Castle...");
                                pause(5000);
                                handleHauntedCastle();
                            }
                            else if (chosenAction == 2)
                            {
                                clip.stop();
                                playMusic("SoundEffects/levelClear.wav");
                                System.out.println("\nMario finds a Starman "
                                        + "power-up nearby, granting him\n"
                                        + "invincibility against the Boos. He "
                                        + "fearlessly passes\nthrough the "
                                        + "maze, with the Boos unable to harm "
                                        + "him.\nMario successfully emerges "
                                        + "from the Warp Pipe Maze,\n"
                                        + "triumphant and ready for further "
                                        + "adventures. Well done!\n");
                                
                                System.out.println("Entering Haunted Castle...");
                                pause(5000);
                                handleHauntedCastle();
                            }
                            else if (chosenAction == 3)
                            {
                                clip.stop();
                                playMusic("SoundEffects/LoseGame.wav");
                                System.out.println("\nMario retreats from the "
                                        + "Boos, deciding to find an "
                                        + "alternative path\nthrough the Warp "
                                        + "Pipe Maze. Unfortunately, he gets "
                                        + "lost in the maze's\ncomplex tunnels "
                                        + "and is unable to find an exit. "
                                        + "Mario's journey in the\nWarp Pipe "
                                        + "Maze comes to a tragic end. "
                                        + "Game Over!\n");
                             
                                pause(3000);
                            }
                            else
                            {
                                System.out.print("Invalid choice! Please "
                                        + "enter a valid number: ");
                            }
                        }catch (InputMismatchException e)
                        {
                            System.out.print("Invalid input! Please enter "
                                    + "an integer value: ");
                            scanner.nextLine(); //reset the scanner
                        }
                    }while (chosenAction < 1 || chosenAction > 3);
               
        
                }// end of else if statement 
                else if (chosenDirection ==3)
                {
                    clip.stop();
                    playMusic("SoundEffects/levelClear.wav");
                    System.out.println("\nMario decides that facing the "
                            + "obstacles ahead is too risky, and he "
                            + "retreats\nto find an alternative route. As he "
                            + "backtracks through the maze, he stumbles\nupon "
                            + "a hidden passage that leads to an underground "
                            + "cavern. In the cavern,\nMario discovers a "
                            + "treasure chest containing a rare power-up, the "
                            + "Propeller\nMushroom. Excited by his find, Mario "
                            + "consumes the mushroom and gains the\nability to "
                            + "soar through the air with a propeller on his "
                            + "head. With his new\npower, Mario takes flight "
                            + "and bypasses the Warp Pipe Maze entirely. "
                            + "Well done!");
                    
                    System.out.println("Entering Haunted Castle...");
                    pause(5000);
                    handleHauntedCastle();
                    
                    
                    
                }
                else if (chosenDirection < 1 || chosenDirection > 3)
                {
                    System.out.print("Invalid input. Please enter 1 or 2: ");
                }
            
            }catch (InputMismatchException e)
            {
                System.out.print("Invalid input! Please enter an integer "
                        + "value: ");
                scanner.nextLine(); //reset the scanner
            }
        }while (chosenDirection < 1 || chosenDirection > 3);    
                
        
        clip.stop();
        
    }//end of method
    
    
    /*****************************************************************
     * Method: handleHauntedCastle 
     * Description: Handles the haunted castle path     
    ******************************************************************/
    private void handleHauntedCastle()
    {
        System.out.println("\n******HAUNTED CASTLE******");
        playMusic("Music/HauntedMansion.wav");
        System.out.print("\nMario enters the Haunted Castle, shrouded in "
                + "darkness and mystery.\nAs he explores the eerie halls, he "
                + "hears unsettling whispers and\ncreaking sounds. Suddenly, "
                + "he encounters a room with three doors.\nWhich door should "
                + "Mario choose?\n"
                + "\n\t1. The Door of Shadows"
                + "\n\t2. The Door of Secrets"
                + "\n\t3. The Door of Illusions\n"
                + "\nEnter the number of your chosen door: ");
        
        int chosenDoor = 0;
        do{
            try{
                chosenDoor = scanner.nextInt();
        
                if (chosenDoor == 1)
                {
                    clip.stop();
                    playMusic("SoundEffects/levelClear.wav");
                    System.out.println("\nMario opens the Door of Shadows, "
                            + "entering a room filled with\nmysterious "
                            + "shadows. He must navigate the room carefully,"
                            + "\navoiding the shadow traps and pitfalls. After "
                            + "successfully\nmaneuvering through the shadows, "
                            + "Mario finds a hidden passage to\nthe castle's "
                            + "tower. As he reaches the top of the tower. "
                            + "Mario is\nstartled to find Bowser holding Peach "
                            + "captive. A fierce battle ensues,\nbut Mario's "
                            + "courage and skills prevail. He defeats Bowser "
                            + "and Rescues\nPrincess Peach.\n\nCongratulations! "
                            + "You've helped Mario save Princess Peach from "
                            + "Bowser!");
                    
                    pause(5000);
                }
                else if (chosenDoor == 2)
                {
                    clip.stop();
                    playMusic("SoundEffects/LoseGame.wav");
                    System.out.println("\nMario opens the Door of Secrets, "
                            + "revealing a chamber filled with ancient riddles "
                            + "and puzzles."
                            + "\nHe must solve the riddles to unlock the "
                            + "castle's secrets and progress further."
                            + "\nDespite his best efforts, Mario struggles to "
                            + "solve the complex riddles. Time\nruns out, "
                            + "and he is trapped within the Haunted Castle. "
                            + "Unfortunatley Mario was unable\nto save "
                            + "Princess Peach. Better luck next time!");
                    pause(3000);
                }
                else if (chosenDoor == 3)
                {
                    clip.stop();
                    playMusic("SoundEffects/LoseGame.wav");
                    System.out.println("\nMario opens the Door of Illusions, "
                            + "stepping into a realm of twisted illusions and"
                            + "\nhallucinations. He must navigate through the "
                            + "illusions, separating reality from deception."
                            + "\nThough challenging, Mario stays focused and "
                            + "eventually finds the exit from the illusionary"
                            + "\nmaze. However, as he emerges from the castle, "
                            + "he realizes that Princess is not there.\nIt was "
                            + "all an illusion created by Bowser. Better "
                            + "luck next time!");
                    pause(3000);
                }
                else
                {
                    System.out.print("Invalid choice! Please enter a valid "
                            + "number: ");
                }
        
            }catch (InputMismatchException e)
            {
                System.out.print("Invalid input! Please enter an integer "
                        + "value: ");
                scanner.nextLine(); //reset the scanner
            }
        }while (chosenDoor < 1 || chosenDoor > 3);
        
        clip.stop();
        
    }//end of method
    
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
        try{
         
         //Find music file and start slip
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream
         (new File(fileName).getAbsoluteFile());
         clip = AudioSystem.getClip();
         clip.open(audioInputStream);
         clip.start();
       
         } catch (IOException | LineUnavailableException | 
                 UnsupportedAudioFileException ex) {
       
         //If file not found
         System.out.println("Error with playing sound.");
     
        }
    }//end of playMusic method
    
}//end of class
