package org.pltw.examples.timelineapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.timeline.Timeline;
import org.pltw.examples.timelineapp.timeline.TimelineEvent;
import org.pltw.examples.timelineapp.timeline.TimelinesSingleton;

/**
 * Created by pieperm on 3/31/17.
 */

public class ViewTimelineActivity extends AppCompatActivity {

    private static Timeline timeline;
    private TextView testView;
    private FloatingActionButton createEventFab;
    private ListView viewTimelineListview;
    private EventsArrayAdapter adapter;
    public static final String EXTRA_POSITION = "org.pltw.examples.timelineapp.activities.viewtimelineactivity.position";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timeline);

        testView = (TextView)findViewById(R.id.test);
        createEventFab = (FloatingActionButton)findViewById(R.id.create_event_fab);
        viewTimelineListview = (ListView)findViewById(R.id.view_timeline_listview);

        int position = getIntent().getIntExtra(EXTRA_POSITION, -1);

        if(position != -1) {

            timeline = TimelinesSingleton.getInstance().getTimeline(position);
            testView.setText(timeline.getName());

            adapter = new EventsArrayAdapter();
            viewTimelineListview.setAdapter(adapter);

            adapter.clear();
            adapter.addAll(timeline.getEvents());
            adapter.notifyDataSetChanged();
        }

        createEventFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createEventIntent = new Intent(ViewTimelineActivity.this, CreateEventActivity.class);
                startActivity(createEventIntent);
            }
        });


    }

    public static Timeline getCurrentTimeline() {
        return timeline;
    }

    private class EventsArrayAdapter extends ArrayAdapter {

        public EventsArrayAdapter() {
            super(ViewTimelineActivity.this, 0, timeline.getEvents());
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null) {
                convertView = ViewTimelineActivity.this.getLayoutInflater().inflate(R.layout.timeline_event_list_item, parent, false);
            }

            TimelineEvent event = getItem(position);

            TextView title = (TextView)findViewById(R.id.timeline_event_title);
            TextView description = (TextView)findViewById(R.id.timeline_event_description);

            title.setText(event.getTitle());
            description.setText(event.getDescription());

            return convertView;

        }

        @Nullable
        @Override
        public TimelineEvent getItem(int position) {
            return timeline.getEvent(position);
        }
    }

}
