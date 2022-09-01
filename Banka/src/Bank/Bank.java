package Bank;

public class Bank {
    private int accountLog =0;
    private int cardNo = 0;

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
