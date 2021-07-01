package classes;

import java.util.*;

public class Compte {

	static long nbComptes=0;

    private final Client proprietaire;
    protected double solde;
    private long numeroCompte;
    protected TreeSet<Opération> listeOperations;
    protected Agence agence;

    public Compte (Client proprietaire,double s,int a) {
    	this.proprietaire=proprietaire;	
    	nbComptes++;
    	this.numeroCompte=nbComptes;
    	this.listeOperations=new TreeSet<Opération>();
    	this.solde=s;
    	this.agence=new Agence();
    	this.agence.setNum(a);
    }
    
    public Agence getAgence() {
    	return this.agence;
    }
    public double getSolde() {return solde;}	  

    public long getNumeroCompte() {return numeroCompte;}

    public Client getProprietaire() {return proprietaire;}
    
    public TreeSet<Opération> getListeOperations(){
    	return this.listeOperations;
    }
    
   public void setNum(int n) {
	   this.numeroCompte=n;
   }
   public void setSolde(double s) {
	   this.solde=s;
   }

    public String toString() {
            return "Compte n°"+numeroCompte+" "+
                    proprietaire.getName()+proprietaire.getPrenom()+" dispose de "+
                    solde+"Euros";
            }

    public double depot(double montant,String libelle) {
            solde+=montant;
            //listeOperations.add(new Opération(montant,libelle));
            return solde;}		

    public double retrait(double montant,String libelle) throws BancaireException {
        if (montant> solde) throw new BancaireException("Pas assez d'argent sur le compte");
            else solde=solde-montant;
            //listeOperations.add(new Opération(-montant,libelle));
        return solde;
    }
    
    public String listeOperations(){
        Iterator<Opération> i =listeOperations.iterator();
        String resultat="";
        while (i.hasNext()) {
            resultat=resultat+i.next().toString()+"\n";
        }
        return resultat;
    }
    public String getClasse() {
    	return "";
    }
		
}
