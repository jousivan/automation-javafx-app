package br.com.ajss.automation.pageobjects;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

	private WebDriver driver;
	private WebDriverWait wait;
	private String agencia;
	
	public Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Retorna o objeto Wait. A partir do objeto wait pode-se colocar uma condição
	 * especifica de espera que deve ser atendida
	 **/
	public WebDriverWait getWait() {
		this.wait = new WebDriverWait(driver, 30);
		return wait;
	}

	private void escreve(By by, String texto) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public void escrever(String id_campo, String texto) {
		escreve(By.name(id_campo), texto);
	}

	public void escreverPorName(String id_campo, String texto) {
		escreve(By.name(id_campo), texto);
	}

	public void escreverPorId(String id, String texto) {
		escreve(By.id(id), texto);
	}

	public void escreverSemLimpar(String id, String texto) {
		getDriver().findElement(By.id(id)).sendKeys(texto);
	}

	// Executar javascript
	public void jsexecutor(String script) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(script);
	}

	// Selecionar item na lista
	public void selecionaOpcaoNaLista(WebElement listaSelecao, String opcao) {

		listaSelecao.click();

		List<WebElement> opcoesLista = driver.findElements(By.xpath("//option"));

		for (WebElement option : opcoesLista) {
			if (option.getText().contains(opcao)) {
				option.click();
				break;
			}
		}
	}

	public void selecionaItem(String campo, String opcao) {
		Select listaSelecao = new Select(obterElementoPorId(campo));
		listaSelecao.selectByVisibleText(opcao);

	}
	
	/** Esperas **/

	public WebElement esperaPorElementoVisivel(By localizacao, int segundos) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(localizacao));
	}

	public WebElement esperaPorElementoVisivelXPath(String localizacao) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(localizacao)));
	}

	public WebElement esperaPorElementoVisivelId(String id) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}

	public WebElement esperaPorElementoVisivelName(String name) {
		return getWait().until(ExpectedConditions.visibilityOfElementLocated(By.name(name)));
	}

	public WebElement esperaElementoClicavelPorId(String id) {
		return getWait().until(ExpectedConditions.elementToBeClickable(By.id(id)));
	}

	public WebElement esperaPorBotaoHabilitado(By localizacao, int segundos) {
		return getWait().until(ExpectedConditions.elementToBeClickable(localizacao));
	}

	/********* Botao ************/

	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}

	public void clicarBotaoXPath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	public void clicarBotaoSeletor(String className) {
		driver.findElement(By.className(className)).click();
	}

	public void clicarBotaoName(String name) {
		driver.findElement(By.name(name)).click();
	}

	/********* Limpar Conteúdo *********/
	public void limparConteudo(String atributo) {
		driver.findElement(By.id(atributo)).clear();
	}
	
	/********* Obter valores *********/
	public String obterValorElemento(String atributo) {
		return driver.findElement(By.id(atributo)).getText();
	}

	public String obterValorPorAtributo(String atributo) {
		return driver.findElement(By.id(atributo)).getAttribute("value");
	}

	public String obterValorElementoXPath(String xpath) {
		return driver.findElement(By.xpath(xpath)).getText();
	}

	public String obterValorPorAtributoXpath(String xpath) {
		return driver.findElement(By.xpath(xpath)).getAttribute("value");
	}

	public boolean elementoPresente(String id) {
		// return driver.findElement(By.id(id)).getAttribute("value");
		return driver.findElement(By.id(id)).isDisplayed();
	}

	public WebElement obterElementoPorId(String id) {
		// return driver.findElement(By.id(id)).getAttribute("value");
		return driver.findElement(By.id(id));
	}

	public WebElement obterElementoPorNome(String name) {
		// return driver.findElement(By.id(id)).getAttribute("value");
		return driver.findElement(By.name(name));
	}
	
	public WebElement obterElementoPorXpath(String xpath) {
		// return driver.findElement(By.id(id)).getAttribute("value");
		return driver.findElement(By.xpath(xpath));
	}

	/********* Link ************/

	public void clicarLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}

	/********* Clicar em Radio ************/
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}

	public void clicarRadioXpath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}
	
	/********* Clicar em Checkbox ************/
	public void clicarCheckbox(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public void clicarCheckboxPorNome(String name) {
		driver.findElement(By.name(name)).click();
	}
	
	
	
	/********* Verificar se Checkbox/Radio está marcado 
	 * @return ************/
	public boolean estaMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}	

	public void clicarCheckboxXPath(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	public String consultaTransacao(String transacao) throws InterruptedException {
		String nsu;
		String situacao = null;
		int i = 1;

		jsexecutor("javascript:analisarLink('consultaTransacao')");
		esperaPorElementoVisivelId("OPERADOR");

		escreverPorId("OPERADOR", usuarioLogado());
		selecionaItem("SERVICOS_1", transacao);
		esperaElementoClicavelPorId("INSERE");
		clicarBotao("INSERE");

		clicarBotao("EXECUTAR");

		esperaPorElementoVisivelXPath("//*[@id='SERVICOS_CONSULTAR_TABELA']/tbody/tr[1]/td[6]");

		situacao = obterValorElementoXPath("//*[@id='SERVICOS_CONSULTAR_TABELA']/tbody/tr[" + i + "]/td[6]");
		while (!situacao.contains("Efet")) {
			i++;
			situacao = obterValorElementoXPath("//*[@id='SERVICOS_CONSULTAR_TABELA']/tbody/tr[" + i + "]/td[6]");
		}

		nsu = obterValorElementoXPath("//*[@id='SERVICOS_CONSULTAR_TABELA']/tbody/tr[" + i + "]/td[1]");
		return nsu;
	}

	public void desfazerTransacao(String transacao) throws InterruptedException {
		String nsu;

		nsu = consultaTransacao(transacao);

		jsexecutor("javascript:analisarLink('efetuaDesfazimentoTransacao')");

		esperaPorElementoVisivelId("nsu");
		escreverPorId("nsu", nsu);
		clicarBotao("consultar");
		esperaElementoClicavelPorId("senha");
		escreverPorId("senha", "123456");

		clicarBotao("executar");

		esperaPorElementoVisivelXPath("//button[contains(text(),'SIM')]");
		clicarBotaoXPath("//button[contains(text(),'SIM')]");
	}

	public String usuarioLogado() {
		String usuarioLogado = getDriver().getPageSource();
		Pattern pattern = Pattern.compile("C[0-9]{6}");
		Matcher matcher = pattern.matcher(usuarioLogado);
		if (matcher.find()) {
			usuarioLogado = matcher.group(0);
		}
		return usuarioLogado;
	}
	
	public boolean verificaElementoVisivelXpath(String xpath) {
	    WebElement element;
		try {
	        element = obterElementoPorXpath(xpath);
	    } catch (org.openqa.selenium.NoSuchElementException e)  {
	        System.out.println("NoSuchElementException!!");
	        return false;
	    } catch (org.openqa.selenium.ElementNotVisibleException e) {
			return false;
		}
		

	    element.isDisplayed();
	    return true;
	}

	public boolean verificaElementoHabilitadoId(String id) {
		WebElement element;
		try {
			element = obterElementoPorId(id);
		} catch (org.openqa.selenium.NoSuchElementException e)  {
			return false;
		} catch (org.openqa.selenium.ElementNotVisibleException e) {
			return false;
		}
		
		return element.isEnabled();
	}

	public boolean verificaElementoMarcadoXpath(String xpath) {
	WebElement element;
		try {
	        element = obterElementoPorXpath(xpath);
	    } catch (org.openqa.selenium.NoSuchElementException e)  {
	        System.out.println("NoSuchElementException!!");
	        return false;
	    } catch (org.openqa.selenium.ElementNotVisibleException e) {
			return false;
		}
		
	    return element.isSelected();
	}

	public String verifyPDFContent(String stringUrl, String contains) throws InterruptedException, IOException {
		
		URL url;
		String pdfContent = null;
		
		try {
			url = new URL(stringUrl);
			InputStream is = url.openStream();
			BufferedInputStream fileParse = new BufferedInputStream(is);
			
			PDDocument document = null;
			
			document = PDDocument.load(fileParse);
			
			pdfContent = new PDFTextStripper().getText(document);
			System.out.println(pdfContent);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return pdfContent;
	}
}