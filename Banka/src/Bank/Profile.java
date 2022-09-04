package Bank;

import ATM.AtmCard;
import ATM.Transaction;

import java.util.ArrayList;

public class Profile {

    private final String number;
    private String name;
    private String address;
    private ArrayList<AtmCard> atmCards;
    private double balance;

    public Profile(String name, String address) {
        number = Bank.getInstance().accountNumberGenerator();
        this.name = name;
        this.address = address;
        atmCards = new ArrayList<>();
        balance = 100.0;
    }
    public void getCard(Bank bank){
        for (int i=0;i<10;i++){
            System.out.println(bank.cardNumberGenerator(this));
        }
    }
    public String getNumber() {
        return number;
    }


    public void addCard(AtmCard atmCard){
        atmCards.add(atmCard);
    }
    public AtmCard atmCards(int index){return atmCards.get(index);}
    public ArrayList<Transaction> allTransactions(){
        ArrayList<Transaction> send = new ArrayList<>();
        for (AtmCard card: atmCards){
            send.addAll(card.getTransactions());
        }
        return send;
    }
    public void requestTransactionApproval(Transaction t){
        if(balance>=t.getAmount()+100)
    }
    public void withdraw(double amount){this.balance-=amount;}
    public void deposit(double amount){this.balance+=amount;}

    public String toString(){
        return  "Name     : "+name+
                "Address  : "+address+
                "Balance  : "+balance;
    }

}
