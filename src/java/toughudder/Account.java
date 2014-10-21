/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toughudder;

import java.io.Serializable;

/**
 *
 * @author Evan Chan
 */
public class Account implements Serializable {

    // data fields
    private String userID;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Cart cart;

    private boolean login;
    private String loginError;

    // constructors
    public Account() {
        this.userID = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
    }

    public Account(String userID, String password, String firstName,
            String lastName, String email) {
        this.userID = userID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // getters and setters
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getLoginError() {
        return loginError;
    }

    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    // methods
    // check if userID and password match
    public void checkLogin(String userID, String password) {
        if (userID == null || userID.isEmpty()
                || password == null || password.isEmpty()) {
            setLogin(false);
            setLoginError("Enter UserID and Password.");

        } else if (userID.equalsIgnoreCase("tough") && password.equals("udder")) {
            // userID and password are hard coded for now
            setLogin(true);
            setFirstName("Tough");
            setLastName("Udder");
            setLoginError("");

        } else {
            setLogin(false);
            setLoginError("UserID or Password incorrect.");
        }
    }

}
