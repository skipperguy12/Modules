package example.modules;

import event.Event;
import event.Listener;
import example.modules.listeners.MaxBuildHeightModuleListener;
import modules.Module;
import modules.ModuleInfo;
import org.jdom2.Document;

/**
 * Created by neiljohari on 2/13/14.
 */
@ModuleInfo(name = "MaxBuildHeightModule", module = MaxBuildHeightModule.class)
public class MaxBuildHeightModule implements Module {
    private Listener maxBuildheightListener;
    private int maxBuildHeight;

    public MaxBuildHeightModule() {/* This must be present in all modules */}

    public MaxBuildHeightModule(int maxBuildHeight) {
        this.maxBuildHeight = maxBuildHeight;
    }
    @Override
    public void onEnable() {
        System.out.println("Enabling MaxBuildHeightModule");
        maxBuildheightListener = new MaxBuildHeightModuleListener();
        Event.addListener(maxBuildheightListener);
    }

    @Override
    public void onDisable() {
        System.out.println("Disabling MaxBuildHeightModule");
        Event.removeListener(maxBuildheightListener);
    }

    public int getMaxBuildHeight() {
        return maxBuildHeight;
    }

    @Override
    public Module parse(Document doc) {
        System.out.println("Parsing MaxBuildHeightModule");
        return new MaxBuildHeightModule(Integer.valueOf(doc.getRootElement().getChild("maxbuildheight").getTextNormalize()));
    }
}
