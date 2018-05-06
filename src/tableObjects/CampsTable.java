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
    String name, location;
    
    public CampsTable(String name, String location){
        this.name = name;
        this.location = location;
    }
    public String getName(){
        return name;
    }
    public void setName(String n){
        this.name = n;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String l){
        this.location = l;
    }
}
