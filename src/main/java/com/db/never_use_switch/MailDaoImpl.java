package com.db.never_use_switch;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MailDaoImpl implements MailDao {

    @Autowired
    private DataFactory dataFactory;

    @Override
    public MailInfo getMailInfo() {
        return MailInfo.builder()
                .clientName(dataFactory.getName())
                .clientMail(dataFactory.getEmailAddress())
                .mailCode(dataFactory.getNumberBetween(1, 4))
                .build();
    }
}
