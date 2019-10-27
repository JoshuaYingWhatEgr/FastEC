package com.examples.joshuayingwhat.latte.ec.detail;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.YoYo;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;
import com.examples.joshuayingwhat.latte.net.rx.RxRestClient;
import com.examples.joshuayingwhat.latte.ui.LatteLoader;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.joanzapata.iconify.widget.IconTextView;
import com.joshuayingwhat.latte_ui.ui.animation.BezierAnimation;
import com.joshuayingwhat.latte_ui.ui.animation.BezierUtil;
import com.joshuayingwhat.latte_ui.ui.banner.HolderCreator;
import com.joshuayingwhat.latte_ui.ui.widget.CircleTextView;
import com.youth.banner.transformer.DefaultTransformer;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 商品详情界面
 *
 * @author joshuayingwhat
 */
public class GoodsDetailDelegate extends LatteDelegate implements AppBarLayout.OnOffsetChangedListener, BezierUtil.AnimationListener {

    private static final String ARG_GOODS_ID = "ARG_GOODS_ID";
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate()
            .override(100, 100);
    @BindView(R2.id.goods_detail_toolbar)
    Toolbar mToolbar = null;
    @BindView(R2.id.tab_layout)
    TabLayout mTabLayout = null;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager = null;
    @BindView(R2.id.detail_banner)
    ConvenientBanner<String> mBanner = null;
    @BindView(R2.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout mCollapsingToolbarLayout = null;
    @BindView(R2.id.app_bar_detail)
    AppBarLayout mAppBar = null;
    //底部
    @BindView(R2.id.icon_favor)
    IconTextView mIconFavor = null;
    @BindView(R2.id.tv_shopping_cart_amount)
    CircleTextView mCircleTextView = null;
    @BindView(R2.id.rl_add_shop_cart)
    RelativeLayout mRlAddShopCart = null;
    @BindView(R2.id.icon_shop_cart)
    IconTextView mIconShopCart = null;
    private int mGoodsId = -1;
    private String mGoodsThumbUrl = null;
    private int mShopCount = 0;

    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        //设置折叠时工具栏布局的颜色
        mCollapsingToolbarLayout.setContentScrimColor(Color.WHITE);
        mAppBar.addOnOffsetChangedListener(this);
        mCircleTextView.setCircleBackGround(Color.RED);
        initData();
        initTabLayout();
    }

    private void initPager(JSONObject data) {
        final PagerAdapter adapter = new TabPagerAdapter(getFragmentManager(), data);
        mViewPager.setAdapter(adapter);
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setSelectedTabIndicatorColor
                (ContextCompat.getColor(getContext(), R.color.app_main));
        mTabLayout.setTabTextColors(ColorStateList.valueOf(Color.BLACK));
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 请求数据
     */
    private void initData() {
        RestClient.builder()
                .loader(getContext())
                .url("goods_detail.php")
                .params("goods_id", mGoodsId)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LatteLoader.stopLoading();
                        if (response.isEmpty() || response.length() <= 0) {
                            getSupportDelegate().pop();
                            return;
                        }
                        final JSONObject data =
                                JSON.parseObject(response).getJSONObject("data");
                        initBanner(data);
                        initGoodsInfo(data);
                        initPager(data);
                        setShopCartCount(data);
                    }
                })
                .build()
                .get();
    }


    private void initBanner(JSONObject data) {
        final JSONArray array = data.getJSONArray("banners");
        final List<String> images = new ArrayList<>();
        final int size = array.size();
        for (int i = 0; i < size; i++) {
            images.add(array.getString(i));
        }
        mBanner.setPages(new HolderCreator(), images)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);
    }

    private void initGoodsInfo(JSONObject data) {
        final String goodsData = data.toJSONString();
        getSupportDelegate().
                loadRootFragment(R.id.frame_goods_info, GoodsInfoDelegate.create(goodsData));
    }

    public static GoodsDetailDelegate creator(@NotNull int goodsId) {
        final Bundle args = new Bundle();
        args.putInt(ARG_GOODS_ID, goodsId);
        final GoodsDetailDelegate delegate = new GoodsDetailDelegate();
        delegate.setArguments(args);
        return delegate;
    }


    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mGoodsId = args.getInt(ARG_GOODS_ID);
        }
    }

    //滚动的事件监听
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

    }


    @OnClick({R2.id.rl_add_shop_cart})
    void onclickAddShopCart() {
        final CircleImageView animImg = new CircleImageView(getContext());
        Glide.with(this)
                .load(mGoodsThumbUrl)
                .apply(OPTIONS)
                .into(animImg);
        BezierAnimation.addCart(this, mRlAddShopCart, mIconShopCart, animImg, this);
    }

    private void setShopCartCount(JSONObject data) {
        mGoodsThumbUrl = data.getString("thumb");
        if (mShopCount == 0) {
            mCircleTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationEnd() {
        YoYo.with(new ScaleUpAnimator())
                .duration(500)
                .playOn(mIconShopCart);
        //将添加到购物车的商品发送给服务器
        RxRestClient.builder()
                .url("add_shop_cart_count.php")
                .params("count", mShopCount)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
//                        if (!TextUtils.isEmpty(response)) {
//                            final boolean isAdded = JSON.parseObject(response).getBoolean("data");
//                            if (isAdded) {
                        mShopCount++;
                        mCircleTextView.setVisibility(View.VISIBLE);
                        mCircleTextView.setText(String.valueOf(mShopCount));
//                            }
//                        }
                    }
                });
    }

    @OnClick({R2.id.icon_goods_back})
    void onClickGoodsDetailBack() {
        getSupportDelegate().pop();
    }
}
