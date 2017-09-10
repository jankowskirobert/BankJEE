/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.controller;

import banking.bean.BankingSystem;
import banking.model.BankClient;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author init0
 */
@ManagedBean(name = "transactor", eager = true)
@ViewScoped
public class Trasaction implements Serializable {

    @EJB
    private BankingSystem b;
    private Long fromID;
    private Long toID;
    

    public Long getFromID() {
        return fromID;
    }

    public void setFromID(Long fromID) {
        this.fromID = fromID;
    }

    public Long getToID() {
        return toID;
    }

    public void setToID(Long toID) {
        this.toID = toID;
    }
    private Double balance;

    private List<BankClient> clients = null;

    public List<BankClient> getClients() {
        if (clients == null) {
            clients = b.getClients();
        }
        return clients;
    }

    public void setClients(List<BankClient> clients) {
        this.clients = clients;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void transact() throws IOException {
        b.updateBalance(toID, balance);
        b.updateBalance(fromID, -balance);

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

}
