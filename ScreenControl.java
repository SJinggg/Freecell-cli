import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
* A class for controlling the clearscreen function and delay function
*/
public class ScreenControl{
  /**
  * Clearing the screen
  */
  public void CLS(){
    try{
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }catch(IOException|InterruptedException e){}
  }

  /**
  * Delay the screen display
  *@param n seconds to pause the system with
  */
  public void delay(double n){
    int N = (int)(n * 1000);
    try{
      TimeUnit.MILLISECONDS.sleep(N);
    }catch(InterruptedException e){}
  }
}
