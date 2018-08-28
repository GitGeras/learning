package com.db.never_use_switch.mail_handlers;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MailCodes {
    MailCode[] value();
}
