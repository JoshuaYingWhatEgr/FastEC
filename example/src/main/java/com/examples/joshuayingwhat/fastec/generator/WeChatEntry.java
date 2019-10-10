package com.examples.joshuayingwhat.fastec.generator;

import com.examples.joshuayingwhat.latte.wechat.templte.WXEntryTemplate;
import com.examples.joshuayingwhat.latte_annotations.EntryGenerator;

@EntryGenerator(
        packageName = "com.examples.joshuayingwhat.fastec",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {

}
