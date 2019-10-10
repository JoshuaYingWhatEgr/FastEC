package com.examples.joshuayingwhat.fastec.generator;

import com.examples.joshuayingwhat.latte.wechat.templte.AppRegisterTemplate;
import com.examples.joshuayingwhat.latte_annotations.AppRegisterGenerator;

@AppRegisterGenerator(
        packageName = "com.examples.joshuayingwhat.fastec",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
