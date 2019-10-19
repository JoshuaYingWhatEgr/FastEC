package com.examples.joshuayingwhat.latte.ec.pay;

public interface IAlPayResutlListener {

    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancle();

    void onPayConnectError();
}
