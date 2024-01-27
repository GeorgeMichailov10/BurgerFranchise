import java.util.ArrayList;


public abstract class Burger211{
    
    private ArrayList<BurgerInfo> burgerInfos = new ArrayList<BurgerInfo>();

    public Burger211(){
        

        BurgerInfo b1 = new BurgerInfo("Inheritance Burger", 5.0, "beef patty, tomato, onion, ranch source");	 
        BurgerInfo b2 = new BurgerInfo("@Override Burger", 5.0, "beef patty, lime, onion, lettuce, tomato sauce");	    
        BurgerInfo b3 = new BurgerInfo("Polymorphism Burger", 5.0, "chicken breast, gallo, onion, rice");	
        burgerInfos.add(b1);
        burgerInfos.add(b2);
        burgerInfos.add(b3);

        //Create an arraylist with options of burgers

    }

    public String getName(int i){
        return burgerInfos.get(i).name;
    }

    public double getPrice(int i){
        return burgerInfos.get(i).price;
    }

    public String getToppings(int i){
        return burgerInfos.get(i).toppings;
    }

    public int getHowManyBurgers(){
        return burgerInfos.size();
    }

    //Create two abstract methods to be made in Menu
    abstract void printMenu();
    abstract void Promotion(double discountRate, String promotion);

}