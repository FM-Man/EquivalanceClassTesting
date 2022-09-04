package atm;

import java.io.Serializable;

public class ATM implements Serializable {
    private String id;
    private double money;

    public boolean hasEnoughMoney(Transaction t){
        return t.getAmount() <= money + 500;
    }
}
