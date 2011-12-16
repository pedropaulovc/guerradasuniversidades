package com.mac242.guerradasuniversidades.core.modelo;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Enum das estruturas disponíveis no jogo.
 */
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
	SEMINARIO("Seminário", "$300(X200)/PE +7/seg"), 
	GUARDA_UNIVERSITARIA("Guarda Universitária", "$1500 / Evita -1 HP");
	
	private String nome;
	private String efeito;

	/**
	 * Construtor de um elemento do enum
	 * @param nome O nome legível por humanos da estrutura
	 * @param efeito Uma descrição breve do efeito da estrutura no jogo.  
	 */
	Estrutura(String nome, String efeito){
		this.nome = nome;
		this.efeito = efeito;
	}
	
	/**
	 * Método getter do nome legível por humanos da estrutura.
	 * @return O nome da estrutura.
	 */
	public String obterNome(){
		return nome;
	}
	
	/**
	 * Método getter da descrição do efeito da estrutura no jogo.
	 * @return O efeito da estrutura no jogo.
	 */
	public String obterEfeito(){
		return efeito;
	}
}
