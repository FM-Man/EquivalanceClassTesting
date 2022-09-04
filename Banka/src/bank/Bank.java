package bank;

import atm.ATM;
import Data.SaveData;

import java.io.Serializable;
import java.util.ArrayList;

public class Bank  implements Serializable {
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
//    private static void setInstance(Bank bank){
//        instance = bank;
//    }
    private Bank() {
        this.atms = new ArrayList<>();
        this.profiles = new ArrayList<>();
    }

    public ATM atms(int index) {
        if(index>=atms.size()) return null;
        else if(index<0) return null;
        else return atms.get(index);
    }
//    public void setAtm(int index,ATM atm) {
//        atms.set(index,atm);
//    }
    public void addATM (ATM atm){
        atms.add(atm);
    }
    public Profile profiles(int index) {
        return profiles.get(index);
    }
//    public void setProfile(int index,Profile profile) {
//        this.profiles.set(index,profile);
//    }
    public void addProfile(Profile p){
        profiles.add(p);
    }
    public Profile findProfile(String accountNumber){
        for (Profile p:profiles){
            if(p.getNumber().equals(accountNumber)) return p;
        }
        return null;
    }
    public String printATM(){
        String string = "";
        for (int i=0;i<atms.size();i++){
            string+=(i+1)+". atm"+i+"\n";
        }
        return string;
    }

    public String accountNumberGenerator(){
        accountLog++;
        StringBuilder ret = new StringBuilder();
        int tempAccNo = accountLog;
        for (int i=0; i<4;i++){
            char c = (char) (tempAccNo%5 +(int) 'A');
            tempAccNo/=5;
            ret.append(c);
        }
        ret.reverse();
        return ret.toString();
    }
    public String cardNumberGenerator(Profile p){
        cardNo++;
        StringBuilder ret = new StringBuilder();
        int tempCardNo = cardNo;
        for (int i=0; i<4;i++){
            int val = tempCardNo%9;
            char c;
            c = (char) (val +(int) '1');
            tempCardNo/=9;
            ret.append(c);
        }
        ret.reverse();
        StringBuilder sb = new StringBuilder(p.getNumber());
        sb.append(ret);
        int val=0;
        for(int i=0;i<8;i++){
            val+=sb.toString().toCharArray()[i];
        }
        sb.append(val % 10);
        return sb.toString();
    }


    public SaveData sendData(){
        return new SaveData(profiles,atms);
    }
}
