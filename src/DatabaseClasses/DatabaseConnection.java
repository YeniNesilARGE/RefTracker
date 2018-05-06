/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseClasses;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author burak_000
 */
public class DatabaseConnection {
 
    private EntityManagerFactory emf=null;
    
    public void connDb(){
        emf = Persistence.createEntityManagerFactory("RefTrackerPU");
    }
    public EntityManager newEntityManager(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em;
    }
    
    public void exitDb(){
        emf.close();
    }
    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}
