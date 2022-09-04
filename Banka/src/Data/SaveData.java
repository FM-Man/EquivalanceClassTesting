package Data;

import atm.ATM;
import bank.Profile;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveData implements Serializable {
    public ArrayList<Profile> profiles;
    public ArrayList<ATM> atms;

    public SaveData(ArrayList<Profile> profiles, ArrayList<ATM> atms){
        this.profiles = profiles;
        this.atms = atms;
    }
}
