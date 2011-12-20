package com.mac242.guerradasuniversidades.core.controle;

import react.UnitSlot;
import com.mac242.guerradasuniversidades.core.visao.ImagemEstrutura;
import com.mac242.guerradasuniversidades.core.visao.TelaPrincipal;

import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterJogador;

/**
 * Classe responsável por tratar eventos de compra de estruturas genéricas na TelaPrincial.
 * 
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 */
public class TratadorBotaoEstrutura extends UnitSlot {

	private ImagemEstrutura imgEstrutura;
	private TelaPrincipal tela;

	/**
	 * Construtor do tratador.
	 * @param imgEstrutura A estrutura a ser comprada.
	 * @param tela O objeto TelaPrincipal da visão do jogo.
	 */
	public TratadorBotaoEstrutura(ImagemEstrutura imgEstrutura, TelaPrincipal tela) {
		this.imgEstrutura = imgEstrutura;
		this.tela = tela;
	}

	/**
	 * Método responsável por realizar a compra da estrutura definida no construtor, 
	 * atualizar o campus com a nova estrutura comprada ou informar o usuário de um erro.
	 */
	@Override
	public void onEmit() {
		if (obterJogador().comprarEstrutura(imgEstrutura.obterEstrutura())) {
			tela.obterEstruturas().put(imgEstrutura, true);
			tela.atualizarCampus();
		} else {
			tela.adicionarAviso("Estrutura indisponível!");
		}
	}
}
