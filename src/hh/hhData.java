package hh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class hhData {
	
	private static String JSONtxt	= null;
	private static String StrURL	= null;
	private static Object ObjJSON	= null;

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
		JSONArray ja = (JSONArray)ObjJSON;
		//System.out.println(ja);
		ArrayList ListPA = new ArrayList();
		for (int i=0;i<ja.size();i++) {
			JSONObject jpa	= (JSONObject) ja.get(i);
			//System.out.println(jpa);
			int id	= Integer.parseInt(jpa.get("id").toString());
			System.out.println(id);
			String name	= (String) jpa.get("name");
			System.out.println(name);
			//System.out.println(Integer.parseInt(jpa.get("id").toString()));
			System.out.println(jpa.get("specializations"));
			ProfArea pa	= new ProfArea(name,id);
			ListPA.add(pa);
		}
		hhForm.setProfData(ListPA);
	}
	
	public static Object getSpecializations() throws IOException, ParseException {
		StrURL	= "https://api.hh.ru/specializations";
		getJSONonURL();
		getObjectJSONonString();
		return ObjJSON; 
	}
}
