package com.examples.joshuayingwhat.fastec;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.examples.joshuayingwhat.latte.delegates.LatteDelegate;
import com.examples.joshuayingwhat.latte.net.RestClient;
import com.examples.joshuayingwhat.latte.net.callback.IError;
import com.examples.joshuayingwhat.latte.net.callback.IFailure;
import com.examples.joshuayingwhat.latte.net.callback.ISuccess;

/**
 * @author joshuayingwhat
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        test();
    }

    private void test() {

        RestClient.builder().url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build().get();
    }
}
