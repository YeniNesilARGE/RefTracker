/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableObjects;

/**
 *
 * @author burak_000
 */
public class RefugeeTable {
    
    String name,surName,nation,campName,tentName,gender,alive;

    public RefugeeTable(String name, String surName, String nation, String campName, String tentName, String gender, String alive) {
        this.name = name;
        this.surName = surName;
        this.nation = nation;
        this.campName = campName;
        this.tentName = tentName;
        this.gender = gender;
        this.alive = alive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getTentName() {
        return tentName;
    }

    public void setTentName(String tentName) {
        this.tentName = tentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }
    
}
