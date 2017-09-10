/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.listener;

import banking.database.DBManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author init0
 */
public class BankContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBManager.getInstance().createEMF();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBManager.getInstance().close();
    }

}
