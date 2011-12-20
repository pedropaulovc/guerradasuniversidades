package com.mac242.guerradasuniversidades.core.modelo;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe empacotadora das variáveis de status do jogador em um determinado instante.
 */
public class StatusJogador {
	private String nome;
	private int HP, PE, FO, maxFO, taxaPE, taxaFO, taxaManutencao, taxaFuncionarios;

	/**
	 * @return O nome do jogador
	 */
	public String obterNome() {
		return nome;
	}
	/**
	 * @param nome the nome do jogador a ser definidor
	 * @return this para encadeamento de métodos
	 */
	public StatusJogador definirNome(String nome) {
		this.nome = nome;
		return this;
	}
	/**
	 * @return O HP
	 */
	public int obterHP() {
		return HP;
	}
	/**
	 * @param HP O HP a ser definido
	 * @return this para encadeamento de métodos
	 */
	public StatusJogador definirHP(int HP) {
		this.HP = HP;
		return this;
	}
	/**
	 * @return O PE
	 */
	public int obterPE() {
		return PE;
	}
	/**
	 * @param PE the pE a ser definido
	 * @return this para encadeamento de métodos
	 */
	public StatusJogador definirPE(int PE) {
		this.PE = PE;
		return this;
	}
	/**
	 * @return O FO
	 */
	public int obterFO() {
		return FO;
	}
	/**
	 * @param FO O FO a ser definido
	 * @return this para encadeamento de métodos
	 */
	public StatusJogador definirFO(int FO) {
		this.FO = FO;
		return this;
	}
	/**
	 * @return O maxFO
	 */
	public int obterMaxFO() {
		return maxFO;
	}
	/**
	 * @param maxFO O maxFO a ser definido
	 * @return this para encadeamento de métodos
	 */
	public StatusJogador definirMaxFO(int maxFO) {
		this.maxFO = maxFO;
		return this;
	}
	/**
	 * @return A taxaPE
	 */
	public int obterTaxaPE() {
		return taxaPE;
	}
	/**
	 * @param taxaPE A taxaPE a ser definida
	 * @return this para encadeamento de métodos
	 */
	public StatusJogador definirTaxaPE(int taxaPE) {
		this.taxaPE = taxaPE;
		return this;
	}
	/**
	 * @return A taxaFO
	 */
	public int obterTaxaFO() {
		return taxaFO;
	}
	/**
	 * @param taxaFO A taxaFO a ser definida
	 * @return this para encadeamento de métodos
	 */
	public StatusJogador definirTaxaFO(int taxaFO) {
		this.taxaFO = taxaFO;
		return this;
	}
	/**
	 * @return A taxaManutencao
	 */
	public int obterTaxaManutencao() {
		return taxaManutencao;
	}
	/**
	 * @param taxaManutencao A taxaManutencao a ser definida
	 * @return this para encadeamento de métodos
	 */
	public StatusJogador definirTaxaManutencao(int taxaManutencao) {
		this.taxaManutencao = taxaManutencao;
		return this;
	}
	/**
	 * @return A de taxaFuncionarios
	 */
	public int obterTaxaFuncionarios() {
		return taxaFuncionarios;
	}
	/**
	 * @param taxaFuncionarios A taxaFuncionarios a ser definida
	 * @return this para encadeamento de métodos
	 */
	public StatusJogador definirTaxaFuncionarios(int taxaFuncionarios) {
		this.taxaFuncionarios = taxaFuncionarios;
		return this;
	}
}
