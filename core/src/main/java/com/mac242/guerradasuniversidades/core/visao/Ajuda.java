package com.mac242.guerradasuniversidades.core.visao;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;
import playn.core.CanvasLayer;
import playn.core.Color;
import playn.core.Font;
import playn.core.Font.Style;
import playn.core.TextFormat;
import playn.core.TextLayout;
import tripleplay.ui.AxisLayout;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Interface;
import tripleplay.ui.Root;
import tripleplay.ui.Stylesheet;

import com.mac242.guerradasuniversidades.core.controle.TratadorTrocarTela;

/**
 * Classe responsavel pela tela ajuda do jogo
 * 
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 */
public class Ajuda extends TipoTela {

	private Interface iface;

	/**
	* Construtor
	* @param jogo 
	*/
	public Ajuda(VisaoGuerraDasUniversidades jogo) {
		super(jogo);
	}

	/**
	* inicializa a tela de ajuda 
	*/
	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenharAjuda();
		desenharVoltar();
	}

	/**
	* Configurando, a tela de ajuda.
	* Setando o formato, fonte tamanho... 
	*/
	private void desenharAjuda() {
		Font font = graphics().createFont("Helvetica", Style.BOLD, 20f);
		TextFormat format = new TextFormat().withFont(font);
		TextLayout layout = graphics().layoutText(
				"Consectetur adisplicing elit.", format);

		int width = (int) Math.ceil(layout.width());
        int height = (int) Math.ceil(layout.height());
		
		CanvasLayer layerAjuda = graphics().createCanvasLayer(width, height);
		base.add(layerAjuda);
		
		layerAjuda.canvas().drawText(layout, 0, 0);
        layerAjuda.setTranslation(graphics().width()/2 - width/2,
        		graphics().height()/2 - height/2);
	}

	/**
	* Criando a opcao(botao) voltar na tela de ajuda.
	* (voltar = tela de menu) 
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
		voltar.clicked().connect(new TratadorTrocarTela(visao, visao.obterMenu()));
		
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
	//==================================================================================
	@Override
	public void shutdown() {
		if (iface != null) {
			pointer().setListener(null);
			iface = null;
		}
		
		destruirBase();
	}

	/**
	* @return ajuda - string
	*/
	@Override
	public String toString() {
		return "Ajuda";
	}
}
