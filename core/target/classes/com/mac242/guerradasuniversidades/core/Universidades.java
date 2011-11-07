package com.mac242.guerradasuniversidades.core;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import static playn.core.PlayN.pointer;
import playn.core.CanvasLayer;
import playn.core.Color;
import playn.core.Font;
import playn.core.Font.Style;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.TextFormat;
import playn.core.TextLayout;
import react.UnitSlot;
import tripleplay.ui.AxisLayout;
import tripleplay.ui.Button;
import tripleplay.ui.Interface;
import tripleplay.ui.Root;
import tripleplay.ui.Stylesheet;

enum NomesUniversidades {
	USP, UFSC, UNICAMP
};

public class Universidades extends TipoTela {

	class TratadorBotaoUniversidade extends UnitSlot {
		private NomesUniversidades nome;

		public TratadorBotaoUniversidade(NomesUniversidades nome) {
			this.nome = nome;
		}

		@Override
		public void onEmit() {
			escolhida = nome;
			jogo.exibirTela(jogo.obterTelaPrincipal());
		}
	}

	private Interface iface;
	private NomesUniversidades escolhida;

	public String toString() {
		return "Universidades";
	}

	public Universidades(GuerraDasUniversidades jogo) {
		super(jogo);
	}

	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenharMapa();
	}

	private void desenharMenu() {
		iface = new Interface(null);
		pointer().setListener(iface.plistener);

		Stylesheet rootSheet = Stylesheet.builder()
				.add(Button.class, obterEstiloBotao()).create();

		Root root = iface.createRoot(AxisLayout.vertical().gap(10), rootSheet);
		root.setSize(graphics().width(), graphics().height());
		base.add(root.layer);

		Button botaoUSP = new Button().setText("USP");
		botaoUSP.clicked().connect(
				new TratadorBotaoUniversidade(NomesUniversidades.USP));

		Button botaoUFSC = new Button().setText("UFSC");
		botaoUFSC.clicked().connect(
				new TratadorBotaoUniversidade(NomesUniversidades.UFSC));

		Button botaoUnicamp = new Button().setText("UNICAMP");
		botaoUnicamp.clicked().connect(
				new TratadorBotaoUniversidade(NomesUniversidades.UNICAMP));

		Font font = graphics().createFont("Helvetica", Style.BOLD, 40f);
		TextFormat format = new TextFormat().withFont(font);
		TextLayout layout = graphics().layoutText("Escolha sua Universidade:",
				format);
		
		int width = (int) Math.ceil(layout.width());
		int height = (int) Math.ceil(layout.height());
		
		CanvasLayer layer = graphics().createCanvasLayer(width, height);
		layer.canvas().drawText(layout, 0, 0);
		
		layer.setTranslation(graphics().width() / 2 - width / 2, 20);
		base.add(layer);

		root.add(botaoUSP, botaoUFSC, botaoUnicamp);
	}

	private void desenharMapa() {
		final Image mapa = assetManager().getImage("images/mapa.jpg");
		mapa.addCallback(new ResourceCallback<Image>() {
			@Override
			public void error(Throwable err) {
				log().error("Erro carregando imagem", err);
			}

			@Override
			public void done(Image resource) {
				ImageLayer layerMapa = graphics().createImageLayer(mapa);
				layerMapa.transform().translate(
						graphics().width() / 2 - mapa.width() / 2, 100);
				base.add(layerMapa);
				desenharMenu();
			}
		});
	}

	/**
	 * @return the escolhida
	 */
	public NomesUniversidades getEscolhida() {
		return escolhida;
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

}
