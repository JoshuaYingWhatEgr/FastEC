package com.examples.joshuayingwhat.latte.global;

import com.examples.joshuayingwhat.latte.utils.storage.LattePreference;

public class AccountManager {

    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 用户第一个登录app的状态值
     *
     * @param state
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    /**
     * 是否已经登录
     */
    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserCheck iUserCheck) {
        //如果用户已经登录
        if (isSignIn()) {
            iUserCheck.onSignIn();
        } else {
            iUserCheck.onSignNotIn();
        }
    }
}
