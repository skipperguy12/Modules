package modules;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
    String name();
    Class<? extends Module> module();
    String desc() default "";
    Class<? extends Module>[] requires() default {};

}
