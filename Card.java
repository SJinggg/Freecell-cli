/**
* Name	 : KOH SHI JING
* ID	 : 1171103504
* SECTION: TT02 
*/

/**
* The class for representing standard playing cards.
* Rank numbers are 0 through 12, corresponding to possible rank names: "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K".
* Suit numbers are 0 through 3, corresponding to possible suit names: "c", "d", "h", "S".
*/
public class Card{
  private String[] Cardarr = {"cA", "dA", "hA", "sA",
                              "c2", "d2", "h2", "s2",
                              "c3", "d3", "h3", "s3",
                              "c4", "d4", "h4", "s4",
                              "c5", "d5", "h5", "s5",
                              "c6", "d6", "h6", "s6",
                              "c7", "d7", "h7", "s7",
                              "c8", "d8", "h8", "s8",
                              "c9", "d9", "h9", "s9",
                              "cX", "dX", "hX", "sX",
                              "cJ", "dJ", "hJ", "sJ",
                              "cQ", "dQ", "hQ", "sQ",
                              "cK", "dK", "hK", "sK"};
  /**
  * Return the cards in Array
  * @return the cards in array
  */
  public String[] getCardarr(){
    return Cardarr;
  }
}
