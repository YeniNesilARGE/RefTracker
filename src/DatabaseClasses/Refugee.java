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
@Table(name = "refugee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Refugee.findAll", query = "SELECT r FROM Refugee r")
    , @NamedQuery(name = "Refugee.findById", query = "SELECT r FROM Refugee r WHERE r.id = :id")
    , @NamedQuery(name = "Refugee.findByName", query = "SELECT r FROM Refugee r WHERE r.name = :name")
    , @NamedQuery(name = "Refugee.findBySurname", query = "SELECT r FROM Refugee r WHERE r.surname = :surname")
    , @NamedQuery(name = "Refugee.findByRace", query = "SELECT r FROM Refugee r WHERE r.race = :race")
    , @NamedQuery(name = "Refugee.findByNationality", query = "SELECT r FROM Refugee r WHERE r.nationality = :nationality")
    , @NamedQuery(name = "Refugee.findByCampId", query = "SELECT r FROM Refugee r WHERE r.campId = :campId")
    , @NamedQuery(name = "Refugee.findByTentId", query = "SELECT r FROM Refugee r WHERE r.tentId = :tentId")
    , @NamedQuery(name = "Refugee.findByDescription", query = "SELECT r FROM Refugee r WHERE r.description = :description")
    , @NamedQuery(name = "Refugee.findByGender", query = "SELECT r FROM Refugee r WHERE r.gender = :gender")
    , @NamedQuery(name = "Refugee.findBySocialId", query = "SELECT r FROM Refugee r WHERE r.socialId = :socialId")
    , @NamedQuery(name = "Refugee.findByIsAlive", query = "SELECT r FROM Refugee r WHERE r.isAlive = :isAlive")
    , @NamedQuery(name = "Refugee.findByIsStay", query = "SELECT r FROM Refugee r WHERE r.isStay = :isStay")
    , @NamedQuery(name = "Refugee.findByEverTransport", query = "SELECT r FROM Refugee r WHERE r.everTransport = :everTransport")
    , @NamedQuery(name = "Refugee.findByTransportDate", query = "SELECT r FROM Refugee r WHERE r.transportDate = :transportDate")})
public class Refugee implements Serializable {

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
    @Column(name = "surname")
    private String surname;
    @Column(name = "race")
    private String race;
    @Column(name = "nationality")
    private String nationality;
    @Basic(optional = false)
    @Column(name = "campId")
    private int campId;
    @Basic(optional = false)
    @Column(name = "tentId")
    private int tentId;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "gender")
    private int gender;
    @Column(name = "socialId")
    private String socialId;
    @Basic(optional = false)
    @Column(name = "isAlive")
    private int isAlive;
    @Basic(optional = false)
    @Column(name = "isStay")
    private int isStay;
    @Column(name = "everTransport")
    private Integer everTransport;
    @Column(name = "transportDate")
    @Temporal(TemporalType.DATE)
    private Date transportDate;

    public Refugee() {
    }

    public Refugee(Integer id) {
        this.id = id;
    }

    public Refugee(Integer id, String name, String surname, int campId, int tentId, int gender, int isAlive, int isStay) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.campId = campId;
        this.tentId = tentId;
        this.gender = gender;
        this.isAlive = isAlive;
        this.isStay = isStay;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public int getTentId() {
        return tentId;
    }

    public void setTentId(int tentId) {
        this.tentId = tentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public int getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(int isAlive) {
        this.isAlive = isAlive;
    }

    public int getIsStay() {
        return isStay;
    }

    public void setIsStay(int isStay) {
        this.isStay = isStay;
    }

    public Integer getEverTransport() {
        return everTransport;
    }

    public void setEverTransport(Integer everTransport) {
        this.everTransport = everTransport;
    }

    public Date getTransportDate() {
        return transportDate;
    }

    public void setTransportDate(Date transportDate) {
        this.transportDate = transportDate;
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
        if (!(object instanceof Refugee)) {
            return false;
        }
        Refugee other = (Refugee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DatabaseClasses.Refugee[ id=" + id + " ]";
    }
    
}
