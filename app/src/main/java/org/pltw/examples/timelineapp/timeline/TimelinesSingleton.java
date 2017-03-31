package org.pltw.examples.timelineapp.timeline;

import java.util.List;

/**
 * Created by pieperm on 3/28/17.
 */
public class TimelinesSingleton {

    private static TimelinesSingleton instance = new TimelinesSingleton();
    private List<Timeline> timelines;

    private TimelinesSingleton() {
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

    public void getTimeline(int position) {
        timelines.get(position);
    }

}
