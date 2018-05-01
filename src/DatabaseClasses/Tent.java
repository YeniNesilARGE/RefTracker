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
@Table(name = "tent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tent.findAll", query = "SELECT t FROM Tent t")
    , @NamedQuery(name = "Tent.findById", query = "SELECT t FROM Tent t WHERE t.id = :id")
    , @NamedQuery(name = "Tent.findByCampId", query = "SELECT t FROM Tent t WHERE t.campId = :campId")
    , @NamedQuery(name = "Tent.findByTypeId", query = "SELECT t FROM Tent t WHERE t.typeId = :typeId")
    , @NamedQuery(name = "Tent.findByDescription", query = "SELECT t FROM Tent t WHERE t.description = :description")
    , @NamedQuery(name = "Tent.findByDate", query = "SELECT t FROM Tent t WHERE t.date = :date")})
public class Tent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "campId")
    private int campId;
    @Basic(optional = false)
    @Column(name = "typeId")
    private int typeId;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Tent() {
    }

    public Tent(Integer id) {
        this.id = id;
    }

    public Tent(Integer id, int campId, int typeId, Date date) {
        this.id = id;
        this.campId = campId;
        this.typeId = typeId;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Tent)) {
            return false;
        }
        Tent other = (Tent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseClasses.Tent[ id=" + id + " ]";
    }
    
}
