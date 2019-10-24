package com.examples.joshuayingwhat.latte.utils.callback;

import androidx.annotation.Nullable;

public interface IGlobalCallBack<T> {
    void executeCallBack(@Nullable T args);
}
