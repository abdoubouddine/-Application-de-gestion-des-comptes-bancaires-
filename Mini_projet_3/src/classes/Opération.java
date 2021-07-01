package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Opération {

	private final double montant;
    private final String dateOperation;
    private final String libelle;

    public Opération(double montant, String libelle) {
        this.montant = montant;
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        this.dateOperation = format.format(Calendar.getInstance().getTime());
        this.libelle = libelle;
    }

    public double getMontant() {
        return montant;
    }

    public String getDateOperation() {
        return dateOperation;
    }

    public String getLibelle() {
        return libelle;
    }

    public String toString() {
        return "OperationBancaire{montant=" + montant + ", le "+ dateOperation + ", intitulée" + libelle + '}';
    }

    public int compareTo(Opération o) {
        int c=o.dateOperation.compareTo(this.dateOperation);
        if (c==0) return o.libelle.compareTo(this.libelle);
        else return c;
    }
    
}



