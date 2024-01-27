//This class took so many hours to create because I had to search online how to do this step by step and finding a decent free exchange api wasn't easy either

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.*;
import org.json.simple.parser.*;

public class ExchangeRate {

	public static double getRate(String currencyCode) {	
		try {					
            ///Create a URL instance
            String firstPartURL = "https://v6.exchangerate-api.com/v6/";
            String key ="4d2b66e10fe91625529fae74"; 
            String thirdPartURL = "/latest/USD";
            String theURL = firstPartURL + key + thirdPartURL;
            
            URL url = new URL(theURL); 

            //Read URL from online
            URLConnection urlConnection = url.openConnection();  //Connects to the URL

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); //Reads the response
            JSONParser parser = new JSONParser(); //Creates a new parser of the JSON stream
            JSONObject responseJson = (JSONObject)parser.parse(bufferedReader);    //new JSON object from the parse stream



            JSONObject conversionRates = (JSONObject)responseJson.get("conversion_rates");   //Reach inside wrapper to get conversion rates
            double exchangeRate = (double)conversionRates.get(currencyCode);   //exchangeRate is the double associated with the key


            return exchangeRate;				  			  	
        }	 
        catch (Exception e) {
            return 1.0;			            //If something goes wrong, assume the exchange rate is 1.0 aka the price stays in USD
        }
   	 }
	
	}
