package classes;

import java.util.*;
import java.text.*;
		
@SuppressWarnings("serial")
public class Date extends GregorianCalendar{
	
	public Date() {
        super();
    }

    public Date(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }
   
    public String toString(String format) {
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.format(this.getTime());
    } 

    @Override
    public String toString() {
        return toString("dd mm YYYY");
    } 

    public void setDate(int d, int m, int y) {
    	new Date(d,m,y);
    }
}



	



