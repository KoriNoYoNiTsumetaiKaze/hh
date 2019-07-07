package hh;

public class Job {
	private String employerName	= null;
	private String name			= null;
	private String url			= null;
	private String salary		= null;
	private String address		= null;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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
