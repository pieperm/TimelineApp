package org.pltw.examples.timelineapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.pltw.examples.timelineapp.R;

/**
 * Created by pieperm on 3/31/17.
 */

public class ViewTimelineActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "org.pltw.examples.timelineapp.activities.viewtimelineactivity.position";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timeline);

        int position = getIntent().getIntExtra(EXTRA_POSITION, -1);



    }
}
