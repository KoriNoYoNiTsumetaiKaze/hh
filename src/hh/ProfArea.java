package hh;

import java.util.ArrayList;

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
	
    @Override
    public String toString() {
    	if (id<10) return "0"+id+" "+name;
    	else return id+" "+name;
    }
}
