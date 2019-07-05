package hh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class hhData {
	
	private static String JSONtxt	= null;
	private static String StrURL	= null;
	private static Object ObjJSON	= null;
	private static ArrayList<ProfArea> ProfData	= null;

	private static void getJSONonURL() throws IOException {
		URL URLobj = new URL(StrURL);
		HttpURLConnection connection = (HttpURLConnection) URLobj.openConnection();
		connection.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			}
		in.close();
		JSONtxt	= response.toString();
	}

	private static void getObjectJSONonString() throws ParseException {
		JSONParser parser = new JSONParser();
		ObjJSON = parser.parse(JSONtxt);
	}
	
	public static Object getSpecializations() throws IOException, ParseException {
		StrURL	= "https://api.hh.ru/specializations";
		getJSONonURL();
		getObjectJSONonString();
		JSONArray ja = (JSONArray)ObjJSON;
		ProfData = new ArrayList<ProfArea>();
		for (int i=0;i<ja.size();i++) {
			JSONObject jpa	= (JSONObject) ja.get(i);
			int id	= Integer.parseInt(jpa.get("id").toString());
			String name	= (String) jpa.get("name");
			ProfArea pa	= new ProfArea(name,id);
			ProfData.add(pa);
		}
		ProfData.sort(new Comparator<ProfArea>(){
			public int compare(ProfArea o1, ProfArea o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		return ProfData; 
	}
}
