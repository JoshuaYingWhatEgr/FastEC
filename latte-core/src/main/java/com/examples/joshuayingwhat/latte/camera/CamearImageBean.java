package com.examples.joshuayingwhat.latte.camera;

import android.net.Uri;

/**
 * 存储一些中心值
 * @author joshuayingwhat
 */
public class CamearImageBean  {

    private Uri mPath = null;

    private static final class Holder {
        private static final CamearImageBean INSTANCE = new CamearImageBean();
    }

    public static CamearImageBean getInstance() {
        return Holder.INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    void setPath(Uri path) {
        mPath = path;
    }
}
