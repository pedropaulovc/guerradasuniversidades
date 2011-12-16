package com.mac242.guerradasuniversidades.html;

import com.mac242.guerradasuniversidades.core.visao.VisaoGuerraDasUniversidades;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

public class GuerraDasUniversidadesHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assetManager().setPathPrefix("guerradasuniversidades/");
    PlayN.run(new VisaoGuerraDasUniversidades());
  }
}
