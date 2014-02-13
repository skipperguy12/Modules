package event;

import java.util.ArrayList;

/**
 */
public class Event {

    private static ArrayList<Listener> listeners = new ArrayList<Listener>();

    /**
     * Method addListener.
     *
     * @param listener Listener
     */
    public static void addListener(Listener listener) {
        listeners.add(listener);
    }

    public static void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    /**
     * Method getListeners.
     *
     * @return ArrayList<Listener>
     */
    public static ArrayList<Listener> getListeners() {
        return Event.listeners;
    }

}

