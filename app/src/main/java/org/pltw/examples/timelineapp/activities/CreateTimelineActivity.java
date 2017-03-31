package org.pltw.examples.timelineapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.timeline.Timeline;
import org.pltw.examples.timelineapp.timeline.TimelinesSingleton;

/**
 * Created by pieperm on 3/15/17.
 */

public class CreateTimelineActivity extends AppCompatActivity {

    private Button saveTimeline, exitTimeline;
    private EditText timelineNameEdit, timelineDescriptionEdit;

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

                Timeline timeline = new Timeline(name, description, null, null, null);
                TimelinesSingleton.getInstance().addTimeline(timeline);

            }
        });

    }
}
