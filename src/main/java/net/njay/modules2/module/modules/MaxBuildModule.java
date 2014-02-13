package net.njay.modules2.module.modules;

import net.njay.modules2.module.Module;
import net.njay.modules2.module.ModuleInfo;

import org.jdom2.Document;

@ModuleInfo(name = "MaxBuildModule", module = MaxBuildModule.class)
public class MaxBuildModule implements Module {

    private int maxBuildHeight;

    public MaxBuildModule() {/* This must be present in all modules */}

    public MaxBuildModule(int maxBuildHeight) {
        this.maxBuildHeight = maxBuildHeight;
    }
    
    public void onEnable() {
        System.out.println("Enabling MaxBuildModule");
    }

    public void onDisable() {
        System.out.println("Disabling MaxBuildModule");
    }

    public int getMaxBuildHeight() {
        return maxBuildHeight;
    }

    public Module parse(Document doc) {
        return new MaxBuildModule(Integer.valueOf(doc.getRootElement().getChild("maxbuildheight").getTextNormalize()));
    }
}
