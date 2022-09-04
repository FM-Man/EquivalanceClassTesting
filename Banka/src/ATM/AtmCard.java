package ATM;

import Bank.Profile;

import java.util.ArrayList;

public class AtmCard {
    private ArrayList<Transaction> transactions;
    private final Profile owner;

    public AtmCard(Profile profile){
        this.owner = profile;
        transactions = new ArrayList<>();
    }

    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }
    public void approveTransaction(Transaction t){
        transactions.add(t);
        owner.withdraw(t.getAmount());
    }
}
