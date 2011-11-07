package com.mac242.guerradasuniversidades.core;

import static playn.core.PlayN.graphics;
import playn.core.Font;
import playn.core.GroupLayer;
import playn.core.SurfaceLayer;
import tripleplay.ui.Background;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;

public abstract class TipoTela {

	protected GuerraDasUniversidades jogo;
	protected GroupLayer base;
	private static Styles estiloBotao;
	private static Styles estiloBotaoSelecionado;
	
	public TipoTela(GuerraDasUniversidades jogo) {
		this.jogo = jogo;
	}

	protected void iniciarBase() {
		base = graphics().createGroupLayer();
		graphics().rootLayer().add(base);
	}
	
	protected void destruirBase(){
		base.destroy();
		base = null;
	}

	protected void desenharFundo(int cor) {
		SurfaceLayer bg = graphics().createSurfaceLayer(graphics().width(),	graphics().height());
		bg.surface().setFillColor(cor);
		bg.surface().fillRect(0, 0, bg.surface().width(), bg.surface().height());
		base.add(bg);
	}
	
	protected Styles obterEstiloBotao(){
		if(estiloBotao == null){
			estiloBotao = Styles.none().
				add(Style.BACKGROUND.is(Background.solid(0xFFCCCCCC, 5))).
				add(Style.FONT.is(graphics().createFont("Helvetica", Font.Style.PLAIN, 18))).
				addSelected(Style.BACKGROUND.is(Background.solid(0xFFBBBBBB, 6, 4, 4, 6)));
		}
		
		return estiloBotao;
	}
	
	protected Styles obterEstiloBotaoSelecionado(){
		if(estiloBotaoSelecionado == null){
			estiloBotaoSelecionado = Styles.none().
				add(Style.BACKGROUND.is(Background.solid(0xFFDDCCCC, 5))).
				add(Style.FONT.is(graphics().createFont("Helvetica", Font.Style.PLAIN, 18))).
				addSelected(Style.BACKGROUND.is(Background.solid(0xFFBBBBBB, 6, 4, 4, 6)));
		}
		
		return estiloBotaoSelecionado;
	}
	
	public abstract void init();

	public abstract void paint(float alpha);

	public abstract void update(float delta);

	public abstract void shutdown();
}