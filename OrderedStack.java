/**
* Name	 : KOH SHI JING
* ID	 : 1171103504
* SECTION: TT02
*/

import java.util.*;

/**
* A custom OrderedStack class for pile operations
*/
public class OrderedStack<E extends Comparable<E>> {
  private ArrayList<E> Pile;
  MapOfCardValue cardvalue = new MapOfCardValue();

  /**
  * The constructor that create a new ArrayList for pile while called
  */
  public OrderedStack(){
    Pile = new ArrayList<>();
  }

  /**
  * Return the size of the Pile
  * @return the size of the Pile
  */
  public int getSize(){
    return Pile.size();
  }

  /**
  * The method to push the object accepted to the related pile
  * @param e the object to be pushed
  * @throws Exception if the Object cannot be push into the pile
  */
  public void push(E e) throws Exception{
    if(isEmpty() && ((String)e).charAt(1) == 'A')
      Pile.add(e);
    else if((cardvalue.get(((String)peek()).charAt(1)) - cardvalue.get(((String)e).charAt(1)) + 1) == 0)
      Pile.add(e);
    else{
      throw new Exception("Cannot put into Pile");
    }

  }

  /**
  * Return the peek of the Pile
  * @return the object at the top of the pile
  */
  public E peek(){
    return Pile.get(getSize() - 1);
  }

  /**
  * Return the state of the pile if empty
  * @return true or false
  */
  public boolean isEmpty(){
    return Pile.isEmpty();
  }

  /**
  * Return the Pile
  * @return the pile
  */
  public String toString(){
    return Pile.toString();
  }
}
