package com.db.never_use_switch.mail_handlers;

import com.db.never_use_switch.MailInfo;

@MailCode(1)
@MailCode(3)
public class FirstMailHandler implements MailHandler{

    @Override
    public void accept(MailInfo mailInfo) {
        System.out.println("Welcome " + mailInfo.getClientName() + "{ mailCode: " + mailInfo.getMailCode() +" }");
    }
}
