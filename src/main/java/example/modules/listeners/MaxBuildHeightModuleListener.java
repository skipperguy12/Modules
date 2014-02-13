package example.modules.listeners;

import event.EventHandler;
import event.Listener;
import example.Main;
import example.event.PlayerBuildEvent;
import example.modules.MaxBuildHeightModule;

/**
 * Created by neiljohari on 2/13/14.
 */
public class MaxBuildHeightModuleListener implements Listener {
    @EventHandler
    public void onPlayerBuildEvent(PlayerBuildEvent event) {
        if(event.getHeight() > ((MaxBuildHeightModule) Main.container.getModule(MaxBuildHeightModule.class)).getMaxBuildHeight()) {
            System.out.println("Player built at height " + event.getHeight() + ", but the max height is " + ((MaxBuildHeightModule) Main.container.getModule(MaxBuildHeightModule.class)).getMaxBuildHeight() + ". If this was an actual Minecraft event we could cancel it!");
        }
    }
}
