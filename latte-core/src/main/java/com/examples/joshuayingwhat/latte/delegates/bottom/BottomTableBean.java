package com.examples.joshuayingwhat.latte.delegates.bottom;

/**
 * 存储底部图标和模块名称
 *
 * @author joshuayingwhat
 */
public final class BottomTableBean {

    private final CharSequence ICON;

    private final CharSequence TITLE;

    public BottomTableBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
