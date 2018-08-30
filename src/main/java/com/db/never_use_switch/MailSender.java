package com.db.never_use_switch;

import com.db.never_use_switch.mail_handlers.MailHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MailSender {

    private Map<Integer, MailHandler> handlers = new HashMap<>();
    @Autowired
    public MailDao mailDao;


    @Autowired
    private void fillHandlers(List<MailHandler> handlerList){
        for (MailHandler mailHandler : handlerList) {
            for (int mailCode : mailHandler.myCodes()) {
                if (handlers.get(mailCode) != null) {
                    throw new UnsupportedOperationException("mailHandler for mailCode " + mailCode + " should be only one");
                }
                handlers.put(mailCode, mailHandler);
            }
        }
    };

    @SneakyThrows
    @Scheduled(fixedDelay = 1000)
     public void sendMail() {
        MailInfo mailInfo = mailDao.getMailInfo();
        int mailCode = mailInfo.getMailCode();
        MailHandler handler = handlers.getOrDefault(mailCode, new MailHandler() {
            @Override
            public void accept(MailInfo mailInfo) {
                throw new UnsupportedOperationException(mailCode + " is not supported yet");
            }

            @Override
            public int[] myCodes() {
                return new int[0];
            }
        });

        handler.accept(mailInfo);
        send(mailInfo);
    }

    /*@SneakyThrows
    @PostConstruct
    private void init() {
        handlerList.
        Set<Class<? extends MailHandler>> classes = reflections.getSubTypesOf(MailHandler.class);
        for (Class<? extends MailHandler> generatorClass : classes) {
            if (!Modifier.isAbstract(generatorClass.getModifiers())) {
                MailHandler mailHandler = generatorClass.newInstance();
                MailCode[] annotations = generatorClass.getAnnotationsByType(MailCode.class);
                if (annotations.length != 0)
                    for (MailCode mailCode : annotations) {
                        putToHandlers(mailHandler, mailCode.value());
                    }

            } else {
                throw new IllegalStateException("each class which impl "
                        + MailHandler.class.getSimpleName()
                        + " must be marked with annotation "
                        + MailCode.class);
            }
        }
    }*/

    /*private void putToHandlers(MailHandler mailHandler, int mailCode) throws Exception {
        if (handlers.containsKey(mailCode)) {
            throw new Exception("Already in use");
        }
        handlers.put(mailCode, mailHandler);
    }*/

    private void send(MailInfo mailInfo) {
        System.out.println("sending to " + mailInfo.getClientMail());
    }
}
