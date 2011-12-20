package com.mac242.guerradasuniversidades.core.controle;

import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterJogador;

import com.mac242.guerradasuniversidades.core.modelo.Estrutura;
import com.mac242.guerradasuniversidades.core.visao.ImagemEstrutura;
import com.mac242.guerradasuniversidades.core.visao.TelaPrincipal;

import react.UnitSlot;

/**
 * Classe responsável por tratar eventos de compra de salas de aula na TelaPrincipal.
 * 
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 */
public class TratadorBotaoBlocoEnsino extends UnitSlot {
	private TelaPrincipal tela;

	/**
	 * Construtor do tratador
	 * @param tela O objeto TelaPrincipal da visão do jogo.
	 */
	public TratadorBotaoBlocoEnsino(TelaPrincipal tela){
		this.tela = tela;
	}
	
	/**
	 * Método responsável por realizar a compra de uma sala de aula e atualizar
	 * o campus com a nova sala comprada ou informar o usuário de um erro.
	 */
	public void onEmit() {
		if(!obterJogador().comprarEstrutura(Estrutura.SALA_AULA)){
			tela.adicionarAviso("Estrutura indisponível!");
			return;
		}
		
		int i = 0;
		while(tela.obterEstruturas().get(ImagemEstrutura.values()[i]))
			i++;
	
		tela.obterEstruturas().put(ImagemEstrutura.values()[i], true);
		
		tela.atualizarCampus();
		return;
	}
}
