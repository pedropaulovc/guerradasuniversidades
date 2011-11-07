package com.mac242.guerradasuniversidades.core;

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

public class Opcoes extends TipoTela {
	
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
	
	class TratadorDificuldade extends TratadorBotaoOpcao {
		
		private Dificuldade difSelec;

		public TratadorDificuldade(Button botaoSelec, Dificuldade difSelec){
			super(botaoSelec);
			this.difSelec = difSelec;
		}
		
		@Override
		public void onEmit() {
			atualizarBotoes(dificuldadeAtual);
			dificuldadeAtual = novo;
			dificuldade = difSelec;
		}
	}
	
	class TratadorMusica extends TratadorBotaoOpcao {
		
		private boolean musicaSelec;

		public TratadorMusica(Button botaoSelec, boolean musicaSelec){
			super(botaoSelec);
			this.musicaSelec = musicaSelec;
		}
		
		@Override
		public void onEmit() {
			atualizarBotoes(musicaAtual);
			musicaAtual = novo;
			musica = musicaSelec;
		}
	}
	
	class TratadorEfeitos extends TratadorBotaoOpcao {
		
		private boolean efeitosSelec;

		public TratadorEfeitos(Button botaoSelec, boolean efeitosSelec){
			super(botaoSelec);
			this.efeitosSelec = efeitosSelec;
		}
		
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
	
	public Opcoes(GuerraDasUniversidades jogo){
		super(jogo);
	}
	
	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenharMenu();
	}
	
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

	private Group gerarMenuInfo() {
		Group info = new Group(AxisLayout.horizontal().offStretch());
		Button creditos = new Button().setText("Créditos");
		Button voltar = new Button().setText("Voltar");
		voltar.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				jogo.exibirTela(jogo.obterMenu());
			}
		});
		creditos.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				jogo.exibirTela(jogo.obterCreditos());
			}
		});
		info.add(voltar, creditos);
		return info;
	}

	
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
