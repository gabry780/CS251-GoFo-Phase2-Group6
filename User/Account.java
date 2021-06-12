package User;

import Exceptions.*;
import System.Application;
import System.Verifier;
import System.Ewallet;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Account 
{

    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    static private int counter = 0;
    private int id;
    private Ewallet wallet;
    private Verifier verifier;

    public Account(Verifier verifier) 
    {
        this.userName = "";
        this.password = "";
        this.email = "";
        this.phoneNumber = "";
        this.id = counter;
        this.counter++;
        this.verifier = verifier;
        wallet = new Ewallet();
    }

    public String getUserName() 
    {
        return this.userName;
    }


    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

   
    public String getPassword() 
    {
        return this.password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getEmail() 
    {
        return this.email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

   
    public String getPhoneNumber() 
    {
        return this.phoneNumber;
    }

    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public int getId() 
    {
        return this.id;
    }

   
    public Ewallet getWallet() 
    {
        return this.wallet;
    }

    public Verifier getVerifier() 
    {
        return verifier;
    }

   
    public void register(String username, String password, String email, String phoneNumber)
    {
        this.userName = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void updateProfile(ArrayList<Account> accounts) throws InvalidEmail, InvalidPassword, InvalidNumber, InvalidAddress 
    {
        Scanner input = new Scanner(System.in);
        boolean isOwner = (this instanceof PlaygroundOwner);
        int maxLimit = (isOwner) ? 4 : 3;
        System.out.println("===========================");
        System.out.println("What would you like to update: ");
        System.out.println("===========================");
        System.out.println("[0] Cancel");
        System.out.println("[1] Email");
        System.out.println("[2] Password");
        System.out.println("[3] Phone Number");
        if (isOwner) 
        {
            System.out.println("[4] Address");
        }
        System.out.println("Please enter a number: ");
        int userChoice = getVerifier().getUserChoice(0, maxLimit);
        if (userChoice == 1) 
        {
            System.out.println("Current email address: " + getEmail());
            System.out.println("Please enter an email: ");
            String newEmail = input.nextLine().toLowerCase();
            boolean duplicateEmail = false;
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getEmail().equals(newEmail)) 
                {
                    duplicateEmail = true;
                    break;
                }
            }
            if ((!verifier.verifyEmail(newEmail)) || duplicateEmail) throw new InvalidEmail();
            else setEmail(newEmail);
            System.out.println("New email address: " + getEmail());
        } else if (userChoice == 2)
        {
            System.out.println("Current password: " + getPassword());
            System.out.println("Please enter a new password: ");
            String newPassword = input.nextLine();
            if (password.length() < 6) throw new InvalidPassword();
            else setPassword(newPassword);
        } else if (userChoice == 3) 
        {
            System.out.println("Current phone number: " + getPhoneNumber());
            System.out.println("Please enter a new phone number: ");
            String newNumber = input.nextLine();
            if (!verifier.verifyNumber(newNumber)) throw new InvalidNumber();
            else setPhoneNumber(newNumber);
            System.out.println("New phone number: " + getPhoneNumber());
        } else if (userChoice == 4) 
        {
            System.out.println("Current address: " + ((PlaygroundOwner) this).getAddress());
            System.out.println("Please enter a new address: ");
            String newAddress = input.nextLine();
            if (newAddress.length() < 1) throw new InvalidAddress();
            else ((PlaygroundOwner) this).setAddress(newAddress);
        }
    }

    public void checkEWallet() 
    {
        System.out.println("===========================");
        System.out.println(getUserName() + "'s e-wallet balance: " + getWallet().getBalance() + " EGP");
        System.out.println("===========================");
    }

}
