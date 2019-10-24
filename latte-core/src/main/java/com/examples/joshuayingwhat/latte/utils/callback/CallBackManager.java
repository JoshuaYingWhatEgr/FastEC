package com.examples.joshuayingwhat.latte.utils.callback;

import java.util.WeakHashMap;

public class CallBackManager {

    private WeakHashMap<Object, IGlobalCallBack> CALLBACKS = new WeakHashMap<>();

    public static CallBackManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CallBackManager INSTANCE = new CallBackManager();
    }

    public CallBackManager addCallBack(Object tag, IGlobalCallBack callBack) {
        CALLBACKS.put(tag, callBack);
        return this;
    }

    public IGlobalCallBack getCallBack(Object tag) {
        return CALLBACKS.get(tag);
    }
}
