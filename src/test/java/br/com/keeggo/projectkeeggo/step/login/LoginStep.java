package br.com.keeggo.projectkeeggo.step.login;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.keeggo.projectkeeggo.config.ConfigEvidencia;
import br.com.keeggo.projectkeeggo.config.ConsoleColors;
import br.com.keeggo.projectkeeggo.logic.HomeLogic;
import br.com.keeggo.projectkeeggo.logic.LoginLogic;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginStep {

	private HomeLogic homeLogic;
	
	private LoginLogic loginLogic;
	
	@Before
	public void setup(Scenario scenario) {
		ConfigEvidencia.nameFile = scenario.getName();
		this.homeLogic = new HomeLogic();
	}
	
	
	@After
	public void after() {
		this.homeLogic.fecharBrowser();
	}
	
	@Given("home clique no icone de login redirecionar")
	public void click_btn_redireciona_login() {
		String initTestTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		System.out.println(ConsoleColors.GREEN  + "\t\t\tLogs: ---- Teste Iniciado na data de "+ initTestTime +" ----");
		this.homeLogic.clickBtnRedirecionarLogin();
		
	}
	
	@When("preencho campos de login")
	public void preencho_campos_login() {
		this.homeLogic.preencherCampoUsuario();
		this.homeLogic.preencherCampoSenha();
	}
	
	@And("clico no btn de logar")
	public void click_btn_realizar_login() {
		this.loginLogic = this.homeLogic.clickBtnRealizarLogin();
	}
	
	
	@When("valido o login")
	public void valido_login() {
		boolean testeValido = this.loginLogic.validarLogin();
		if (testeValido) {
			System.out.println(ConsoleColors.GREEN + "\t\t\tLogs: ------------ TESTE PASSOU ------------");
			assertTrue(testeValido);
		} else {
			System.out.println(ConsoleColors.RED + "\t\t\tLogs: ------------ TESTE FALHOU ------------");
			assertTrue(false);
		}
	}
	
	
}