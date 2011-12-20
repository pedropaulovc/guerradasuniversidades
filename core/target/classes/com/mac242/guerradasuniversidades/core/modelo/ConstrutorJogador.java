package com.mac242.guerradasuniversidades.core.modelo;

/**
 * Classe representante das configurações iniciais do jogador.
 * 
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 */
public class ConstrutorJogador {
	private String nome;
	private FocoAdministracao foco;
	private NomeUniversidade universidade;

	/**
	 * Método setter do nome do jogador.
	 * @param nome O nome do jogador
	 * @return this para encademento de métodos.
	 */
	public ConstrutorJogador definirNome(String nome){
		this.nome = nome;
		return this;
	}

	/**
	 * Método setter do foco do jogador.
	 * @param foco O foco do jogador
	 * @return this para encademento de métodos.
	 */
	public ConstrutorJogador definirFoco(FocoAdministracao foco){
		this.foco = foco;
		return this;
	}
	
	/**
	 * Método setter da universidade do jogador.
	 * @param universidade A universidade escolhida pelo jogador.
	 * @return this para encademento de métodos.
	 */
	public ConstrutorJogador definirUniversidade(NomeUniversidade universidade){
		this.universidade = universidade;
		return this;
	}
	
	/**
	 * Método getter do nome do jogador.
	 * @return O nome do jogador.
	 */
	public String obterNome() {
		return nome;
	}

	/**
	 * Método getter do foco do jogador.
	 * @return O foco do jogador.
	 */
	public FocoAdministracao obterFoco() {
		return foco;
	}

	/**
	 * Método getter do nome da universidade do jogador.
	 * @return A universidade escolhida pelo jogador.
	 */
	public NomeUniversidade obterUniversidade() {
		return universidade;
	}

	/**
	 * Método responsável por criar uma instância de um Jogador associada
	 * a uma instância de GuerraDasUniversidades.
	 * @param jogo O jogo ao qual o jogador estará associado.
	 * @return Uma nova instância de Jogador com as configurações definidas
	 * nesta classe.
	 */
	public Jogador construir(GuerraDasUniversidades jogo) {
		return new Jogador(this, jogo);
	}

}
