package com.mac242.guerradasuniversidades.core.modelo;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.mac242.guerradasuniversidades.core.util.Observable;
import com.mac242.guerradasuniversidades.core.util.Observer;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe principal do modelo lógico do jogo. Responsável por informações
 * comuns a todos os jogadores.
 */
public class GuerraDasUniversidades extends Observable implements Observer {
	private int segundos;
	private int dia = 1;
	private static final int duracaoDia = 10;
	
	private Jogador jogador;
	private Map<NomeUniversidade, TipoJogador> jogadores;
	private Map<NomeUniversidade, TipoJogador> vivos;

	/**
	 * Construtor do jogo.
	 * @param construtor Os dados do jogador.
	 */
	public GuerraDasUniversidades(ConstrutorJogador construtor) {
		jogadores = new HashMap<NomeUniversidade, TipoJogador>();
		vivos = new HashMap<NomeUniversidade, TipoJogador>();
		
		iniciar(construtor);
	}

	/**
	 * Método responsável por popular a lista de jogadores para iniciar
	 * o jogo.
	 * @param construtor Os dados do jogador.
	 */
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
	 * Método responsável por marcar a passagem do tempo no jogo.
	 * Deve ser invocado a cada segundo após iniciado o jogo.
	 */
	public void atualizarEventos() {
		segundos++;

		for (TipoJogador j : jogadores.values()) {
			j.atualizarSegundo();
			j.realizarJogada();
		}

		if (segundos % duracaoDia == 0){
			dia++;
			for (TipoJogador j : jogadores.values())
				j.atualizarDia();
		}
	}

	/**
	 * @return Um objeto FachadaJogador representando o jogador principal
	 * do jogo. 
	 */
	public FachadaJogador obterJogador() {
		return new FachadaJogador(jogador, this);
	}

	/**
	 * @return O dia atual no jogo.
	 */
	public int obterDia() {
		return dia;
	}

	/**
	 * @return O tempo de duração em segundos do dia no jogo.
	 */
	public static int obterDuracaoDia() {
		return duracaoDia;
	}

	/**
	 * @param nome O nome da universidade do jogador de interesse
	 * @return Um objeto contendo dados de status de um jogador
	 */
	public StatusJogador obterStatus(NomeUniversidade nome) {
		return jogadores.get(nome).obterStatus();
	}

	/**
	 * Método de atualização do padrão Observador-Observado, incrementa informações
	 * recebidas e notifica observadores.
	 */
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

	/**
	 * @return Um conjunto de jogadores ainda vivos no jogo.
	 */
	public Set<NomeUniversidade> obterVivos() {
		return vivos.keySet();
	}
	
	/**
	 * @param nome O nome do jogador de interesse
	 * @return A instância do jogador.
	 */
	public TipoJogador obterTipoJogador(NomeUniversidade nome){
		return jogadores.get(nome);
	}
}
