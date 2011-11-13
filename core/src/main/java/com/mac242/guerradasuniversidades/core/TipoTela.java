package com.mac242.guerradasuniversidades.core;

import static playn.core.PlayN.graphics; 
import playn.core.Font;
import playn.core.GroupLayer;
import playn.core.SurfaceLayer;
import tripleplay.ui.Background;
import tripleplay.ui.Style;
import tripleplay.ui.Styles;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsavel pelo style das telas.
 */

public abstract class TipoTela {

	//GroupLayer agrupa os layers numa forma hierarquica.
	protected GuerraDasUniversidades jogo;
	protected GroupLayer base;
	private static Styles estiloBotao;
	private static Styles estiloBotaoSelecionado;
	
	/**
	* Construtor
	* @param jogo 
	*/
	public TipoTela(GuerraDasUniversidades jogo) {
		this.jogo = jogo;
	}

	/**
	* Cria a camada hierarquica e adiciona uma nova camada no fundo do grupo
	*/
	protected void iniciarBase() {
		base = graphics().createGroupLayer();
		graphics().rootLayer().add(base);
	}
	
	/**
	* Destroi uma camada
	*/
	protected void destruirBase(){
		base.destroy();
		base = null;
	}

	/**
	* Desenha o fundo da tela, fazendo a renderizacao baseada em sprites de animação
	* @param cor  
	*/
	protected void desenharFundo(int cor) {
		SurfaceLayer bg = graphics().createSurfaceLayer(graphics().width(),	graphics().height());
		bg.surface().setFillColor(cor);
		bg.surface().fillRect(0, 0, bg.surface().width(), bg.surface().height());
		base.add(bg);
	}
	
	/**
	* Dando estilo aos botoes
	* @return estiloBotao 
	*/
	protected Styles obterEstiloBotao(){
		if(estiloBotao == null){
			estiloBotao = Styles.none().
				add(Style.BACKGROUND.is(Background.solid(0xFFCCCCCC, 5))).
				add(Style.FONT.is(graphics().createFont("Helvetica", Font.Style.PLAIN, 18))).
				addSelected(Style.BACKGROUND.is(Background.solid(0xFFBBBBBB, 6, 4, 4, 6)));
		}
		
		return estiloBotao;
	}
	
	/**
	* Dando estilo aos botoes quando selecionados
	* @return estiloBotaoSelecionado 
	*/
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