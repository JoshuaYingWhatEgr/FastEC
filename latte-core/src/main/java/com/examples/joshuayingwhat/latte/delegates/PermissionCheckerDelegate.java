package com.examples.joshuayingwhat.latte.delegates;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.examples.joshuayingwhat.latte.camera.LatteCamer;

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
    @NeedsPermission(Manifest.permission.CAMERA)
    void startCamear() {
        LatteCamer.start(this);
    }

    /**
     * 真正调用的方法
     */
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCamearWithPermissionCheck(this);
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


}


