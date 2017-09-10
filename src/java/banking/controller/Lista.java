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
import java.util.ArrayList;
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
@ManagedBean(name = "lista", eager = true)
@ViewScoped
public class Lista implements Serializable {

    @EJB
    private BankingSystem bankingSystem;
    private BankClient client = null;
    private List<BankClient> clients = null;
    private Map<Long, Boolean> clista = new HashMap<Long, Boolean>();
    private Set<Long> test = new HashSet<>();

    public Map<Long, Boolean> getClista() {
        return clista;
    }

    public void setClista(Map<Long, Boolean> clista) {
        this.clista = clista;
    }

    public void submit() throws IOException {
        test.clear();
        for (BankClient entity : clients) {
            if (clista.get(entity.getId())) {
                bankingSystem.removeClient(entity.getId());

                //test.add(entity.getId());
            }
        }
        if (clista.isEmpty()) {
            test.clear();
            clients.clear();
        }
        clients = bankingSystem.getClients();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public Set<Long> getTest() {
        return test;
    }

    public BankClient getClient() {
        return client;
    }

    public List<BankClient> getClients() {
        if (clients == null) {
            clients = bankingSystem.getClients();
        }
        return clients;
    }

//    public 
    public void refresh() throws IOException {
        clients = bankingSystem.getClients();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

}
