package com.examples.joshuayingwhat.latte.ui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class LauncherHolderCreator implements CBViewHolderCreator<LauncherHodler> {

    @Override
    public LauncherHodler createHolder() {
        return new LauncherHodler();
    }
}
