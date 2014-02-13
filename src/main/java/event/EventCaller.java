package event;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 */
public class EventCaller<Type extends Event> {

    boolean started = false;

    /**
     * Method call.
     *
     * @param listeners ArrayList<Listener>
     * @param e         Event
     */
    @SuppressWarnings("unchecked")
    public void call(ArrayList<Listener> listeners, Event e) {
//Iterate through all of the listeners
        for (Object obj : listeners.toArray()) {
            Listener h = (Listener) obj;
//Iterate through each method
            for (Method m : h.getClass().getMethods()) {
//check if @EventHandler is present
                if (m.isAnnotationPresent(EventHandler.class)) {
//get params
                    for (Class<?> param : m.getParameterTypes()) {
//check if parameter is the same type as the passed event
                        if (param.equals(e.getClass())) {
                            try {
                                m.invoke(h, (Type) e);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }

                    }
                }
            }
        }
    }
}