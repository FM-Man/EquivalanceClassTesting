package Bank;

public class Profile {

    private final String number;

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
