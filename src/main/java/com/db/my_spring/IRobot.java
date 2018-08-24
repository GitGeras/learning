package com.db.my_spring;


import com.db.my_spring.annotation.InjectByType;

public class IRobot {
    @InjectByType
    private Speaker speaker;
    @InjectByType
    private Cleaner cleaner;

    public void cleanRoom() {
        speaker.speak("Я начал работать!!!");
        cleaner.clean();
        speaker.speak("Я закончил работать!!!");
    }
}
