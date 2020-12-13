/**
* Name	 : KOH SHI JING
* ID	 : 1171103504
* SECTION: TT02
*/

import java.util.*;

/**
* Text-based view and controller for freecell Game
* @author Koh Shi Jing
*/

public class Freecell{
  /**
  * This is a program for running text-based freecell game
  * @param args - (not used)
  */
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    String commands;
    String colNum = "", cardName = "", destination = "";
    Game game = new Game();
    Card cards = new Card();
    ScreenControl sc = new ScreenControl();

    sc.CLS();

    System.out.println("\n\n\n\t\t\t=================================================");
    System.out.println("\t\t\t+                                               +");
    System.out.println("\t\t\t+        Welcome To Freecell                    +");
    System.out.println("\t\t\t+               A text-based game               +");
    System.out.println("\t\t\t+                                               +");
    System.out.println("\t\t\t=================================================");



    do{
      System.out.println("\n\t\t\tSpecial Command: ");
      System.out.println("\t\t\tx - terminated the program.");
      System.out.println("\t\t\tr - restart \n");
      System.out.println("\t\t\tCard(s) Moving Commands: ");
      System.out.println("\t\t\t<sourceColumn> <CardName> <Destination>");
      System.out.println("\t\t\t[Exp] 1 c6 2\n");
      System.out.println("\t\t\tColumn Rotation Command: ");
      System.out.println("\t\t\t<ColumnToBeRotated>");
      System.out.println("\t\t\t[Exp] 4\n");
      sc.delay(1);
      sc.CLS();

      do{
      System.out.println();
        do{
            System.out.println("Pile c\t\t: " + game.getPile(0));
            System.out.println("Pile d\t\t: " + game.getPile(1));
            System.out.println("Pile h\t\t: " + game.getPile(2));
            System.out.println("Pile s\t\t: " + game.getPile(3));
          for(int i = 0; i < game.getCol().size(); i++)
              System.out.println("Column " + (i + 1) + "\t: " + game.getCol().get(i).getColumn());

          System.out.print("Command > ");
          commands = input.nextLine();
          while(commands.equals("")){
            commands = input.nextLine();
          }
          if(commands.compareToIgnoreCase("x") == 0){
            sc.CLS();
            System.out.println("Program terminating.");
            sc.delay(0.5);
            sc.CLS();
            System.out.println("Program terminating..");
            sc.delay(0.5);
            sc.CLS();
            System.out.println("Program terminating...");
            sc.delay(0.5);
            sc.CLS();
            System.exit(0);
          }
          else if(commands.compareToIgnoreCase("r") == 0){
            sc.CLS();
            System.out.println("Program restarting.");
            sc.delay(0.5);
            sc.CLS();
            System.out.println("Program restarting..");
            sc.delay(0.5);
            sc.CLS();
            System.out.println("Program restarting...");
            sc.delay(0.5);
            sc.CLS();
            System.out.println();
            game = new Game();
          }
        }while(commands.compareToIgnoreCase("r") == 0);
        commands = commands.trim();
        String[] command = commands.split("\\s+");
        colNum = command[0].toLowerCase();
        if(command.length > 1){
          try{
            cardName = command[1];
            destination = command[2].toUpperCase();
          }catch(ArrayIndexOutOfBoundsException e){}
        }

        boolean repeat = true;
        do{
          try{
            if(Integer.parseInt(colNum) >= 1 && Integer.parseInt(colNum) <= 9){
              break;
            }
            System.out.print("Invalid source column. \nPlease re-enter the source column: ");
            colNum = input.next();
          }catch(NumberFormatException e){
            if(colNum.equals("c") || colNum.equals("d") || colNum.equals("h") || colNum.equals("s"))
              System.out.println("Warning: Card cannot be moved from pile.");
            colNum = "0";
          }
        }while(repeat);

        while(!cardName.equals("") && !Arrays.stream(cards.getCardarr()).anyMatch(cardName :: equalsIgnoreCase)){
          System.out.print("Invalid card Name. \nPlease re-enter the card name: ");
          cardName = input.next();
        }

        if(!cardName.equals(""))
          cardName = cardName.charAt(0) + Character.toString(cardName.charAt(1)).toUpperCase();

        do{
          try{
            if(cardName.equals("") && destination.equals(""))
              break;
            if(destination.length() == 1 && Integer.parseInt(destination) >= 1 && Integer.parseInt(destination) <= 9){
              break;
            }
            System.out.println("Invalid destination. \nPlease re-enter the destination: ");
            destination = input.next().toUpperCase();
          }catch(NumberFormatException e){
            if(destination.equals("C") || destination.equals("D") || destination.equals("H") || destination.equals("S")){
              break;
            }
            System.out.println("Invalid destination. \nPlease re-enter the destination: ");
            destination = input.next().toUpperCase();
          }
        }while(repeat);

        try{
          if(!cardName.equals("") && !destination.equals("") && colNum.equals(destination)){
            System.out.println("Warning: The source column and the destination entered are the same.");
            sc.delay(1);
          }
          else if(cardName.equals("") && destination.equals("")){
            game.ColumnRotation(colNum);
          }
          else
            game.MovingCard(colNum, cardName, destination);
        }catch(ArrayIndexOutOfBoundsException|NullPointerException e){
          System.out.println("Invalid1: Card entered not found at the entered column.");
          sc.delay(1);
        }

        if(game.isWin()){
          sc.CLS();
          System.out.println("Pile c\t\t: " + game.getPile(0));
          System.out.println("Pile d\t\t: " + game.getPile(1));
          System.out.println("Pile h\t\t: " + game.getPile(2));
          System.out.println("Pile s\t\t: " + game.getPile(3));
          for(int i = 0; i < game.getCol().size(); i++)
            System.out.println("Column " + (i + 1) + "\t: " + game.getCol().get(i).getColumn());
          break;
        }

        colNum = ""; cardName = ""; destination = "";
        sc.CLS();

      }while(commands.compareToIgnoreCase("x") != 0);

      System.out.println("\n\n\n\t\t\t================================================");
      System.out.println("\t\t\t| \t\tCongratulations! \t\t|");
      System.out.println("\t\t\t| \t\tYou have won the game \t\t|");
      System.out.println("\t\t\t================================================");

      do{
        System.out.print("Play again? [Y|N]: ");
        commands = input.next();
        if(!commands.equalsIgnoreCase("Y") && !commands.equalsIgnoreCase("N"))
          System.out.println("Wrong input. Please enter again.");
      }while(!commands.equalsIgnoreCase("Y") && !commands.equalsIgnoreCase("N"));
       game = new Game();
    }while(commands.equalsIgnoreCase("Y"));
    input.close();
  }
}
