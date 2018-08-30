package com.db.never_use_switch.mail_handlers;

import com.db.never_use_switch.MailInfo;
import org.springframework.stereotype.Component;

@Component
public class FirstMailHandler implements MailHandler{

    @Override
    public void accept(MailInfo mailInfo) {
        System.out.println("Welcome " + mailInfo.getClientName() + "{ mailCode: " + mailInfo.getMailCode() +" }");
    }

    @Override
    public int[] myCodes() {
        return new int[] {1, 3};
    }
}
