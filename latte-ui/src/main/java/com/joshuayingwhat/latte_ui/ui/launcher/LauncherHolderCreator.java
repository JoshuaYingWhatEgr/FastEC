package com.joshuayingwhat.latte_ui.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.joshuayingwhat.latte_ui.ui.launcher.LauncherHodler;

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHodler> {

    @Override
    public LauncherHodler createHolder() {
        return new LauncherHodler();
    }
}
