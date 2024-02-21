public class SMSNotification extends Notification{

    public SMSNotification(String Message) {
        super(Message);
    }

    @Override
    void send() {
        System.out.println("sent by sms");
    }
    
}
