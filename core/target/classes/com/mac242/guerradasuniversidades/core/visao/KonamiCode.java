package com.mac242.guerradasuniversidades.core.visao;

import static playn.core.PlayN.graphics;
import playn.core.CanvasLayer;
import playn.core.Color;
import playn.core.Font;
import playn.core.Font.Style;
import playn.core.TextFormat;
import playn.core.TextLayout;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327 
 * 
 * Classe criada para exibição de uma tela "Easter Egg" no jogo, implementada como forma de aprendizado.
 * 
 */


public class KonamiCode extends TipoTela {
	
	private static final int[] konami = {38, 38, 40, 40, 37, 39, 37, 39, 66, 65};
	private int estado = 0; 
	
	public KonamiCode(VisaoGuerraDasUniversidades jogo) {
		super(jogo);
	}
	
	public boolean pressionarTecla(int tecla){
		if (tecla == konami[estado])
			estado++;
		else
			estado = 0;

		if (estado == 10) {
			estado = 0;
			return true;
		}
		return false;
	}

	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(0, 0, 0));
		desenharKonamiCode();
	}

	private void desenharKonamiCode() {
		Font font = graphics().createFont("Helvetica", Style.BOLD, 60f);
        TextFormat format = new TextFormat().
        		withFont(font).
        		withTextColor(Color.rgb(255, 255, 255));
        TextLayout layout = graphics().layoutText("THE GAME", format);
        
        int width = (int) Math.ceil(layout.width());
        int height = (int) Math.ceil(layout.height());
        
        CanvasLayer layer = graphics().createCanvasLayer(width, height);
        layer.canvas().drawText(layout, 0, 0);
        
        layer.setTranslation(graphics().width()/2 - width/2, graphics().height()/2 - height/2);
        base.add(layer);
	}

	@Override
	public void paint(float alpha) {
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void shutdown() {
		destruirBase();
	}

}
