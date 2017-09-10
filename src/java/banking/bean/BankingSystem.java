/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.bean;

import banking.database.DBManager;
import banking.model.BankClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

/**
 *
 * @author init0
 */
@Stateless
public class BankingSystem {

    private List<BankClient> clients = new ArrayList<>();

    public BankingSystem() {

    }

    public synchronized List<BankClient> getClients() {
        EntityManager entitymanager = DBManager.getInstance().createEM();
        Query q = entitymanager.createQuery("from BankClient");
        clients = q.getResultList();
        entitymanager.close();
        return clients;
    }

    public synchronized BankClient getClient(Long id) {
        EntityManager entitymanager = DBManager.getInstance().createEM();

        BankClient tmp = entitymanager.find(BankClient.class, id);
        entitymanager.close();

        return tmp;

    }

    public EntityManager getEntityManager() {
        return DBManager.getInstance().createEM();
    }

    public synchronized BankClient addClient(BankClient b) throws EJBException {

        if (b.equals(getClient(b.getId()))) {
            return getClient(b.getId());
        } else {
            EntityManager entitymanager = DBManager.getInstance().createEM();
            entitymanager.getTransaction().begin();
            entitymanager.persist(b);
            entitymanager.getTransaction().commit();
            entitymanager.close();
            clients.add(b);
            return b;
        }
    }

    public synchronized Map<BankClient, Boolean> getBankMap() {
        Map<BankClient, Boolean> clista = new HashMap<BankClient, Boolean>();
        for (BankClient h : getClients()) {
            clista.put(h, Boolean.FALSE);
        }
        return clista;
    }

    public synchronized boolean removeClient(Long id) {
        if (getClient(id) != null) {
            EntityManager entitymanager = DBManager.getInstance().createEM();
            Session session = entitymanager.unwrap(Session.class);
            String hql = "DELETE FROM BankClient " + "WHERE cID = :employee_id";

            org.hibernate.Query query = session.createQuery(hql);
            query.setParameter("employee_id", id);
            session.getTransaction().begin();
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
            session.getTransaction().commit();
            session.close();

            return true;
        } else {
            return false;
        }
    }

    public synchronized Double sumAllClientsBalances() {
        EntityManager entitymanager = DBManager.getInstance().createEM();
        Session session = entitymanager.unwrap(Session.class);
        Transaction tx = null;
        Double tmp = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(BankClient.class);
            cr.setProjection(Projections.sum("balance"));
            List totalSalary = cr.list();
            tmp = (Double) totalSalary.get(0);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tmp;
    }

    public synchronized Double updateBalance(Long id, Double balance) {
        EntityManager entitymanager = DBManager.getInstance().createEM();
        Session session = entitymanager.unwrap(Session.class);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            BankClient employee = (BankClient) session.get(BankClient.class, id);
            employee.setBalance(balance + employee.getBalance());

            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return getClient(id).getBalance();
    }

    public synchronized Double updateBalance(BankClient c, Double balance) {
        EntityManager entitymanager = DBManager.getInstance().createEM();
        Session session = entitymanager.unwrap(Session.class);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            c.setBalance(c.getBalance() + balance);
            session.update(c);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return c.getBalance();
    }

    public synchronized List<BankClient> findByName(String value){
        EntityManager entitymanager = DBManager.getInstance().createEM();
        Session session = entitymanager.unwrap(Session.class);
        org.hibernate.Query q = (org.hibernate.Query) session.getNamedQuery("findByNameFull").setString("name", value);
        return q.list();
    }
    
}
