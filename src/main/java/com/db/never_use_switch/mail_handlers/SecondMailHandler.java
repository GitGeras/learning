package com.db.never_use_switch.mail_handlers;

import com.db.never_use_switch.MailInfo;
import org.springframework.stereotype.Component;

@Component
public class SecondMailHandler implements MailHandler {
    @Override
    public void accept(MailInfo mailInfo) {
        System.out.println("don't call us we call you");
    }

    @Override
    public int[] myCodes() {
        return new int[] {2};
    }
}
