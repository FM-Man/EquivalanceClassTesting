package Bank;

import ATM.ATM;

import java.util.ArrayList;

public class Bank {
    private int accountLog =0;
    private int cardNo = 0;

    private ArrayList<ATM> atms = new ArrayList<ATM>();
    private ArrayList<Profile> profiles = new ArrayList<>();


    private static Bank instance = null;
    public static Bank getInstance(){
        if (instance==null)
            instance = new Bank();
        return instance;
    }
    private Bank() {
        this.atms = new ArrayList<>();
        this.profiles = new ArrayList<>();
    }

    public ATM atms(int index) {
        return atms.get(index);
    }

    public void setAtm(int index,ATM atm) {
        atms.set(index,atm);
    }

    public void addATM (ATM atm){
        atms.add(atm);
    }

    public Profile profiles(int index) {
        return profiles.get(index);
    }

    public void setProfile(int index,Profile profile) {
        this.profiles.set(index,profile);
    }

    public void addProfile(Profile p){
        profiles.add(p);
    }

    public String accountNumberGenerator(){
        accountLog++;
        String ret = "";
        int tempAccNo = accountLog;
        for (int i=0; i<4;i++){
            char c = (char) (tempAccNo%26 +(int) 'A');
            tempAccNo/=26;
            ret+=c;
        }
        return ret;
    }
    public String cardNumberGenerator(Profile p){
        cardNo++;
        String ret = p.getNumber();
        int tempCardNo = cardNo;
        for (int i=0; i<4;i++){
            int val = tempCardNo%35;
            char c;
            if(val <9)
                c = (char) (val +(int) '1');
            else
                c = (char) (val-9 +(int) 'a');
            tempCardNo/=35;
            ret+=c;
        }
        int val=0;
        for(int i=0;i<8;i++){
            val+=(int) ret.toCharArray()[i];
        }
        ret+=val%100;
        return ret;
    }
}
