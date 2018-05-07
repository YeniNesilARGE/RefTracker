/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableObjects;

import java.util.Date;

/**
 *
 * @author Bedirhan YILDIRIM
 */
public class TentTable {

    String id, tentType, description;

    public TentTable(String id, String tentType, String description) {
        this.id = id;
        this.tentType = tentType;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTentType() {
        return tentType;
    }

    public String getDescription() {
        return description;
    }

    public void setTentType(String tentType) {
        this.tentType = tentType;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
