package com.example;
import javafx.beans.property.SimpleStringProperty;
public class User {
    private final SimpleStringProperty spphoneno;
    private final SimpleStringProperty password;
    private final SimpleStringProperty accountcreated;
    private final SimpleStringProperty uname;

    public User(String phoneno, String pword, String dcreated, String uname) {

        this.spphoneno = new SimpleStringProperty(phoneno);
        this.password = new SimpleStringProperty(pword);
        this.accountcreated = new SimpleStringProperty(dcreated);
        this.uname = new SimpleStringProperty(uname);
    }

        public String getPhoneNo() {
            return spphoneno.get();
        }

        public String getPassword(){
            return password.get();
        }
         public String getAccountcreated(){
         return accountcreated.get();
        }

        public String getUsername(){
            return uname.get();
        }
}    

