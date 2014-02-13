package modules;

import com.google.common.collect.Maps;
import modules.exceptions.InvalidModuleException;
import org.jdom2.Document;

import java.util.HashMap;
import java.util.Map;

public class ModuleContainer {
    private HashMap<Module, ModuleInfo> modules = Maps.newHashMap();


    public ModuleContainer(Document doc) {
        for(ModuleInfo info : ModuleRegistry.getModules())
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



    public Module getModule(Class<? extends Module> clazz) {
        for(Module module : modules.keySet())
            if(module.getClass().equals(clazz))
                return module;
        return null;
    }

    public void enableModules() {
        for(Module module : modules.keySet())
            module.onEnable();
    }
    public void disableModules() {
        for(Module module : modules.keySet())
            module.onDisable();
    }


    public void addModule(ModuleInfo info, Document doc) throws InstantiationException, IllegalAccessException, InvalidModuleException{
        for (Class<? extends Module> dependency : info.requires()){
            if (!dependency.isAnnotationPresent(ModuleInfo.class))
                throw new InvalidModuleException("modules.Module: " + dependency.getClass().getName() + " does not have @modules.ModuleInfo tag!");
            addModule(dependency.getAnnotation(ModuleInfo.class), doc);
        }
        if (registered(info)) return;
        Module module = ((Module) info.module().newInstance()).parse(doc);
        if (module == null) return;
        modules.put(module, info);
    }

    private boolean registered(ModuleInfo info){
        for (Map.Entry<Module, ModuleInfo> entry : modules.entrySet())
            if (entry.getValue().name().equals(info.name()))
                return true;
        return false;
    }

}
