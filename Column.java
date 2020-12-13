/**
* Name	 : KOH SHI JING
* ID	 : 1171103504
* SECTION: TT02 
*/

import java.util.*;

/**
* A class that distributes the shuffled cards to 8 columns
*/
public class Column extends CardPile{
    private ArrayList<String> Column = new ArrayList<>();
    private static int counter = 0;
    private static int j = 0;

    /**
    * Count the number of cards been allocated in the corresponding column
    */
    public static void Counter(){
      counter += 1;
    }

    public Column(){}

    /**
    * The constructor that distributed the cards everytime been called
    * @param temp accept a list of string to be distributed
    */
    public Column(List<String> temp){
      if(j == 52){
        j = 0;
        counter = 0;
      }
      int max = (counter >= 4) ? 6:7;
      for(int i = j; i < (j + max); i += 1){
        Column.add(temp.get(i));
      }
      j = (j < 46) ? (j += max) : 52;

      Counter();
    }

    /**
    * Return the size of the columns
    * @return the size of column
    */
    public int getSize(){
      return Column.size();
    }

    /**
    * Return the result if the column is isEmpty
    * @return true or false
    */
    public boolean isEmpty(){
      return (getSize() == 0);
    }

    /**
    * Return the Column in ArrayList of String
    * @return The Column
    */
    public ArrayList<String> getColumn(){
      return Column;
    }
}
