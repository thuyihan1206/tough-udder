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
public class User implements Serializable {

    // data fields
    private Account account;
    private Cart cart;

    // constructors
    public User() {
        this.account = new Account();
        this.cart = new Cart();
    }

    public User(Account account, Cart cart) {
        this.account = account;
        this.cart = cart;
    }

    // getters and setters
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
