package com.mac242.guerradasuniversidades.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.mac242.guerradasuniversidades.core.GuerraDasUniversidades;

public class GuerraDasUniversidadesActivity extends GameActivity {

  @Override
  public void main(){
    platform().assetManager().setPathPrefix("com/mac242/guerradasuniversidades/resources");
    PlayN.run(new GuerraDasUniversidades());
  }
}
