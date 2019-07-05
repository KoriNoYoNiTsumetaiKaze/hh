package hh;

public class Prof {
	private String name			= null;
	private double id			= 0.000;
	private boolean laboring	= false;

	Prof(String name, double id, boolean laboring){
		this.name		= name;
		this.id			= id;
		this.laboring	= laboring;
	}
	
    @Override
    public String toString() {
    	return name;
    }	
}
