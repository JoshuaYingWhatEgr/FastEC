package com.examples.joshuayingwhat.latte.ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * Created by joshuayingwhat on 2018/12/24.
 */

public class FontEcModule implements IconFontDescriptor{
  @Override public String ttfFileName() {
    return null;
  }

  @Override public Icon[] characters() {
    return EcIcons.values();
  }
}
