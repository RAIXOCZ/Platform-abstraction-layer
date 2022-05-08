package net.playeranalytics.plugin.server;

import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;

import java.util.Set;

public class MinestomListeners implements Listeners {

    private final EventNode<Event> eventHandler;

    public MinestomListeners(EventNode<Event> eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void registerListener(Object listener) {
        if (listener == null) {
            throw new IllegalArgumentException("'listener' can not be null!");
        }
        if (!(listener instanceof EventNode)) {
            throw new IllegalArgumentException("'listener' needs to be of type " + listener.getClass().getName() +
                    ", but was " + listener.getClass());
        }
        eventHandler.addChild((EventNode<? extends Event>) listener);
    }

    @Override
    public void unregisterListener(Object listener) {
        if (listener == null) {
            throw new IllegalArgumentException("'listener' can not be null!");
        }
        if (!(listener instanceof EventNode)) {
            throw new IllegalArgumentException("'listener' needs to be of type " + listener.getClass().getName() +
                    ", but was " + listener.getClass());
        }
        eventHandler.removeChild((EventNode<? extends Event>) listener);
    }

    @Override
    public void unregisterListeners() {
        for (Set<EventNode<Event>> child : Set.of(eventHandler.getChildren())) {
            eventHandler.removeChild((EventNode<? extends Event>) child);
        }
    }

}
