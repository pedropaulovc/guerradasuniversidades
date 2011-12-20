package com.mac242.guerradasuniversidades.core.visao;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.keyboard;

import com.mac242.guerradasuniversidades.core.modelo.ConstrutorJogador;
import com.mac242.guerradasuniversidades.core.modelo.FachadaJogador;
import com.mac242.guerradasuniversidades.core.modelo.GuerraDasUniversidades;

import playn.core.Game;
import playn.core.Keyboard;

/**
 * Classe responsavel pelo main loop do jogo, setando o display e a
 * atualizacao dos graficos.
 * 
 * @author Pedro Paulo Vezza Campos NUSP: 7538743
 * @author Daniel Huguenin NUSP: 5118403
 * @author Antonio Rui Castro Junior NUSP: 5984327
 */

public class VisaoGuerraDasUniversidades implements Game {

	private TipoTela atual;
	private Menu menu;
	private Opcoes opcoes;
	private Recordes recordes;
	private KonamiCode kc;
	private Iniciar iniciar;
	private Universidades universidades;
	private TelaPrincipal telaPrincipal;
	private Ajuda ajuda;
	private Creditos creditos;
	private Oponentes oponentes;
	private FimJogo fimJogo;
	private int contaUpdates;
	private static GuerraDasUniversidades jogo;
	private static ConstrutorJogador construtor;

	public VisaoGuerraDasUniversidades() {
		menu = new Menu(this);
		opcoes = new Opcoes(this);
		recordes = new Recordes(this);
		kc = new KonamiCode(this);
		iniciar = new Iniciar(this);
		universidades = new Universidades(this);
		telaPrincipal = new TelaPrincipal(this);
		ajuda = new Ajuda(this);
		creditos = new Creditos(this);
		oponentes = new Oponentes(this);
		fimJogo = new FimJogo(this);
	}

	public static ConstrutorJogador obterConstrutor() {
		if(construtor == null)
			 construtor = new ConstrutorJogador();
		return construtor;
	}

	public static void iniciarJogo(){
		jogo = new GuerraDasUniversidades(construtor);
	}
	
	public static GuerraDasUniversidades obterJogo() {
		return jogo;
	}

	public static FachadaJogador obterJogador() {
		if(jogo == null)
			return null;
		return jogo.obterJogador();
	}

	/**
	 * Seta o tamanho da tela e exibe a tela de menu
	 */
	@Override
	public void init() {
		graphics().setSize(711, 531);
		tratarTeclado();
		exibirTela(menu);
	}

	/**
	 * Trato dos eventos do teclado
	 */
	private void tratarTeclado() {
		keyboard().setListener(new Keyboard.Adapter() {
			public void onKeyDown(Keyboard.Event event) {
				int key = event.keyCode();
				if (key == Keyboard.KEY_ESC) {
					exibirTela(menu);
				} else if (kc.pressionarTecla(key)) {
					exibirTela(kc);
				}
			}
		});
	}

	/**
	 * Exibe a tela
	 */
	public void exibirTela(TipoTela tela) {
		if (atual != null) {
			atual.shutdown();
		}
		atual = tela;
		atual.init();
	}

	// =======================parte do codigo onde se faz a atualizacao dos
	// graficos, redesenhando =============
	@Override
	public void update(float delta) {
		contaUpdates++;
		if(contaUpdates == 1000/updateRate()){
			contaUpdates = 0;
			if(jogo != null)
				jogo.atualizarEventos();
		}
		
		atual.update(delta);
	}

	@Override
	public void paint(float alpha) {
		atual.paint(alpha);
	}

	@Override
	public int updateRate() {
		return 100;
	}

	public Menu obterMenu() {
		return menu;
	}

	public Opcoes obterOpcoes() {
		return opcoes;
	}

	public Recordes obterRecordes() {
		return recordes;
	}

	public Iniciar obterIniciar() {
		return iniciar;
	}

	public Universidades obterUniversidades() {
		return universidades;
	}

	public TelaPrincipal obterTelaPrincipal() {
		return telaPrincipal;
	}

	public Ajuda obterAjuda() {
		return ajuda;
	}

	public Creditos obterCreditos() {
		return creditos;
	}

	public Oponentes obterOponentes() {
		return oponentes;
	}
	
	public FimJogo obterFimJogo(){
		return fimJogo;
	}
}
