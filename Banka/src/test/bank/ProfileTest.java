package test.bank;

import code.atm.ATM;
import code.atm.AtmCard;
import code.atm.Transaction;
import code.bank.Bank;
import code.bank.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {
    private Profile testProfile;
    private ATM testATM;
    private AtmCard testCard;
    public ProfileTest(){
        testATM = new ATM();
        Bank.getInstance().addATM(testATM);
        testProfile = new Profile("name", "address");
        testCard = new AtmCard(testProfile,1234);
        testProfile.addCard(testCard);
        Bank.getInstance().addProfile(testProfile);
    }

    /****************************************************************/

    @Test
    public void getAndAddCardTest_pin3Digit(){
        assertFalse(testProfile.getAndAddCard(500));
    }

    @Test
    public void getAndAddCardTest_pin5Digit(){
        assertFalse(testProfile.getAndAddCard(90500));
    }

    @Test
    public void getAndAddCardTest_pin1000(){
        assertTrue(testProfile.getAndAddCard(1000));
    }

    @Test
    public void getAndAddCardTest_pin9999(){
        assertTrue(testProfile.getAndAddCard(9999));
    }

    @Test
    public void getAndAddCardTest_pin999(){
        assertFalse(testProfile.getAndAddCard(999));
    }

    @Test
    public void getAndAddCardTest_pin10000(){
        assertFalse(testProfile.getAndAddCard(10000));
    }

    @Test
    public void getAndAddCardTest_pin4Digit(){
        assertTrue(testProfile.getAndAddCard(1234));
    }

    /******************************************************************/

    @Test
    public void findCardTest_CardExists(){
        assertEquals(testProfile.findCard(testCard.getNumber()), testCard);
    }

    @Test
    public void findCardTest_CardDoesNotExists(){
        assertNull(testProfile.findCard("DAAD11142"));
    }

    /************************************************************/

    @Test
    public void requestTransactionApprovalTest_validAmount(){
        testProfile.deposit(1000);
        Transaction t = new Transaction(200,testCard);
        assertTrue(testProfile.requestTransactionApproval(t));
    }

    @Test
    public void requestTransactionApprovalTest_invalidPositiveAmount(){
        testProfile.deposit(1000);
        Transaction t = new Transaction(50,testCard);
        assertFalse(testProfile.requestTransactionApproval(t));
    }

    @Test
    public void requestTransactionApprovalTest_amountMoreThanBalance(){
        testProfile.deposit(1000);
        Transaction t = new Transaction(1150,testCard);
        assertFalse(testProfile.requestTransactionApproval(t));
    }

    @Test
    public void requestTransactionApprovalTest_amountMoreThanLimit(){
        testProfile.deposit(1000);
        Transaction t = new Transaction(1050,testCard);
        assertFalse(testProfile.requestTransactionApproval(t));
    }

    @Test
    public void requestTransactionApprovalTest_amountLessThanZero(){
        testProfile.deposit(1000);
        Transaction t = new Transaction(-50,testCard);
        assertFalse(testProfile.requestTransactionApproval(t));
    }

    /***************************************************/

    @Test
    public void withdrawTest_Negative(){
        assertFalse(testProfile.withdraw(-10));
    }

    @Test
    public void withdrawTest_valid(){
        assertTrue(testProfile.withdraw(110));
    }

    /*************************************************/

    @Test
    public void depositTest_Negative(){
        assertFalse(testProfile.deposit(-10));
    }

    @Test
    public void depositTest_valid(){
        assertTrue(testProfile.deposit(110));
    }

}
