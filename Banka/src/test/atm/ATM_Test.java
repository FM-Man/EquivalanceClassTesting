package test.atm;

import atm.ATM;
import atm.AtmCard;
import atm.Transaction;
import bank.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ATM_Test {
    Profile testProfile = new Profile("Fahim Morshed", "Moghbazar");
    AtmCard ac = new AtmCard(testProfile,1234);
    ATM testAtm = new ATM();
    public ATM_Test(){
        testProfile.addCard(ac);
    }

    @Test
    public void hasEnoughMoneyTestValid(){
        Transaction testTransaction = new Transaction(100,ac);
        assertTrue(testAtm.hasEnoughMoney(testTransaction));
    }
    @Test
    public void hasEnoughMoneyTestLessThanEqualZero1(){
        Transaction testTransaction = new Transaction(0,ac);
        assertTrue(!testAtm.hasEnoughMoney(testTransaction));
    }
    @Test
    public void hasEnoughMoneyTestLessThanEqualZero2(){
        Transaction testTransaction = new Transaction(-1,ac);
        assertTrue(!testAtm.hasEnoughMoney(testTransaction));
    }
    @Test
    public void hasEnoughMoneyTestMoreThanCash(){
        Transaction testTransaction = new Transaction(10000,ac);
        assertTrue(!testAtm.hasEnoughMoney(testTransaction));
    }
}
