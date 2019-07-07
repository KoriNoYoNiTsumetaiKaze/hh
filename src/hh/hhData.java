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
	private static ArrayList<Job> FindJob	= null;

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
		JSONArray ja	= (JSONArray)ObjJSON;
		ProfData		= new ArrayList<ProfArea>();
		ProfArea pa		= new ProfArea("All/Все",0);
		ProfData.add(pa);
		for (int ipa=0;ipa<ja.size();ipa++) {
			JSONObject jpa	= (JSONObject) ja.get(ipa);
			//System.out.println(jpa.get("specializations"));
			int id_pa		= Integer.parseInt(jpa.get("id").toString());
			String name_pa	= (String) jpa.get("name");
			JSONArray jas	= (JSONArray)jpa.get("specializations");
			pa	= new ProfArea(name_pa,id_pa);
			for (int ip=0;ip<jas.size();ip++) {
				JSONObject jp	= (JSONObject) jas.get(ip);
				double id_p		= Double.parseDouble(jp.get("id").toString());
				String name_p	= (String) jp.get("name");
				boolean laboring	= (boolean) jp.get("laboring");
				Prof p	= new Prof(name_p,id_p,laboring);
				pa.addProf(p);
			}
			Prof p	= new Prof("All/Все",0,false);
			pa.addProf(p);			
			pa.sortProf();
			ProfData.add(pa);
		}
		ProfData.sort(new Comparator<ProfArea>(){
			public int compare(ProfArea o1, ProfArea o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		return ProfData; 
	}
		
	public static ArrayList<Job> getJobs() throws IOException, ParseException {
	    try {
	        int page	= Integer.parseInt(hhForm.getPage());
	        int pages	= hhForm.getPages();
	        if (page<pages)	StrURL	= "https://api.hh.ru/vacancies?per_page=100&page="+page;
	        	else StrURL	= "https://api.hh.ru/vacancies?per_page=100";
	    } catch (NumberFormatException e) {
	    	StrURL	= "https://api.hh.ru/vacancies?per_page=100";
	    }
		ProfArea selectProfArea	= hhForm.getSelectProfArea();
		Prof selectProf	= hhForm.getSelectProf();
		if (selectProf==null){
			if (selectProfArea!=null) {
				int specialization	= selectProfArea.getId();
				if (specialization>0) StrURL	= StrURL+"&specialization="+specialization;
			}
		}
		else {
			double specialization	= selectProf.getId();
			if (specialization>0) StrURL	= StrURL+"&specialization="+specialization;
		}
		FindJob	= new ArrayList<Job>();
		getJSONonURL();
		getObjectJSONonString();
		if (ObjJSON==null) return FindJob;
		JSONObject fj	= (JSONObject) ObjJSON;
		if (fj==null) return FindJob;
		hhForm.setPages(fj.get("page").toString(), fj.get("pages").toString());
		JSONArray items	= (JSONArray)fj.get("items");
		if (items==null) return FindJob;
		for (int ij=0;ij<items.size();ij++) {
			JSONObject job	= (JSONObject) items.get(ij);
			if (job==null) continue;
			JSONObject employer	= (JSONObject) job.get("employer");
			if (employer==null) continue;
			Job vacancy	= new Job(job.get("name").toString(),employer.get("name").toString());
			vacancy.setUrl(job.get("alternate_url").toString());
			JSONObject salary	= (JSONObject) job.get("salary");
			if (salary!=null) {
				StringBuffer buffer = new StringBuffer();
				
				Object from	= salary.get("from");
				if (from!=null) {
					buffer.append("от ");
					buffer.append(from.toString());
					buffer.append(" ");
				}
				Object to	= salary.get("to");
				if (to!=null) {
					buffer.append("до ");
					buffer.append(to.toString());
					buffer.append(" ");
				}
				Object currency	= salary.get("currency");
				if (currency!=null) {
					buffer.append(currency.toString());
					}
				vacancy.setSalary(buffer.toString());
			}
			FindJob.add(vacancy);
		}
		return FindJob;
	}
}
