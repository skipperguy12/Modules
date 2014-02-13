package net.njay.modules2.module;

import net.njay.modules2.exception.InvalidModuleException;

import org.jdom2.Document;

import java.util.HashMap;
import java.util.Map;

public class ModuleContainer {
    private HashMap<Module, ModuleInfo> modules = new HashMap<Module, ModuleInfo>();


    public ModuleContainer(Document doc) {
        for(ModuleInfo info : ModuleRegistry.getPossibleModules()) {
            try {
                addModule(info,doc);
            } catch (InvalidModuleException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void addModule(ModuleInfo info, Document doc) throws InstantiationException, IllegalAccessException, InvalidModuleException{
        for (Class<? extends Module> dependency : info.requires()){
            if (!dependency.isAnnotationPresent(ModuleInfo.class))
                throw new InvalidModuleException("Module: " + dependency.getClass().getName() + " does not have @ModuleInfo tag!");
            addModule(dependency.getAnnotation(ModuleInfo.class), doc);
        }
        if (registered(info)) return;
        Module module = ((Module) info.module().newInstance()).parse(doc);
        if (module == null) return;
        module.onEnable();
        modules.put(module, info);
    }
    
    public Module getModule(Class<? extends Module> clazz) {
        for(Module module : modules.keySet())
            if(module.getClass().equals(clazz))
                return module;
        return null;
    }
    
    public void destroy(){
    	for (Module module : modules.keySet())
    		module.onDisable();
    }

    private boolean registered(ModuleInfo info){
        for (Map.Entry<Module, ModuleInfo> entry : modules.entrySet()){
            if (entry.getValue().name().equals(info.name()))
                return true;
        }
        return false;
    }
}
