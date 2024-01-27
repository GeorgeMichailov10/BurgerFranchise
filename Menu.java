import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;


import java.util.ArrayList;

public class Menu extends Burger211{
    private boolean debug = false;

	public String country;
	public String franchise;
	public double discountRate;
	public String promotion;
	public String currencyCode;
	public String currencySymbol;
	public int updatedBurgerNum=-1;
	public String updatedTopping;


    //Constructor
	public Menu(String country, String franchise){
		this.country = country;
		this.franchise = franchise;
		this.currencyCode = getCurrencyCode(country);
		this.currencySymbol = getCurrencySymbol(country);     
	}

	//Currency Code for JSON and exchange rate
	public String getCurrencyCode(String country){
		Locale locale = new Locale.Builder().setRegion(country).build();
		String currencyCode = Currency.getInstance(locale).getCurrencyCode();
		return currencyCode;
	}

	//Currency Symbol such as $
	public String getCurrencySymbol(String country){
		Locale locale = new Locale.Builder().setRegion(country).build();
        String currencySymbol = Currency.getInstance(locale).getSymbol();
		return currencySymbol;
	}

	//In the case of a special promotion happening
	@Override
	public void Promotion(double discount, String promotion){
		this.discountRate = discount;
		this.promotion = promotion;
	}


	@Override
	public void printMenu(){
		
		ArrayList<String> prices = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("#,###,###.00");  //Creates a formatting object
		for(int i = 0; i < super.getHowManyBurgers(); i++){
			double price = super.getPrice(i);                          
			double rate = ExchangeRate.getRate(this.currencyCode);     //Creates a double equivalent to the exchange rate
			price *= rate;                                             //Changes the price to the equivalent value in another currency

			String formattedPrice = this.currencySymbol + " ";             
			String regularPrice = df.format(price);
			if(discountRate > 0.0 && discountRate < 100){              //If there is a promotion happening
				price *= (100.0 - discountRate) / 100;                 //Applies the discount
				String discountedPrice = df.format(price);             //Formats the Discount
				formattedPrice += discountedPrice + " (regular price: " + regularPrice + ")";           
			} 
			else{
				formattedPrice += regularPrice;                        //Just add the normal price without promotion
			}
			prices.add(formattedPrice);
		}

		if (debug) {                                                   //This was before the MenuGUI was working and tried to make it work in just the console
			for(int iter = 0; iter < getHowManyBurgers(); iter++){
				System.out.println(iter + ". " + getName(iter) + ": " + (this.updatedBurgerNum == iter ? this.updatedTopping : super.getToppings(iter)) + "     " + getCurrencyCode(country) + " " + prices.get(iter));
			}
		}
		else {
			new MenuGUI(this.franchise, this.promotion != null ? this.promotion : "",
				getName(0), prices.get(0), (this.updatedBurgerNum == 0 ? this.updatedTopping : super.getToppings(0)),
				getName(1), prices.get(1), (this.updatedBurgerNum == 1 ? this.updatedTopping : super.getToppings(1)),
				getName(2), prices.get(2), (this.updatedBurgerNum == 2 ? this.updatedTopping : super.getToppings(2)),
				ExchangeRate.getRate(this.currencyCode));

				//If there is a promotion, creates a menuGUI with this promotion in the constructor or if not, it creates one with an empty string
				//And for toppings checks if the burger Number has been updated because the toppings have been changed or it just gets the basic built in toppings
		}
	}

	//returns price with exchange rate applied
	@Override
	public double getPrice(int i){
		double rate = ExchangeRate.getRate(this.currencyCode);
		return super.getPrice(i) * rate;                           //Apply exchange rate
	}

	//If the user decides to change toppings
	public void updateToppings(int i, String newToppings){
        this.updatedBurgerNum = i;
		this.updatedTopping = newToppings;
	}

}