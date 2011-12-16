package com.mac242.guerradasuniversidades.core.testes;

import org.junit.Before;
import org.junit.Test;

import com.mac242.guerradasuniversidades.core.modelo.ConstrutorJogador;
import com.mac242.guerradasuniversidades.core.modelo.FocoAdministracao;
import com.mac242.guerradasuniversidades.core.modelo.NomeUniversidade;

import static org.junit.Assert.assertEquals;

public class TestesConstrutorJogador {
	
	private ConstrutorJogador construtor;

	@Before
	public void inicializar(){
		construtor = new ConstrutorJogador()
			.definirFoco(FocoAdministracao.BIOMEDICAS)
			.definirNome("Lorem Ipsum")
			.definirUniversidade(NomeUniversidade.USP);
	}
	
	@Test
	public void testarObterFoco(){
		assertEquals(FocoAdministracao.BIOMEDICAS, construtor.obterFoco());
	}

	@Test
	public void testarObterNome(){
		assertEquals("Lorem Ipsum", construtor.obterNome());
	}

	@Test
	public void testarObterUniversidade(){
		assertEquals(NomeUniversidade.USP, construtor.obterUniversidade());
	}
	
}
