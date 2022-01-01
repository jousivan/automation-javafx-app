package br.com.ajss.automation.pageobjects.conta;

import org.openqa.selenium.WebDriver;

import la.foton.sisag.automation.pageobjects.LoginPage;
import la.foton.sisag.automation.testutil.datapool.DatapoolUtil;

public class TransferenciaValoresPage extends LoginPage {

	
	public TransferenciaValoresPage(WebDriver driver) {
		super(driver);
	}

	public void realizarTransferenciaOperacao() {
		jsexecutor("javascript:analisarLink('transferenciaValores')");
		
		esperaPorElementoVisivelXPath("//button[contains(text(),'SIM')]");
		clicarBotaoXPath("//button[contains(text(),'SIM')]");
		
		esperaPorElementoVisivelId("valor");
		escreverPorId("valor", "500000");
		selecionaItem("opcao", "Para Mesmo Titular - PF");
		escrever("operacaoRemetente", DatapoolUtil.InformacoesAgencia().getOperacao("001").getNumOperacao());
		escrever("contaDVRemetente", DatapoolUtil.InformacoesAgencia().getOperacao("001").getNumConta());
		clicarBotao("consultar");
		

		autorizaTransacao();
		
		esperaElementoClicavelPorId("agenciaFavorecido");
		clicarBotao("agenciaFavorecido");
		escreverPorId("agenciaFavorecido", DatapoolUtil.InformacoesAgencia().getOperacao("001").getAgenciaDestino());
		escreverPorId("operacaoFavorecido",  DatapoolUtil.InformacoesAgencia().getOperacao("001").getNumOperacaoDestino());
		escreverPorId("contaDVFavorecido", DatapoolUtil.InformacoesAgencia().getOperacao("001").getNumContaDestino());
		clicarBotao("executar");
		esperaPorElementoVisivelId("motivo");
		escreverPorId("motivo", "Testar a aplicação");
		clicarBotao("executarConfirmacao");
		esperaPorElementoVisivelXPath("//button[contains(text(),'NÃO')]");
		clicarBotaoXPath("//button[contains(text(),'NÃO')]");
	}

	public void realizarTransferenciaProduto() {
		jsexecutor("javascript:analisarLink('transferenciaValores')");
		
		esperaPorElementoVisivelXPath("//button[contains(text(),'SIM')]");
		clicarBotaoXPath("//button[contains(text(),'SIM')]");
		
		esperaPorElementoVisivelId("valor");
		escreverPorId("valor", "500000");
		selecionaItem("opcao", "Para Mesmo Titular - PF");
		escrever("produtoRemetente", DatapoolUtil.InformacoesAgencia().getProduto());
		escrever("contaDVRemetente", DatapoolUtil.InformacoesAgencia().getContaNsgd());
		clicarBotao("consultar");

		autorizaTransacao();
		
		esperaElementoClicavelPorId("agenciaFavorecido");
		clicarBotao("agenciaFavorecido");
		escreverPorId("agenciaFavorecido", DatapoolUtil.InformacoesAgencia().getOperacao("001").getAgenciaDestino());
		escreverPorId("operacaoFavorecido",  DatapoolUtil.InformacoesAgencia().getOperacao("001").getNumOperacaoDestino());
		escreverPorId("contaDVFavorecido", DatapoolUtil.InformacoesAgencia().getOperacao("001").getNumContaDestino());
		clicarBotao("executar");
		esperaPorElementoVisivelId("motivo");
		escreverPorId("motivo", "Testar a aplicação");
		clicarBotao("executarConfirmacao");
		esperaPorElementoVisivelXPath("//button[contains(text(),'NÃO')]");
		clicarBotaoXPath("//button[contains(text(),'NÃO')]");
	}

}
