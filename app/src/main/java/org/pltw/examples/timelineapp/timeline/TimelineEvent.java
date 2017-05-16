package org.pltw.examples.timelineapp.timeline;

import java.util.GregorianCalendar;

/**
 * Created by pieperm on 3/14/17.
 */

public class TimelineEvent {

    private String title, description;
    private GregorianCalendar date;

    public TimelineEvent(String title, String description, GregorianCalendar date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public TimelineEvent() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    // TODO: add a toString method using StringBuilder

}
