package hh;

import java.util.ArrayList;

public class ProfArea {
	private String name	= null;
	private int id	= 0;
	private ArrayList<Prof> Prof	= null;
	
	ProfArea(String name, int id){
		this.name	= name;
		this.id		= id;
	}

	ProfArea(String name, int id, ArrayList<Prof> Prof){
		this.name	= name;
		this.id		= id;
		this.Prof	= Prof;
	}
	
    @Override
    public String toString() {
    	if (id<10) return "0"+id+" "+name;
    	else return id+" "+name;
    }
}
