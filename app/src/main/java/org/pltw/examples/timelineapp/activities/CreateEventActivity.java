package org.pltw.examples.timelineapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.timeline.TimelineEvent;

/**
 * Created by pieperm on 5/14/17.
 */

public class CreateEventActivity extends AppCompatActivity {

    private EditText eventTitleEdit, eventDescriptionEdit;
    private Button saveEvent, exitEvent;
    public static final String TAG = "CreateEventActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        eventTitleEdit = (EditText)findViewById(R.id.event_title_edit);
        eventDescriptionEdit = (EditText)findViewById(R.id.event_description_edit);
        saveEvent = (Button)findViewById(R.id.save_edit_event_button);
        exitEvent = (Button)findViewById(R.id.exit_edit_event_button);

        exitEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEventActivity.super.onBackPressed();
            }
        });

        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = eventTitleEdit.getText().toString();
                String description = eventDescriptionEdit.getText().toString();

                TimelineEvent timelineEvent = new TimelineEvent(title, description, null);

                ViewTimelineActivity.getCurrentTimeline().addEvent(timelineEvent);
                Log.i(TAG, "New event added to " + ViewTimelineActivity.getCurrentTimeline().getName() + ": " + timelineEvent.getTitle());

            }
        });

    }
}
