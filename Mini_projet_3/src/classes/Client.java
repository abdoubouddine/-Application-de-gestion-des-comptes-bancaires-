package classes;


public class Client extends Personne {

	private String CIN;
	static int code=0;
	private int num;
	private String img;
	
	public Client(String c,String n, String p,String i ) {
		super(n,p);
		this.CIN=c;
		code++;
		this.num=code;
		this.img=i;
		}
	
	public Client(String c) {
		this.CIN=c;
		}

	public String getName() {
		return super.getName();
	}
	public String getPrenom() {
		return super.getPrenom();
	}
	public String getCIN() {
		return this.CIN;
	}
	public String getImage() {
		return this.img;
	}
	public int getNum() {
		return this.num;
	}
	
	public void setName(String n) {
		super.setNom(n);
	}
	public void setPrenom(String p) {
		super.setPrenom(p);
	}
	public void setCIN(String c) {
		this.CIN=c;
	}
	public void setNum(int n) {
		this.num=n;
	}
	
	public void afficher() {
		super.afficher();
		System.out.println(". CIN du client est :"+CIN);
	}
	
}

