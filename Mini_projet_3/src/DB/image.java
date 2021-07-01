package DB;


public class image {
	
	private String url;
	private String name;
	
	public image(String string,String n) {
		
		this.url=string ;
		this.name=n;
		
	}
	
	public String getUrl() {
		return this.url;
	}
	public String getName() {
		return this.name;
	}

}
