package classes;

public class Personne {

	protected String name;
	protected String prenom;
	
	public Personne() {
		
	}
	public Personne(String n, String p) {
		this.name=n;
		this.prenom=p;
	}
	
	protected String getName() {
		return name;
	}
	protected String getPrenom() {
		return prenom;
	}
	
	protected void setNom(String n) {
		this.name=n;
	}
	protected void setPrenom(String p) {
		this.prenom=p;
	}
	
	protected void afficher() {
		System.out.print("Le nom est :"+ name+ ". Le prénom est :"+prenom);
	}
}
