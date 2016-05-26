/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.customer;

/**
 *
 * @author init0
 */
public class Account {

    private double balance;
    private int number;

    public Account() {
    }

    public Account(int number, double balance) {
        this.balance = balance;
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
