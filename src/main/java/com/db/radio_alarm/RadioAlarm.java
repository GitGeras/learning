package com.db.radio_alarm;

public class RadioAlarm implements Alarm, Radio{
    private Alarm alarm;
    private Radio radio;

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public void setRadio(Radio radio) {
        this.radio = radio;
    }

    @Override
    public void setAlarmTime() {
        alarm.setAlarmTime();
    }

    @Override
    public void stopAlarm() {
        alarm.stopAlarm();
    }

    @Override
    public void setChannel() {
        radio.setChannel();
    }

    @Override
    public void setVolume() {
        radio.setVolume();
    }
}
