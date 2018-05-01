/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseClasses;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author burak_000
 */
@Entity
@Table(name = "transfer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t")
    , @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id")
    , @NamedQuery(name = "Transfer.findByRefugeeId", query = "SELECT t FROM Transfer t WHERE t.refugeeId = :refugeeId")
    , @NamedQuery(name = "Transfer.findBySourceCampId", query = "SELECT t FROM Transfer t WHERE t.sourceCampId = :sourceCampId")
    , @NamedQuery(name = "Transfer.findByDestCampId", query = "SELECT t FROM Transfer t WHERE t.destCampId = :destCampId")
    , @NamedQuery(name = "Transfer.findBySourceTentId", query = "SELECT t FROM Transfer t WHERE t.sourceTentId = :sourceTentId")
    , @NamedQuery(name = "Transfer.findByDestTentId", query = "SELECT t FROM Transfer t WHERE t.destTentId = :destTentId")
    , @NamedQuery(name = "Transfer.findByDate", query = "SELECT t FROM Transfer t WHERE t.date = :date")})
public class Transfer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "refugeeId")
    private int refugeeId;
    @Basic(optional = false)
    @Column(name = "sourceCampId")
    private int sourceCampId;
    @Basic(optional = false)
    @Column(name = "destCampId")
    private int destCampId;
    @Basic(optional = false)
    @Column(name = "sourceTentId")
    private int sourceTentId;
    @Basic(optional = false)
    @Column(name = "destTentId")
    private int destTentId;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Transfer() {
    }

    public Transfer(Integer id) {
        this.id = id;
    }

    public Transfer(Integer id, int refugeeId, int sourceCampId, int destCampId, int sourceTentId, int destTentId, Date date) {
        this.id = id;
        this.refugeeId = refugeeId;
        this.sourceCampId = sourceCampId;
        this.destCampId = destCampId;
        this.sourceTentId = sourceTentId;
        this.destTentId = destTentId;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRefugeeId() {
        return refugeeId;
    }

    public void setRefugeeId(int refugeeId) {
        this.refugeeId = refugeeId;
    }

    public int getSourceCampId() {
        return sourceCampId;
    }

    public void setSourceCampId(int sourceCampId) {
        this.sourceCampId = sourceCampId;
    }

    public int getDestCampId() {
        return destCampId;
    }

    public void setDestCampId(int destCampId) {
        this.destCampId = destCampId;
    }

    public int getSourceTentId() {
        return sourceTentId;
    }

    public void setSourceTentId(int sourceTentId) {
        this.sourceTentId = sourceTentId;
    }

    public int getDestTentId() {
        return destTentId;
    }

    public void setDestTentId(int destTentId) {
        this.destTentId = destTentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseClasses.Transfer[ id=" + id + " ]";
    }
    
}
