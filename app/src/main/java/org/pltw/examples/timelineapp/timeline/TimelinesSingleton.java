package org.pltw.examples.timelineapp.timeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pieperm on 3/28/17.
 */
public class TimelinesSingleton {

    private static TimelinesSingleton instance = new TimelinesSingleton();
    private List<Timeline> timelines;

    private TimelinesSingleton() {
        timelines = new ArrayList<>();
    }

    public static TimelinesSingleton getInstance() {
        return instance;
    }

    public void addTimeline(Timeline timeline) {
        timelines.add(timeline);
    }

    public void removeTimeline(Timeline timeline) {
        timelines.add(timeline);
    }

    public Timeline getTimeline(int position) {
        return timelines.get(position);
    }

    public List<Timeline> getTimelines() {
        return timelines;
    }

}
