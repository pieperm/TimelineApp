package org.pltw.examples.timelineapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.timeline.Timeline;
import org.pltw.examples.timelineapp.timeline.TimelineEvent;
import org.pltw.examples.timelineapp.timeline.TimelinesSingleton;

import java.util.ArrayList;

/**
 * Created by pieperm on 3/15/17.
 */

public class CreateTimelineActivity extends AppCompatActivity {

    private Button saveTimeline, exitTimeline;
    private EditText timelineNameEdit, timelineDescriptionEdit;
    private static final String TAG = "CreateTimelineActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_timeline);

        saveTimeline = (Button)findViewById(R.id.save_edit_timeline_button);
        exitTimeline = (Button)findViewById(R.id.exit_edit_timeline_button);
        timelineNameEdit = (EditText)findViewById(R.id.timeline_name_edit);
        timelineDescriptionEdit = (EditText)findViewById(R.id.timeline_description_edit);

        exitTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateTimelineActivity.super.onBackPressed();
            }
        });

        saveTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = timelineNameEdit.getText().toString();
                String description = timelineDescriptionEdit.getText().toString();

                if(inputIsValid()) {

                    Timeline timeline = new Timeline(name, description, new ArrayList<TimelineEvent>());
                    TimelinesSingleton.getInstance().addTimeline(timeline);

                    Backendless.Persistence.save(timeline, new AsyncCallback<Timeline>() {
                        @Override
                        public void handleResponse(Timeline response) {
                            Log.i(TAG, "Created new trip: " + timelineNameEdit.getText().toString());
                            Log.i(TAG, "Timelines: " + TimelinesSingleton.getInstance().getTimelines());
                            CreateTimelineActivity.super.onBackPressed();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            MainActivity.createAlertDialog(CreateTimelineActivity.this, "Create Timeline", fault.getMessage());
                        }
                    });

                }
                else {
                    Log.i(TAG, "Invalid input");
                    MainActivity.createAlertDialog(CreateTimelineActivity.this, "Create Timeline", "You must fill out all required fields.");
                }

            }
        });

    }

    private boolean inputIsValid() {

        boolean nameIsValid = !timelineNameEdit.getText().toString().equals("");

        return nameIsValid;

    }



}
