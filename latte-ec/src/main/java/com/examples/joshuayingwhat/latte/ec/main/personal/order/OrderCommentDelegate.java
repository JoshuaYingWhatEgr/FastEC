package com.examples.joshuayingwhat.latte.ec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.R;
import com.examples.joshuayingwhat.latte.ec.R2;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackManager;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackType;
import com.examples.joshuayingwhat.latte.utils.callback.IGlobalCallBack;
import com.joshuayingwhat.latte_ui.ui.widget.AutoPhotoLayout;
import com.joshuayingwhat.latte_ui.ui.widget.StartLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 评分晒单
 *
 * @author joshuayingwhat
 */
public class OrderCommentDelegate extends LatteDelegate {

    @BindView(R2.id.top_tv_comment_commit)
    AppCompatTextView topTvCommentCommit;
    @BindView(R2.id.tb_shop_cart)
    Toolbar tbShopCart;
    @BindView(R2.id.img_order_comment)
    AppCompatImageView imgOrderComment;
    @BindView(R2.id.tv_comment_title)
    TextView tvCommentTitle;
    @BindView(R2.id.custom_star_layout)
    StartLayout mStarLayout = null;
    @BindView(R2.id.et_order_comment)
    AppCompatEditText etOrderComment;
    @BindView(R2.id.custom_auto_photo_layout)
    AutoPhotoLayout mAutoPhotoLayout;

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mAutoPhotoLayout.setDelegate(this);
        CallBackManager.getInstance().addCallBack(CallBackType.ON_CROP, new IGlobalCallBack<Uri>() {
            @Override
            public void executeCallBack(@Nullable Uri uri) {
                mAutoPhotoLayout.onCropTarget(uri);
            }
        });
    }

    @OnClick(R2.id.top_tv_comment_commit)
    void onClickSubmit() {
        int starCount = mStarLayout.getStarCount();
        Toast.makeText(getContext(), "评分："+starCount, Toast.LENGTH_SHORT).show();
    }

}


