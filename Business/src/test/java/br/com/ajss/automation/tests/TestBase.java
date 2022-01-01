package br.com.ajss.automation.tests;

import java.io.IOException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import la.foton.sisag.automation.pageobjects.LoginPage;
import la.foton.sisag.automation.pageobjects.Page;

public abstract class TestBase
{
	private Page page;
	
	public TestBase() {
	super();
		initPage();
	}

	@BeforeClass
	public void logar() throws IOException, Exception
	{

		/*
		String retorno = page.getDriver().getPageSource();
		if(page.getDriver() != null && !retorno.contains("Logoff")){
			LoginPage loginPage = (LoginPage) page;
			loginPage.logar();
		}else if(page.getDriver() != null && retorno.contains("Logoff")) {
			System.out.println("Usuário já está logado.");
		}
		*/
	}
    
  @AfterSuite
   public void posCondicao() throws InterruptedException
   {   	
	  /*
	   * logOff();
	  
	  closeDriver();
	   */
   }

	private void closeDriver()
	{		
		page.getDriver().quit();
		
		/** Mata processos do chromedriver.exe que fica em memória */
		Runtime rt = Runtime.getRuntime();
	    try {
	        rt.exec("taskkill /f /im chromedriver.exe /t");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	private void logOff() throws InterruptedException
	{
		LoginPage loginPage = (LoginPage) page;
		loginPage.logoff();
	}
	
	private void initPage()
	{
		this.page = getInstacePage();
	}
	
	public abstract Page getInstacePage();

}
