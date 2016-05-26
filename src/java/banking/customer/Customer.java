package banking.customer;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author init0
 */
public class Customer {
    private String name;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private List<Account> acc;
    private Account lastAcc;
    public Customer(){};
    public Customer(String name){
        this.name = name;
    }
    public Customer(String name,String type, int no, double balance){
        this.name = name;
        this.type = type;
        this.acc = new ArrayList<Account>();
        this.acc.add(new Account(no, balance));
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAcc() {
        return acc;
    }

    public void setAcc(List<Account> acc) {
        this.acc = acc;
    }

    public Account getLastAcc() {
        if(this.lastAcc == null)
            if(this.getAcc() != null && !this.getAcc().isEmpty())
                this.lastAcc = this.getAcc().get(this.getAcc().size() -1);
        return this.lastAcc;
    }

    public void setLastAcc(Account lastAcc) {
        this.lastAcc = lastAcc;
    }
    
    
    
}
