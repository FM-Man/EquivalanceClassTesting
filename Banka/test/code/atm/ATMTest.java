package code.atm;

import code.bank.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ATMTest {
    Profile testProfile = new Profile("Fahim Morshed", "Moghbazar");
    AtmCard ac = new AtmCard(testProfile,1234);
    ATM testAtm = new ATM();
    public ATMTest(){
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
        assertFalse(testAtm.hasEnoughMoney(testTransaction));
    }

    @Test
    public void hasEnoughMoneyTest_LessThanZero(){
        Transaction testTransaction = new Transaction(-1,ac);
        assertFalse(testAtm.hasEnoughMoney(testTransaction));
    }

    @Test
    public void hasEnoughMoneyTest_MoreThanCash(){
        Transaction testTransaction = new Transaction(10000,ac);
        assertFalse(testAtm.hasEnoughMoney(testTransaction));
    }
}
