package org.pltw.examples.timelineapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.activities.CreateTimelineActivity;

/**
 * Created by pieperm on 3/8/17.
 */

public class TimelineListFragment extends Fragment {

    private FloatingActionButton createTimelineFab;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_timeline_list, container, false);

        createTimelineFab = (FloatingActionButton)rootView.findViewById(R.id.create_timeline_fab);

        createTimelineFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTimelineIntent = new Intent(getActivity(), CreateTimelineActivity.class);
                startActivity(createTimelineIntent);
            }
        });

        return rootView;

    }
}
