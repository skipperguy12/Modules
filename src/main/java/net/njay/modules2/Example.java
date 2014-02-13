package net.njay.modules2;

import net.njay.modules2.exception.InvalidModuleException;
import net.njay.modules2.module.ModuleContainer;
import net.njay.modules2.module.ModuleRegistry;
import net.njay.modules2.module.modules.MaxBuildModule;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.io.IOException;
import java.io.StringReader;

public class Example {
    public static void main(String... args) throws JDOMException, IOException {
        try {
            ModuleRegistry.register(MaxBuildModule.class);
        } catch (InvalidModuleException e) {
            e.printStackTrace();
        }

        String exampleXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><maxbuildheight>38</maxbuildheight></root>";
        SAXBuilder sb=new SAXBuilder();
        Document doc=sb.build(new StringReader(exampleXML));
        ModuleContainer container = new ModuleContainer(doc);
        System.out.println(((MaxBuildModule)container.getModule(MaxBuildModule.class)).getMaxBuildHeight());
        container.destroy();
    }
}
