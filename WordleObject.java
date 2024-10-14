import java.util.*;
//this class is used to represent the word people are trying to solve.
public class WordleObject
{
   private String[] letters;  //size 5
   private ArrayList<String> alphabet;  //everytime a user guesses a letter, take out a letter

   // Default constructor
   public WordleObject() {
       this.letters = new String[0];
       alphabet = new ArrayList<>();
   }

   // Custom constructor
   public WordleObject(String[] letters)
   {
       this.letters = letters;
       alphabet = new ArrayList<String>();
       String[] x = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w"
               , "x", "y", "z",};
       for (String temp : x)
           alphabet.add(temp);
   }

   // Getters
   public String getLetters(){
       String word = "";
       // Convert String array to a String
       for(String character: letters)
           word += character;

       return word;
   }

   public ArrayList getAlphabets(){
       return alphabet;
   }

   // Setters
   public void setLetters(String[] l){
       letters = l;
   }

   public void setAlphabets(ArrayList<String> a){
       alphabet = a;
   }

   // Remove the letters guessed by the user from Arraylist
   public void removeLetters(String[] guess){
       for (String character : guess)
           alphabet.remove(character);
   }

   // Method to get the matching part of the correct word
   public String checkWord(int location){
       String word;
       // Get the string from Letters array
       word = getLetters();

       // return the substring
       return word.substring(location, location+1);
   }

   // Return the index of the string in the letters array
   public int locateWord(String word){
       String wordLetters;
       wordLetters = getLetters();
       return wordLetters.indexOf(word);
   }

   public String toString(){
       return ("Letters available: " + this.alphabet);
   }

}