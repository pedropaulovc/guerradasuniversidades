package com.mac242.guerradasuniversidades.core.controle;

import com.mac242.guerradasuniversidades.core.visao.TipoTela;
import com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades;

import react.UnitSlot;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsável por tratar eventos de troca de tela
 */
public class TratadorTrocarTela extends UnitSlot {
	
	private VisaoGuerraDasUniversidades visao;
	private TipoTela tela;

	/**
	 * O construtor do tratador.
	 * @param visao A instância da classe principal da visão do jogo
	 * @param tela A tela a ser aberta.
	 */
	public TratadorTrocarTela(VisaoGuerraDasUniversidades visao, TipoTela tela){
		this.visao = visao;
		this.tela = tela;
	}
	
	/**
	 * Método responsável por trocar a tela da visão do jogo para a tela escolhida
	 * no construtor. 
	 */
	@Override
	public void onEmit() {
		visao.exibirTela(tela);
	}

}
