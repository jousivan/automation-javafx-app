package br.com.ajss.automation;

import java.util.List;

import br.com.ajss.automation.simulador.dao.ParametrosSimuladorDao;
import br.com.ajss.automation.simulador.pojo.TbParamentro;

public class ConsultaParametros {

   public static void main(String arqs[]) {
	   System.out.println("Consulta do Simulador");
	   ParametrosSimuladorDao paramSimuladorDao = new ParametrosSimuladorDao(TbParamentro.class);
	   List<TbParamentro> listaSimulador = paramSimuladorDao.buscaTodos();
	   for (TbParamentro paramentro : listaSimulador) {
		   System.out.println(paramentro.getDescricaoParametro());
	   }

   }

}
