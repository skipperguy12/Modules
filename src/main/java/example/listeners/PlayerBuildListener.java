package example.listeners;

import event.EventHandler;
import event.Listener;
import example.event.PlayerBuildEvent;

/**
 * Created by neiljohari on 2/13/14.
 */
public class PlayerBuildListener implements Listener {
    @EventHandler
    public void onPlayerBuild(PlayerBuildEvent event) {
        System.out.println("PlayerBuildEvent fired! Player built at " + event.getHeight());
    }
}
