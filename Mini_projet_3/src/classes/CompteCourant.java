package classes;

public class CompteCourant extends Compte {

private double montantDecouvertAutorise;
    
    public CompteCourant(Client proprietaire, double decouvert,double s,int a) {
        super(proprietaire,s,a);
        montantDecouvertAutorise=decouvert;
    }

    public double getMontantDecouvertAutorise() {
        return montantDecouvertAutorise;
    }
    public double getSolde() {return super.getSolde();}
    public long getNumeroCompte() {
    	return super.getNumeroCompte();
    }
    
   
    
    @Override
    public double retrait(double montant,String libelle) throws BancaireException {
        if (montant> this.solde+this.montantDecouvertAutorise) throw new BancaireException("Pas assez d'argent sur le compte");
        else this.solde-=montant;
        //super.getListeOperations().add(new Opération(montant,libelle));
        return solde;
    }
    
    public String listeOperations() {
    	return super.listeOperations();
    }
}

