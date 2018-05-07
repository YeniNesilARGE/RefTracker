/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableObjects;

import javafx.beans.property.StringProperty;

/**
 *
 * @author Furkan
 */
public class CampsTable {

    String name, location, campType;

    public CampsTable(String name, String location, String campType) {
        this.name = name;
        this.location = location;
        this.campType = campType;
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

    public void setLocation(String surName) {
        this.location = location;
    }

    public void setCampType(String campType) {
        this.campType = campType;
    }

    public String getCampType() {
        return campType;
    }

}
