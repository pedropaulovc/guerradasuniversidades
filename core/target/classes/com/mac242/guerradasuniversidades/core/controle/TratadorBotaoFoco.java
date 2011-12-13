package com.mac242.guerradasuniversidades.core.controle;

import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterConstrutor;
import react.UnitSlot;

import com.mac242.guerradasuniversidades.core.modelo.FocoAdministracao;
import com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsável por tratar eventos de definição do foco da administração na tela Iniciar. 
 */
public class TratadorBotaoFoco extends UnitSlot{
	private FocoAdministracao foco;
	private VisaoGuerraDasUniversidades visao;
	private String nome;
	
	/**
	 * Construtor do tratador.
	 * @param foco O foco escolhido pelo usuário.
	 * @param visao A instância da classe principal da visão do jogo
	 * @param nome O nome do jogador
	 */
	public TratadorBotaoFoco(FocoAdministracao foco, VisaoGuerraDasUniversidades visao, String nome){
		this.foco = foco;
		this.visao = visao;
		this.nome = nome;
	}
	
	/**
	 * Método responsável por definir o foco e o nome do jogador no construtor do jogo
	 * e exibir a tela para a escolha da universidade.
	 */
	@Override
	public void onEmit() {
		obterConstrutor().definirFoco(foco).definirNome(nome);
		visao.exibirTela(visao.obterUniversidades());
	}
}
