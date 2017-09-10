/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import banking.bean.BankingSystem;
import banking.database.DBManager;
import banking.model.BankClient;
import java.util.List;
import javax.persistence.EntityManager;
import org.mockito.Mock;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author init0
 */
public class BasicTests {

    public BasicTests() {
    }

    @Mock
    BankingSystem bankM;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testGetSomeClientWithID() {
        when(bankM.getClient((long) 23444)).thenCallRealMethod();
        Assert.assertNotNull(bankM.getClient((long) 23444));
    }

    @Test
    public void testWithdraw() {
        BankClient h = mock(BankClient.class);
        when(bankM.getClient((long) 23444)).thenCallRealMethod();
        h = bankM.getClient((long) 23444);
        when(h.getBalance()).thenCallRealMethod();
        Double tmp = h.getBalance();
        System.out.println(h);
        System.out.println(tmp);
        when(bankM.updateBalance(h, (double) -1000)).thenCallRealMethod();
        Double a = bankM.updateBalance(h, (double) -1000);
        Assert.assertEquals(a, tmp - 1000);
    }
//    

    @Test
    public void testDeposite() {
        BankClient h = mock(BankClient.class);
        when(bankM.getClient((long) 23444)).thenCallRealMethod();
        h = bankM.getClient((long) 23444);
        when(h.getBalance()).thenCallRealMethod();
        Double tmp = h.getBalance();
        System.out.println(h);
        System.out.println(tmp);
        when(bankM.updateBalance(h, (double) +1000)).thenCallRealMethod();
        Double a = bankM.updateBalance(h, (double) +1000);
        Assert.assertEquals(a, tmp + 1000);
    }

    @Test
    public void testSum() {
        when(bankM.getClients()).thenCallRealMethod();
        when(bankM.sumAllClientsBalances()).thenCallRealMethod();
        List<BankClient> tmp = bankM.getClients();
        double sum = 0;
        System.out.println(tmp.size());
        for (BankClient b : tmp) {
            sum += b.getBalance();
        }
        Assert.assertEquals(sum, bankM.sumAllClientsBalances());
    }

    @Test
    public void testAddClient() {
        BankClient y = mock(BankClient.class);
        y = new BankClient((long) 123456, "test", "test", (double) 666);
        when(bankM.addClient(y)).thenReturn(y);
        when(bankM.removeClient(y.getId())).thenCallRealMethod();
        Assert.assertEquals(bankM.addClient(y), y);
        Assert.assertTrue(!bankM.removeClient(y.getId()));
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
