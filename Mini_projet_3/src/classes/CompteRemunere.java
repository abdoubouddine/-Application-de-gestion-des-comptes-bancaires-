package classes;

public class CompteRemunere extends Compte {

	 private double tauxInterets;
	    
	    public CompteRemunere(Client proprietaire,double taux,double s,int a) {
	        super(proprietaire,s,a);
	        this.tauxInterets=taux;
	    }

	    public double getTauxInterets() {
	        return this.tauxInterets;
	    }
	    public long getNumeroCompte() {
	    	return super.getNumeroCompte();
	    }
	    double calculInterets() {
	        solde=solde+solde*tauxInterets;
	        return solde;
	    }
	    public double depot(double montant,String libelle) {
	    	return super.depot(montant, libelle);
	    }
	    public double retrait(double montant,String libelle) throws BancaireException {
	    	return super.retrait(montant, libelle);
	    }
	    public String getClasse() {
	    	return "Remunere";
	    }
	    public String listeOperations() {
	    	return super.listeOperations();
	    }
	}
