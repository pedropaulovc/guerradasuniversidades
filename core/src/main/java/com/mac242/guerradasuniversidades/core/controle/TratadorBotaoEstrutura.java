package com.mac242.guerradasuniversidades.core.controle;

import react.UnitSlot;
import com.mac242.guerradasuniversidades.core.visao.ImagemEstrutura;
import com.mac242.guerradasuniversidades.core.visao.TelaPrincipal;

import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterJogador;

public class TratadorBotaoEstrutura extends UnitSlot {

	private ImagemEstrutura imgEstrutura;
	private TelaPrincipal tela;

	public TratadorBotaoEstrutura(ImagemEstrutura imgEstrutura, TelaPrincipal tela) {
		this.imgEstrutura = imgEstrutura;
		this.tela = tela;
	}

	@Override
	public void onEmit() {
		if (obterJogador().comprarEstrutura(imgEstrutura.obterEstrutura())) {
			tela.obterEstruturas().put(imgEstrutura, true);
			tela.atualizarCampus();
		} else {
			tela.atualizarAviso("Estrutura indisponível!");
		}
	}
}
