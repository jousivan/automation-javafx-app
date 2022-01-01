package br.com.ajss.automation.simulador.dao;

import br.com.ajss.automation.dao.SimuladorImplDao;
import br.com.ajss.automation.simulador.pojo.TbParamentro;

public class ParametrosSimuladorDao extends SimuladorImplDao<Integer, TbParamentro>{

   public ParametrosSimuladorDao(Class<TbParamentro> classeDaEntidade) {
      super(classeDaEntidade);
      
   }
   
   

}
