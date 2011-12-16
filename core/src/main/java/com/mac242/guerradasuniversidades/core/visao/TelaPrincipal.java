package com.mac242.guerradasuniversidades.core.visao;

import static com.mac242.guerradasuniversidades.core.visao.ImagemEstrutura.*;
import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterConstrutor;
import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterJogador;
import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterJogo;
import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import playn.core.Canvas;
import playn.core.CanvasLayer;
import playn.core.Color;
import playn.core.Font;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.TextFormat;
import playn.core.TextLayout;
import react.UnitSlot;
import tripleplay.ui.AxisLayout;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Interface;
import tripleplay.ui.Root;
import tripleplay.ui.Styles;
import tripleplay.ui.Stylesheet;

import com.mac242.guerradasuniversidades.core.controle.TratadorTrocarTela;
import com.mac242.guerradasuniversidades.core.controle.TratadorBotaoBlocoEnsino;
import com.mac242.guerradasuniversidades.core.controle.TratadorBotaoEstrutura;
import com.mac242.guerradasuniversidades.core.modelo.FachadaJogador;
import com.mac242.guerradasuniversidades.core.modelo.Notificacao;
import com.mac242.guerradasuniversidades.core.modelo.StatusSalaAula;

import com.mac242.guerradasuniversidades.core.util.Observable;
import com.mac242.guerradasuniversidades.core.util.Observer;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsavel pela tela principal do jogo( onde o usuario ira jogar mesmo).
 */

public class TelaPrincipal extends TipoTela implements Observer {
	private Interface iface;
	
	private Canvas canvasFO;
	private Canvas canvasTaxas;
	private Canvas canvasCampus;
	private Canvas canvasDia;
	private Canvas canvasAvisos;
	private Canvas canvasHP;
	private Canvas canvasSalasAula;
	private Canvas canvasPE;
	
	private Map<ImagemEstrutura, Boolean> estruturas;
	private static Map<Float, TextFormat> fontes;
	
	private int posicaoMenu = 0;
	private List<Group> botoes;
	private Button esquerda;
	private Button direita;
	private Root root;

	private List<String> avisos = new LinkedList<String>();
	private static final int qtdAvisos = 5;
	
	public TelaPrincipal(VisaoGuerraDasUniversidades visao) {
		super(visao);
		
		estruturas = new HashMap<ImagemEstrutura, Boolean>();
		for(ImagemEstrutura e : ImagemEstrutura.values()){
			estruturas.put(e, false);
		}
		
		fontes = new HashMap<Float, TextFormat>();
	}
	
	/**
	 * Inicializa a tela de jogo
	 */
	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenharTelaFundo();

		iface = new Interface(null);
		pointer().setListener(iface.plistener);

		inicializarBarraSuperior();
		desenharLogo();

		canvasDia = inicializarCanvas(71, 25, 505, 498);
		canvasHP = inicializarCanvas(80, 20, 612, 498);
		canvasAvisos = inicializarCanvas(500, 80, 32, 370);
		canvasFO = inicializarCanvas(150, 15, 534, 473);
		canvasPE = inicializarCanvas(150, 13, 534, 453);
		canvasTaxas = inicializarCanvas(140, 51, 570, 385);
		canvasCampus = inicializarCanvas(671, 290, 20, 154);
		canvasSalasAula = inicializarCanvas(437,59,25,453);
		
		inicializarSalasAula();
		
		atualizarDia();
		atualizarTaxas();
		exibirAvisos();
		atualizarCampus();
		
		obterJogo().addObserver(this);
	}

	/**
	 * Inicializando a barra superior(setas esq e dir, edificios, professor, etc) da tela de principal
	 */
	private void inicializarBarraSuperior() {
		posicaoMenu = 0;
		esquerda = new Button();
		direita = new Button();
		
		direita.clicked().connect(new UnitSlot() {

			@Override
			public void onEmit() {
				if(posicaoMenu < botoes.size()-4){
					posicaoMenu++;
					atualizarBarraSuperior();
				}
			}
		});
		
		esquerda.clicked().connect(new UnitSlot() {

			@Override
			public void onEmit() {
				if(posicaoMenu > 0){
					posicaoMenu--;
					atualizarBarraSuperior();
				}
			}
		});
	
		botoes = new ArrayList<Group>();
		botoes.add(inicializarEstrutura(B1, new TratadorBotaoBlocoEnsino(this)));
		botoes.add(inicializarEstrutura(SETOR_DADOS));
		botoes.add(inicializarEstrutura(BANDEJAO));
		botoes.add(inicializarEstrutura(PROFESSOR));
		botoes.add(inicializarEstrutura(ALUNO));
		botoes.add(inicializarEstrutura(PRACA_CENTRAL));
		botoes.add(inicializarEstrutura(CENTRO_ESPORTES));
		botoes.add(inicializarEstrutura(AUMENTO_SALARIAL));
		botoes.add(inicializarEstrutura(CHURRASCO_DEBATE));
		botoes.add(inicializarEstrutura(FESTA));
		botoes.add(inicializarEstrutura(SOBREMESA_BANDEJAO));
		botoes.add(inicializarEstrutura(SEMINARIO));
		botoes.add(inicializarEstrutura(GUARDA_UNIVERSITARIA));
		
		atualizarBarraSuperior();
	}

	//============= Inicializacao dos objetos contidos na barra superior do jogo=======================
	private Group inicializarEstrutura(ImagemEstrutura img){
		TratadorBotaoEstrutura tratador = new TratadorBotaoEstrutura(img, this);
		return inicializarEstrutura(img, tratador);
	}
	
	private Group inicializarEstrutura(ImagemEstrutura img, UnitSlot tratador){
		Button item = new Button();
		
		item.setIcon(img.obterImagem());
		Button texto = new Button().setText(img.obterNome() 
				+ "\n" + img.obterEfeito());
		
		item.clicked().connect(tratador);
		texto.clicked().connect(tratador);
		
		Group grupo = new Group(AxisLayout.vertical());
		grupo.add(item, texto);
		return grupo;
	}
	
	public void atualizarCampus() {
		canvasCampus.clear();
		for(ImagemEstrutura e : estruturas.keySet()){
			if(estruturas.get(e) && e.obterX() >= 0 && e.obterY() >= 0){
				canvasCampus.drawImage(e.obterImagem(), e.obterX(), e.obterY());
			}
		}
	}

	private void atualizarTaxas() {
		FachadaJogador jogador = obterJogador();
		String texto = 
				"Manutenção: " + jogador.obterTaxaManutencao()
				+ "/dia\nFuncionários: " + jogador.obterTaxaFuncionarios()
				+ "/dia\nPE: " + jogador.obterTaxaPontosEnsino()
				+ "/seg\nFO: " + jogador.obterTaxaFoco()
				+ "/dia";
		canvasTaxas.clear();
		atualizarTexto(texto, 10f, canvasTaxas);
	}

	private void atualizarFO() {
		int FOatual = obterJogador().obterFoco();
		int FOmax = obterJogador().obterFocoMaximo();
		float unidade = 1.0f * canvasFO.width() / FOmax;
		
		canvasFO.clear();
		canvasFO.setFillColor(Color.rgb(255, 255, 255));
		canvasFO.fillRect(0, 0, FOatual * unidade,
				canvasFO.height());
		atualizarTexto(FOatual + "/" + FOmax, 12, canvasFO);
	}
	
	private void atualizarPE() {
		int PEatual = obterJogador().obterPontosEnsino();
		int PEmax = obterJogador().obterPontosEnsinoMaximo();
		float unidade = 1.0f * canvasFO.width() / PEmax;
		
		canvasPE.clear();
		canvasPE.setFillColor(Color.rgb(255, 255, 255));
		canvasPE.fillRect(0, 0, PEatual * unidade,
				canvasPE.height());
		atualizarTexto(PEatual + "/" + PEmax, 12, canvasPE);
	}
	
	public void adicionarAviso(String aviso) {
		if (aviso.length() == 0)
			aviso = " ";
		
		if(avisos.size() == qtdAvisos)
			avisos.remove(0);
		avisos.add(aviso);
		exibirAvisos();
	}
	
	public void exibirAvisos(){
		StringBuilder sb = new StringBuilder();
		
		while(avisos.size() < qtdAvisos)
			avisos.add(" ");
		
		for(int i = 0; i < avisos.size() - 1; i++)
			sb.append(avisos.get(i) + "\n");
		sb.append(avisos.get(avisos.size() - 1));
		
		Font font = graphics().createFont("Helvetica",
				playn.core.Font.Style.BOLD, 11f);
		TextFormat format = new TextFormat().withFont(font).withTextColor(
				Color.rgb(255, 255, 255));
		TextLayout layout = graphics().layoutText(sb.toString(), format);
		canvasAvisos.clear();
		canvasAvisos.drawText(layout, 0, 0);
	}
	
	private void atualizarDia() {
		String texto = "Dia: " + obterJogo().obterDia();
		canvasDia.clear();
		atualizarTexto(texto, 15f, canvasDia);
	}
	
	private void atualizarHP() {
		String texto = "HP " + obterJogador().obterHP() + "/10";
		canvasHP.clear();
		atualizarTexto(texto, 15f, canvasHP);
	}

	private void inicializarSalasAula(){
		Root root = iface.createRoot(AxisLayout.vertical().offStretch());
		root.setSize(437,59);
		root.layer.setTranslation(25,453);
		base.add(root.layer);
		
		Button botao = new Button().setConstraint(AxisLayout.stretched());
		botao.clicked().connect(new TratadorTrocarTela(visao, visao.obterOponentes()));
		
		root.add(botao);
	}
	
	private void atualizarBarraSuperior(){
		if(root != null){
			base.remove(root.layer);
			iface.removeRoot(root);
		}
		
		Styles estiloLegenda = Styles.none().
				add(tripleplay.ui.Style.FONT.is(graphics().createFont("Helvetica",
						Font.Style.BOLD, 10)));
		Stylesheet rootStyle = Stylesheet.builder().
				add(Button.class, estiloLegenda).
				create();
		
		Group barraSuperior = new Group(AxisLayout.horizontal().gap(-2));
		
		if(posicaoMenu == 0)
			esquerda.setIcon(assetManager().getImage("images/setaEsquerdaCinza.png"));
		if(posicaoMenu == 1)
			esquerda.setIcon(assetManager().getImage("images/setaEsquerda.png"));
			
		if(posicaoMenu == botoes.size()-5 || direita.icon() == null)
			direita.setIcon(assetManager().getImage("images/setaDireita.png"));
		if(posicaoMenu == botoes.size()-4)
			direita.setIcon(assetManager().getImage("images/setaDireitaCinza.png"));

		
		barraSuperior.add(esquerda);
				for(int i = posicaoMenu; i < posicaoMenu + 4; i++){
			barraSuperior.add(botoes.get(i));
		}
		barraSuperior.add(direita);
		
		root = iface.createRoot(AxisLayout.horizontal().gap(-2), rootStyle);
		root.setSize(graphics().width(), graphics().height());
		root.layer.setTranslation(56, -190);
		base.add(root.layer);
		root.add(barraSuperior);
	}
	
	private void atualizarSalasAula(){
		int[] posSalas = {0, 90, 180, 270, 360};
		List<StatusSalaAula> status = obterJogador().obterInfoSalas();
		int i = 0;
		Font font = graphics().createFont("Helvetica", Font.Style.BOLD, 12);
		TextFormat formato = new TextFormat().withFont(font);
		TextLayout layout;
		int hSala = canvasSalasAula.height();
		int lSala = 76;
		
		canvasSalasAula.clear();

		canvasSalasAula.setFillColor(Color.rgb(0, 255, 0));
		while(i < status.size() && status.get(i).estaCompleta()){
			canvasSalasAula.fillRect(posSalas[i], 0, lSala, hSala);
			layout = graphics().layoutText("Clique\npara\natacar", formato);
			canvasSalasAula.drawText(layout, posSalas[i] + lSala/2 - layout.width()/2, hSala/2 - layout.height()/2);
			i++;
		}
		
		canvasSalasAula.setFillColor(Color.rgb(255, 255, 0));
		while(i < status.size()){
			canvasSalasAula.fillRect(posSalas[i], 0, lSala, hSala);
			layout = graphics().layoutText(status.get(i).toString(), formato);
			canvasSalasAula.drawText(layout, posSalas[i] + lSala/2 - layout.width()/2, hSala/2 - layout.height()/2);
			i++;
		}
	}
	
	//============================================================================================================
	/**
	 * Desenha as varias imagens na tela de jogo principal, seta fonte, ajust a posicao.
	 */
	private void desenharLogo() {
		String nomejogador = obterConstrutor().obterNome();
		String nomeuniversidade = obterConstrutor().obterUniversidade().toString().toLowerCase();
		Image logo = assetManager().getImage("images/" + nomeuniversidade + ".jpg");
		ImageLayer layerLogo = graphics().createImageLayer(logo);
		layerLogo.setTranslation(14, 15);
		base.add(layerLogo);

		Font font = graphics().createFont("Helvetica",
				playn.core.Font.Style.BOLD, 15f);
		TextFormat format = new TextFormat().withFont(font);
		TextLayout layout = graphics().layoutText(nomejogador, format);
		int width = (int) Math.ceil(layout.width());
		int height = (int) Math.ceil(layout.height());
		CanvasLayer layer = graphics().createCanvasLayer(width, height);
		layer.canvas().drawText(layout, 0, 0);
		layer.setTranslation(64 - width / 2, 117);
		base.add(layer);

	}
	/**
	 * Desenha o fundo da tela de jogo
	 */
	public void desenharTelaFundo() {
		Image fundo = assetManager().getImage("images/fundomain.png");
		ImageLayer layerFundo = graphics().createImageLayer(fundo);
		base.add(layerFundo);
	}
	
	private Canvas inicializarCanvas(int l, int h, int x, int y) {
		CanvasLayer layerCanvas = graphics().createCanvasLayer(l, h);
		layerCanvas.setTranslation(x, y);
		base.add(layerCanvas);
		return layerCanvas.canvas();
	}
	
	/**
	 * Atualiza textos da tela do jogo
	 * @param texto
	 * @param tamanho
	 * @param canvas
	 */
	private void atualizarTexto(String texto, float tamanho, Canvas canvas){
		TextFormat formato = fontes.get(tamanho);
		if(formato == null){
			Font font = graphics().createFont("Helvetica", Font.Style.BOLD, tamanho);
			formato = new TextFormat().withFont(font);
			fontes.put(tamanho, formato);
		}
		
		canvas.drawText(graphics().layoutText(texto, formato), 0, 0);
	}
	
	/**
	 * Atualiza os graficos
	 */
	@Override
	public void update(float delta) {
		if (iface != null) {
			iface.update(delta);
		}

		atualizarDia();
		atualizarPE();
		atualizarFO();
		atualizarHP();
		atualizarTaxas();
		atualizarSalasAula();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Notificacao notificacao = (Notificacao) arg1;
		
		String jogador = notificacao.obterNome() + " (" + notificacao.obterUniversidade() + ")";
		
		switch (notificacao.obterTipo()) {
		case PERDA_HP:
			adicionarAviso(jogador + " perdeu HP");
			break;
		case GREVE:
			adicionarAviso("Funcionários da " + notificacao.obterUniversidade()
					+ " entraram em greve (Foco zerado)");
			break;
		case COMPRA:
			adicionarAviso(jogador + " comprou "
					+ notificacao.obterEstrutura().obterNome());
			break;
		case DESTRUICAO:
			ImagemEstrutura destruida = mapearParaImagemEstrutura(notificacao.obterEstrutura());
			estruturas.put(destruida, false);
			adicionarAviso(jogador + " perdeu " + notificacao.obterEstrutura().obterNome());
			if(notificacao.obterUniversidade().equals(obterJogador().obterNomeUniversidade()))
				atualizarCampus();
			break;
		case ATAQUE:
			adicionarAviso(jogador + " realizou um ataque.");
			break;
		case SUPORTAR_ATAQUE:
			adicionarAviso(jogador + " suportou o ataque (FO >= poder ataque)");
			break;
		case DERROTA:
			visao.obterFimJogo().exibirDerrota();
			visao.exibirTela(visao.obterFimJogo());
			break;
		case VITORIA:
			visao.obterFimJogo().exibirVitoria();
			visao.exibirTela(visao.obterFimJogo());
		default:
			break;
		}
	}
	
	/**
	 * redesenha as animacoes, para refletir o estado atual do joog
	 */
	@Override
	public void paint(float alpha) {
		if (iface != null) {
			iface.paint(alpha);
		}
	}

	@Override
	public void shutdown() {
		if (iface != null) {
			pointer().setListener(null);
			iface = null;
			root = null;
		}

		destruirBase();
	}

	public String toString() {
		return "Tela Principal";
	}

	public Map<ImagemEstrutura, Boolean> obterEstruturas() {
		return estruturas;
	}
	
}
