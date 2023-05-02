import java.util.EventObject;

public class OnLineBavardEvent extends EventObject {
    private Bavard bavardConnected;

    public OnLineBavardEvent(Object source, Bavard bavardConnected) {
        super(source);
        this.bavardConnected = bavardConnected;
    }
}
