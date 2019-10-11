package com.examples.joshuayingwhat.latte.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.examples.joshuayingwhat.latte.R;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;

/**
 * 这个是每一个模块的fragment
 *
 * @author joshuayingwhat
 */
public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    private static final long CLICK_TIME = 2000L;

    private long mExitTime = 0;

    @Override
    public void onResume() {
        super.onResume();
        // TODO: 2019-10-11  这里解决重新返回fragment 点击事件不生效的情况
//        final View rootView = getView();
//        if (rootView != null) {
//            rootView.setFocusableInTouchMode(true);
//            rootView.requestFocus();
//            rootView.setOnKeyListener(this);
//        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > CLICK_TIME) {
                Toast.makeText(getContext(), "点击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                _mActivity.finish();
                if (mExitTime != 0) {
                    mExitTime = 0;
                }
            }
            //自己消化事件
            return true;
        }
        return false;
    }
}
