import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
public class Crossword 
{
   private int width;
   private int height;
   private ArrayList<String> horizontalWords; 
   private ArrayList<String> verticalWords; 
   private char[][] grid;
   private ArrayList<String> placedWords;
   private ArrayList<String> leftOverWords;
   private boolean firstWord = false;
     
   public Crossword(int width, int height, String[] array)
   {
      this.width = width;
      this.height = height;
      this.grid = new char[height][width];  //makes new 2d grid
      this.placedWords = new ArrayList<String>();  
      this.horizontalWords = new ArrayList<String>();
      this.verticalWords = new ArrayList<String>();
      this.leftOverWords = new ArrayList<String>();
      for(int i = 0; i<array.length; i++)
      {
         leftOverWords.add(array[i]);
      }
      
      
   
      
   }
   public void fillGrid()
   {
      for(int row = 0; row<grid.length; row++)
      {
         for(int col = 0; col<grid[row].length; col++)
         {
            grid[row][col] = ' ';
         }
      }
   }
  
   
   public void placeWord(String word, String prev)
   {
      //System.out.println("PLACING "+word+" IN "+prev);
      if(placedWords.contains(word))
      {
        // System.out.println(word+" Is already placed!");
         return;
      }
      else
      {
      
         int row = 1;  //the ROW where word generates
         int col = 1;  //the COLUMN where word generates
         char a = word.charAt(0); //first character of string we are inputting
         String find = findChar(a);
         //System.out.println(find);
         if(!find.equals("CANNOT FIND"))   //checks if the character exists
         {   
            row = Integer.parseInt(find.substring(0, 1));
            col = Integer.parseInt(find.substring(1));
            grid[row][col] = word.charAt(0);
         }
         
        // System.out.println(canFit(word, col, row)); //checks if the word can fit 
         if(canFit(word, col, row).equals("HForward")) //horizontal
         {
            if(!find.equals("CANNOT FIND") || firstWord == false)
            {
               for(int i = 0; i<word.length(); i++)
               {
                  char c = word.charAt(i);
                  grid[row][col] = c;
                  col++;
               }
               firstWord = true;
               placedWords.add(word);
               horizontalWords.add(word);
               leftOverWords.remove(word);
               print();
            }
            else
            {// System.out.println(word+" CANNOT FIT");
            }
         }
         
         /*   else if(canFit(word, col, row).equals("HBackward")) //horizontal     /// WE ARE NOT USING THIS YET ///
         {
         
         for(int i = 0; i<word.length(); i++)
         {
         System.out.println(i);
            char c = word.charAt(i);
            grid[row][col] = c;
            col--;
         }
         }
         */   
         else if(canFit(word, col, row).equals("V"))   //check this
         {
            if(!find.equals("CANNOT FIND") || firstWord == false)
            {
               for(int i = 0; i<word.length(); i++)
               {
                  char c = word.charAt(i);
                  grid[row][col] = c;
                  row++;
               }
               firstWord = true;
               verticalWords.add(word);
               placedWords.add(word);
               leftOverWords.remove(word);
               print();
            
            }
            else
            {  }
         
         
         }
      
      }
   }
   
   
   
   public String canFit(String word, int charCol, int charRow)  
   {
   
      String t = "cannotFit";
      //CHECKS IF NEXT COLUMNS DO NOT HAVE A WORD
     
      
      /*  else if(charCol-(word.length()-1) >= 0 ) //horizontal backwards
      {
         t = "HBackward";
      } */
      if(charRow+word.length()<=height && grid[charRow-1][charCol] == ' ') //vertical 
      {
         t = "V";
         for(int v = 1; v<word.length(); v++)
         {
            if(grid[charRow+v][charCol] != ' ' || grid[charRow+v][charCol-1] != ' ' || grid[charRow+v][charCol+1] != ' ')
            {
               t = "cannotFit";
            }
         }
      } 
      else if(charCol+word.length()<=width && grid[charRow][charCol-1] == ' ') //horizontal forwards 
      {
         t = "HForward";
         for(int k = 1; k<word.length(); k++)
         {
            if(grid[charRow][charCol+k] != ' ' || grid[charRow+1][charCol+k] != ' ' || grid[charRow-1][charCol+k] != ' ')
            {
               t = "cannotFit";
            }
         }
      }
   
      return t;
   }
   
   public void print()
   {
      for(int i = 0; i<height; i++)
      {
         for(int j = 0; j<width; j++)
         {
            System.out.print(grid[i][j]+" ");
         }
         System.out.println("");
      }
   }
   public String findChar(char cha)
   {
      for(int r = 0; r<height; r++)
      {
         for(int c = 0; c<width; c++)
         {
            if(grid[r][c] == cha)
            { 
               return r+""+c;
            }
         }
      }
      return "CANNOT FIND";
   }
   
   public char[][] getGrid()
   {
      return grid;
   }
   
   public void printInfo()
   {
      System.out.println("\n\nPLACED WORDS");
      for(String placed: placedWords)
      {
         System.out.print(placed+" ");
      }
      System.out.println("\n\nHORIZONTAL WORDS");
      for(String hor: horizontalWords)
      {
         System.out.print(hor+" ");
      }
      System.out.println("\n\nVERTICAL WORDS");
      for(String ver: verticalWords)
      {
         System.out.print(ver+" ");
      }
      System.out.println("\n\nLEFTOVER WORDS");
      for(String left: leftOverWords)
      {
         System.out.print(left+" ");
      }
      
   
   
   }
   
   
  




}