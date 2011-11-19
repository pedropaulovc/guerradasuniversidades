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

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsavel pela tela iniciar(escolha de personagem).
 */

public class Iniciar extends TipoTela{

	private Interface iface;
	
	/**
	* String que sera exibida num dos botoes da tela menu 
	*/
	public String toString(){
		return "Iniciar";
	}
	
	public Iniciar(GuerraDasUniversidades jogo) {
		super(jogo);
	}

	/**
	* Inicia a tela de iniciar o jogo 
	*/
	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenhaMenu();
	}
	
	
	/**
	* Desenha a tela do menu iniciar, contendo as opcoes de escolha de personagem. Seta a fonte, o tamanho 
	* a interface, os botoes para a escolha de personagem.
	*/
	public void desenhaMenu(){
		iface = new Interface(null);
		pointer().setListener(iface.plistener);
		
		Styles estiloLabel = Styles.none().
				add(Style.FONT.is(graphics().createFont("Helvetica", Font.Style.BOLD, 20)));
		
		Styles estiloTitulo = Styles.none().
				add(Style.FONT.is(graphics().createFont("Helvetica", Font.Style.BOLD, 32)));
			
		Stylesheet rootSheet = Stylesheet.builder().
				add(Button.class, obterEstiloBotao()).
				add(Label.class, estiloLabel).
				create();
		
		Root root = iface.createRoot(AxisLayout.vertical().gap(15), rootSheet);
		root.setSize(graphics().width(), graphics().height());
		base.add(root.layer);

		Label titulo = new Label("Escolha seu personagem:").setStyles(estiloTitulo);
		
		Label nomeReitor = new Label("Nome do Reitor: _______________________");
		
		//botoes para escolha de personagem
		Button botaoEsportes = new Button().setText("Esportes");
		Label infoEsportes = new Label("Upgrades de Velocidade e Força mais baratos");
		Group esportes = new Group(AxisLayout.vertical());
		esportes.add(botaoEsportes, infoEsportes);
		
		Button botaoHumanas = new Button().setText("Humanas");
		Label infoHumanas = new Label("Upgrades de Poder de Ensino mais baratos");
		Group humanas = new Group(AxisLayout.vertical());
		humanas.add(botaoHumanas, infoHumanas);
		
		Button botaoExatas = new Button().setText("Exatas");
		Label infoExatas = new Label("Upgrades de Estratégia e Foco mais baratos");
		Group exatas = new Group(AxisLayout.vertical());
		exatas.add(botaoExatas, infoExatas);
		
		Button botaoBiomedicas = new Button().setText("Biomédicas");
		Label infoBiomedicas = new Label("Upgrades de Pontos de Vida e Foco mais baratos");
		Group biomedicas = new Group(AxisLayout.vertical());
		biomedicas.add(botaoBiomedicas, infoBiomedicas);
		
		//redirecionado o jogador para a proxima tela ao esolher o personagem
		UnitSlot tratadorPersonagem = new UnitSlot() {
			@Override
			public void onEmit() {
				jogo.exibirTela(jogo.obterUniversidades());
			}
		};
		
		botaoEsportes.clicked().connect(tratadorPersonagem);
		botaoHumanas.clicked().connect(tratadorPersonagem);
		botaoExatas.clicked().connect(tratadorPersonagem);
		botaoBiomedicas.clicked().connect(tratadorPersonagem);
	
		// voltar ao menu
		Button voltar = new Button().setText("Voltar");
		voltar.clicked().connect(new UnitSlot() {
			@Override
			public void onEmit() {
				jogo.exibirTela(jogo.obterMenu());
			}
		});
		
		root.add(titulo, nomeReitor, esportes, humanas, exatas, biomedicas, voltar);
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
}
