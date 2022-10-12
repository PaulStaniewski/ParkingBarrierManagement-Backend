package gee.solution;

public interface SmsSender {

    Object sendSms(SmsRequest smsRequest);

    // or maybe void sendSms(String phoneNumber, String message);
}