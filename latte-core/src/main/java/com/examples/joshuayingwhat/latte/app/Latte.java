package com.examples.joshuayingwhat.latte.app;

import android.content.Context;
import java.util.WeakHashMap;

/**
 * @author joshuayingwhat
 * @date 2018/12/18
 */

public final class Latte {
  public static Configurator init(Context context) {
    getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
    return Configurator.getInstance();
  }

  private static WeakHashMap<String, Object> getConfigurations() {
    return Configurator.getInstance().getLatteConfigs();
  }
}
