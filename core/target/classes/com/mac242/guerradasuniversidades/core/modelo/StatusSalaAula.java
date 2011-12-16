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

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusSalaAula other = (StatusSalaAula) obj;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		return true;
	}
	
}
