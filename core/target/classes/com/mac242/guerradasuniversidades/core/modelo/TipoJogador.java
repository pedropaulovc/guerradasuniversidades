package com.mac242.guerradasuniversidades.core.modelo;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Interface de um jogador no jogo, implementada por diferentes tipo de jogador como
 * humano ou máquina.
 */
public interface TipoJogador {

	/**
	 * Incrementa o PE do jogador com a taxa. Chamado uma vez por segundo.
	 */
	public void atualizarSegundo();

	/**
	 * Atualiza o foco e os gastos diários.
	 */
	public void atualizarDia();

	/**
	 * @return Os pontos de ensino atuais do jogador.
	 */
	public int obterPontosEnsino();

	/**
	 * @return O foco atual do jogador
	 */
	public int obterFoco();

	/**
	 * @return Os pontos de vida atuais do jogador.
	 */
	public int obterHP();

	/**
	 * @return A taxa de manutenção atual do jogador por dia.
	 */
	public int obterTaxaManutencao();

	/**
	 * @return A taxa de folha de pagamento do jogador por dia
	 */
	public int obterTaxaFuncionarios();

	/**
	 * @return A taxa de foco do jogador por dia
	 */
	public int obterTaxaFoco();

	/**
	 * @return O gerente de estruturas associado ao jogador.
	 */
	public GerenteEstruturas obterGerenteEstruturas();

	/**
	 * @return A taxa de pontos de ensino do jogador por segundo
	 */
	public int obterTaxaPontosEnsino();

	/**
	 * @return O limite de foco do jogador
	 */
	public int obterFocoMaximo();

	/**
	 * @return O nome do jogador
	 */
	public String obterNome();

	/**
	 * Método da inteligência artifical do jogador para realizar a jogada. 
	 */
	public void realizarJogada();

	/**
	 * @return Um objeto apresentando as informações do jogador
	 */
	public StatusJogador obterStatus();

	/**
	 * @return O nome da universidade do jogador.
	 */
	public NomeUniversidade obterNomeUniversidade();

	/**
	 * @return O poder de um ataque segundo a fórmula:
	 * se possui pelo menos uma classe completa : FO 
	 * senão : 0;
	 */
	public int calcularPoderAtaque();

	/**
	 * Método responsável por realizar um ataque ao alvo definido em parâmetro.
	 * @param alvo O jogador alvo do ataque.
	 */
	public void atacar(TipoJogador alvo);

	/**
	 * Método a ser invocado caso ocorra um ataque ao jogador com o poder 
	 * passado via parâmetro.
	 * @param poder O poder do ataque.
	 */
	public void receberAtaque(int poder);

	/**
	 * @return O máximo de pontos de ensino que o jogador pode ter.
	 */
	public int obterPontosEnsinoMaximo();
}
