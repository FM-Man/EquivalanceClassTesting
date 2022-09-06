package test.bank;


import code.atm.ATM;
import code.atm.AtmCard;
import code.bank.Bank;
import code.bank.Profile;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private final Profile testProfile;

    public BankTest(){
        ATM testATM = new ATM();
        Bank.getInstance().addATM(testATM);
        testProfile = new Profile("name", "address");
        testProfile.deposit(1000);
        AtmCard testCard = new AtmCard(testProfile, 1234);
        testProfile.addCard(testCard);
        Bank.getInstance().addProfile(testProfile);
    }


    /*******************************************************/
    @Test
    public void getInstanceTest(){
        assertNotNull(Bank.getInstance());
    }

    /*******************************************************/
    @Test
    public void findProfile_wrongAccountNumber(){
        assertNull(Bank.getInstance().findProfile("1234"));
    }

    @Test
    public void findProfile_ValidAccountNumber(){
        assertEquals(testProfile.toString(),Bank.getInstance().findProfile("AAAD").toString());
    }

    /********************************************************/

    @Test
    public void accountNumberGeneratorTest_Valid(){
        Pattern p = Pattern.compile("^[ABCDE]{4}$");
        Matcher m = p.matcher(Bank.getInstance().accountNumberGenerator());
        assertTrue(m.find());
    }

    @Test
    public void accountNumberGeneratorTest_NotContainsNumber(){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(Bank.getInstance().accountNumberGenerator());
        assertFalse(m.find());
    }

    @Test
    public void accountNumberGeneratorTest_NotContainsLowercase(){
        Pattern p = Pattern.compile("[a-z]+");
        Matcher m = p.matcher(Bank.getInstance().accountNumberGenerator());
        assertFalse(m.find());
    }

    @Test
    public void accountNumberGeneratorTest_ContainsAnythingButA_E(){
        Pattern p = Pattern.compile("[F-Z]+");
        Matcher m = p.matcher(Bank.getInstance().accountNumberGenerator());
        assertFalse(m.find());
    }

    /**************************************************************/

    @Test
    public void cardNumberGeneratorTest_MatchingFormat(){
        Pattern p = Pattern.compile("^[A-E]{4}[1-9]{4}[0-9]$");
        Matcher m = p.matcher(Bank.getInstance().cardNumberGenerator(testProfile));
        assertTrue(m.find());
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

    @Test
    public void cardNumberGeneratorTest_MatchingCheckSumBit(){
        String cardNo = Bank.getInstance().cardNumberGenerator(testProfile);
        int lastByte = Integer.parseInt(cardNo.charAt(8)+"");
        int charSum = 0;
        for(int i=0;i<8;i++){
            charSum+=cardNo.charAt(i);
        }
        assertEquals(charSum%10, lastByte);
    }

    @Test
    public void cardNumberGeneratorTest_NotContainingSmallLetter(){
        Pattern p = Pattern.compile("[a-z]+");
        Matcher m = p.matcher(Bank.getInstance().cardNumberGenerator(testProfile));
        assertFalse(m.find());
    }

    @Test
    public void cardNumberGeneratorTest_NotContainingNumberInFirstFour(){
        Pattern p = Pattern.compile("^[^0-9]{4}");
        Matcher m = p.matcher(Bank.getInstance().cardNumberGenerator(testProfile));
        assertTrue(m.find());
    }

    @Test
    public void cardNumberGeneratorTest_NotContainingF_Z(){
        Pattern p = Pattern.compile("[^F-Z]+");
        Matcher m = p.matcher(Bank.getInstance().cardNumberGenerator(testProfile));
        assertTrue(m.find());
    }

    @Test
    public void cardNumberGeneratorTest_NotContainingAlphabetInLast5(){
        Pattern p = Pattern.compile("[a-zA-Z]{5}$");
        Matcher m = p.matcher(Bank.getInstance().cardNumberGenerator(testProfile));
        assertFalse(m.find());
    }

}
