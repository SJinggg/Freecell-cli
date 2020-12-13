/**
* Name	 : KOH SHI JING
* ID	 : 1171103504
* SECTION: TT02 
*/

import java.util.*;

/**
* Allocating the card to a List for shuffling
*/
public class CardPile extends Card{
  private List<String> cards = Arrays.asList(super.getCardarr());

  /**
  * Return the shuffled deck in List type
  * @return the shuffled deck
  */
  public List<String> getShuffledDeck(){
    Collections.shuffle(cards);
    return cards;
  }
}
