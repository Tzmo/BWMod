package com.bw.mod.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import com.bw.mod.BWMod;

public class HTTPRequests {	
	public static Object GetLevel(String uuid) throws Exception {
		
		String url = "https://api.hypixel.net/player?key=" + BWMod.APIKey + "&uuid=" + uuid;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setConnectTimeout(3000);
		con.setReadTimeout(3000);
		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		if (responseCode == 200) {
			try {
				JSONObject myResponse = new JSONObject(response.toString());
				
				if (!myResponse.has("player")) {
					return null;
				}
				
				if (!myResponse.getJSONObject("player").has("achievements")) {
					return null;
				}
				
				if (!myResponse.getJSONObject("player").getJSONObject("achievements").has("bedwars_level")) {
					return null;
				}
				
				if (!myResponse.getJSONObject("player").has("stats")) {
					return null;
				}
				
				if (!myResponse.getJSONObject("player").getJSONObject("stats").has("Bedwars")) {
					return null;
				}
				
				if (!myResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").has("final_kills_bedwars")) {
					return null;
				}
				
				if (!myResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").has("final_deaths_bedwars")) {
					return null;
				}
				
				if (!myResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").has("wins_bedwars")) {
					return null;
				}
				
				if (!myResponse.getJSONObject("player").getJSONObject("stats").getJSONObject("Bedwars").has("losses_bedwars")) {
					return null;
				}
				
				JSONObject JSONResponse = myResponse;
				return JSONResponse;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		} else {
			return null;
		}
	}
}