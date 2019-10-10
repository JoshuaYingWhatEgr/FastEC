package com.examples.joshuayingwhat.fastec.generator;

import com.examples.joshuayingwhat.latte.wechat.templte.WPayXEntryTemplate;
import com.examples.joshuayingwhat.latte.wechat.templte.WXEntryTemplate;
import com.examples.joshuayingwhat.latte_annotations.EntryGenerator;
import com.examples.joshuayingwhat.latte_annotations.PayEntryGenerator;

@PayEntryGenerator(
        packageName = "com.examples.joshuayingwhat.fastec",
        payEntryTemplete = WPayXEntryTemplate.class
)
public interface WeChatPayEntry {
}
