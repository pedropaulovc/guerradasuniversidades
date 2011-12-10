package com.mac242.guerradasuniversidades.core.visao;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;
import playn.core.Color;
import playn.core.Image;
import playn.core.ImageLayer;
import react.UnitSlot;
import tripleplay.ui.AxisLayout;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Interface;
import tripleplay.ui.Root;
import tripleplay.ui.Stylesheet;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe reponsavel pela tela de menu do jogo.
 */

public class Menu extends TipoTela {
	private Interface iface;

	public Menu(VisaoGuerraDasUniversidades jogo) {
		super(jogo);
	}

	/**
	*  Inicializa a tela de menu setando, as opcoes de menu, fundo, logo.
	*/
	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenharLogo();
		desenharMenu();
	}

	/**
	*  Desenha a tela de menu do jogo, setando os botoes de menu
	*/
	private void desenharMenu() {
		Root root = inicializarInterface();

		Group buttons = new Group(AxisLayout.vertical().offStretch());
		root.add(buttons);

		for (final TipoTela tela : new TipoTela[] { visao.obterIniciar(),
				visao.obterOpcoes(), visao.obterRecordes(), visao.obterAjuda() }) {
			Button button = new Button().setText(tela.toString());
			buttons.add(button);
			button.clicked().connect(new UnitSlot() {
				public void onEmit() {
					visao.exibirTela(tela);
				}
			});
		}
	}

	private Root inicializarInterface() {
		iface = new Interface(null);
		pointer().setListener(iface.plistener);

		Stylesheet rootSheet = Stylesheet.builder()
				.add(Button.class, obterEstiloBotao()).create();

		Root root = iface.createRoot(AxisLayout.vertical().offStretch(),
				rootSheet);
		root.setSize(250, graphics().height());
		root.layer.setTranslation(10, 0);
		base.add(root.layer);
		return root;
	}

	/**
	* Desenha a imagem que sera visivel na tela de menu 
	*/
	private void desenharLogo() {
		Image logo = assetManager().getImage("images/logo.jpg");
		ImageLayer layerLogo = graphics().createImageLayer(logo);
		layerLogo.transform().translate(270, 50);
		base.add(layerLogo);
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

	public String toString() {
		return "Menu";
	}
}
