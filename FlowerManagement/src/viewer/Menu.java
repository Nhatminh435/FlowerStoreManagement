package viewer;

import tool.Adder;

/**
 * Provide the methods that display the menu of program.
 * @author N.Minh
 */
public class Menu {
    /**
     * Display the menu of Flower Store program and return an
     * integer number which has values between 1 - 10 
     * @return (int) getChoice
     */
    public static int getChoice(){
        System.out.println(" --------------------------- ");
        System.out.println("|       Flower Store        |");
        System.out.println("|---------------------------|");
        System.out.println("|       Manage flower       |");
        System.out.println("|   ---------------------   |");
        System.out.println("| 1.    Add a flower        |");
        System.out.println("| 2.    Find a flower       |");
        System.out.println("| 3.    Update a flower     |");
        System.out.println("| 4.    Delete a flower     |");
        System.out.println("|                           |");
        System.out.println("|---------------------------|");
        System.out.println("|       Manage Order        |");
        System.out.println("|   ---------------------   |");
        System.out.println("| 5.    Add an order        |");
        System.out.println("| 6.    Display orders      |");
        System.out.println("| 7.    Sort orders         |");
        System.out.println("| 8.    Save data           |");
        System.out.println("| 9.    Load data           |");
        System.out.println("| 10.   Quit                |");
        System.out.println(" --------------------------- ");
        System.out.println("");
        System.out.print("➜ Your choice: ");
        int getChoice = Adder.addIntegerNumber();
        if (!((getChoice >= 1) && (getChoice <= 10))) {
            do {                
                System.out.println("Unvalid request.");
                System.out.print("➜ Please enter again: ");
                getChoice = Adder.addIntegerNumber();
            } while (!((getChoice >= 1) && (getChoice <= 10)));
        }
        return getChoice;
    }
}   
