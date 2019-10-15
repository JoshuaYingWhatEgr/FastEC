package com.examples.joshuayingwhat.latte.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;

import com.examples.joshuayingwhat.latte.R;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 代理activity
 *
 * @author joshuayingwhat
 */
public abstract class ProxyActivity extends SupportActivity {

    /**
     * 用户返回根delegate
     *
     * @return
     */
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        @SuppressLint("RestrictedApi") final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            //所有的delegate的目的就是为了能够load到root fragment中
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
