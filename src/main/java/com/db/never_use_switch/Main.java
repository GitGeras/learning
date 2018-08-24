package com.db.never_use_switch;

import lombok.SneakyThrows;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        MailSender mailSender = new MailSender();
        while (true) {
            try {
                mailSender.sendMail();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            Thread.sleep(1000);
        }
    }
}
