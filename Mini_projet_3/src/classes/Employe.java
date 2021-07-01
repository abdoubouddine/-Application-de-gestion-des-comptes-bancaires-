package classes;

public class Employe extends Personne {

	private Date dateEmbauche;
	
	public Employe(String n, String p,Date d) {
		super(n,p);
		this.dateEmbauche=d;
	}
	
	public String getName() {
		return super.getName();
	}
	public String getPrenom() {
		return super.getPrenom();
	}
	public String getDate() {
		return dateEmbauche.toString();
	}
	
	public void setNom(String n) {
		super.setNom(n);
	}
	public void setPrenom(String p) {
		super.setPrenom(p);
	}
	public void setDate(int d,int m, int y) {
		dateEmbauche.setDate(d, m, y);
	}
	
	public String toString() {
        return "Employé{nom :" + this.getName() + ", prénom : "+ this.getPrenom() + ", date d'Embauche : " + this.getDate() + '}';
    }

}
