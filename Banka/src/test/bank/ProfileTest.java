package test.bank;

import atm.ATM;
import atm.AtmCard;
import bank.Bank;
import bank.Profile;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {
    private Profile testProfile;
    private ATM testATM;
    private AtmCard testCard;
    public ProfileTest(){
        testATM = new ATM();
        Bank.getInstance().addATM(testATM);
        testProfile = new Profile("name", "address");
        testProfile.deposit(1000);
        testCard = new AtmCard(testProfile,1234);
        testProfile.addCard(testCard);
        Bank.getInstance().addProfile(testProfile);
    }
    @Test
    public void getAndAddCardTest_pin3Digit(){
        assertFalse(testProfile.getAndAddCard(500));
    }
}
