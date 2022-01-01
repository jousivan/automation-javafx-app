
package br.com.ajss.automation.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class SimuladorImplDao<CHAVE_PRIMARIA extends Serializable, ENTIDADE> extends AbstractDao<Serializable, ENTIDADE> {

   // @PersistenceContext
   private EntityManager entityManager;
   private EntityManagerFactory emFactory;

   private final Class<ENTIDADE> classeDaEntidade;

   public SimuladorImplDao(Class<ENTIDADE> classeDaEntidade) {
      this.classeDaEntidade = classeDaEntidade;

      if(System.getProperty("ambiente") == "TST")  {
    	  emFactory = Persistence.createEntityManagerFactory("SIMULADOR_DS_TST");

      }else if (System.getProperty("ambiente") == "AUT" || System.getProperty("ambiente") != "TST" ){
    	  emFactory = Persistence.createEntityManagerFactory("SIMULADOR_DS");
      }
      
      entityManager = emFactory.createEntityManager();
   }

   public void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   public EntityManager getEntityManager() {
      return this.entityManager;
   }

   @Override
   public Class<ENTIDADE> getClasseDaEntidade() {
      return this.classeDaEntidade;
   }
}