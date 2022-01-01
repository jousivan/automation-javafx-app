
package br.com.ajss.automation.simulador;

import java.util.List;

import br.com.ajss.automation.simulador.dao.ParametrosSimuladorDao;
import br.com.ajss.automation.simulador.pojo.TbParamentro;

public class ConsultaParametroSimulador {

   /**
    * @TODO Comentar Método
    * @param arqs
    */
   public static void main(String arqs[]) {
      ParametrosSimuladorDao paramDao = new ParametrosSimuladorDao(TbParamentro.class);
      List<TbParamentro> lista = paramDao.buscaTodos();
      for (TbParamentro paramentro : lista) {
         System.out.println(paramentro.getDescricaoParametro());
      }

   }

}
