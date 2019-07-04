package hh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class hhData {
	
	private String JSONtxt	= "";
	private String StrURL	= "";
	private Object ObjJSON	= null;

	private void getJSONonURL() throws IOException {
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

	private void getObjectJSONonString() throws ParseException {
		JSONParser parser = new JSONParser();
		ObjJSON = parser.parse(JSONtxt);
	}
	
	public Object getSpecializations() throws IOException, ParseException {
		StrURL	= "https://api.hh.ru/specializations";
		getJSONonURL();
		getObjectJSONonString();
		return ObjJSON; 
	}
}
