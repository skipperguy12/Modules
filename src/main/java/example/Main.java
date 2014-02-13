package example;

import event.Event;
import example.event.PlayerBuildEvent;
import example.listeners.PlayerBuildListener;
import modules.exceptions.InvalidModuleException;
import example.modules.MaxBuildHeightModule;
import modules.ModuleContainer;
import modules.ModuleRegistry;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by neiljohari on 2/13/14.
 */
public class Main {
    public static ModuleContainer container;

    public static void main(String... args) throws JDOMException, IOException {

        // Registers all the modules
        try {
            ModuleRegistry.register(MaxBuildHeightModule.class);
        } catch (InvalidModuleException e) {
            e.printStackTrace();
        }

        // This event is always registered, no matter what's happening with modules
        Event.addListener(new PlayerBuildListener());

        // Create the XML File from the String
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><maxbuildheight>38</maxbuildheight></root>";
        SAXBuilder sb = new SAXBuilder();

        Document doc = sb.build(new StringReader(xml));

        // Creating the container from the doc iterates through everything we registered, and calls onEnable on it.
        // In MaxBuildHeightModule we register the event, and when it gets disabled we unregister it
        container = new ModuleContainer(doc);

        // Enabling the modules calls onEnable on each module, we use this because we load all our maps when the Bukkit plugin enables
        // Then when the match is created we register all modules for the map playing in the match
        container.enableModules();


        // Because the module is registered, when we call the event at a height > what's defined in the XML, the module listener will see it.
        PlayerBuildEvent.call(5);
        PlayerBuildEvent.call(39);

        System.out.println("The max build height defined in the module is " + ((MaxBuildHeightModule)container.getModule(MaxBuildHeightModule.class)).getMaxBuildHeight());

        // Destroys the container, in a real game system we would most likely create a new instance whenever we create a map
        // and disableModules it when the match ends
        container.disableModules();

        // However, when we disableModules the container, as you can see it doesn't matter anymore as we unregistered all of the listeners!
        PlayerBuildEvent.call(40);
    }
}
