/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.customer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author init0
 */
public class Banker {
    private List<Customer> clients;
    private String symActiveType;
    
    public Customer getActiveType(){
        for(Customer c: clients){
            if(c.getType().equals(this.getSymActiveType()))
                return c;
        }
        return null;
    }
    
    public String getSymActiveType() {
        return symActiveType;
    }

    public void setSymActiveType(String symActiveType) {
        this.symActiveType = symActiveType;
    }
    
    public Banker(){
        clients = new ArrayList<Customer>();
        clients.add(new Customer("TEST", "LOK", 123213, 555));
        clients.add(new Customer("TEST1", "LOK",6666, 22));
        clients.add(new Customer("TEST2","DEB",234,5555));
        clients.get(0).getAcc().add(new Account(43534534, 999999));
        clients.get(1).getAcc().add(new Account(30990, 123));
        clients.get(2).getAcc().add(new Account(93939, 1299));
    }

    public List<Customer> getClients() {
        return clients;
    }

    public void setClients(List<Customer> clients) {
        this.clients = clients;
    }
    
}
