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
	public String obterNome() {
		return nome;
	}

	/**
	 * @param nome O nome do jogador gerador da notificação
	 * @return this para encadeamento de métodos
	 */
	public Notificacao definirNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	/**
	 * @return O nome da universidade do jogador gerador da notificação
	 */
	public NomeUniversidade obterUniversidade() {
		return universidade;
	}

	/**
	 * @param universidade O nome da universidade do jogador gerador da notificação
	 * @return this para encadeamento de métodos
	 */
	public Notificacao definirUniversidade(NomeUniversidade universidade) {
		this.universidade = universidade;
		return this;
	}

	/**
	 * @return O tipo da notificação
	 */
	public TipoNotificacao obterTipo() {
		return tipo;
	}

	/**
	 * @param tipo O tipo da notificação
	 * @return this para encadeamento de métodos
	 */
	public Notificacao definirTipo(TipoNotificacao tipo) {
		this.tipo = tipo;
		return this;
	}

	/**
	 * @return A estrutura relacionada à notificação
	 */
	public Estrutura obterEstrutura() {
		return estrutura;
	}

	/**
	 * @param estrutura A estrutura relacionada à notificação
	 * @return this para encadeamento de métodos
	 */
	public Notificacao obterEstrutura(Estrutura estrutura) {
		this.estrutura = estrutura;
		return this;
	}
	
	public String toString(){
		return nome + "(" + universidade + ") " + tipo + " " + estrutura;
	}

}
