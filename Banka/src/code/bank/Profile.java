package code.bank;

import code.atm.AtmCard;
import code.atm.Transaction;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {

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
        System.out.println("Account Number: "+number);
    }
//    public void getCard(Bank java.bank){
//        for (int i=0;i<10;i++){
//            System.out.println(java.bank.cardNumberGenerator(this));
//        }
//    }
    public String getNumber() {
        return number;
    }


    public void addCard(AtmCard atmCard){
        atmCards.add(atmCard);
    }
    public AtmCard atmCards(int index){return atmCards.get(index);}
    public AtmCard findCard(String number){
        for (AtmCard atmCard:atmCards){
            if(atmCard.getNumber().equals(number)) return atmCard;
        }
        return null;
    }

    public boolean getAndAddCard(int pin){
        if(pin>=1000&&pin<10000){
            addCard(new AtmCard(this,pin));
            return true;
        }
        else return false;
    }

    public ArrayList<Transaction> allTransactions(){
        ArrayList<Transaction> send = new ArrayList<>();
        for (AtmCard card: atmCards){
            send.addAll(card.getTransactions());
        }
        return send;
    }

    public boolean requestTransactionApproval(Transaction t){
        return t.getAmount() >= 100 && balance >= t.getAmount() + 100;
    }

    public boolean withdraw(double amount){
        if(amount>=100){
            this.balance -= amount;
            return true;
        }
        return false;
    }
    public boolean deposit(double amount){
        if(amount>=100){
            this.balance += amount;
            return true;
        }
        return false;
    }

    public double getBalance(){
        return balance;
    }
//    public String printCards(){
//        String s = "";
//        for ("C")
//    }

    public String toString(){
        return  "Name     : "+name+
                "\nAddress  : "+address+
                "\nBalance  : "+balance;
    }

}
