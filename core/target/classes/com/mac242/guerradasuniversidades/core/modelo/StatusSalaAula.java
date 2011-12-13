package com.mac242.guerradasuniversidades.core.modelo;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe representante do status uma sala de aula no jogo. 
 */
public class StatusSalaAula {
	private SalaAula sala;
	
	/**
	 * O construtor do objeto
	 * @param sala A sala ao qual está associada.
	 */
	public StatusSalaAula(SalaAula sala){
		this.sala = sala;
	}
	
	/**
	 * @return true caso possua professor, false em caso contrário
	 */
	public boolean possuiProfessor() {
		return sala.possuiProfessor();
	}

	/**
	 * @return A quantidade de alunos na sala
	 */
	public int obterQtdAlunos() {
		return sala.obterQtdAlunos();
	}

	/**
	 * @return true caso a sala tenha a mesma quantidade de alunos que a sua capacidade
	 * e um professor.
	 */
	public boolean estaCompleta(){
		return sala.obterQtdAlunos() == SalaAula.obterCapacidade() && possuiProfessor();
	}
	
	/**
	 * @return A capacidade da sala.
	 */
	public static int obterCapacidade(){
		return SalaAula.obterCapacidade();
	}
	
	public String toString(){
		int qtdProf = possuiProfessor() ? 1 : 0;
		int qtdAlunos = obterQtdAlunos();
		int capacidade = obterCapacidade();
		
		return qtdProf + "P " + qtdAlunos + "/" + capacidade + "A"; 
	}
}
