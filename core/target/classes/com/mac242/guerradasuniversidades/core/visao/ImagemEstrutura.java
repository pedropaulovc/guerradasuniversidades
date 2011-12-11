package com.mac242.guerradasuniversidades.core.visao;

import static playn.core.PlayN.assetManager;
import playn.core.Image;

import com.mac242.guerradasuniversidades.core.modelo.Estrutura;

public enum ImagemEstrutura { // setando as imagens dos edificios que serao visiveis no jogo
	B1(102, 0, "blocoEnsino.png", Estrutura.SALA_AULA),
	B2(430, 0, "blocoEnsino.png", Estrutura.SALA_AULA), 
	B3(50, 150, "blocoEnsino.png", Estrutura.SALA_AULA),
	B4(280, 150, "blocoEnsino.png", Estrutura.SALA_AULA),
	B5(500, 150, "blocoEnsino.png", Estrutura.SALA_AULA),
	BANDEJAO(180, 80, "bandejao.png", Estrutura.BANDEJAO),
	SETOR_DADOS(350, 80, "setorDados.png", Estrutura.SETOR_DADOS), 
	PRACA_CENTRAL(303, 111, "praca.png", Estrutura.PRACA_CENTRAL),
	CENTRO_ESPORTES(148, 84, "centroEsportes.jpg", Estrutura.CENTRO_ESPORTES),
	AUMENTO_SALARIAL(-1, -1, "professor.jpg", Estrutura.AUMENTO_SALARIAL),
	CHURRASCO_DEBATE(-1, -1, "professor.jpg", Estrutura.CHURRASCO_DEBATE), 
	FESTA(-1, -1, "professor.jpg", Estrutura.FESTA), 
	SOBREMESA_BANDEJAO(-1, -1, "professor.jpg", Estrutura.SOBREMESA_BANDEJAO),
	SEMINARIO(-1, -1, "professor.jpg", Estrutura.SEMINARIO), 
	GUARDA_UNIVERSITARIA(224, 190, "guarda.jpg", Estrutura.GUARDA_UNIVERSITARIA), 
	PROFESSOR(-1, -1, "professor.jpg", Estrutura.PROFESSOR),
	ALUNO(-1, -1, "aluno.png", Estrutura.ALUNO);

	private int x;
	private int y;
	private Image imagem;
	private Estrutura estrutura;
	
	ImagemEstrutura(int x, int y, String nome, Estrutura estrutura){// carregando a imagem de fundo
		this.x = x;
		this.y = y;
		imagem = assetManager().getImage("images/" + nome);
		this.estrutura = estrutura;
	}

	//getter and setters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public Image getImagem() {
		return imagem;
	}
	
	public Estrutura obterEstrutura(){
		return estrutura;
	}
	
	public String obterNome(){
		return estrutura.obterNome();
	}
	
	public String obterEfeito(){
		return estrutura.obterEfeito();
	}
};