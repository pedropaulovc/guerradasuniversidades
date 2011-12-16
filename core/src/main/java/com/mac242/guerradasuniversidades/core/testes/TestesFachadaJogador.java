package com.mac242.guerradasuniversidades.core.testes;

import org.junit.Before;
import org.junit.Test;

import com.mac242.guerradasuniversidades.core.modelo.ConstrutorJogador;
import com.mac242.guerradasuniversidades.core.modelo.Estrutura;
import com.mac242.guerradasuniversidades.core.modelo.FachadaJogador;
import com.mac242.guerradasuniversidades.core.modelo.FocoAdministracao;
import com.mac242.guerradasuniversidades.core.modelo.GuerraDasUniversidades;
import com.mac242.guerradasuniversidades.core.modelo.NomeUniversidade;
import com.mac242.guerradasuniversidades.core.modelo.SalaAula;
import com.mac242.guerradasuniversidades.core.modelo.StatusSalaAula;

import static org.junit.Assert.*;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.*;

public class TestesFachadaJogador {

	private GuerraDasUniversidades jogo;
	private FachadaJogador jogador;

	@Before
	public void inicializar(){
		ConstrutorJogador construtor = new ConstrutorJogador()
			.definirFoco(FocoAdministracao.ESPORTES)
			.definirNome("Lorem")
			.definirUniversidade(NomeUniversidade.UNICAMP);
		
		jogo = new GuerraDasUniversidades(construtor);
		jogador = jogo.obterJogador();
	}
	
	@Test
	public void testarObterEstruturasDisponiveis() {
		Object[] disp = jogador.obterEstruturasDisponiveis().toArray();
		Estrutura[] esperado = {SALA_AULA, BANDEJAO, PRACA_CENTRAL,
				CENTRO_ESPORTES, CHURRASCO_DEBATE, FESTA};
		
		assertArrayEquals(esperado, disp);
	}

	@Test
	public void testarObterPontosEnsino() {
		assertEquals(1000, jogador.obterPontosEnsino());
		jogo.atualizarEventos();
		assertEquals(1015, jogador.obterPontosEnsino());
		jogo.atualizarEventos();
		assertEquals(1030, jogador.obterPontosEnsino());
	}

	@Test
	public void testarObterFoco() {
		assertEquals(0, jogador.obterFoco());
		jogo.atualizarEventos();
		assertEquals(0, jogador.obterFoco());
	}

	@Test
	public void testarObterHP() {
		assertEquals(10, jogador.obterHP());
		jogo.atualizarEventos();
		assertEquals(10, jogador.obterHP());
	}

	@Test
	public void testarObterInfoSalas() {
		assertEquals(0, jogador.obterInfoSalas().size());
	}

	@Test
	public void testarObterTaxaManutencao() {
		assertEquals(0, jogador.obterTaxaManutencao());
	}

	@Test
	public void testarObterTaxaFuncionarios() {
		assertEquals(0, jogador.obterTaxaFuncionarios());
	}
	
	@Test
	public void testarObterTaxaFoco() {
		assertEquals(0, jogador.obterTaxaFoco());
	}
	
	@Test
	public void testarObterTaxaPontosEnsino(){
		assertEquals(15, jogador.obterTaxaPontosEnsino());
	}

	@Test
	public void testarComprarSala() {
		assertTrue(jogador.comprarEstrutura(SALA_AULA));
		assertEquals(1, jogador.obterTaxaManutencao());
		assertEquals(600, jogador.obterPontosEnsino());
		
		Object[] disp = jogador.obterEstruturasDisponiveis().toArray();
		Estrutura[] esperado = {SALA_AULA, PROFESSOR, ALUNO, BANDEJAO,
				SETOR_DADOS, PRACA_CENTRAL, CHURRASCO_DEBATE, FESTA, SEMINARIO};
		assertArrayEquals(esperado, disp);
		
		assertEquals(1, jogador.obterInfoSalas().size());
		
		SalaAula sala = new SalaAula();
		assertEquals(new StatusSalaAula(sala), jogador.obterInfoSalas().get(0));
	}

	@Test
	public void testarContratarProfessor() {
		assertFalse(jogador.comprarEstrutura(PROFESSOR));
		jogador.comprarEstrutura(SALA_AULA);
		assertTrue(jogador.comprarEstrutura(PROFESSOR));
		assertEquals(400, jogador.obterPontosEnsino());
		
		SalaAula sala = new SalaAula();
		sala.alterarProfessor(true);
		
		assertEquals(new StatusSalaAula(sala), jogador.obterInfoSalas().get(0));
	}

	@Test
	public void testarMatricularAluno() {
		assertFalse(jogador.comprarEstrutura(ALUNO));
		jogador.comprarEstrutura(SALA_AULA);
		assertTrue(jogador.comprarEstrutura(ALUNO));
		assertEquals(525, jogador.obterPontosEnsino());
		
		SalaAula sala = new SalaAula();
		sala.definirQtdAlunos(1);
		
		assertEquals(new StatusSalaAula(sala), jogador.obterInfoSalas().get(0));
	}

	@Test
	public void testarComprarBandejao() {
		assertTrue(jogador.comprarEstrutura(BANDEJAO));
		assertEquals(400, jogador.obterPontosEnsino());
		
		assertEquals(2, jogador.obterTaxaManutencao());
		assertEquals(13, jogador.obterFocoMaximo());
		
		Object[] disp = jogador.obterEstruturasDisponiveis().toArray();
		Estrutura[] esperado = {SALA_AULA, PRACA_CENTRAL,
				CHURRASCO_DEBATE, FESTA, SOBREMESA_BANDEJAO};
		assertArrayEquals(esperado, disp);
	}

	@Test
	public void testarComprarSetorDados() {
		assertFalse(jogador.comprarEstrutura(SETOR_DADOS));
		jogador.comprarEstrutura(SALA_AULA);
		assertTrue(jogador.comprarEstrutura(SETOR_DADOS));
		assertEquals(100, jogador.obterPontosEnsino());
		
		assertEquals(4, jogador.obterTaxaManutencao());
		assertEquals(25, jogador.obterTaxaPontosEnsino());
	}

	@Test
	public void testarComprarPracaCentral() {
		assertTrue(jogador.comprarEstrutura(PRACA_CENTRAL));
		assertEquals(600, jogador.obterPontosEnsino());
		
		assertEquals(2, jogador.obterTaxaManutencao());
		assertEquals(1, jogador.obterTaxaFoco());
	}

	@Test
	public void testarComprarCentroEsportes() {
		assertTrue(jogador.comprarEstrutura(CENTRO_ESPORTES));
		assertEquals(300, jogador.obterPontosEnsino());
		
		assertEquals(2, jogador.obterTaxaManutencao());
		assertEquals(1, jogador.obterTaxaFoco());
	}

	@Test
	public void testarConcederAumentoSalarial() {
		assertFalse(jogador.comprarEstrutura(AUMENTO_SALARIAL));
		jogador.comprarEstrutura(SALA_AULA);
		assertFalse(jogador.comprarEstrutura(AUMENTO_SALARIAL));
		for(int i = 0; i < 7; i++)
			jogo.atualizarEventos();
		assertTrue(jogador.comprarEstrutura(AUMENTO_SALARIAL));
		assertEquals(5, jogador.obterPontosEnsino());
		
		assertEquals(1, jogador.obterTaxaManutencao());
		assertEquals(2, jogador.obterTaxaFuncionarios());
		assertEquals(18, jogador.obterTaxaPontosEnsino());
	}

	@Test
	public void testarPromoverChurrascoDebate() {

	}

	@Test
	public void testarPromoverFesta() {

	}

	@Test
	public void testarServirSobremesaBandejao() {

	}

	@Test
	public void testarPromoverSeminario() {

	}

	@Test
	public void testarComprarGuardaUniversitaria() {

	}
	
	@Test
	public void testarAtacar(){
	
	}
	
	@Test
	public void testarObterFocoMaximo() {
	
	}

	@Test
	public void testarObterNomeUniversidade(){
	
	}

	@Test
	public void testarObterPontosEnsinoMaximo() {
	
	}

}
