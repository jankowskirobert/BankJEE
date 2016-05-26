/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.customer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author init0
 */
public class AccountsInfo {
    private Banker bankier;
    private Map<String, Double> balances;
    
    public AccountsInfo(){}

    public Banker getBankier() {
        return bankier;
    }

    public void setBankier(Banker bankier) {
        this.bankier = bankier;
    }

    public Map<String, Double> getBalances() {
        balances = new HashMap<>();
        
        for(Customer c: this.getBankier().getClients()){
            Double status = balances.get(c.getType());
            if(status == null)
                status = 0.0;
            balances.put(c.getType(), status + c.getLastAcc().getBalance());
        }
        return balances;
    }
    
    public Object[] getTypes(){
        Map<String, Double> m = this.getBalances();
        return m.keySet().toArray();
    }
    
    
    
}
