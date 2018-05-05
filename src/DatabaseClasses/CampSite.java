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
@Table(name = "camp_site")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CampSite.findAll", query = "SELECT c FROM CampSite c")
    , @NamedQuery(name = "CampSite.findById", query = "SELECT c FROM CampSite c WHERE c.id = :id")
    , @NamedQuery(name = "CampSite.findByName", query = "SELECT c FROM CampSite c WHERE c.name = :name")
    , @NamedQuery(name = "CampSite.findByLocation", query = "SELECT c FROM CampSite c WHERE c.location = :location")
    , @NamedQuery(name = "CampSite.findByCampType", query = "SELECT c FROM CampSite c WHERE c.campType = :campType")
    , @NamedQuery(name = "CampSite.findByDate", query = "SELECT c FROM CampSite c WHERE c.date = :date")})
public class CampSite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @Column(name = "camp_type")
    private int campType;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public CampSite() {
    }

    public CampSite(Integer id) {
        this.id = id;
    }

    public CampSite(Integer id, String name, String location, int campType, Date date) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.campType = campType;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCampType() {
        return campType;
    }

    public void setCampType(int campType) {
        this.campType = campType;
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
        if (!(object instanceof CampSite)) {
            return false;
        }
        CampSite other = (CampSite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseClasses.CampSite[ id=" + id + " ]";
    }
    
}
