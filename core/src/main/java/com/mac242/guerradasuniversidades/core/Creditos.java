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

public class Creditos extends TipoTela {

	private Interface iface;

	public Creditos(GuerraDasUniversidades jogo){
		super(jogo);
	}
	
	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenharRecordes();
		desenharVoltar();
	}

	private void desenharRecordes() {
		Font font = graphics().createFont("Helvetica", Style.BOLD, 20f);
		TextFormat format = new TextFormat().withFont(font);
		TextLayout layout = graphics().layoutText(
				"Neque porro nullam adisplicing elit.", format);

		int width = (int) Math.ceil(layout.width());
        int height = (int) Math.ceil(layout.height());
		
		CanvasLayer layerAjuda = graphics().createCanvasLayer(width, height);
		base.add(layerAjuda);
		
		layerAjuda.canvas().drawText(layout, 0, 0);
        layerAjuda.setTranslation(graphics().width()/2 - width/2,
        		graphics().height()/2 - height/2);
	}

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
				jogo.exibirTela(jogo.obterOpcoes());
			}
		});
		
		root.add(grupoVoltar);
		grupoVoltar.add(voltar);
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
		return "Créditos";
	}
}
