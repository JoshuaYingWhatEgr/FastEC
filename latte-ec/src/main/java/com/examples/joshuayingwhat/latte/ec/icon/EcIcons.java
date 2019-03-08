package com.examples.joshuayingwhat.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by joshuayingwhat on 2018/12/24.
 */

public enum EcIcons implements Icon {
  ;

  private char character;

  EcIcons(char character) {
    this.character = character;
  }

  @Override public String key() {
    return name().replace('_','-');
  }

  @Override public char character() {
    return character;
  }
}
