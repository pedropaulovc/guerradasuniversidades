package com.mac242.guerradasuniversidades.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades;


/**
 * @author Pedro Paulo Vezza Campos    NUSP: 7538743
 * @author Daniel Huguenin             NUSP: 5118403
 * @author Antonio Rui Castro Junior   NUSP: 5984327
 * 
 * Classe principal para a inicializacao e mostra do jogo.
 */

public class GuerraDasUniversidadesJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assetManager().setPathPrefix("src/main/java/com/mac242/guerradasuniversidades/resources");
    platform.setTitle("Guerra das Universidades");
    PlayN.run(new VisaoGuerraDasUniversidades());
  }
}
