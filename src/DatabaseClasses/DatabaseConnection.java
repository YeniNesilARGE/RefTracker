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
 
    EntityManagerFactory emf=null;
    EntityManager em=null;
    
    public void connDb(){
        emf = Persistence.createEntityManagerFactory("RefTrackerPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public void exitDb(){
        em.close();
        emf.close();
    }
}
