package com.mac242.guerradasuniversidades.core.modelo;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe representante de uma notificação do jogo.
 */
public class Notificacao {
	private String nome;
	private NomeUniversidade universidade;
	private TipoNotificacao tipo;
	private Estrutura estrutura;

	/**
	 * @return O nome do jogador gerador da notificação
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome O nome do jogador gerador da notificação
	 * @return this para encadeamento de métodos
	 */
	public Notificacao setNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	/**
	 * @return O nome da universidade do jogador gerador da notificação
	 */
	public NomeUniversidade getUniversidade() {
		return universidade;
	}

	/**
	 * @param nome O nome da universidade do jogador gerador da notificação
	 * @return this para encadeamento de métodos
	 */
	public Notificacao setUniversidade(NomeUniversidade universidade) {
		this.universidade = universidade;
		return this;
	}

	/**
	 * @return O tipo da notificação
	 */
	public TipoNotificacao getTipo() {
		return tipo;
	}

	/**
	 * @param nome O tipo da notificação
	 * @return this para encadeamento de métodos
	 */
	public Notificacao setTipo(TipoNotificacao tipo) {
		this.tipo = tipo;
		return this;
	}

	/**
	 * @return A estrutura relacionada à notificação
	 */
	public Estrutura getEstrutura() {
		return estrutura;
	}

	/**
	 * @param nome A estrutura relacionada à notificação
	 * @return this para encadeamento de métodos
	 */
	public Notificacao setEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
		return this;
	}
	
	public String toString(){
		return nome + "(" + universidade + ") " + tipo + " " + estrutura;
	}

}
