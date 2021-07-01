package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Op�ration {

	private final double montant;
    private final String dateOperation;
    private final String libelle;

    public Op�ration(double montant, String libelle) {
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
        return "OperationBancaire{montant=" + montant + ", le "+ dateOperation + ", intitul�e" + libelle + '}';
    }

    public int compareTo(Op�ration o) {
        int c=o.dateOperation.compareTo(this.dateOperation);
        if (c==0) return o.libelle.compareTo(this.libelle);
        else return c;
    }
    
}



