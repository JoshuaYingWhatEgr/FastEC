package com.joshuayingwhat.latte_ui.ui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class HolderCreator implements CBViewHolderCreator<com.examples.joshuayingwhat.latte.ui.banner.ImageHolder> {
    @Override
    public com.examples.joshuayingwhat.latte.ui.banner.ImageHolder createHolder() {
        return new com.examples.joshuayingwhat.latte.ui.banner.ImageHolder();
    }
}
