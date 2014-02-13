package example.event;

import event.Event;
import event.EventCaller;

/**
 * Created by neiljohari on 2/13/14.
 */
public class PlayerBuildEvent extends Event {
    private int height;

    public PlayerBuildEvent(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public static void call(int height){
        PlayerBuildEvent e = new PlayerBuildEvent(height);
        EventCaller<PlayerBuildEvent> caller = new EventCaller<PlayerBuildEvent>();
        caller.call(Event.getListeners(), e);
    }

}
