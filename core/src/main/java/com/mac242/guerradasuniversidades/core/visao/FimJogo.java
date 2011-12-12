package com.mac242.guerradasuniversidades.core.visao;

import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;
import static playn.core.PlayN.pointer;
import playn.core.Color;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import tripleplay.ui.Interface;

public class FimJogo extends TipoTela {

	private Interface iface;
	private String imagem;

	public FimJogo(VisaoGuerraDasUniversidades jogo) {
		super(jogo);
	}
	
	public void exibirVitoria(){
		this.imagem = "vitoria";
	}
	
	public void exibirDerrota(){
		this.imagem = "derrota";
	}

	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		desenharFimJogo();
	}

	private void desenharFimJogo() {
		final Image imgFimJogo = assetManager().getImage("images/" + imagem + ".jpg");
		imgFimJogo.addCallback(new ResourceCallback<Image>() {
			@Override
			public void error(Throwable err) {
				log().error("Erro carregando imagem", err);
			}

			@Override
			public void done(Image resource) {
				ImageLayer layerImagem = graphics().createImageLayer(imgFimJogo);
				layerImagem.transform().translate(
						graphics().width() / 2 - imgFimJogo.width() / 2, 
						graphics().height() / 2 - imgFimJogo.height() / 2);
				base.add(layerImagem);
			}
		});
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

	public String toString() {
		return "Fim de Jogo";
	}

}
