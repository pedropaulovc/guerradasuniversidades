package com.mac242.guerradasuniversidades.core.modelo;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class GuerraDasUniversidades extends Observable implements Observer {
	private int segundos;
	private int dia = 1;
	private static final int duracaoDia = 10;
	
	private Jogador jogador;
	private Map<NomeUniversidade, TipoJogador> jogadores;
	private Map<NomeUniversidade, TipoJogador> vivos;

	public GuerraDasUniversidades(ConstrutorJogador construtor) {
		jogadores = new HashMap<NomeUniversidade, TipoJogador>();
		vivos = new HashMap<NomeUniversidade, TipoJogador>();
		
		iniciar(construtor);
	}

	private void iniciar(ConstrutorJogador construtor) {
		jogador = construtor.construir(this);
		jogador.addObserver(this);
		NomeUniversidade universidade = construtor.obterUniversidade();
		jogadores.put(universidade, jogador);
		vivos.put(universidade, jogador);

		for (JogadorMaquina j : JogadorMaquina.construirOponentes(universidade, this)) {
			jogadores.put(j.obterNomeUniversidade(), j);
			vivos.put(j.obterNomeUniversidade(), j);
			j.addObserver(this);
		}
	}

	/**
	 * Deve ser invocado a cada segundo após iniciado o jogo.
	 */
	public void atualizarEventos() {
		segundos++;

		for (TipoJogador j : jogadores.values()) {
			j.atualizarPE();
			j.realizarJogada();
		}

		if (segundos % duracaoDia == 0){
			dia++;
			for (TipoJogador j : jogadores.values())
				j.atualizarTaxas();
		}
	}

	public FachadaJogador obterJogador() {
		return new FachadaJogador(jogador, this);
	}

	public int obterDia() {
		return dia;
	}

	public static int obterDuracaoDia() {
		return duracaoDia;
	}

	public StatusJogador obterStatus(NomeUniversidade nome) {
		return jogadores.get(nome).obterStatus();
	}

	@Override
	public void update(Observable o, Object arg) {
		Notificacao notificacao = (Notificacao) arg;
		if(notificacao.getTipo().equals(TipoNotificacao.MORTE)) {
			vivos.remove(notificacao.getUniversidade());
			if(notificacao.getUniversidade().equals(jogador.obterNomeUniversidade()))
				notificacao.setTipo(TipoNotificacao.DERROTA);
			else if(vivos.size() == 1)
				notificacao.setTipo(TipoNotificacao.VITORIA);
		}

		setChanged();
		notifyObservers(arg);
	}

	public Set<NomeUniversidade> obterVivos() {
		return vivos.keySet();
	}
	
	//TODO
	public TipoJogador obterTipoJogador(NomeUniversidade nome){
		return jogadores.get(nome);
	}
}
