package com.examples.joshuayingwhat.fastec.generator;

import com.examples.joshuayingwhat.latte.wechat.templte.WXPayEntryTemplate;
import com.examples.joshuayingwhat.latte_annotations.PayEntryGenerator;

@PayEntryGenerator(
        packageName = "com.examples.joshuayingwhat.fastec",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
