package bank;

import atm.AtmCard;
import atm.Transaction;

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
    public boolean addCard(int pin){
        if(pin>=1000&&pin<10000){
            addCard(new AtmCard(this,pin));
            return true;
        }
        else return false;
    }
    public AtmCard atmCards(int index){return atmCards.get(index);}
    public ArrayList<Transaction> allTransactions(){
        ArrayList<Transaction> send = new ArrayList<>();
        for (AtmCard card: atmCards){
            send.addAll(card.getTransactions());
        }
        return send;
    }
    public boolean requestTransactionApproval(Transaction t){
        return balance >= t.getAmount() + 100;
    }
    public void withdraw(double amount){this.balance-=amount;}
    public void deposit(double amount){this.balance+=amount;}
    public AtmCard findCard(String number){
        for (AtmCard atmCard:atmCards){
            if(atmCard.getNumber().equals(number)) return atmCard;
        }
        return null;
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
