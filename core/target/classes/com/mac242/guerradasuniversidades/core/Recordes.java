package com.mac242.guerradasuniversidades.core;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;
import playn.core.CanvasLayer;
import playn.core.Color;
import playn.core.Font;
import playn.core.TextFormat;
import playn.core.TextLayout;
import playn.core.Font.Style;
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
 * Classe responsavel pela tela de recordes no jogo.
 */

public class Recordes extends TipoTela {

	private Interface iface;

	public Recordes(GuerraDasUniversidades jogo){
		super(jogo);
	}
	
	/**
	 * Inicializa a tela de recordes 
	 */
	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenharRecordes();
		desenharVoltar();
	}

	/**
	 * Desenha a tela de recordes
	 */
	private void desenharRecordes() {
		Font font = graphics().createFont("Helvetica", Style.BOLD, 20f);
		TextFormat format = new TextFormat().withFont(font);
		TextLayout layout = graphics().layoutText(
				"Lorem ipsum dolor sit amet.", format);

		int width = (int) Math.ceil(layout.width());
        int height = (int) Math.ceil(layout.height());
		
		CanvasLayer layerAjuda = graphics().createCanvasLayer(width, height);
		base.add(layerAjuda);
		
		layerAjuda.canvas().drawText(layout, 0, 0);
        layerAjuda.setTranslation(graphics().width()/2 - width/2,
        		graphics().height()/2 - height/2);
	}

	/**
	* Desenha o botao voltar na tela de recordes
	*/
	private void desenharVoltar() {
		iface = new Interface(null);
		pointer().setListener(iface.plistener);
		
		Stylesheet rootSheet = Stylesheet.builder().
			add(Button.class, obterEstiloBotao()).
			create();
		
		Root root = iface.createRoot(AxisLayout.vertical(), rootSheet);
		root.setSize(graphics().width(), graphics().height());
		root.layer.setTranslation(0, 200);
		base.add(root.layer);
		
		Group grupoVoltar = new Group(AxisLayout.horizontal());
		Button voltar = new Button().setText("Voltar");
		voltar.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				jogo.exibirTela(jogo.obterMenu());
			}
		});
		
		root.add(grupoVoltar);
		grupoVoltar.add(voltar);
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
		return "Recordes";
	}
}
