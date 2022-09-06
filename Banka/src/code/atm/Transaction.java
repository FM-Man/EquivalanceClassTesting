package code.atm;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Transaction  implements Serializable {
    private double amount;
    private AtmCard card;
    private String time;

    public Transaction(double amount, AtmCard card) {
        this.amount = amount;
        this.card = card;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        time = dateFormat.format(date);
    }

    public double getAmount() {
        return amount;
    }
    public String toString(){
        return time+"      "+card.getNumber()+"      "+amount;
    }
}
