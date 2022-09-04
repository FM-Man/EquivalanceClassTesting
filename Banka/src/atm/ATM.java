package atm;

import java.io.Serializable;

public class ATM implements Serializable {
    private String id;
    private double money;

    public ATM(){
        money = 10000;
    }

    public boolean hasEnoughMoney(Transaction t){
        if(t.getAmount()<=0) return false;
        return t.getAmount() <= money - 500;
    }
}
