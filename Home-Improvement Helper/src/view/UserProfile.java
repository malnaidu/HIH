package view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * UserProfile is the credentials object created for a specific user, also contains
 * methods for exporting and importing all user credentials to profile.ser
 * commented/javadoc by brian
 * Class written by Travis
 */
@SuppressWarnings("serial")
public class UserProfile implements Serializable
{
    private String username;
    private String password;

    public UserProfile(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    /**
     * returns a string of the username and password
     * @return username and password string
     */
    @Override
	public String toString()
    {
        return username + " " + password;
    }

    /**
     * getter for username
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * getter for password
     * @return password for user
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Saves the user profiles created during session to profile.ser
     * @param userlist
     */
    public static void export (ArrayList<UserProfile> userlist) {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream("profile.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(userlist);
        } catch (Exception ex) {}
        finally {
            if(oos != null){
                try {oos.close();} catch (Exception e) {}
            }
        }
    }

    /**
     * Imports user credentials from profile.ser
     * @return an arraylist of UserProfiles recovered from file
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<UserProfile> importData()
    {
        ObjectInputStream ois = null;
        FileInputStream fin = null;

        ArrayList<UserProfile> temp = null;

        try{
            fin = new FileInputStream("profile.ser");
            ois = new ObjectInputStream(fin);
            temp = (ArrayList<UserProfile>) ois.readObject();

//            this.tagName = temp.gettagName();
//            this.email = temp.getemail();
        } catch (Exception ex) {}
        finally {
            if(ois != null){
                try {ois.close();} catch (Exception e) {}
            }
        }
        return temp;
    }
}