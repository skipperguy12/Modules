package net.njay.modules2.module;

import java.util.ArrayList;
import java.util.List;

import net.njay.modules2.exception.InvalidModuleException;

public class ModuleRegistry {

	private static List<ModuleInfo> modules = new ArrayList<ModuleInfo>();
	
	public static void register(Class<? extends Module> clazz) throws InvalidModuleException{
		if (!clazz.isAnnotationPresent(ModuleInfo.class))
			throw new InvalidModuleException("Module: " + clazz.getName() + " is missing @ModuleInfo annotation!");
		modules.add(clazz.getAnnotation(ModuleInfo.class));
	}
	
	public static List<ModuleInfo> getPossibleModules(){
		return modules;
	}
}
