/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author student
 */
@Entity
@Table(name = "loan")
@NamedQueries({
    @NamedQuery(name = "Loan.findAll", query = "SELECT l FROM Loan l")})
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "loaned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loaned;
    @Column(name = "returned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returned;
    @JoinColumn(name = "bookitem_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Bookitem bookItem;
    @JoinColumn(name = "reader_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Reader reader;

    public Loan() {
    }

    public Loan(Integer id) {
        this.id = id;
    }

    public Loan(Integer id, Date from) {
        this.id = id;
        this.loaned = from;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLoaned() {
        return loaned;
    }

    public void setLoaned(Date from) {
        this.loaned = from;
    }

    public Date getReturned() {
        return returned;
    }

    public void setReturned(Date to) {
        this.returned = to;
    }

    public Bookitem getBookitemId() {
        return bookItem;
    }

    public void setBookitemId(Bookitem bookitemId) {
        this.bookItem = bookitemId;
    }

    public Reader getReaderId() {
        return reader;
    }

    public void setReaderId(Reader readerId) {
        this.reader = readerId;
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
        if (!(object instanceof Loan)) {
            return false;
        }
        Loan other = (Loan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Loan[ id=" + id + " ]";
    }

}
