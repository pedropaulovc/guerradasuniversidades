package com.mac242.guerradasuniversidades.core.modelo;
public enum Estrutura {
	SALA_AULA("Sala Aula", "$400 / Salas +1"),
	PROFESSOR("Professor", "$200"),
	ALUNO("Aluno", "$75"),
	BANDEJAO("Bandejão", "$600 / FO MAX +3"), 
	SETOR_DADOS("Setor Dados", "$500 / PE +10/seg"), 
	PRACA_CENTRAL("Praça Central", "$400 / FO +1/dia"),
	CENTRO_ESPORTES("Centro Esportes", "$700 / FO +1/dia"),
	AUMENTO_SALARIAL("Aumento Salarial", "$700 / PE +3/seg"), 
	CHURRASCO_DEBATE("Churrasco Debate", "$400(H275)/PE +1/seg"), 
	FESTA("Festa", "$400(B300)/FO +2"), 
	SOBREMESA_BANDEJAO("Sobremesa Bandejão", "$250(B150) / FO +1"),
	SEMINARIO("Seminário", "$300(X200)/PE +3/seg"), 
	GUARDA_UNIVERSITARIA("Guarda Universitária", "$1500 / Evita -1 HP");
	
	private String nome;
	private String efeito;

	Estrutura(String nome, String efeito){
		this.nome = nome;
		this.efeito = efeito;
		
	}
	
	public String obterNome(){
		return nome;
	}
	
	public String obterEfeito(){
		return efeito;
	}
}





