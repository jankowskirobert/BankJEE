/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author init0
 */
public class DBManager {

    private static final String DB_SYM = "mariadb";
    private static DBManager instance;
    private EntityManagerFactory factory;

    private DBManager() {
    }

    public synchronized static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public EntityManagerFactory createEMF() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(DB_SYM);
        }
        return factory;
    }

    public EntityManager createEM() {
        return this.createEMF().createEntityManager();
    }

    public void close() {
        if (factory != null) {
            factory.close();
        }
    }

}
