package code.atm;

import code.bank.Bank;
import code.bank.Profile;

import java.io.Serializable;
import java.util.ArrayList;

public class AtmCard implements Serializable {
    private String number;
    private ArrayList<Transaction> transactions;
    private final Profile owner;
    private int PIN;

    public AtmCard(Profile profile, int PIN){
        number = Bank.getInstance().cardNumberGenerator(profile);
        System.out.println("Card Number: "+number);
        this.owner = profile;
        transactions = new ArrayList<>();
        this.PIN = PIN;
    }

    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }
    public String getNumber(){return number;}
    private void approveTransaction(Transaction t){
        transactions.add(t);
        owner.withdraw(t.getAmount());
    }

    public String swipe(double amount, ATM atm, int PIN){
        if(PIN!=this.PIN){
            return "PIN does not match!";
        }
        else {
            Transaction t = new Transaction(amount,this);
            if(owner.requestTransactionApproval(t)){
                if(atm.hasEnoughMoney(t)) {
                    approveTransaction(t);
                    return "Transaction Successful";
                }
                else return "ATM processing error. Transaction Unsuccessful";
            }
            else {
                return "Not sufficient Balance";
            }
        }
    }

}
