package com.example;

public class CreateUser {
    private String Newusername;
    private String Newpassword;
    private String Phonenumber;

    public CreateUser(String Newusername, String Newpassword, String Phonenumber) {
        this.Newusername = Newusername;
        this.Newpassword = Newpassword;
        this.Phonenumber = Phonenumber;

    }
    
    public String getNewUsername() {
        return this.Newusername;
    }

    public String getNewPassword() {
        return this.Newpassword;
    }

        public String getPhonenumber() {
        return this.Phonenumber;
    }

    
}
