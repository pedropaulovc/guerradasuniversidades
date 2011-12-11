package com.mac242.guerradasuniversidades.core.visao;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;
import playn.core.Color;
import playn.core.Font;
import react.UnitSlot;
import tripleplay.ui.AxisLayout;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Interface;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;
import tripleplay.ui.Stylesheet;

enum Dificuldade{FACIL, MEDIO, DIFICIL};

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsavel pela tela de opcoes.
 */

public class Opcoes extends TipoTela {
	
	/**
	 * Classe que trata os eventos dos botoes na tela de opcao 
	 */
	class TratadorBotaoOpcao extends UnitSlot {
		protected Button novo;
		
		public TratadorBotaoOpcao(Button novo){
			this.novo = novo;
		}
		
		public void atualizarBotoes(Button atual) {
			atual.setStyles(obterEstiloBotao());
			novo.addStyles(obterEstiloBotaoSelecionado());
		}

		public void onEmit() { }
	}
	
	/**
	 * Classe que trata os eventos dos botoes quando se escohe o nivel de dificuldades do jogo  
	 */
	class TratadorDificuldade extends TratadorBotaoOpcao {
		
		private Dificuldade difSelec;

		public TratadorDificuldade(Button botaoSelec, Dificuldade difSelec){
			super(botaoSelec);
			this.difSelec = difSelec;
		}
		
		/**
		 * Atualiza para o botao selecionado  
		 */
		@Override
		public void onEmit() {
			atualizarBotoes(dificuldadeAtual);
			dificuldadeAtual = novo;
			dificuldade = difSelec;
		}
	}
	
	/**
	 * Classe que trata os eventos dos botoes quando se escolhe musica on ou off  
	 */
	class TratadorMusica extends TratadorBotaoOpcao {
		
		private boolean musicaSelec;

		public TratadorMusica(Button botaoSelec, boolean musicaSelec){
			super(botaoSelec);
			this.musicaSelec = musicaSelec;
		}
		
		/**
		 * Atualiza para o botao selecionado  
		 */
		@Override
		public void onEmit() {
			atualizarBotoes(musicaAtual);
			musicaAtual = novo;
			musica = musicaSelec;
		}
	}
	
	/**
	 * Classe que trata os eventos dos botoes de efeitos do jogo  
	 */
	class TratadorEfeitos extends TratadorBotaoOpcao {
		
		private boolean efeitosSelec;

		public TratadorEfeitos(Button botaoSelec, boolean efeitosSelec){
			super(botaoSelec);
			this.efeitosSelec = efeitosSelec;
		}
		
		/**
		 * Atualiza para o botao selecionado  
		 */
		@Override
		public void onEmit() {
			atualizarBotoes(efeitoAtual);
			efeitoAtual = novo;
			efeitos = efeitosSelec;
		}
	}
	
	private Interface iface;
	private Dificuldade dificuldade = Dificuldade.MEDIO;
	private boolean musica = true;
	private boolean efeitos = true;
	private Button dificuldadeAtual, musicaAtual, efeitoAtual;
	
	public Opcoes(VisaoGuerraDasUniversidades jogo){
		super(jogo);
	}
	
	/**
	 * Inicializa a tela de opcoes  
	 */
	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenharMenu();
	}
	
	/**
	 * Desenha na tela de opcoes, o menu de opcoes. Seta fonte, tamanho, adiciona botoes a tela de opcoes   
	 */
	private void desenharMenu() {
		iface = new Interface(null);
		pointer().setListener(iface.plistener);
		
		Styles estiloLabel = Styles.none().
			add(Style.FONT.is(graphics().createFont("Helvetica", Font.Style.BOLD, 20)));
		Stylesheet rootSheet = Stylesheet.builder().
			add(Button.class, obterEstiloBotao()).
			add(Label.class, estiloLabel).
			create();

		Root root = iface.createRoot(AxisLayout.vertical().gap(80), rootSheet);
		root.setSize(graphics().width(), graphics().height());
		base.add(root.layer);

		root.add(gerarMenuDificuldade(), gerarMenuMusica(), 
				gerarMenuEfeitos(), gerarMenuInfo());
	}

	/**
	 * Gera o menu(opcoes de dificuldade) de dificuldades do jogo 
	 * @return  dificuldade
	 */
	private Group gerarMenuDificuldade(){
		Group dificuldade = new Group(AxisLayout.horizontal().offStretch());
		dificuldade.add(new Label("Dificuldade: "));
		
		Button facil = new Button().setText("Fácil");
		Button medio = new Button().setText("Médio");
		Button dificil = new Button().setText("Difícil");
		
		dificuldadeAtual = medio;
		
		facil.clicked().connect(new TratadorDificuldade(facil, Dificuldade.FACIL));
		medio.clicked().connect(new TratadorDificuldade(medio, Dificuldade.MEDIO));	
		dificil.clicked().connect(new TratadorDificuldade(dificil, Dificuldade.DIFICIL));
		
		medio.addStyles(obterEstiloBotaoSelecionado());

		dificuldade.add(facil, medio, dificil);
		return dificuldade;
	}
	
	/**
	 * Gera o menu(opcoes de musica) de musica do jogo 
	 * @return  musica
	 */
	private Group gerarMenuMusica() {
		Group musica = new Group(AxisLayout.horizontal().offStretch());
		musica.add(new Label("Música: "));
		
		Button ligada = new Button().setText("Ligada");
		Button desligada = new Button().setText("Desligada");

		musicaAtual = ligada;
		
		ligada.clicked().connect(new TratadorMusica(ligada, true));		
		desligada.clicked().connect(new TratadorMusica(desligada, false));		
		
		ligada.addStyles(obterEstiloBotaoSelecionado());
		
		musica.add(ligada, desligada);
		return musica;
	}
	
	/**
	 * Gera o menu(opcoes de efeitos) de efeitos do jogo 
	 * @return  efeitos
	 */
	private Group gerarMenuEfeitos() {
		Group efeitos = new Group(AxisLayout.horizontal().offStretch());
		efeitos.add(new Label("Efeitos: "));
		
		Button ligados = new Button().setText("Ligados");
		final Button desligados = new Button().setText("Desligados");
		
		efeitoAtual = ligados;
		
		ligados.clicked().connect(new TratadorEfeitos(ligados, true));
		desligados.clicked().connect(new TratadorEfeitos(desligados, false));
		
		ligados.addStyles(obterEstiloBotaoSelecionado());
		
		efeitos.add(ligados, desligados);
		return efeitos;
	}
	
	/**
	 * Gera o menu (opcoes) de voltar e creditos do jogo 
	 * @return  info
	 */
	private Group gerarMenuInfo() {
		Group info = new Group(AxisLayout.horizontal().offStretch());
		Button creditos = new Button().setText("Créditos");
		Button voltar = new Button().setText("Voltar");
		voltar.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				visao.exibirTela(visao.obterMenu());
			}
		});
		creditos.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				visao.exibirTela(visao.obterCreditos());
			}
		});
		info.add(voltar, creditos);
		return info;
	}

	//====== Redesenha as animacoes, para refletir o estado atual do tela =====
	//====== Atualiza os graficos =============================================
	@Override
	public void update(float delta) {
		if (iface != null) {
			iface.update(delta);
		}
	}

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
	
	public String toString(){
		return "Opções";
	}

}