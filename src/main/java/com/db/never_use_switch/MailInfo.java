package com.db.never_use_switch;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MailInfo {
    private String  clientName;
    private String  clientMail;
    private int mailCode;
}
