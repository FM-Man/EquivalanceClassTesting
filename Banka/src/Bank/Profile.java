package Bank;

import ATM.AtmCard;

import java.util.ArrayList;

public class Profile {

    private final String number;
    private String name;
    private String address;
    private ArrayList<AtmCard> atmCards = new ArrayList<>();

    public Profile(Bank bank) {
        this.number = bank.accountNumberGenerator();
    }
    public void getCard(Bank bank){
        for (int i=0;i<10;i++){
            System.out.println(bank.cardNumberGenerator(this));
        }
    }
    public String getNumber() {
        return number;
    }

}
