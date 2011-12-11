package com.mac242.guerradasuniversidades.core.controle;

import com.mac242.guerradasuniversidades.core.modelo.FachadaJogador;
import com.mac242.guerradasuniversidades.core.modelo.NomeUniversidade;

import react.UnitSlot;

public class TratadorAtacarOponente extends UnitSlot {
	
	private FachadaJogador jogador;
	private NomeUniversidade oponente;

	public TratadorAtacarOponente(FachadaJogador jogador, NomeUniversidade oponente){
		this.jogador = jogador;
		this.oponente = oponente;
	}
	
	@Override
	public void onEmit() {
		System.out.println(jogador.atacar(oponente));
	}

}
