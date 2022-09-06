package code;

import code.atm.ATM;
import code.atm.AtmCard;
import code.atm.Transaction;
import code.bank.Profile;
import code.bank.Bank;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Profile p1 = new Profile("Fahim Morshed","Moghbazar");
        AtmCard c11= new AtmCard(p1,1234);
        p1.addCard(c11);
        AtmCard c12 = new AtmCard(p1, 1234);
        p1.addCard(c12);
        AtmCard c13 = new AtmCard(p1,1234);
        p1.addCard(c13);
        p1.deposit(1000);

        Profile p2 = new Profile("FM", "Barishal");
        AtmCard c21= new AtmCard(p2,1234);
        p2.addCard(c21);
        AtmCard c22 = new AtmCard(p2, 1234);
        p2.addCard(c22);
        AtmCard c23 = new AtmCard(p2,1234);
        p2.addCard(c23);
        p2.deposit(1000);

        Profile p3 = new Profile("IDK","IDK either");
        AtmCard c31= new AtmCard(p3,1234);
        p3.addCard(c31);
        AtmCard c32 = new AtmCard(p3, 1234);
        p3.addCard(c32);
        AtmCard c33 = new AtmCard(p3,1234);
        p3.addCard(c33);
        p3.deposit(1000);

        Bank.getInstance().addProfile(p1);
        Bank.getInstance().addProfile(p2);
        Bank.getInstance().addProfile(p3);

        ATM atm1 = new ATM();
        Bank.getInstance().addATM(atm1);
        ATM atm2 = new ATM();
        Bank.getInstance().addATM(atm2);
        ATM atm3 = new ATM();
        Bank.getInstance().addATM(atm3);




        String divider = "\n=====================================================================\n";
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print(
                    "=====================================================================\n" +
                    "1. Log In\n" +
                    "2. Create New Account\n" +
                    "3. Exit\n" +
                    "\n" +
                    "Enter your choice: "
            );
            int c = sc.nextInt();
            if(c==1){
                System.out.print(divider+"Enter Account Number: ");
                sc.nextLine();
                String c2 = sc.nextLine();
                Profile profile = Bank.getInstance().findProfile(c2);
                if(profile==null) {
                    System.out.println("Account Not Found.");
                }
                else {
                    int wrongCounter = 0;
                    while (true){
                        System.out.print("=========================================================================\n" +
                                         "1. Withdraw\n" +
                                         "2. Deposit\n" +
                                         "3. Issue New Card\n" +
                                         "4. See Information\n" +
                                         "5. Log Out\n" +
                                         "\n" +
                                         "Enter Your Choice: "
                        );
                        int c3 = sc.nextInt();
                        if(c3==1){
                            System.out.print(Bank.getInstance().printATM()+"\nEnter ATM: ");
                            int atmChoice = sc.nextInt();
                            ATM atm = Bank.getInstance().atms(atmChoice-1);
                            System.out.print("Enter Card No: ");
                            sc.nextLine();
                            String cardNumber = sc.nextLine();
                            System.out.println(cardNumber);
                            AtmCard card = profile.findCard(cardNumber);
                            System.out.print("Enter PIN    : ");
                            int pin = sc.nextInt();
                            System.out.print("Enter amount : ");
                            double amount = sc.nextDouble();
                            if(atm == null ||  card == null) {
                                System.out.println("wrong java.atm card");
                                wrongCounter++;
                                if(wrongCounter>=3) {
                                    System.out.println("Session Expired");
                                    break;
                                }
                            }
                            else{
                                String s = card.swipe(amount,atm,pin);
                                System.out.println(s);
                                if(s.equals("PIN does not match!")){
                                    wrongCounter++;
                                    if(wrongCounter>=3) {
                                        System.out.println("Session Expired");
                                        break;
                                    }
                                }
                            }
                        }
                        else if(c3==2){
                            System.out.print("Enter amount :");
                            double s = sc.nextDouble();
                            profile.deposit(s);
                            System.out.println("Deposit Successful");
                        }
                        else if(c3==3){
                            System.out.println("Enter new PIN: ");
                            int pin = sc.nextInt();
                            if(profile.getAndAddCard(pin)){
                                System.out.println("Card Creation Successful.");
                            }
                            else System.out.println("Invalid PIN");
                        }
                        else if(c3==4){
                            System.out.print("=========================================================================\n" +
                                             "1. Personal Information\n" +
                                             "2. Balance\n" +
                                             "3. Transaction History of a card\n" +
                                             "4. Full Transaction History\n" +
                                             "5. Log Out\n" +
                                             "\n" +
                                             "Enter Your Choice: "
                            );
                            sc.nextLine();
                            int c5 = sc.nextInt();
                            if(c5==1){
                                System.out.println(profile);
                            }
                            else if(c5==2){
                                System.out.println(profile.getBalance());
                            }
                            else if(c5==3){
                                System.out.println("Enter Card Number: ");
                                String card = sc.nextLine();
                                AtmCard ac= profile.findCard(card);
                                if(ac==null) {
                                    System.out.println("Wrong Input");
                                    wrongCounter++;
                                    if(wrongCounter>=3) {
                                        System.out.println("Session Expired");
                                        break;
                                    }
                                }
                                else {
                                    ArrayList<Transaction> transactions = ac.getTransactions();
                                    for (Transaction t: transactions){
                                        System.out.println(t);
                                    }
                                }
                            }
                            else if(c5==4){
                                ArrayList<Transaction> transactions = profile.allTransactions();
                                for (Transaction t: transactions){
                                    System.out.println(t);
                                }
                            }
                            else {break;}
                        }
                        else if(c3==5) break;
                    }
                }
            }
            else if(c==2){
                System.out.print("Enter your name: ");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.print("Enter Address  : ");
                String address = sc.nextLine();
                Profile p = new Profile(name,address);
                Bank.getInstance().addProfile(p);
            }
            else if(c==3)break;
        }

//        updateDatabase();
    }

    public static void updateDatabase() throws IOException {
//        SaveData data = Bank.getInstance().sendData();
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File( "Database.txt")));
        os.writeObject(Bank.getInstance());
        os.close();
    }
}