package test.bank;


import atm.ATM;
import atm.AtmCard;
import bank.Bank;
import bank.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {
    private Profile testProfile;
    private ATM testATM;
    private AtmCard testCard;
    public BankTest(){
        testATM = new ATM();
        Bank.getInstance().addATM(testATM);
        testProfile = new Profile("name", "address");
        testProfile.deposit(1000);
        testCard = new AtmCard(testProfile,1234);
        testProfile.addCard(testCard);
        Bank.getInstance().addProfile(testProfile);
    }

    @Test
    public void getInstanceTest(){
        assertTrue(Bank.getInstance()!=null);
    }

    @Test
    public void findProfile_wrongAccountNumber(){
        assertEquals(null,Bank.getInstance().findProfile("1234"));
    }

    @Test
    public void findProfile_ValidAccountNumber(){
        assertEquals(testProfile.toString(),Bank.getInstance().findProfile("AAAD").toString());
    }

    @Test
    public void accountNumberGeneratorTest(){
        assertTrue(Bank.getInstance().accountNumberGenerator().matches("[ABCDE]{4}"));
    }

    @Test
    public void cardNumberGeneratorTest_MatchingFormat(){
        assertTrue(Bank.getInstance().cardNumberGenerator(testProfile).matches("[ABCDE]{4}[1-9]{4}[0-9]"));
    }

    @Test
    public void cardNumberGeneratorTest_MatchingAccount(){
        assertEquals(
                Bank.getInstance()
                .cardNumberGenerator(testProfile)
                .substring(0, 4),
                testProfile.getNumber()
        );
    }


}
