package event;

import java.lang.annotation.*;

/**
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
    /**
     * Method priority.
     *
     * @return String
     */
    String priority() default "LOW";
}