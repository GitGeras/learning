package com.db.never_use_switch;

import com.db.never_use_switch.mail_handlers.MailCode;
import com.db.never_use_switch.mail_handlers.MailHandler;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Set;

public class MailSender {
    private static final HashMap<Integer, MailHandler> handlers = new HashMap<>();
    public MailDao mailDao;

/*
    private static final HashMap<Integer, Class<? extends Consumer<MailInfo>>> instances = new HashMap<>();

    static {
        instances.put(1, FirstMailHandler.class);
        instances.put(2, SecondMailHandler.class);

    }*/

    public MailSender() {
        mailDao = new MailDaoImpl();
        init();
    }

    @SneakyThrows
     public void sendMail() {
        MailInfo mailInfo = mailDao.getMailInfo();
        int mailCode = mailInfo.getMailCode();
        MailHandler handler = handlers.getOrDefault(mailCode, mailInfo1 -> {
            throw new UnsupportedOperationException(mailCode + " is not supported yet");
        });

        /*MailHandler handler = handlers.get(mailCode);
        if (handler == null) {
            throw new UnsupportedOperationException(mailCode + " is not supported yet");
        }*/
        handler.accept(mailInfo);
        send(mailInfo);
    }

    @SneakyThrows
    private void init() {
        Reflections reflections = new Reflections("com.db.never_use_switch.mail_handlers");
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
    }
        /*handlers = reflections.getSubTypesOf(MailHandler.class).stream()
                .collect(Collectors.groupingBy(clazz -> clazz.getAnnotation(MailCode.class).value()));*/


    private void putToHandlers(MailHandler mailHandler, int mailCode) throws Exception {
        if (handlers.containsKey(mailCode)) {
            throw new Exception("Already in use");
        }
        handlers.put(mailCode, mailHandler);
    }

    private void send(MailInfo mailInfo) {
        System.out.println("sending to " + mailInfo.getClientMail());
    }
}
