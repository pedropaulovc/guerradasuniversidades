package com.mac242.guerradasuniversidades.core.modelo;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.ALUNO;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.AUMENTO_SALARIAL;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.BANDEJAO;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.CENTRO_ESPORTES;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.CHURRASCO_DEBATE;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.FESTA;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.GUARDA_UNIVERSITARIA;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.PRACA_CENTRAL;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.PROFESSOR;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.SALA_AULA;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.SEMINARIO;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.SETOR_DADOS;
import static com.mac242.guerradasuniversidades.core.modelo.Estrutura.SOBREMESA_BANDEJAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.mac242.guerradasuniversidades.core.util.Observable;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsável por gerir as estruturas possuídas pelo jogador além
 * de gastos relacionados a estas estruturas. Esta classe é interna ao modelo
 * lógico do jogo, operando segundo o modelo de programação por contrato.
 */
public class GerenteEstruturas extends Observable {
	private List<SalaAula> salas = new ArrayList<SalaAula>();

	private int deltaTaxaPE;
	private int deltaTaxaFO;
	private int deltaTaxaManutencao;
	private int deltaTaxaFuncionarios;
	private int deltaFOMax;
	private int deltaFO;
	private int gastos;
	private Map<Estrutura, Integer> custos;
	private Map<Estrutura, Integer> disp;
	private List<Integer> seminariosEmAndamento;

	/**
	 * Construtor do gerente.
	 * @param foco O foco da administração do jogador.
	 */
	public GerenteEstruturas(FocoAdministracao foco) {
		calcularCustos(foco);
		calcularLimites();
		seminariosEmAndamento = new ArrayList<Integer>();
	}

	/**
	 * Método responsável por definir os custos de cada estrutura
	 * para o jogador dependendo do foco da administração passado por
	 * parâmetro.
	 * @param foco O foco da administração do jogador
	 */
	private void calcularCustos(FocoAdministracao foco) {
		custos = new HashMap<Estrutura, Integer>();
		custos.put(SALA_AULA, 400);
		custos.put(PROFESSOR, 200);
		custos.put(ALUNO, 75);
		custos.put(BANDEJAO, 600);
		custos.put(SETOR_DADOS, 500);
		custos.put(PRACA_CENTRAL, 400);
		custos.put(CENTRO_ESPORTES, 700);
		custos.put(AUMENTO_SALARIAL, 700);

		custos.put(CHURRASCO_DEBATE, 400);
		if (foco == FocoAdministracao.HUMANAS)
			custos.put(CHURRASCO_DEBATE, 275);

		custos.put(FESTA, 400);
		if (foco == FocoAdministracao.BIOMEDICAS)
			custos.put(FESTA, 300);

		custos.put(SOBREMESA_BANDEJAO, 250);
		if (foco == FocoAdministracao.BIOMEDICAS)
			custos.put(SOBREMESA_BANDEJAO, 140);

		custos.put(SEMINARIO, 300);
		if (foco == FocoAdministracao.BIOMEDICAS)
			custos.put(SEMINARIO, 200);
		
		custos.put(GUARDA_UNIVERSITARIA, 1500);
	}

	/**
	 * Método responsável por definir os limites de estruturas compráveis pelo jogador
	 * inicialmente.
	 */
	private void calcularLimites() {
		disp = new HashMap<Estrutura, Integer>();
		disp.put(SALA_AULA, 5);
		disp.put(PROFESSOR, 0);
		disp.put(ALUNO, 0);
		disp.put(BANDEJAO, 1);
		disp.put(SETOR_DADOS, -1);
		disp.put(PRACA_CENTRAL, 1);
		disp.put(CENTRO_ESPORTES, 1);
		disp.put(AUMENTO_SALARIAL, -1);
		disp.put(CHURRASCO_DEBATE, 15);
		disp.put(FESTA, Integer.MAX_VALUE);
		disp.put(SOBREMESA_BANDEJAO, 0);
		disp.put(SEMINARIO, -1);
		disp.put(GUARDA_UNIVERSITARIA, 1);
	}

	public void atualizarDia(){
		int encerrados = 0;
		
		for(int i = 0; i < seminariosEmAndamento.size(); i++){
			int tempo = seminariosEmAndamento.remove(i) - 1;
			seminariosEmAndamento.add(i, tempo);
			if(tempo == 0)
				encerrados++;
		}
		
		for(int i = 0; i < encerrados; i++)
			encerrarSeminario();
	}


	/**
	 * Precondição:Supõe que usuário possui PE >= 500 e pelo menos 
	 * um espaço vago.
	 * Efeito: Libera uma nova sala para ser populada 
	 * com professor e alunos. Atualiza limites de compras de estruturas
	 * relacionadas.
	 */
	public void comprarSala() {
		salas.add(new SalaAula());
		deltaTaxaManutencao++;

		int profDisp = disp.get(PROFESSOR);
		int alunoDisp = disp.get(ALUNO);
		disp.put(PROFESSOR, profDisp + 1);
		disp.put(ALUNO, alunoDisp + 10);

		if (disp.get(SETOR_DADOS) == -1)
			disp.put(SETOR_DADOS, 1);
		if (disp.get(AUMENTO_SALARIAL) == -1)
			disp.put(AUMENTO_SALARIAL, 10);
		if (disp.get(SEMINARIO) == -1)
			disp.put(SEMINARIO, Integer.MAX_VALUE);

		comprarEstrutura(SALA_AULA);
	}

	/**
	 * @return Retorna a quantidade de salas que o jogador possui no
	 * momento.
	 */
	public int obterQtdSalas() {
		return salas.size();
	}

	/**
	 * Precondição: Supõe que haja uma sala sem professor.
	 * Efeito: Adiciona um professor à primeira sala sem professor.
	 */
	public void contratarProfessor() {
		int i = 0;
		while (salas.get(i).possuiProfessor())
			i++;
		salas.get(i).alterarProfessor(true);

		comprarEstrutura(PROFESSOR);
	}

	public int obterQtdProfessores() {
		int qtdProfessores = 0;

		for (SalaAula s : salas) {
			if (s.possuiProfessor())
				qtdProfessores++;
		}

		return qtdProfessores;
	}

	/**
	 * Precondição: Supõe que haja uma sala não cheia (10 alunos)
	 * Efeito: Adiciona um aluno à
	 * primeira sala que atender ao requisito.
	 */
	public void matricularAluno() {
		int i = 0;
		while (salas.get(i).estaCheia())
			i++;
		salas.get(i).adicionarAluno();

		comprarEstrutura(ALUNO);
	}

	/**
	 * @return O número total de alunos matriculados na universidade.
	 */
	public int obterQtdAlunos() {
		int qtdAlunos = 0;

		for (SalaAula s : salas) {
			qtdAlunos += s.obterQtdAlunos();
		}

		return qtdAlunos;
	}

	/**
	 * Precondição: Não há bandejão no momento
	 * Efeito: Insere um bandejão ao campus e atualiza o limite 
	 * de compra de sobremesas e custos relacionados.
	 */
	public void comprarBandejao() {
		deltaTaxaManutencao += 2;
		deltaFOMax += 3;

		disp.put(SOBREMESA_BANDEJAO, Integer.MAX_VALUE);

		comprarEstrutura(BANDEJAO);
	}
	
	/**
	 * Precondição: Há um bandejão no campus
	 * Efeito: Atualiza o limite de compra de sobremesas
	 * e custos relacionados.
	 */
	public void destruirBandejao() {
		deltaTaxaManutencao -= 2;
		deltaFOMax -= 3;

		disp.put(SOBREMESA_BANDEJAO, 0);

		destruirEstrutura(BANDEJAO);
	}

	/**
	 * Precondição: Não há setor de dados no campus
	 * Efeito: Insere um setor de dados no campus e atualiza 
	 * custos relacionados
	 */
	public void comprarSetorDados() {
		deltaTaxaManutencao += 3;
		deltaTaxaPE += 10;

		comprarEstrutura(SETOR_DADOS);
	}
	
	/**
	 * Precondição: Há setor de dados no campus
	 * Efeito: Remove um setor de dados no campus e atualiza 
	 * custos relacionados
	 */
	public void destruirSetorDados() {
		deltaTaxaManutencao -= 3;
		deltaTaxaPE -= 10;

		destruirEstrutura(SETOR_DADOS);
	}

	/**
	 * Precondição: Não há praça central no campus.
	 * Efeito: Insere uma praça central no campus e atualiza 
	 * custos relacionados
	 */
	public void comprarPracaCentral() {
		deltaTaxaManutencao += 2;
		deltaTaxaFO++;

		comprarEstrutura(PRACA_CENTRAL);
	}
	
	/**
	 * Precondição: Não há praça central no campus
	 * Efeito: Remove a praça no campus e atualiza 
	 * custos relacionados
	 */
	public void destruirPracaCentral() {
		deltaTaxaManutencao -= 2;
		deltaTaxaFO--;

		destruirEstrutura(PRACA_CENTRAL);
	}

	/**
	 * Precondição: Não há centro de esportes no campus.
	 * Efeito: Insere um centro de esportes no campus e atualiza 
	 * custos relacionados
	 */
	public void comprarCentroEsportes() {
		deltaTaxaManutencao += 2;
		deltaTaxaFO++;

		comprarEstrutura(CENTRO_ESPORTES);
	}
	
	/**
	 * Precondição: Há centro de esportes no campus
	 * Efeito: Remove o centro de esportes do campus e atualiza 
	 * custos relacionados
	 */
	public void destruirCentroEsportes() {
		deltaTaxaManutencao -= 2;
		deltaTaxaFO--;

		destruirEstrutura(CENTRO_ESPORTES);
	}

	/**
	 * Precondição: Há uma sala de aula no campus
	 * Efeito: Concede o aumento e atualiza custos relacionados.
	 */
	public void concederAumentoSalarial() {
		deltaTaxaFuncionarios += 2;
		deltaTaxaPE += 3;

		comprarEstrutura(AUMENTO_SALARIAL);
	}

	/**
	 * Precondição: Houve menos de 15 churrascos debate
	 * Efeito: Realiza o churrasco e atualiza custos relacionados.
	 */
	public void promoverChurrascoDebate() {
		deltaTaxaPE++; 
		
		comprarEstrutura(CHURRASCO_DEBATE);
	}

	/**
	 * Precondição: Não há
	 * Efeito: Promove a festa e atualiza custos relacionados.
	 */
	public void promoverFesta() {
		deltaFO += 2;

		comprarEstrutura(FESTA);
	}

	/**
	 * Precondição: Há um bandejão
	 * Efeito: Serve a sobremesa e atualiza custos relacionados.
	 */
	public void servirSobremesaBandejao() {
		deltaFO++;

		comprarEstrutura(SOBREMESA_BANDEJAO);
	}

	/**
	 * Precondição: Há um bloco de ensino
	 * Efeito: Promove o seminário e atualiza custos relacionados.
	 */
	public void promoverSeminario() {
		deltaTaxaPE += 7;
		seminariosEmAndamento.add(7);
		
		comprarEstrutura(SEMINARIO);
	}
	
	/**
	 * Precondição: Há um seminário em andamento que chegou ao fim
	 * Efeito: Encerra o seminário e atualiza custos relacionados.
	 */
	private void encerrarSeminario() {
		deltaTaxaPE -= 7;
		seminariosEmAndamento.remove(0);
		
		setChanged();
		Notificacao notificacao = new Notificacao()
			.obterEstrutura(SEMINARIO)
			.definirTipo(TipoNotificacao.ATUALIZACAO);
		notifyObservers(notificacao);
	}
	
	/**
	 * Precondição: Nunca foi comprada uma guarda universitária.
	 * Efeito: Compra a guarda e atualiza custos relacionados.
	 */
	public void comprarGuardaUniversitaria(){
		deltaTaxaManutencao += 2;
		
		comprarEstrutura(GUARDA_UNIVERSITARIA);
	}

	/**
	 * Precondição: Há uma guarda universitária no campus.
	 * Efeito: Remove a guarda e atualiza custos relacionados.
	 */
	public void destruirGuardaUniversitaria() {
		deltaTaxaManutencao -= 2;
		disp.put(GUARDA_UNIVERSITARIA, -2);
		
		Notificacao notificacao = new Notificacao()
			.obterEstrutura(GUARDA_UNIVERSITARIA)
			.definirTipo(TipoNotificacao.DESTRUICAO);
		setChanged();
		notifyObservers(notificacao);
	}
	
	/**
	 * Método para a compra de uma estrutura arbitrária definida via parâmetro.
	 * Atualiza os gastos e notifica observadores da compra.
	 * @param e A estrutura a ser comprada.
	 */
	private void comprarEstrutura(Estrutura e) {
		gastos += custos.get(e);

		int disponibilidade = disp.get(e);
		disp.put(e, disponibilidade - 1);

		setChanged();
		Notificacao notificacao = new Notificacao()
			.obterEstrutura(e)
			.definirTipo(TipoNotificacao.COMPRA);
		notifyObservers(notificacao);
	}
	
	/**
	 * Método para a destruição de uma estrutura arbitrária definida via parâmetro.
	 * Atualiza os gastos e notifica observadores da destruição.
	 * @param e A estrutura a ser destruída.
	 */
	private void destruirEstrutura(Estrutura e) {
		int disponibilidade = disp.get(e);
		disp.put(e, disponibilidade + 1);
		
		setChanged();
		Notificacao notificacao = new Notificacao()
			.obterEstrutura(e)
			.definirTipo(TipoNotificacao.DESTRUICAO);
		notifyObservers(notificacao);
	}

	/**
	 * @return A variação da taxa de manutenção desde a última chamada ao
	 *         método.
	 */
	public int obterDeltaTaxaManutencao() {
		int taxa = deltaTaxaManutencao;
		deltaTaxaManutencao = 0;
		return taxa;
	}

	/**
	 * @return A variação da taxa de PE/seg desde a última chamada ao método.
	 */
	public int obterDeltaTaxaPE() {
		int PE = deltaTaxaPE;
		deltaTaxaPE = 0;
		return PE;
	}

	/**
	 * @return Os gastos desde a última chamada ao método.
	 */
	public int obterGastos() {
		int valor = gastos;
		gastos = 0;
		return valor;
	}

	/**
	 * @return a variação da taxa de funcionários desde a última chamada ao
	 *         método.
	 */
	public int obterDeltaTaxaFuncionarios() {
		int funcionarios = deltaTaxaFuncionarios;
		deltaTaxaFuncionarios = 0;
		return funcionarios;
	}

	/**
	 * @return A variação da taxa do foco desde a última chamada ao método.
	 */
	public int obterDeltaTaxaFO() {
		int FO = deltaTaxaFO;
		deltaTaxaFO = 0;
		return FO;
	}

	/**
	 * @return A variação da taxa do foco desde a última chamada ao método.
	 */
	public int obterDeltaFOMax() {
		int FOMax = deltaFOMax;
		deltaFOMax = 0;
		return FOMax;
	}
	
	/**
	 * A variação do foco desde a última chamada ao método.
	 * @return A variação do foco desde a última chamada ao método.
	 */
	public int obterDeltaFO() {
		int varFO = deltaFO;
		deltaFO = 0;
		return varFO;
	}

	/**
	 * Método responsável por quebrar uma estrutura aleatória dentre uma lista de 
	 * possíveis.
	 */
	public void quebrarEstruturaAleatoria() {
		List<Estrutura> possiveis = new ArrayList<Estrutura>();
		
		for(Estrutura e : new Estrutura[] {BANDEJAO, SETOR_DADOS, 
				CENTRO_ESPORTES, PRACA_CENTRAL}){
			if(disp.get(e) == 0)
				possiveis.add(e);
		}
		
		if(possiveis.size() == 0)
			return;
		
		Estrutura candidato = possiveis.get(new Random().nextInt(possiveis.size()));
		
		switch (candidato) {
		case BANDEJAO:
			destruirBandejao();
			break;
		case SETOR_DADOS:
			destruirSetorDados();
			break;
		case CENTRO_ESPORTES:
			destruirCentroEsportes();
			break;
		case PRACA_CENTRAL:
			destruirPracaCentral();
			break;
		default:
			break;
		}
	}

	/**
	 * Método responsável por retornar se uma estrutura pode ser comprada.
	 * @param e A estrutura a ser analisada.
	 * @param PE O saldo de pontos de ensino do jogador no momento.
	 * @return true caso a estrutura possa ser comprada ou false em caso contrário.
	 */
	public boolean estruturaDisponivel(Estrutura e, int PE) {
		return disp.get(e) > 0 && PE >= custos.get(e);
	}

	/**
	 * Método responsável por retornar uma lista contendo em cada posição
	 * o status de uma sala de aula que o jogador possui, informando se ela 
	 * possui professor e o número de alunos na sala.
	 * @return A lista com o status das salas da universidade.
	 */
	public List<StatusSalaAula> obterInfoSalas() {
		List<StatusSalaAula> lista = new ArrayList<StatusSalaAula>();

		for (SalaAula s : salas) {
			lista.add(new StatusSalaAula(s));
		}

		return lista;
	}
	
	/**
	 * Método por remover professor e alunos da primeira sala da fila de salas
	 * da universidade. Insere a sala vazia ao fim da fila.
	 */
	public void esvaziarSala(){
		SalaAula vazia = salas.remove(0);
		vazia.alterarProfessor(false);
		vazia.definirQtdAlunos(0);
		salas.add(vazia);
		
		int dispProf = disp.get(PROFESSOR);
		int dispAluno = disp.get(ALUNO);
		
		disp.put(PROFESSOR, dispProf + 1);
		disp.put(ALUNO, dispAluno + 10);
		
		
		Notificacao notificacao = new Notificacao()
			.obterEstrutura(SALA_AULA)
			.definirTipo(TipoNotificacao.ATUALIZACAO);
		setChanged();
		notifyObservers(notificacao);
	}

	/**
	 * @return true caso possua guarda universitária ou false
	 * caso contrário.
	 */
	public boolean possuiGuardaUniversitaria() {
		return disp.get(GUARDA_UNIVERSITARIA) == 0;
	}
	
}
