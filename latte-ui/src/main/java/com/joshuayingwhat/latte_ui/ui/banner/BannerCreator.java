package com.joshuayingwhat.latte_ui.ui.banner;


import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.examples.joshuayingwhat.latte.R;

import java.util.ArrayList;

public class BannerCreator {

    public static void setDefalut(ConvenientBanner<String> convenientBanner, ArrayList<String> banners
            , OnItemClickListener clickListener) {

        convenientBanner.setPages(new HolderCreator(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }
}
