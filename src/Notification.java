
abstract class Notification {
private  String Message ;
    public Notification(String Msg) {
        this.Message= Msg;
    }
    abstract void send();
    
}
