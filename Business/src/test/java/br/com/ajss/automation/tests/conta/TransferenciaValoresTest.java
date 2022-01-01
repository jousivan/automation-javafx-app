package br.com.ajss.automation.tests.conta;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.ajss.automation.pageobjects.conta.TransferenciaValoresPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import la.foton.sisag.automation.pageobjects.Page;
import br.com.ajss.automation.tests.TestBase;
import la.foton.sisag.automation.testutil.DriverFactory;
import br.com.ajss.automation.testutil.ScreenshotUtil;

public class TransferenciaValoresTest extends TestBase {
	TransferenciaValoresPage transferenciaValoresPage;

	@Override
	public Page getInstacePage() {
		transferenciaValoresPage = new TransferenciaValoresPage(new DriverFactory().getDriver());
		return transferenciaValoresPage;
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void realizarTevPorOperacao() throws Exception {

		transferenciaValoresPage.realizarTransferenciaOperacao();

		transferenciaValoresPage.getWait().until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'SUCESSO NA EXECU')]")));
		ScreenshotUtil.takeScreenshot(transferenciaValoresPage.getDriver(),
				this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName());
		assertThat(transferenciaValoresPage.obterValorElementoXPath("//span[contains(text(),'SUCESSO NA EXECU')]"))
				.contains("SUCESSO NA EXECUÇÃO DO SERVIÇO");
	}
/*
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void realizarTevPorProduto() throws Exception {

		transferenciaValoresPage.realizarTransferenciaProduto();

		transferenciaValoresPage.getWait().until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'SUCESSO NA EXECU')]")));
		ScreenshotUtil.takeScreenshot(transferenciaValoresPage.getDriver(),
				this.getClass().getSimpleName() + "-" + Thread.currentThread().getStackTrace()[1].getMethodName());
		assertThat(transferenciaValoresPage.obterValorElementoXPath("//span[contains(text(),'SUCESSO NA EXECU')]"))
				.contains("SUCESSO NA EXECUÇÃO DO SERVIÇO");
	}
	*/
}
