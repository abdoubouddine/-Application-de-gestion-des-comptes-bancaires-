package classes;

import java.util.Iterator;
import java.util.TreeSet;

public class Agence {

	private String ville;
	private String nom;
	static int nbrAgences=0;
	private int num;
	private TreeSet<Employe> listeEmployes;
	
	public Agence() {
		
	}
	public Agence(String v, String n) {
		nbrAgences++;
		this.num=nbrAgences;
		this.ville=v;
		this.nom=n;
		listeEmployes = new TreeSet<Employe>();
	}
	
	public String getVille() {
		return this.ville;
	}
	public String getNom() {
		return this.nom;
	}
	public int getNum() {
		return this.num;
	}
	public String listeEmployes(int n){
        Iterator<Employe> i =listeEmployes.iterator();
        int index=0;
        String resultat="";
        while (index<n && i.hasNext()) {
            resultat=resultat+i.next().toString()+"\n";
            index=index+1;
        }
        return resultat;
    }
	
	public void setNum(int n) {
		this.num=n;
	}

}
