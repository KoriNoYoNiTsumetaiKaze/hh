package hh;

public class Job {
	private static String employerName	= null;
	private static String name			= null;
	
	Job(String name,String employerName){
		this.employerName	= employerName;
		this.name			= name;
	}

	public static String getEmployerName() {
		return employerName;
	}

	public static String getName() {
		return name;
	}
	
    @Override
    public String toString() {
    	return name+" "+employerName;
    }		
}
