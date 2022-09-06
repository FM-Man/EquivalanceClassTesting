package code.atm;

import code.bank.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtmCardTest {
    public Profile testProfile;
    public AtmCard testCard;
    public ATM testATM;
    public AtmCardTest(){
        testATM = new ATM();
        testProfile = new Profile("A","B");
        testCard = new AtmCard(testProfile,1234);
        testProfile.addCard(testCard);
        testProfile.deposit(1000);
    }

    /***************************************************/

    @Test
    public void swipeTest_NegativeAmount(){
        assertEquals("ATM processing error. Transaction Unsuccessful",testCard.swipe(   -1,testATM,1234));
    }

    @Test
    public void swipeTest_AmountOverBalance(){
        assertEquals("Not sufficient Balance",testCard.swipe(1200,testATM,1234));
    }

    @Test
    public void swipeTest_ValidAmount(){
        assertEquals("Transaction Successful",testCard.swipe(100  ,testATM,1234));
    }

    @Test
    public void swipeTest_WrongPIN(){
        assertEquals("PIN does not match!",testCard.swipe(100  ,testATM,1235));
    }

    @Test
    public void swipeTest_CorrectPIN(){
        assertEquals("Transaction Successful",testCard.swipe(100  ,testATM,1234));
    }

    @Test
    public void swipeTest_AmountOverLimit(){
        testProfile.deposit(10000);
        assertEquals("ATM processing error. Transaction Unsuccessful",testCard.swipe(10000  ,testATM,1234));
    }
}
