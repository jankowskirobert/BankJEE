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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
@ManagedBean(name = "withdrawer", eager = true)
@ViewScoped
public class Withdraw implements Serializable {

    @EJB
    private BankingSystem b;
    private Double balance;
    private Map<Long, Boolean> clista = new HashMap<Long, Boolean>();
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

    public Map<Long, Boolean> getClista() {
        return clista;
    }

    public void setClista(Map<Long, Boolean> clista) {
        this.clista = clista;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void withdraw() throws IOException {
        clients.stream().filter((entity) -> (clista.get(entity.getId()))).forEach((entity) -> {
            b.updateBalance(entity.getId(), -balance);
        });
        if (clista.isEmpty()) {
            clients.clear();
        }
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

}
