package com.mac242.guerradasuniversidades.core.visao;

import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterJogador;
import static com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades.obterJogo;
import static playn.core.PlayN.assetManager;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.pointer;

import java.util.HashMap;
import java.util.Map;

import playn.core.Color;
import playn.core.Font;
import playn.core.Image;
import tripleplay.ui.AxisLayout;
import tripleplay.ui.Button;
import tripleplay.ui.Group;
import tripleplay.ui.Interface;
import tripleplay.ui.Root;
import tripleplay.ui.Styles;

import com.mac242.guerradasuniversidades.core.controle.TratadorAtacarOponente;
import com.mac242.guerradasuniversidades.core.controle.TratadorTrocarTela;
import com.mac242.guerradasuniversidades.core.modelo.NomeUniversidade;
import com.mac242.guerradasuniversidades.core.modelo.StatusJogador;

/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe responsavel pela visualização dos oponentes no jogo.
 */
public class Oponentes extends TipoTela {

	private Interface iface;
	private Map<NomeUniversidade, Button> legendas;

	public Oponentes(VisaoGuerraDasUniversidades jogo) {
		super(jogo);
		legendas = new HashMap<NomeUniversidade, Button>();
	}

	@Override
	public void init() {
		iniciarBase();
		desenharFundo(Color.rgb(255, 255, 255));
		exibirOponentes();
	}

	private void exibirOponentes() {
		iface = new Interface(null);
		pointer().setListener(iface.plistener);
		
		Root root = iface.createRoot(AxisLayout.vertical().gap(30));
		root.setSize(graphics().width(), graphics().height());
		base.add(root.layer);
		
		Group oponentes = new Group(AxisLayout.horizontal().gap(50));
		
		for(NomeUniversidade nome : NomeUniversidade.values()){
			oponentes.add(gerarInfoOponente(nome));
		}
		
		Button voltar = new Button().setText("Voltar");
		voltar.clicked().connect(new TratadorTrocarTela(visao, visao.obterTelaPrincipal()));
		voltar.addStyles(obterEstiloBotao());
		
		root.add(oponentes, voltar);
	}

	private Group gerarInfoOponente(NomeUniversidade nome) {
		TratadorAtacarOponente tratador = new TratadorAtacarOponente()
			.definirJogador(obterJogador())
			.definirOponente(nome)
			.definirVisao(visao);
		
		Button item = new Button();
		String nomeuniversidade = nome.toString().toLowerCase();
		Image logo = assetManager().getImage("images/" + nomeuniversidade + ".jpg");
		item.setIcon(logo);
		item.clicked().connect(tratador);
		
		Styles estiloLegenda = Styles.none().
			add(tripleplay.ui.Style.FONT.is(graphics().createFont("Helvetica",
				Font.Style.BOLD, 15)));
	
		Button legenda = new Button();
		legendas.put(nome, legenda);
		atualizarInfoOponente(nome);
		legenda.addStyles(estiloLegenda);
		legenda.clicked().connect(tratador);
		
		Button atacar = new Button().setText("Atacar");
		atacar.addStyles(obterEstiloBotao());
		atacar.clicked().connect(tratador);
		
		Group grupo = new Group(AxisLayout.vertical());
		grupo.add(item, legenda, atacar);
		return grupo;
	}
	
	private void atualizarInfoOponente(NomeUniversidade nome){
		StatusJogador status = obterJogo().obterStatus(nome);
		Button legenda = legendas.get(nome);
		
		String texto = 
			status.nome + "\n" +
			"HP: " + status.HP + "/10" + "\n" +
			"PE: " + status.PE + "\n" +
			"FO: " + status.FO + "\n" +
			"FO Máximo: " + status.maxFO + "\n" +
			"PE/seg: "	+ status.taxaPE + "\n" +
			"FO/dia: " + status.taxaFO + "\n" +
			"Manutenção/dia: " + status.taxaManutencao + "\n" +
			"Funcionários/dia: "	+ status.taxaFuncionarios + "\n";
		legenda.setText(texto);
	}

	@Override
	public void update(float delta) {
		if (iface != null) {
			iface.update(delta);
		}
		
		for(NomeUniversidade nome : legendas.keySet()){
			atualizarInfoOponente(nome);	
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
	
	public String toString(){
		return "Oponentes";
	}

}
