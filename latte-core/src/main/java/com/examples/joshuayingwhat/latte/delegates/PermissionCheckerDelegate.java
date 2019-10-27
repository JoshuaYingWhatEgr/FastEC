package com.examples.joshuayingwhat.latte.delegates;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.examples.joshuayingwhat.latte.camera.CamearImageBean;
import com.examples.joshuayingwhat.latte.camera.LatteCamer;
import com.examples.joshuayingwhat.latte.camera.RequestCode;
import com.examples.joshuayingwhat.latte.ui.scanner.ScannerDelegate;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackManager;
import com.examples.joshuayingwhat.latte.utils.callback.CallBackType;
import com.examples.joshuayingwhat.latte.utils.callback.IGlobalCallBack;
import com.yalantis.ucrop.UCrop;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 用于处理权限的delegate
 *
 * @author joshuayingwhat
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {

    /**
     * 仅仅只是为了生成代码所有
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE})
    void startCamear() {
        LatteCamer.start(this);
    }

    /**
     * 真正调用的方法
     */
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCamearWithPermissionCheck(this);
    }

    public void startScanWithCheck(BaseDelegate delegate) {
        PermissionCheckerDelegatePermissionsDispatcher.startScanWithPermissionCheck(this, delegate);
    }

    //扫描二维码
    @NeedsPermission(Manifest.permission.CAMERA)
    public void startScan(BaseDelegate delegate) {
        delegate.getSupportDelegate().startForResult(new ScannerDelegate(), RequestCode.SCAN);
    }

    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void camearOnDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRational(PermissionRequest request) {
        showRationaleDialog(request);
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCamearNever() {
        Toast.makeText(getContext(), "永远拒绝了拍照权限", Toast.LENGTH_SHORT).show();
    }

    private void showRationaleDialog(PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .setMessage("权限管理")
                .show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RequestCode.TAKE_PHOTO:
                    final Uri resultUri = CamearImageBean.getInstance().getPath();
                    UCrop.of(resultUri, resultUri)
                            .withMaxResultSize(400, 400).start(getContext(), this);
                    break;
                case RequestCode.PICK_PHOTO:
                    if (data != null) {
                        final Uri pickPath = data.getData();
                        final String pickCropPath = LatteCamer.createCropFile().getPath();
                        UCrop.of(pickPath, Uri.parse(pickCropPath))
                                .withMaxResultSize(400, 400).start(getContext(), this);
                    }
                    break;
                case RequestCode.CROP_PHOTO:
                    final Uri cropUri = UCrop.getOutput(data);
                    //拿到剪裁后的数据进行处理
                    final IGlobalCallBack<Uri> callBack = CallBackManager.getInstance()
                            .getCallBack(CallBackType.ON_CROP);
                    if (callBack != null) {
                        callBack.executeCallBack(cropUri);
                    }
                    break;
                case RequestCode.CROP_ERROR:
                    Toast.makeText(getContext(), "剪裁出错", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.SCAN) {
            if (data != null) {
                final String qrCode = data.getString("SCAN_RESULT");

            }
        }
    }
}


