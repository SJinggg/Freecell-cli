/**
* Name	 : KOH SHI JING
* ID	 : 1171103504
* SECTION: TT02 
*/

import java.util.*;

/**
* Map the card with its value for further sorting
*/
public class MapOfCardValue{
  private Map<Character, Integer> cardvalue= new LinkedHashMap<>();

  public MapOfCardValue(){
    cardvalue.put('0', 0);
    cardvalue.put('A', 1);
    cardvalue.put('2', 2);
    cardvalue.put('3', 3);
    cardvalue.put('4', 4);
    cardvalue.put('5', 5);
    cardvalue.put('6', 6);
    cardvalue.put('7', 7);
    cardvalue.put('8', 8);
    cardvalue.put('9', 9);
    cardvalue.put('X', 10);
    cardvalue.put('J', 11);
    cardvalue.put('Q', 12);
    cardvalue.put('K', 13);
  }

  /**
  * Return the value of the card
  * @param e the key of the card
  * @return the value of the card
  */
  public int get(char e){
    return cardvalue.get(e);
  }
}
