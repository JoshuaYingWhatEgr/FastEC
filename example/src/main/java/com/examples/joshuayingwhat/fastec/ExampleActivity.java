package com.examples.joshuayingwhat.fastec;

import com.examples.joshuayingwhat.latte.activities.ProxyActivity;
import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.ec.launcher.LauncherDelegate;
import com.examples.joshuayingwhat.latte.ec.launcher.LauncherScrollDelegate;

/**
 * @author joshuayingwhat
 */
public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }
}
