package test.atm;

import code.atm.ATM;
import code.atm.AtmCard;
import code.atm.Transaction;
import code.bank.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ATM_Test {
    Profile testProfile = new Profile("Fahim Morshed", "Moghbazar");
    AtmCard ac = new AtmCard(testProfile,1234);
    ATM testAtm = new ATM();
    public ATM_Test(){
        testProfile.addCard(ac);
    }

    /*****************************************************/

    @Test
    public void hasEnoughMoneyTest_Valid(){
        Transaction testTransaction = new Transaction(100,ac);
        assertTrue(testAtm.hasEnoughMoney(testTransaction));
    }

    @Test
    public void hasEnoughMoneyTest_Zero(){
        Transaction testTransaction = new Transaction(0,ac);
        assertTrue(!testAtm.hasEnoughMoney(testTransaction));
    }

    @Test
    public void hasEnoughMoneyTest_LessThanZero(){
        Transaction testTransaction = new Transaction(-1,ac);
        assertTrue(!testAtm.hasEnoughMoney(testTransaction));
    }

    @Test
    public void hasEnoughMoneyTest_MoreThanCash(){
        Transaction testTransaction = new Transaction(10000,ac);
        assertTrue(!testAtm.hasEnoughMoney(testTransaction));
    }
}
