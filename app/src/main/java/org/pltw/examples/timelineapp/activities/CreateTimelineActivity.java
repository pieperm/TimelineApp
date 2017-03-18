package org.pltw.examples.timelineapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.pltw.examples.timelineapp.R;

/**
 * Created by pieperm on 3/15/17.
 */

public class CreateTimelineActivity extends AppCompatActivity {

    private Button saveTimeline, exitTimeline;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_timeline);

        saveTimeline = (Button)findViewById(R.id.save_edit_timeline_button);
        exitTimeline = (Button)findViewById(R.id.exit_edit_timeline_button);

        exitTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMainActivity = new Intent(CreateTimelineActivity.this, MainActivity.class);
                startActivity(goToMainActivity);
            }
        });

    }
}
