package la.foton.sisag.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MenuPage {
	private WebDriver driver;
	
	public MenuPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean logadoSucesso() {
		WebElement divSucesso = driver.findElement(By.className("alert-success"));
		return divSucesso.getText().contains("Projeto incluído com sucesso!");
	}

	public boolean isValida() {
		return temBarraNavegacao() && temListaMenu();
	}

	private boolean temBarraNavegacao() {
		return driver.findElement(By.className("navbar")) != null;
	}

	private boolean temListaMenu() {
		return driver.findElement(By.id("ROOT_0")).getText().contains("Caixa");
		//return driver.findElement(By.tagName("h2")).getText().contains("Projetos");
	}

}

