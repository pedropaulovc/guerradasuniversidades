package com.mac242.guerradasuniversidades.core.modelo;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe representante de uma sala de aula no jogo. 
 */
public class SalaAula {
	private boolean temProfessor;
	private int qtdAlunos;
	private static final int capacidade = 10;

	/**
	 * @return true caso possua professor, false em caso contrário
	 */
	public boolean possuiProfessor() {
		return temProfessor;
	}

	/**
	 * Método setter para a sala possuir professor 
	 * @param temProfessor true caso passe a ter professor, false
	 * em caso contrário.
	 */
	public void alterarProfessor(boolean temProfessor) {
		this.temProfessor = temProfessor;
	}

	/**
	 * @return A quantidade de alunos na sala
	 */
	public int obterQtdAlunos() {
		return qtdAlunos;
	}

	/**
	 * Método responsável por adicionar um aluno à sala.
	 */
	public void adicionarAluno() {
		qtdAlunos++;
	}
	
	/**
	 * @return true caso a sala tenha a mesma quantidade de alunos que a sua capacidade
	 */
	public boolean estaCheia(){
		return qtdAlunos == capacidade;
	}
	
	/**
	 * @return A capacidade da sala.
	 */
	public static int obterCapacidade(){
		return capacidade;
	}

	/**
	 * Método setter para a quantidade de alunos na sala
	 * @param i A quantidade de alunos na sala.
	 */
	public void definirQtdAlunos(int i) {
		this.qtdAlunos = 0;
	}
}
