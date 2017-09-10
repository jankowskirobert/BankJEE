/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.controller;

import banking.bean.BankingSystem;
import banking.model.BankClient;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author init0
 */
@ManagedBean(name = "clientadder")
@SessionScoped
public class AddClient implements Serializable {

    @EJB
    private BankingSystem bankingSystem;
    private String name;
    private String surrname;
    private Double balance;
    private Long id;

    public BankingSystem getBankingSystem() {
        return bankingSystem;
    }

    public void setBankingSystem(BankingSystem bankingSystem) {
        this.bankingSystem = bankingSystem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurrname() {
        return surrname;
    }

    public void setSurrname(String surrname) {
        this.surrname = surrname;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void add() {
        BankClient tmp = new BankClient(id, name, surrname, balance);
        bankingSystem.addClient(tmp);
    }

}
