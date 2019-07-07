package hh;

public class Job {
	private String employerName	= null;
	private String name			= null;
	
	Job(String name,String employerName){
		this.employerName	= employerName;
		this.name			= name;
	}

	public String getEmployerName() {
		return employerName;
	}

	public String getName() {
		return name;
	}
	
    @Override
    public String toString() {
    	return name+" "+employerName;
    }		
}
