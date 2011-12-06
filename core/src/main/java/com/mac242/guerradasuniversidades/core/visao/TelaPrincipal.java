package com.mac242.guerradasuniversidades.core.visao;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;

import java.util.HashMap;
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

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsavel pela tela principal do jogo( onde o usuario ira jogar mesmo).
 */

enum Estrutura { // setando as imagens dos edificios que serao visiveis no jogo
	B1(102, 0, "blocoEnsino.png"), B2(430, 0, "blocoEnsino.png"), 
	B3(50, 150, "blocoEnsino.png"), B4(280, 150, "blocoEnsino.png"), B5(500, 150, "blocoEnsino.png"),
	BANDEJAO(180, 80, "bandejao.png"), SETOR_DADOS(350, 80, "setorDados.png"), 
	PRACA_CENTRAL(303, 111, "pracaCentral.jpg"), 
	CENTRO_ESPORTES(148, 84, "centroEsportes.jpg"), GUARDA(224, 190, "guarda.jpg");

	private int x;
	private int y;
	private Image imagem;
	
	Estrutura(int x, int y, String nome){// carregando a imagem de fundo
		this.x = x;
		this.y = y;
		imagem = assetManager().getImage("images/" + nome);
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
	
};

public class TelaPrincipal extends TipoTela {
	
	/**
	 * 
	 */
	class TratadorBotaoEstrutura extends UnitSlot {

		private Estrutura estrutura;

		public TratadorBotaoEstrutura(Estrutura estrutura){
			this.estrutura = estrutura;
		}
		
		@Override
		public void onEmit() {
			estruturas.put(estrutura, true);
		}
	}
	
	private Interface iface;
	
	private int contaUpdates = 0;
	private int dia = 1;
	private int segundos = 0;
	
	private String avisoAtual;
	private int PE = 1000;
	private int FO = 0;
	private int HP = 10;
	
	private int taxaPE = 15;
	private int taxaManutencao = 0;
	private int taxaFuncionarios = 0;
	
	private int maxPE = 9000;
	
	private Canvas canvasFO;
	private Canvas canvasTaxas;
	private Canvas canvasCampus;
	private Canvas canvasDia;
	private Canvas canvasAvisos;
	private Canvas canvasHP;
	
	private Map<Estrutura, Boolean> estruturas;
	private static Map<Float, TextFormat> fontes;
	
	private String[] avisosExemplo = {
			"Funcionários entraram em greve (Foco zerado)",
			"Unicamp atacou você (-1 HP)", " ", "-- Avisos exemplo --",
			"USP atacou UFSC (-1 HP)", };

	public TelaPrincipal(GuerraDasUniversidades jogo) {
		super(jogo);
		
		estruturas = new HashMap<Estrutura, Boolean>();
		for(Estrutura e : Estrutura.values()){
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

		inicializarDia();
		atualizarDia();
		
		inicializarHP();
		inicializarAvisos();
		inicializarFO();
		
		inicializarTaxas();
		atualizarTaxas();
		
		inicializarCampus();
	}

	private void atualizarCampus() {
		canvasCampus.clear();
		for(Estrutura e : estruturas.keySet()){
			if(estruturas.get(e)){
				canvasCampus.drawImage(e.getImagem(), e.getX(), e.getY());
			}
		}
	}

	private void atualizarTaxas() {
		String texto = "Manutenção: " + taxaManutencao + "/dia\nFuncionários: "
				+ taxaFuncionarios + "/dia\nPE: " + taxaPE + "/seg";
		atualizarTexto(texto, 10f, canvasTaxas);
	}

	private void atualizarFO() {
		canvasFO.clear();
		canvasFO.setFillColor(Color.rgb(255, 255, 255));
		canvasFO.fillRect(10 * (segundos % 16), 0, canvasFO.width() - 10
				* (segundos % 16), canvasFO.height());
	}

	
	private void atualizarAviso(String aviso) {
		if (aviso.length() == 0)
			aviso = " ";
		Font font = graphics().createFont("Helvetica",
				playn.core.Font.Style.BOLD, 11f);
		TextFormat format = new TextFormat().withFont(font).withTextColor(
				Color.rgb(255, 255, 255));
		TextLayout layout = graphics().layoutText(aviso, format);
		canvasAvisos.clear();
		canvasAvisos.drawText(layout, 0, 0);
	}

	private void atualizarDia() {
		String texto = "Dia: " + dia;
		atualizarTexto(texto, 15f, canvasDia);
	}
	
	private void atualizarHP() {
		String texto = "HP " + HP + "/10";
		atualizarTexto(texto, 15f, canvasHP);
	}
	
	private void inicializarCampus() {
		canvasCampus = inicializarCanvas(671, 290, 20, 154);
	}

	private void inicializarTaxas() {
		canvasTaxas = inicializarCanvas(140, 51, 570, 395);
	}

	private void inicializarFO() {
		canvasFO = inicializarCanvas(150, 15, 534, 473);
	}

	private void inicializarAvisos() {
		canvasAvisos = inicializarCanvas(500, 39, 32, 396);
	}

	private void inicializarDia() {
		canvasDia = inicializarCanvas(71, 25, 505, 498);
	}

	private void inicializarHP() {
		canvasHP = inicializarCanvas(80, 20, 612, 498);
	}

	/**
	 * Inicializando a barra superior(setas esq e dir, edificios, professor, etc) da tela de principal
	 */
	private void inicializarBarraSuperior() {
		Button esquerda = new Button();
		esquerda.setIcon(assetManager().getImage("images/setaEsquerda.png"));

		Button direita = new Button();
		direita.setIcon(assetManager().getImage("images/setaDireita.png"));

		Styles estiloLegenda = Styles.none().
				add(tripleplay.ui.Style.FONT.is(graphics().createFont("Helvetica",
						Font.Style.BOLD, 10)));
		Stylesheet rootStyle = Stylesheet.builder().
				add(Button.class, estiloLegenda).
				create();
		
		Group blocoEnsino = inicializarBlocoEnsino();
		Group setorDados = inicializarSetorDados();
		Group bandejao = inicializarBandejao();
		Group professor = inicializarProfessor();

		Group botoes = new Group(AxisLayout.horizontal().gap(-2));
		botoes.add(esquerda, blocoEnsino, setorDados, bandejao, professor,
				direita);

		Root root = iface.createRoot(AxisLayout.horizontal().gap(-2), rootStyle);
		root.setSize(graphics().width(), graphics().height());
		root.layer.setTranslation(56, -190);
		base.add(root.layer);
		root.add(botoes);
	}

	//============= Inicializacao dos objetos contidos na barra superior do jogo=======================
	private Group inicializarProfessor() {
		Button itemProfessor = new Button();
		
		itemProfessor.setIcon(assetManager().getImage("images/professor.jpg"));
		Button textoProfessor = new Button().setText("Professor\n$600");
		
		Group professor = new Group(AxisLayout.vertical());
		professor.add(itemProfessor, textoProfessor);
		return professor;
	}

	private Group inicializarBandejao() {
		Button itemBandejao = new Button();
		itemBandejao.setIcon(Estrutura.BANDEJAO.getImagem());
		Button textoBandejao = new Button().setText("Bandejão\n$600 / FO MAX +3");
		
		itemBandejao.clicked().connect(new TratadorBotaoEstrutura(Estrutura.BANDEJAO));
		textoBandejao.clicked().connect(new TratadorBotaoEstrutura(Estrutura.BANDEJAO));
		
		Group bandejao = new Group(AxisLayout.vertical());
		bandejao.add(itemBandejao, textoBandejao);
		return bandejao;
	}

	private Group inicializarSetorDados() {
		Button itemSetorDados = new Button();
		
		itemSetorDados.setIcon(Estrutura.SETOR_DADOS.getImagem());
		Button textoSetorDados = new Button().setText("Setor Dados\n$500 / PE +4/seg");
		
		itemSetorDados.clicked().connect(new TratadorBotaoEstrutura(Estrutura.SETOR_DADOS));
		textoSetorDados.clicked().connect(new TratadorBotaoEstrutura(Estrutura.SETOR_DADOS));
		
		Group setorDados = new Group(AxisLayout.vertical());
		setorDados.add(itemSetorDados, textoSetorDados);
		return setorDados;
	}

	private Group inicializarBlocoEnsino() {
		Button itemBlocoEnsino = new Button();
		itemBlocoEnsino.setIcon(Estrutura.B1.getImagem());
		Button textoBlocoEnsino = new Button().setText("Bloco Ensino\n$400 / Salas +1");
		
		UnitSlot tratadorBlocoEnsino = new UnitSlot() {
			@Override
			public void onEmit() {
				for(int i = 0; i < 5; i++){
					if(!estruturas.get(Estrutura.values()[i])){
						estruturas.put(Estrutura.values()[i], true);
						return;
					}
				}
			}
		};
		
		itemBlocoEnsino.clicked().connect(tratadorBlocoEnsino);
		textoBlocoEnsino.clicked().connect(tratadorBlocoEnsino);
		
		Group blocoEnsino = new Group(AxisLayout.vertical());
		blocoEnsino.add(itemBlocoEnsino, textoBlocoEnsino);
		return blocoEnsino;
	}
	//============================================================================================================
	/**
	 * Desenha as varias imagens na tela de jogo principal, seta fonte, ajust a posicao.
	 */
	private void desenharLogo() {
		NomesUniversidades escolhida = jogo.obterUniversidades().getEscolhida();
		String nome = escolhida.toString().toLowerCase();
		Image logo = assetManager().getImage("images/" + nome + ".jpg");
		ImageLayer layerLogo = graphics().createImageLayer(logo);
		layerLogo.setTranslation(14, 15);
		base.add(layerLogo);

		Font font = graphics().createFont("Helvetica",
				playn.core.Font.Style.BOLD, 15f);
		TextFormat format = new TextFormat().withFont(font);
		TextLayout layout = graphics().layoutText(nome.toUpperCase(), format);
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
	
	private Canvas inicializarCanvas(int h, int l, int x, int y) {
		CanvasLayer layerCanvas = graphics().createCanvasLayer(h, l);
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
		
		canvas.clear();
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

		contaUpdates++;

		if (contaUpdates == 10) {
			contaUpdates = 0;
			segundos++;

			if (segundos % 240 == 0) {
				dia++;
				atualizarDia();
			}

			if (segundos % 3 == 0) {
				atualizarAviso(avisosExemplo[segundos % avisosExemplo.length]);
			}

		}

		atualizarFO();
		atualizarHP();
		atualizarCampus();

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
		}

		destruirBase();
	}

	public String toString() {
		return "Tela Principal";
	}
}
