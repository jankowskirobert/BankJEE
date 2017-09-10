/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.controller;

import banking.bean.BankingSystem;
import banking.model.BankClient;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author init0
 */
@ManagedBean(name = "searchengine", eager = true)
@SessionScoped
public class SearchBy implements Serializable {

    @EJB
    private BankingSystem bankingSystem;
    private String value;
    private List<BankClient> searched = null;
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<BankClient> getSearched() {
        return searched;
    }

    public void setSearched(List<BankClient> searched) {
        this.searched = searched;
    }

    public String search() {
        searched = bankingSystem.findByName(value);
        return "searchresult";
    }

}
