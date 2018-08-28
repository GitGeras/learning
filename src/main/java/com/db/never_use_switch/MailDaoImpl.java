package com.db.never_use_switch;

import org.fluttercode.datafactory.impl.DataFactory;

public class MailDaoImpl implements MailDao {

    private DataFactory dataFactory = new DataFactory();

    @Override
    public MailInfo getMailInfo() {
        return MailInfo.builder()
                .clientName(dataFactory.getName())
                .clientMail(dataFactory.getEmailAddress())
                .mailCode(dataFactory.getNumberBetween(1,4))
                .build();
    }
}
