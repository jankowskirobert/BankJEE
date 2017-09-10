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
import java.util.List;
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
@ManagedBean(name = "goodbank")
@ViewScoped
public class Bank implements Serializable {

    @EJB
    private BankingSystem bankingSystem;
    private BankClient client = null;
    private List<BankClient> clients = null;

    public BankClient getClient() {
        return client;
    }

    public List<BankClient> getClients() {
        if (clients == null) {
            clients = bankingSystem.getClients();
        }
        return clients;
    }

    public void refresh() throws IOException {
        clients = bankingSystem.getClients();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public Double getSumOfAll() {
        return bankingSystem.sumAllClientsBalances();
    }

}
