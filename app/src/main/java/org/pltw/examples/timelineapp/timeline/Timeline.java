package org.pltw.examples.timelineapp.timeline;

import java.util.List;

/**
 * Created by pieperm on 3/14/17.
 */

public class Timeline {

    private String name, description;
    private List<TimelineEvent> events;

    public Timeline(String name, String description, List<TimelineEvent> events) {
        this.name = name;
        this.description = description;
        this.events = events;
    }

    public Timeline() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TimelineEvent> getEvents() {
        return events;
    }

    public void setEvents(List<TimelineEvent> events) {
        this.events = events;
    }

    public TimelineEvent getEvent(int position) {
        return events.get(position);
    }

    public void addEvent(TimelineEvent event) {
        events.add(event);
    }

    // TODO: Add a toString method using StringBuilder

}
