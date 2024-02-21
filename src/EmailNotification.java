public class EmailNotification extends Notification{

    public EmailNotification(String Message) {
        super(Message);
    }

    @Override
    void send() {
        System.out.println("send by email");
    }
    
    
}
