/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseClasses;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author burak_000
 */
@Entity
@Table(name = "tent_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TentType.findAll", query = "SELECT t FROM TentType t")
    , @NamedQuery(name = "TentType.findById", query = "SELECT t FROM TentType t WHERE t.id = :id")
    , @NamedQuery(name = "TentType.findByType", query = "SELECT t FROM TentType t WHERE t.type = :type")
    , @NamedQuery(name = "TentType.findByCapasity", query = "SELECT t FROM TentType t WHERE t.capasity = :capasity")})
public class TentType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "type")
    private String type;
    @Column(name = "capasity")
    private Integer capasity;

    public TentType() {
    }

    public TentType(Integer id) {
        this.id = id;
    }

    public TentType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCapasity() {
        return capasity;
    }

    public void setCapasity(Integer capasity) {
        this.capasity = capasity;
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
        if (!(object instanceof TentType)) {
            return false;
        }
        TentType other = (TentType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseClasses.TentType[ id=" + id + " ]";
    }
    
}
