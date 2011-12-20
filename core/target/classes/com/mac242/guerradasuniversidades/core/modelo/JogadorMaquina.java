package com.mac242.guerradasuniversidades.core.modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mac242.guerradasuniversidades.core.util.Observable;
import com.mac242.guerradasuniversidades.core.util.Observer;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe representante de um jogador máquina no jogo. É uma fachada para um objeto
 * Jogador segundo a interface TipoJogador. 
 */
public class JogadorMaquina extends Observable implements Observer, TipoJogador {
	private Jogador jogador;
	private FachadaJogador fachada;
	private Random rand;
	
	private final static String[] nomes = { "Alair", "Alan", "Goldman",
			"Ana Cristina", "Fujita", "Mandel", "Carlinhos", "Hitoshi",
			"Humes", "Cris", "Batista", "Ernesto", "Kon", "Flávio", "Reverbel",
			"JEF", "Coelho", "Kunio", "Leliane", "Leônidas", "Marcílio",
			"Marcel", "Finger", "MQZ", "Gerosa", "Gubi", "Maria Ângela",
			"Nami", "Nelson", "Nina", "Feofiloff", "Silva e Silva", "Renata",
			"Hirata", "Marcondes", "Ronaldo", "Routo", "Siang", "Setzer",
			"Yoshi", "Yoshiko" };
	
	/**
	 * O construtor do jogador
	 * @param construtor Os dados do jogador.
	 * @param jogo O jogo ao qual está associado
	 */
	public JogadorMaquina(ConstrutorJogador construtor, GuerraDasUniversidades jogo) {
		jogador = construtor.construir(jogo);
		jogador.addObserver(this);
		fachada = new FachadaJogador(jogador, jogo);
		rand = new Random();
	}

	/**
	 * Método responsável por construir uma lista de oponentes máquina
	 * @param jogador O nome da universidade do jogador principal
	 * @param jogo O jogo ao qual os jogadores estarão associados.
	 * @return Uma lista de JogadoresMaquina prontos para serem utilizados no jogo
	 */
	public static List<JogadorMaquina> construirOponentes(NomeUniversidade jogador, 
			GuerraDasUniversidades jogo) {
		ConstrutorJogador construtor = new ConstrutorJogador();
		Random rand = new Random();
		List<JogadorMaquina> oponentes = new ArrayList<JogadorMaquina>();
		
		for (NomeUniversidade n : NomeUniversidade.values()) {
			if (n.equals(jogador))
				continue;
			FocoAdministracao[] focos = FocoAdministracao.values();
			construtor
				.definirFoco(focos[rand.nextInt(focos.length)])
				.definirNome(nomes[rand.nextInt(nomes.length)])
				.definirUniversidade(n);
			oponentes.add(new JogadorMaquina(construtor, jogo));
		}

		return oponentes;
	}

	@Override
	public void atualizarSegundo() {
		jogador.atualizarSegundo();
	}

	@Override
	public void atualizarDia() {
		jogador.atualizarDia();
	}

	@Override
	public int obterPontosEnsino() {
		return jogador.obterPontosEnsino();
	}

	@Override
	public int obterFoco() {
		return jogador.obterFoco();
	}

	@Override
	public int obterHP() {
		return jogador.obterHP();
	}

	@Override
	public int obterTaxaManutencao() {
		return jogador.obterTaxaFoco();
	}

	@Override
	public int obterTaxaFuncionarios() {
		return jogador.obterTaxaFuncionarios();
	}

	@Override
	public int obterTaxaFoco() {
		return jogador.obterTaxaFoco();
	}

	@Override
	public GerenteEstruturas obterGerenteEstruturas() {
		return jogador.obterGerenteEstruturas();
	}

	@Override
	public int obterTaxaPontosEnsino() {
		return jogador.obterTaxaPontosEnsino();
	}

	@Override
	public int obterFocoMaximo() {
		return jogador.obterFocoMaximo();
	}

	@Override
	public String obterNome() {
		return jogador.obterNome();
	}

	@Override
	public void realizarJogada() {
		double operacao = rand.nextDouble();
		
		if(operacao < 0.8)
			return;
		else if(operacao <= 0.96){
			Estrutura aComprar = null; 
			double opcao = rand.nextDouble();

			if(opcao < 0.2)
				aComprar = Estrutura.SALA_AULA;
			else if(opcao < 0.5)
				aComprar = Estrutura.ALUNO;
			else if(opcao < 0.7)
				aComprar = Estrutura.PROFESSOR;
			else if(opcao < 0.8)
				aComprar = Estrutura.PRACA_CENTRAL;
			else {
				List<Estrutura> disp = fachada.obterEstruturasDisponiveis();
				if(disp.size() > 0)
					aComprar = disp.get(rand.nextInt(disp.size()));
			}

			if(aComprar != null)
				fachada.comprarEstrutura(aComprar);
		} else {
			NomeUniversidade alvo;
			do {
				int escolhido = rand.nextInt(NomeUniversidade.values().length);
				alvo = NomeUniversidade.values()[escolhido];
			} while(alvo.equals(obterNomeUniversidade()));
			fachada.atacar(alvo);
		}
	}

	@Override
	public StatusJogador obterStatus() {
		return jogador.obterStatus();
	}

	@Override
	public NomeUniversidade obterNomeUniversidade() {
		return jogador.obterNomeUniversidade();
	}

	/**
	 * Método de atualização do padrão Observador-Observado, repassa para 
	 * observadores a notificação.
	 */
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);
	}

	@Override
	public int calcularPoderAtaque() {
		return jogador.calcularPoderAtaque();
	}

	@Override
	public void atacar(TipoJogador alvo) {
		jogador.atacar(alvo);
	}

	@Override
	public void receberAtaque(int poder) {
		jogador.receberAtaque(poder);
	}
	
	public int obterPontosEnsinoMaximo(){
		return jogador.obterPontosEnsinoMaximo();
	}
}
