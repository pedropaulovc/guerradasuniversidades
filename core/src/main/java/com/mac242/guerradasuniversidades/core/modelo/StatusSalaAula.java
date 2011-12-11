package com.mac242.guerradasuniversidades.core.modelo;
public class StatusSalaAula {
	private SalaAula sala;
	
	public StatusSalaAula(SalaAula sala){
		this.sala = sala;
	}
	
	public boolean possuiProfessor() {
		return sala.possuiProfessor();
	}

	public int obterQtdAlunos() {
		return sala.obterQtdAlunos();
	}

	public boolean estaCompleta(){
		return sala.obterQtdAlunos() == SalaAula.obterCapacidade() && possuiProfessor();
	}
	
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
