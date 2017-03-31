package org.pltw.examples.timelineapp.timeline;

import java.util.ArrayList;

/**
 * Created by pieperm on 3/28/17.
 */
public class EventsSingleton {

    private static EventsSingleton instance = new EventsSingleton();
    private ArrayList<TimelineEvent> events;

    private EventsSingleton() {
        events = new ArrayList<>();
    }

    public static EventsSingleton getInstance() {
        return instance;
    }

    public void addEvent(TimelineEvent event) {
        events.add(event);
    }

    public void removeEvent(TimelineEvent event) {
        events.remove(event);
    }

    public TimelineEvent getEvent(int position) {
        return events.get(position);
    }

}
