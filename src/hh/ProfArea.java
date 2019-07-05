package hh;

import java.util.ArrayList;
import java.util.Comparator;

public class ProfArea {
	private String name	= null;
	private int id	= 0;
	private ArrayList<Prof> prof	= null;
	
	ProfArea(String name, int id){
		this.name	= name;
		this.id		= id;
	}

	ProfArea(String name, int id, ArrayList<Prof> prof){
		this.name	= name;
		this.id		= id;
		this.prof	= prof;
	}
	
	public void addProf(Prof p) {
		if (p==null) return;
		if (prof==null) prof	= new ArrayList<Prof>();
		prof.add(p);
	}
	
	public ArrayList<Prof> getProf(){
		return prof;
	}
	
	public void sortProf() {
		prof.sort(new Comparator<Prof>(){
			public int compare(Prof o1, Prof o2) {
				return o1.toString().compareTo(o2.toString());
			}});
	}
	
    @Override
    public String toString() {
    	return name;
    }
}
