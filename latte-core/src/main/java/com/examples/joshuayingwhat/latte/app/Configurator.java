package com.examples.joshuayingwhat.latte.app;

import java.util.WeakHashMap;

/**
 *
 * @author joshuayingwhat
 * @date 2018/12/18
 */

public class Configurator {

  private static final WeakHashMap<String, Object> LATTE_CONFIGS = new WeakHashMap<>();

  private Configurator() {
    //初始化配置  首先设置初始化配置未完成
    LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
  }

  /**
   * 静态内部类的初始化
   */
  public static Configurator getInstance() {
    return Holder.INSTANCE;
  }

  private static class Holder {
    private static final Configurator INSTANCE = new Configurator();
  }

  final WeakHashMap<String, Object> getLatteConfigs() {
    return LATTE_CONFIGS;
  }

  public final void configure() {
    LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
  }

  public final Configurator withApiHost(String host) {
    LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
    return this;
  }

  /**
   * 检查配置项是否完成
   */
  private void checkConfiguration() {
    final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
    if (!isReady) {
      throw new RuntimeException("Configuration is not ready,call Configurat");
    }
  }

  @SuppressWarnings("unchecked") final <T> T getConfigruation(Enum<ConfigType> key) {
    checkConfiguration();
    return (T) LATTE_CONFIGS.get(key.name());
  }
}
