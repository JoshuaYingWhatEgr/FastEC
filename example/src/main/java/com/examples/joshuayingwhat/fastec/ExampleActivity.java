package com.examples.joshuayingwhat.fastec;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.examples.joshuayingwhat.latte.activities.ProxyActivity;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.launcher.LauncherDelegate;
import com.examples.joshuayingwhat.latte.ec.launcher.LauncherScrollDelegate;

/**
 * @author joshuayingwhat
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
