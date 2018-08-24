package com.db.never_use_switch.mail_handlers;

import com.db.never_use_switch.MailInfo;

public interface MailHandler {
    void accept(MailInfo mailInfo);
}
