package org.pltw.examples.timelineapp.timeline;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by pieperm on 3/14/17.
 */

public class Timeline {

    private String name, description;
    private ArrayList<TimelineEvent> events;
    private Date startDate, endDate;

    public Timeline(String name, String description, ArrayList<TimelineEvent> events, Date startDate, Date endDate) {
        this.name = name;
        this.description = description;
        this.events = events;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public ArrayList<TimelineEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<TimelineEvent> events) {
        this.events = events;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // TODO: Add a toString method using StringBuilder

}
