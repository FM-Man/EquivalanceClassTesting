import Bank.Bank;
import Bank.Profile;

public class Main {
    public static void main(String[] args) {
        Bank b = new Bank();
        for (int i=0;i<100;i++){
            Profile p= new Profile(b);
            p.getCard(b);
        }
    }
}