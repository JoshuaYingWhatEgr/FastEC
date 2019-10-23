package com.examples.joshuayingwhat.latte.camera;

import android.net.Uri;

import com.examples.joshuayingwhat.latte.delegates.PermissionCheckerDelegate;
import com.examples.joshuayingwhat.latte.utils.file.FileUtil;

/**
 * 具体调用相机
 */
public class LatteCamer {
    /**
     * 剪裁文件
     *
     * @return
     */
    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CamearHander(delegate).beginCameraDialog();
    }

}
