/**
* Name	 : KOH SHI JING
* ID	 : 1171103504
* SECTION: TT02
*/

import java.util.*;

/**
* A class where all the operations happen here
*/
public class Game{
  CardPile newc;
  List<String> shuffledDeck;
  Stack<Column> col;
  OrderedStack<String>[] pile;
  Map<OrderedStack<String>, String> pilevalue = new LinkedHashMap<>();
  MapOfCardValue cardvalue = new MapOfCardValue();
  List<String> removeCardList;
  ScreenControl sc = new ScreenControl();

  /**
  * The constructor creates a new game everytime it has been called
  */
  public Game(){
    newc = new CardPile();
    shuffledDeck = newc.getShuffledDeck();
    col = new Stack<>();
    pile = new OrderedStack[4];

    for(int i = 0; i < 4; i++){
      pile[i] = new OrderedStack<>();
    }

    for(int i = 0; i < 8; i++){
       col.add(new Column(shuffledDeck));
     }
     col.add(new Column());
   }

   /**
   * Return a stack with data type of Column
   * @return a stack with data type of Column
   */
   public Stack<Column> getCol(){
     return col;
   }

  /**
  * Return the pile depends on the index user entered
  * @param index the number of pile to be returned
  * @return the pile
  */
  public OrderedStack<String> getPile(int index){
    return pile[index];
  }

  /**
  * Set each of the pile value
  */
  public void setPileValue(){
    pilevalue.put(pile[0], "c");
    pilevalue.put(pile[1], "d");
    pilevalue.put(pile[2], "h");
    pilevalue.put(pile[3], "s");
  }

  /**
  * Moving the card depends on the user instruction
  * @param colNum The number of the source column
  * @param cardName The card to be moved
  * @param destination The destination the card to be moved to
  */
  public void MovingCard(String colNum, String cardName, String destination){
    setPileValue();
    String lastcardofDes;
    boolean toColumn = true;
    String removeCard = col.get(Integer.parseInt(colNum) - 1).getColumn().get(col.get(Integer.parseInt(colNum) - 1).getColumn().size() - 1);

    //check the cardName
    try{
      removeCardList = new ArrayList<>(col.get(Integer.parseInt(colNum) - 1).getColumn().subList(col.get(Integer.parseInt(colNum) - 1).getColumn().indexOf(cardName), col.get(Integer.parseInt(colNum) - 1).getColumn().indexOf(removeCard) + 1));
    }
    catch(IndexOutOfBoundsException e){
      removeCardList = null;
    }

    try{
      lastcardofDes = col.get(Integer.parseInt(destination) - 1).getColumn().get(col.get(Integer.parseInt(destination) - 1).getColumn().size() - 1);
      toColumn = true;
    }catch(NumberFormatException e){
      toColumn = false;
      switch(destination){
        case "C": destination = "A"; break;
        case "D": destination = "B"; break;
        case "H": destination = "C"; break;
        case "S": destination = "D"; break;
      }
      if(getPile(destination.charAt(0) - 65).isEmpty()){
        lastcardofDes = "00";
      }
      else
        lastcardofDes = getPile(destination.charAt(0) - 65).peek();
    }catch(ArrayIndexOutOfBoundsException e){
      lastcardofDes = null;
    }
    if(removeCard.equalsIgnoreCase(cardName)){
      if(toColumn && (lastcardofDes == null || ((cardvalue.get(removeCard.charAt(1)) - cardvalue.get(lastcardofDes.charAt(1)) + 1) == 0))){
        removeCard = col.get(Integer.parseInt(colNum) - 1).getColumn().remove(col.get(Integer.parseInt(colNum) - 1).getColumn().size() - 1);
        col.get(Integer.parseInt(destination) - 1).getColumn().add(removeCard);
      }
      else if(!toColumn ){
          try{
            getPile(destination.charAt(0) - 65).push(removeCard);
            removeCard = col.get(Integer.parseInt(colNum) - 1).getColumn().remove(col.get(Integer.parseInt(colNum) - 1).getColumn().size() - 1);
          }catch(Exception e){
            if(!toColumn && !(Character.toString(removeCard.charAt(0)).equals(pilevalue.get(getPile(destination.charAt(0) - 65))))){
              System.out.println("Invalid2: The card entered should be moved to the respective pile.");
              sc.delay(1);
            }
            else if(!toColumn && removeCard.charAt(1) != 'A'){
              System.out.println("Invalid3: The four foundation pile must be started with aces, arranged in ascending order, and end up with King.");
              sc.delay(1);
            }
          }
      }
      else{

          if (toColumn){
            System.out.println("Invalid4: Card must be placed in descending order");
            sc.delay(1);
          }
      }
    }
    else if(removeCard.equalsIgnoreCase(removeCardList.get(removeCardList.size() - 1))){
      boolean movable = true;
      if(toColumn){
        for(int i = 0; i < removeCardList.size() - 1; i++){
          if((cardvalue.get((removeCardList.get(i)).charAt(1)) - cardvalue.get((removeCardList.get(i + 1)).charAt(1)) - 1) != 0)
            movable = false;
        }
        if(movable && (lastcardofDes == null || ((cardvalue.get(removeCardList.get(0).charAt(1)) - cardvalue.get(lastcardofDes.charAt(1)) + 1) == 0))){
          for(int i = 0; i < removeCardList.size(); i++){
            removeCard = col.get(Integer.parseInt(colNum) - 1).getColumn().remove(col.get(Integer.parseInt(colNum) - 1).getColumn().size() - 1);
            col.get(Integer.parseInt(destination) - 1).getColumn().add(removeCardList.get(i));
          }
        }
        else if(movable && !((cardvalue.get(removeCardList.get(0).charAt(1)) - cardvalue.get(lastcardofDes.charAt(1)) + 1) == 0)){
          System.out.println("Cards selected cannot be moved:  The moved card must be placed to a Column where its last card is one point bigger than the first of moved card");
          sc.delay(1);
        }
        else{
          System.out.println("Cards selected cannot be moved: It's not arranged orderly.");
          sc.delay(1);
        }
      }
      else{ //to pile
        Collections.reverse(removeCardList);
        for(int i = 0; i < removeCardList.size() - 1; i++){
          if((cardvalue.get((removeCardList.get(i)).charAt(1)) - cardvalue.get((removeCardList.get(i + 1)).charAt(1)) + 1) != 0)
            movable = false;
          if((removeCardList.get(i)).charAt(0) != (removeCardList.get(i + 1)).charAt(0))
            movable = false;
        }
        if(movable &&  ((cardvalue.get(removeCardList.get(0).charAt(1)) - cardvalue.get(lastcardofDes.charAt(1)) - 1) == 0) && (Character.toString(removeCardList.get(0).charAt(0)).equals(pilevalue.get(getPile(destination.charAt(0) - 65))))){
          for(int i = 0; i < removeCardList.size(); i++){
            try{
              getPile(destination.charAt(0) - 65).push(removeCardList.get(i));
              removeCard = col.get(Integer.parseInt(colNum) - 1).getColumn().remove(col.get(Integer.parseInt(colNum) - 1).getColumn().size() - 1);
            }catch(Exception e){}
          }
        }
        else if(movable && !((cardvalue.get(removeCardList.get(0).charAt(1)) - cardvalue.get(lastcardofDes.charAt(1)) - 1) == 0)){
          System.out.println("Cards selected cannot be moved:  The moved card must be placed to a Column where its last card is one point bigger than the first of moved card");
          sc.delay(1);
        }
        else{
          System.out.println("Cards selected cannot be moved: It's not arranged orderly.");
          sc.delay(1);
        }
      }
    }
    else{
      //not equal
      System.out.println("Invalid5: Card entered not found at the entered column.");
      sc.delay(1);
    }
  }

  /**
  * When a user enters a column number, the last element of the column will be moved to the front of the column.
  * @param colNum The number of column to be rotated
  */
  public void ColumnRotation(String colNum){
    col.get(Integer.parseInt(colNum) - 1).getColumn().add(0, col.get(Integer.parseInt(colNum) - 1).getColumn().remove(col.get(Integer.parseInt(colNum) - 1).getColumn().size() - 1));
  }

  /**
  * Check if all the column is empty indicating the user has won the game
  * @return true or false of the win condition
  */
  public boolean isWin(){
     for(int i = 0; i < col.size(); i++){
       if(col.get(i).getColumn().isEmpty()){}
       else return false;
     }
     return true;
  }
}
