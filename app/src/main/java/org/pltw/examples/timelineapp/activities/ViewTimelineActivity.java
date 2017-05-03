package org.pltw.examples.timelineapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.timeline.Timeline;
import org.pltw.examples.timelineapp.timeline.TimelinesSingleton;

/**
 * Created by pieperm on 3/31/17.
 */

public class ViewTimelineActivity extends AppCompatActivity {

    private Timeline timeline;
    private TextView testView;
    public static final String EXTRA_POSITION = "org.pltw.examples.timelineapp.activities.viewtimelineactivity.position";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timeline);

        testView = (TextView)findViewById(R.id.test);

        int position = getIntent().getIntExtra(EXTRA_POSITION, -1);

        if(position != -1) {
            timeline = TimelinesSingleton.getInstance().getTimeline(position);
            testView.setText(timeline.getName());
        }


    }
}
