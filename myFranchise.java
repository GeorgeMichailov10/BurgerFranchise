/*George Michailov
 *Ctc ID:202485251
 *04/27/2022
 *Description: This program functions as a worldwide interface for a burger chain to set prices and options in stores.
 */


import java.util.Scanner;

public class myFranchise{
    

    public static void main(String[] args){
        String country;
        String franchiseName;
        int discount;
        String promotion;
        int burgerNumber;
        String newToppings;

        System.out.println("Project 2. Burger 211\n");
        boolean run = true;

        //Start the UI
        while(run){
            Scanner console = new Scanner(System.in);
            System.out.println("Enter country name. (Alpha-2 Code)");
            country = console.next();
            if(country.equals("0")){
                run = false;
                System.out.println("Bye!");
                break;
            }
            else{
                console.nextLine();
                System.out.println("Enter your franchise name");
                franchiseName = console.nextLine();

                //Burger211 = new Menu() does not work in this case
                Menu menu = new Menu(country, franchiseName);

                System.out.println("Enter discount rate % (0 ~ 99)");
                discount = console.nextInt();

                //Keep going until a valid discount is entered
                while(discount < 0 || discount > 99){
                    System.out.println("This is not a valid discount. Please enter a number between 0 and 99 to continue.");
                    discount = console.nextInt();
                }
                //There is a promotion if the discount is not 0, otherwise nothing changes
                if(discount != 0){
                    console.nextLine();
                    System.out.println("Enter promotion");
                    promotion = console.nextLine();
                    menu.Promotion(discount, promotion);
                }


                System.out.println("Which burger would you like to change? Enter " + menu.getHowManyBurgers() + " for none");
                
                //Lets the owner edit as many burgers as they want
                boolean keepGoing = true;
                while(keepGoing == true){

                    menu.printMenu();
                    
                    burgerNumber = console.nextInt();
                    console.nextLine();
                    //Keeps asking for a valid number until one is given
                
                    while(burgerNumber > 3 || burgerNumber < 0){
                        System.out.println("This number is not valid. Please enter a valid entry.");
                        burgerNumber = console.nextInt();
                        console.nextLine();
                    }
                    //Instructions were not clear as to what happens if 3 is entered so I assumed it meant the owner is done editting
                    if(burgerNumber == 3){
                        run = false;
                        System.out.println("Goodbye!");
                        break;
                    }
                    else{
                        System.out.println("What new toppings would you like?");
                        newToppings = console.nextLine();
                        menu.updateToppings(burgerNumber, newToppings);
                    }
                }
                menu.printMenu();
            }
            console.close();
        }

    }

    

    
}