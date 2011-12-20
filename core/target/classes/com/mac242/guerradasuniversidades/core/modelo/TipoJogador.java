package com.mac242.guerradasuniversidades.core.modelo;

/**
 * Interface de um jogador no jogo, implementada por diferentes tipo de jogador como
 * humano ou máquina.
 * 
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
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
	 * Método getter dos pontos de ensino do jogador.
	 * @return Os pontos de ensino atuais do jogador.
	 */
	public int obterPontosEnsino();

	/**
	 * Método getter do foco do jogador.
	 * @return O foco atual do jogador
	 */
	public int obterFoco();

	/**
	 * Método getter dos HP do jogador.
	 * @return Os pontos de vida atuais do jogador.
	 */
	public int obterHP();

	/**
	 * Método getter da taxa de manutenção do jogador.
	 * @return A taxa de manutenção atual do jogador por dia.
	 */
	public int obterTaxaManutencao();

	/**
	 * Método getter da taxa de manutenção do jogador.
	 * @return A taxa de folha de pagamento do jogador por dia
	 */
	public int obterTaxaFuncionarios();

	/**
	 * Método getter da taxa de foco do jogador.
	 * @return A taxa de foco do jogador por dia
	 */
	public int obterTaxaFoco();

	/**
	 * Método getter do gerente de estruturas do jogador.
	 * @return O gerente de estruturas associado ao jogador.
	 */
	public GerenteEstruturas obterGerenteEstruturas();

	/**
	 * Método getter da taxa de pontos de ensino do jogador.
	 * @return A taxa de pontos de ensino do jogador por segundo
	 */
	public int obterTaxaPontosEnsino();

	/**
	 * Método getter do foco máximo do jogador.
	 * @return O limite de foco do jogador
	 */
	public int obterFocoMaximo();

	/**
	 * Método getter do nome do jogador.
	 * @return O nome do jogador
	 */
	public String obterNome();

	/**
	 * Método da inteligência artifical do jogador para realizar a jogada. 
	 */
	public void realizarJogada();

	/**
	 * Método getter do status do jogador.
	 * @return Um objeto apresentando as informações do jogador
	 */
	public StatusJogador obterStatus();

	/**
	 * Método getter do nome da universidade do jogador.
	 * @return O nome da universidade do jogador.
	 */
	public NomeUniversidade obterNomeUniversidade();

	/**
	 * Método que calcula o poder de um ataque do jogador
	 * 
	 * O poder é calculado com a fórmula
	 * 		se possui pelo menos uma classe completa : FO 
	 * 		senão : 0;
	 * @return O poder de um ataque
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
	 * Método getter do número máximo de pontos de ensino do jogador
	 * @return O máximo de pontos de ensino que o jogador pode ter.
	 */
	public int obterPontosEnsinoMaximo();
}
