package com.mac242.guerradasuniversidades.core.modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Classe fachada do objeto Jogador, responsável por permitir o acesso 
 * apenas a métodos autorizados a serem executados externamente ao modelo lógico
 * do jogo. Realiza todas as checagens antes executar um método, retornando informações
 * sobre o sucesso ou não de uma operação.
 * 
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 

 */
public class FachadaJogador {
	private Jogador jogador;
	private GerenteEstruturas gerente;
	private GuerraDasUniversidades jogo;

	/**
	 * Construtor da fachada do jogador.
	 * @param jogador O jogador ao qual a fachada está associada.
	 * @param jogo O jogo ao qual a fachada está associada.
	 */
	public FachadaJogador(Jogador jogador, GuerraDasUniversidades jogo) {
		this.jogador = jogador;
		this.jogo = jogo;
		this.gerente = jogador.obterGerenteEstruturas();
	}

	/**
	 * Método responsável por retornar uma lista contendo as estruturas
	 * que podem ser compradas no momento, dadas as estruturas que o 
	 * jogador possui e seu saldo de PE.
	 * @return A lista de estruturas disponíveis..
	 */
	public List<Estrutura> obterEstruturasDisponiveis() {
		List<Estrutura> disponiveis = new ArrayList<Estrutura>();
		
		for(Estrutura e : Estrutura.values()){
			if(gerente.estruturaDisponivel(e, jogador.obterPontosEnsino()))
				disponiveis.add(e);
		}
		
		return disponiveis;
	}

	/**
	 * @return O número de pontos de ensino que o jogador possui
	 * atualmente.
	 */
	public int obterPontosEnsino() {
		return jogador.obterPontosEnsino();
	}

	/**
	 * @return O foco que o jogador possui atualmente.
	 */
	public int obterFoco() {
		return jogador.obterFoco();
	}

	/**
	 * @return O número de pontos de visa que o jogador possui atualmente.
	 */
	public int obterHP() {
		return jogador.obterHP();
	}

	/**
	 * Método responsável por retornar uma lista contendo em cada posição
	 * o status de uma sala de aula que o jogador possui, informando se ela 
	 * possui professor e o número de alunos na sala.
	 * @return A lista com o status das salas da universidade.
	 */
	public List<StatusSalaAula> obterInfoSalas() {
		return gerente.obterInfoSalas();
	}

	/**
	 * @return A taxa de manutenção diária cobrada do jogador.
	 */
	public int obterTaxaManutencao() {
		return jogador.obterTaxaManutencao();
	}

	/**
	 * @return A taxa diária da folha de salários cobrada do jogador.  
	 */
	public int obterTaxaFuncionarios() {
		return jogador.obterTaxaFuncionarios();
	}
	
	/**
	 * @return A taxa de crescimento do foco do jogador por dia.
	 */
	public int obterTaxaFoco() {
		return jogador.obterTaxaFoco();
	}
	
	/**
	 * @return A taxa de crescimento do número de pontos de ensino do
	 * jogador por segundo.
	 */
	public int obterTaxaPontosEnsino(){
		return jogador.obterTaxaPontosEnsino();
	}
	
	/**
	 * Método responsável por tentar comprar uma estrutura passada por parâmetro.
	 * Caso a estrutura não esteja disponível retorna falso, caso contrário
	 * realiza a compra e retorna verdadeiro.
	 * @param e A estrutura a ser comprada.
	 * @return Verdadeiro caso a compra tenha sido feita com sucesso ou false
	 * em caso contrário.
	 */
	public boolean comprarEstrutura(Estrutura e){
		if(!gerente.estruturaDisponivel(e, jogador.obterPontosEnsino()))
			return false;
		switch (e) {
		case SALA_AULA:
			gerente.comprarSala();
			break;
		case PROFESSOR:
			gerente.contratarProfessor();
			break;
		case ALUNO:
			gerente.matricularAluno();
			break;
		case BANDEJAO:
			gerente.comprarBandejao();
			break;
		case SETOR_DADOS:
			gerente.comprarSetorDados();
			break;
		case PRACA_CENTRAL:
			gerente.comprarPracaCentral();
			break;
		case CENTRO_ESPORTES:
			gerente.comprarCentroEsportes();
			break;
		case AUMENTO_SALARIAL:
			gerente.concederAumentoSalarial();
			break;
		case CHURRASCO_DEBATE:
			gerente.promoverChurrascoDebate();
			break;
		case FESTA:
			gerente.promoverFesta();
			break;
		case SOBREMESA_BANDEJAO:
			gerente.servirSobremesaBandejao();
			break;
		case SEMINARIO:
			gerente.promoverSeminario();
			break;
		case GUARDA_UNIVERSITARIA:
			gerente.comprarGuardaUniversitaria();
			break;
		default:
			return false;
		}
		
		return true;
	}
	
	/**
	 * Método responsável por tentar realizar um ataque a um alvo definido no
	 * parâmetro. 
	 * @param alvo O alvo do ataque.
	 * @return O resultado do ataque dependendo das condições atuais do jogador.
	 */
	public ResultadoAtaque atacar(NomeUniversidade alvo){
		Set<NomeUniversidade> vivos = jogo.obterVivos();
		
		if(jogador.obterFoco() == 0)
			return ResultadoAtaque.FOCO_ZERO;
		if(jogador.calcularPoderAtaque() == 0)
			return ResultadoAtaque.SEM_SALA_COMPLETA;
		if(alvo.equals(jogador.obterNomeUniversidade()))
			return ResultadoAtaque.AUTO_ATAQUE;
		if(!vivos.contains(alvo))
			return ResultadoAtaque.ALVO_MORTO;
		
		jogador.atacar(jogo.obterTipoJogador(alvo));
		
		return ResultadoAtaque.SUCESSO;
	}
	
	/**
	 * @return O foco máximo do jogador.
	 */
	public int obterFocoMaximo() {
		return jogador.obterFocoMaximo();
	}
	
	/**
	 * @return O nome da universidade do jogador.
	 */
	public NomeUniversidade obterNomeUniversidade(){
		return jogador.obterNomeUniversidade();
	}

	/**
	 * @return O número máximo de pontos de ensino.
	 */
	public int obterPontosEnsinoMaximo() {
		return jogador.obterPontosEnsinoMaximo();
	}

}
