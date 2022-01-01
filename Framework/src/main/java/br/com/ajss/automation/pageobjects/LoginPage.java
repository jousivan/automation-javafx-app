package la.foton.sisag.automation.pageobjects;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import la.foton.sisag.automation.sisag.dao.ParametrosSistemaDao;
import la.foton.sisag.automation.sisag.pojo.Sagtb008Paramentro;
import la.foton.sisag.automation.testutil.datapool.DatapoolUtil;

public class LoginPage extends Page
{
	public LoginPage(WebDriver driver) {
		super(driver);		
	}
	
	public void logar() throws IOException, Exception
	{
		if(System.getProperty("flag") == "S")
		{
			loginCertificadoDigital();

		}else if (System.getProperty("flag") == "N" || System.getProperty("flag") != "N"){

			loginMatriculaSenha();
		}

        Thread.sleep(2000);
        
        if(verificaElementoVisivelXpath("//button[contains(text(),'OK')]")) 
        {
        	clicarBotaoXPath("//button[contains(text(),'OK')]");
        	esperaPorElementoVisivelId("executar");
        	clicarBotao("executar");
        	esperaPorElementoVisivelXPath("//button[contains(text(),'SIM')]");
        	clicarBotaoXPath("//button[contains(text(),'SIM')]");
        	esperaPorElementoVisivelXPath("//button[contains(text(),'OK')]");
        	clicarBotaoXPath("//button[contains(text(),'OK')]");
        	Thread.sleep(5000);
        	esperaPorElementoVisivelXPath("//b[contains(text(),'SISAG - Sistema de Automação de Produtos')]");
        }
        else 
        {
        	esperaPorElementoVisivelXPath("//b[contains(text(),'SISAG - Sistema de Automação de Produtos')]");
        }
        
        jsexecutor("javascript:simuladorPerifericos(\"N\");");
        
	}
	
	public void logoff() throws InterruptedException
	{   	
		this.jsexecutor("javascript:doEncerrarSessao()");
		getDriver().findElement(By.xpath("//button[contains(text(),'Executar')]")).click();
		Thread.sleep(2000);
		getDriver().findElement(By.xpath("//span[@class='loginLink' and contains(text(),'clique aqui')]")).click();
    }
	
	
	public void loginMatriculaSenha() {
		getDriver().findElement(By.id("divAcessoMatriculaSenha")).click();
		getWait();
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.name("matricula")));
		
		esperaPorElementoVisivelName("matricula");
		escrever("matricula", DatapoolUtil.InformacoesAgencia().getOperador());
		escrever("senha", DatapoolUtil.InformacoesAgencia().getSenha());
		
		jsexecutor("javascript:formSubmit()");
		
		esperaPorElementoVisivel(By.name("numeroAgencia"), 10);
		//page.selecionaOpcaoNaLista("selecaoAgencias", 10);
		//page.selecionarCombo("selecaoAgencias", "10");
		//page.selecionaOpcaoNaLista(WebElement elemento, opcao);
		
        clicarBotao("imgSelecione");
		
	}
	
	public void loginCertificadoDigital() throws Exception {
		jsexecutor("javascript:simuladorPerifericos(\"S\");");
		//setaFormaAutenticacao("1");
		clicarBotao("linkAcessoCertificadoDigital");
		esperaPorElementoVisivelName("dadoSimuladoNavedaor");
		//escreverPorName("dadoSimuladoNavedaor", SimuladorCertificado.getInstance().geraMensagemSucesso(new File("../device-simulator/src/test/resources/" + DatapoolUtil.InformacoesAgencia().getOperadorAutorizadorCertificado() + ".cer")));
		
		geraStringCertificadoDigital();
		
		clicarBotaoXPath("//button[contains(text(),'OK')]");
		
		esperaPorElementoVisivelName("numeroAgencia");
		clicarBotao("imgSelecione");
	}
	
	/** Setar forma de Autenticação. Acessar somente com certificação digital : 1 - CERTIFICADO DIGITAL, 2 - CERTIFICADO OU MATRICULA/SENHA */
	public void setaFormaAutenticacao(String parametro) {
		
		  ParametrosSistemaDao paramDao = new ParametrosSistemaDao(Sagtb008Paramentro.class);	      
	      Sagtb008Paramentro parametroAutenticacaoSisag = paramDao.buscaPorId("FORMATO_AUTENTICACAO_SISAG");
	      if(!parametroAutenticacaoSisag.getDeCndoParametro().equals(parametro)) {
	    	  parametroAutenticacaoSisag.setDeCndoParametro(parametro);
		      paramDao.atualiza(parametroAutenticacaoSisag);
	      }
	      
	      /*
	      ParametrosSistemaDao paramDao2 = new ParametrosSistemaDao(Sagtb008Paramentro.class);
	      Sagtb008Paramentro parametroAutenticacaoSisagEF = paramDao2.buscaPorId("FORMA_AUTENTICACAO_SISAG_EF");
	      if(!parametroAutenticacaoSisagEF.getDeCndoParametro().equals(parametro)) {
	    	  parametroAutenticacaoSisagEF.setDeCndoParametro(parametro);
	  	      paramDao2.atualiza(parametroAutenticacaoSisagEF);
	      }
	      */
	}
}
