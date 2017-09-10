/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author init0
 */
@NamedQueries({
    @NamedQuery(
            name = "findByName",
            query = "select cName from BankClient where cName = :name"           
    ),
    @NamedQuery(
            name = "findBySurname",
            query = "select cSurname from BankClient where cSurname = :surname"           
    ),
    @NamedQuery(
            name = "findByNameFull",
            query = "from BankClient where cName = :name"           
    )
})
@Entity
@Table(name = "clients")
public class BankClient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cID")
    private Long id;
    private String cName;
    private String cSurname;
    private Double balance;

    public BankClient() {

    }

    public BankClient(Long id, String cName, String cSurrname, Double balance) {
        this.id = id;
        this.cName = cName;
        this.cSurname = cSurrname;
        this.balance = balance;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcSurname() {
        return cSurname;
    }

    public void setcSurname(String cSurname) {
        this.cSurname = cSurname;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankClient)) {
            return false;
        }
        BankClient other = (BankClient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BankClient{" + "id=" + id + ", cName=" + cName + ", cSurname=" + cSurname + ", balance=" + balance + '}';
    }

}
