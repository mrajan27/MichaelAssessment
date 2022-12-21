package CoinMarketCap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class APIPoc {

	public static String gbpPrice;
	// Main driver method
	public static void main(String args[])throws IOException
	{
		try {

			URL url = new URL("https://pro-api.coinmarketcap.com/v2/tools/price-conversion?amount=10000000&symbol=GTQ&convert=GBP");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-CMC_PRO_API_KEY", "4aa65e25-1151-4204-aead-213e0657aa57");
			con.setRequestProperty("Content-Type","application/json");
			con.setRequestProperty("Accept","application/json");

			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
				
				JSONObject obj = new JSONObject(response.toString());
				JSONArray dataArr = (JSONArray) obj.get("data"); 
				for(int i=0;i < dataArr.length(); i++) {
					JSONObject dataArrObj = (JSONObject) dataArr.get(i); 
					JSONObject queotObj = (JSONObject) dataArrObj.get("quote"); 
					JSONObject GBP = (JSONObject) queotObj.get("GBP");
					BigDecimal price = (BigDecimal) GBP.get("price");    
					System.out.println("GBP Price : "+price.toString());
					gbpPrice = price.toString();
				}

			} else {
				System.out.println("GET request did not work.");
			}

			
		}

		catch (Exception e) {		
			System.out.println(e.getMessage());
		}
		
		
		try {

			URL url = new URL("https://pro-api.coinmarketcap.com/v2/tools/price-conversion?amount="+gbpPrice+"&symbol=GBP&convert=DOGE");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-CMC_PRO_API_KEY", "4aa65e25-1151-4204-aead-213e0657aa57");
			con.setRequestProperty("Content-Type","application/json");
			con.setRequestProperty("Accept","application/json");

			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());

				JSONObject obj = new JSONObject(response.toString());
				JSONArray dataArr = (JSONArray) obj.get("data"); 
				for(int i=0;i < dataArr.length(); i++) {
					JSONObject dataArrObj = (JSONObject) dataArr.get(i); 
					JSONObject queotObj = (JSONObject) dataArrObj.get("quote"); 
					JSONObject DOGE = (JSONObject) queotObj.get("DOGE");
					BigDecimal price = (BigDecimal) DOGE.get("price");    
					System.out.println("DOGE Coin Price :"+price.toString());
				}

			} else {
				System.out.println("GET request did not work.");
			}
			
		}

		catch (Exception e) {		
//			System.out.println(e.getMessage());
		}
	
	}
}

