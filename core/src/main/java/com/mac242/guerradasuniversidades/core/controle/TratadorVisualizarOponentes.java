package com.mac242.guerradasuniversidades.core.controle;

import com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades;

import react.UnitSlot;

public class TratadorVisualizarOponentes extends UnitSlot {
	
	private VisaoGuerraDasUniversidades visao;

	public TratadorVisualizarOponentes(VisaoGuerraDasUniversidades visao){
		this.visao = visao;
	}
	
	@Override
	public void onEmit() {
		visao.exibirTela(visao.obterOponentes());
	}

}
