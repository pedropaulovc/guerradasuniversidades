package com.mac242.guerradasuniversidades.core.controle;

import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterJogador;

import com.mac242.guerradasuniversidades.core.modelo.Estrutura;
import com.mac242.guerradasuniversidades.core.visao.ImagemEstrutura;
import com.mac242.guerradasuniversidades.core.visao.TelaPrincipal;

import react.UnitSlot;

public class TratadorBotaoBlocoEnsino extends UnitSlot {
	private TelaPrincipal tela;

	public TratadorBotaoBlocoEnsino(TelaPrincipal tela){
		this.tela = tela;
	}
	
	public void onEmit() {
		if(!obterJogador().comprarEstrutura(Estrutura.SALA_AULA)){
			tela.adicionarAviso("Estrutura indisponível!");
			return;
		}
		
		int i = 0;
		while(tela.obterEstruturas().get(ImagemEstrutura.values()[i]))
			i++;
	
		tela.obterEstruturas().put(ImagemEstrutura.values()[i], true);
		
		tela.atualizarCampus();
		return;
	}
}
