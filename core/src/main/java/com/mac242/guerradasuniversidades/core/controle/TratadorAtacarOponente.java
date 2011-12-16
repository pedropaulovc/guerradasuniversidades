package com.mac242.guerradasuniversidades.core.controle;

import com.mac242.guerradasuniversidades.core.modelo.FachadaJogador;
import com.mac242.guerradasuniversidades.core.modelo.NomeUniversidade;
import com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades;

import react.UnitSlot;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsável por tratar eventos de ataque de oponentes na tela Oponentes.
 */
public class TratadorAtacarOponente extends UnitSlot {
	
	private FachadaJogador jogador;
	private NomeUniversidade oponente;
	private VisaoGuerraDasUniversidades visao;

	/**
	 * Método setter do jogador atacante
	 * @param jogador O objeto representante do jogador humano obtido da instância 
	 * do jogo.
	 * @return this para encadeamento de métodos.
	 */
	public TratadorAtacarOponente definirJogador(FachadaJogador jogador){
		this.jogador = jogador;
		return this;
	}
	
	/**
	 * Método setter do alvo do ataque
	 * @param jogador O nome da universidade alvo do ataque.
	 * @return this para encadeamento de métodos.
	 */
	public TratadorAtacarOponente definirOponente(NomeUniversidade oponente){
		this.oponente = oponente;
		return this;
	}
	
	/**
	 * Método setter da classe principal da visão do jogo
	 * @param jogador A instância da classe principal da visão do jogo. 
	 * @return this para encadeamento de métodos.
	 */
	public TratadorAtacarOponente definirVisao(VisaoGuerraDasUniversidades visao){
		this.visao = visao;
		return this;
	}
	
	/**
	 * Método responsável por desferir um ataque ao alvo definido anteriormente
	 * e notificar o usuário através da visão do jogo o resultado.
	 */
	@Override
	public void onEmit() {
		String aviso = "";
		
		switch (jogador.atacar(oponente)) {
		case ALVO_MORTO:
			aviso = "O alvo está morto. Ataque falhou.";
			break;
		case FOCO_ZERO:
			aviso = "Você tem FO = 0. Ataque falhou.";
			break;
		case AUTO_ATAQUE:
			aviso = "Autoflagelação? Ataque falhou.";
			break;
		case SEM_SALA_COMPLETA:
			aviso = "Você não tem uma sala completa. Ataque falhou.";
			break;
		default:
			break;
		}
		if(aviso != "")
			visao.obterTelaPrincipal().adicionarAviso(aviso);
		visao.exibirTela(visao.obterTelaPrincipal());
	}

}
