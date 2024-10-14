import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;

public class WordleMain {
    public static void main(String[] args)
    {
        // Declare and initialize variables
        // Colors for the text values
        final String GREEN = "\u001B[32m"; // Green color
        final String YELLOW = "\u001B[33m"; // Blue color
        final String NOCOLOR = "\u001b[0m"; // No color

        final int MAXGUESS = 6; // Maximum number of guesses allowed
        final int MAXWORDLENGTH = 5; // Maximum word length for words used for guessing
        int randomIndex = 0; // Index for the random word
        String randomWord = ""; // Random word to be guessed by user
        String guessWord = ""; // User guessed word
        String[] letters = {""}; // Letters array for the WordleObject

        //this part of code will take all the words from the list and store it all in allWords
        ArrayList<String> allWords = new ArrayList<String>();
        try {
            File myObj = new File("wordle.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                allWords.add(data);
            }
            myReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        //START YOUR CODE HERE
//        System.out.println(allWords.size());
//        System.out.print(allWords);
//        System.out.println();

        // Generate a random index to pick a word
        randomIndex = (int)(Math.random() * allWords.size());
        // Get the word at the random index location
        randomWord = allWords.get(randomIndex);
        //System.out.println("Correct answer: " + randomWord);

        // Convert the word into a String array
        letters = convertToArray(randomWord);

        // Create an instance of WordleObject
        WordleObject wordleObj = new WordleObject(letters);
        System.out.println(wordleObj);

        System.out.println("You have " + MAXGUESS + " chances to guess the 5-letter word");
        Scanner input = new Scanner(System.in);

        // Give the user MAXGUESS chances to guess the word
        for(int i = 1; i <= MAXGUESS; i++)
        {
            boolean correctNum = false;
            while (!correctNum){
                System.out.print("Please guess the 5-letter word (try " + i + "): ");
                // Store the user entered word
                guessWord = input.nextLine();

                if(guessWord.length() == 5)
                    correctNum = true;
                else
                    System.out.println("Enter 5 letter words only");
            }



            // Remove the guessed answer letters from alphabets Arraylist
            wordleObj.removeLetters(convertToArray(guessWord));

            // Print the available letters in the alphabets Arraylist
            System.out.println(wordleObj);

            // Loop to compare each letter of the 5-letter word entered by user
            for(int j = 0; j < MAXWORDLENGTH; j++)
            {
                if(guessWord.substring(j, j+1).equals(wordleObj.checkWord(j)))
                {
                    // If the current letter guessed word matches the current letter of correct answer
                    System.out.print(GREEN + guessWord.substring(j, j+1) + NOCOLOR);
                }
                else if(wordleObj.locateWord(guessWord.substring(j, j+1)) > -1)
                {
                    // The current letter is in the correct answer but at a different location
                    System.out.print(YELLOW + guessWord.substring(j, j+1) + NOCOLOR);
                }
                else
                {
                    // The current letter is not in the answer at all
                    System.out.print(guessWord.substring(j, j+1));
                }
            }

            System.out.println("");

            // If the user entered word is the correct answer
            if(guessWord.equals(randomWord))
            {
                System.out.println("Congratulations! You guessed the word correctly!");
                break;
            }

        }

        // If the user entered a wrong answer after MAXGUESS tries
        if(!guessWord.equals(randomWord))
        {
            System.out.println("Sorry, you were not correct. Correct answer is: " + randomWord + ".");
        }
    }

    // Method to convert a String to a String array
    public static String[] convertToArray(String word){
        // Define a String array
        String[] letters = new String[5];

        // Loop to get each character in the word and store as string array element
        for (int i = 0; i < word.length(); i++) {
            letters[i] = String.valueOf(word.substring(i, i+1));
        }

        // Return the String array
        return letters;
    }
}