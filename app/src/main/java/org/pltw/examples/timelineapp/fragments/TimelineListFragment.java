package org.pltw.examples.timelineapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.activities.CreateTimelineActivity;
import org.pltw.examples.timelineapp.activities.ViewTimelineActivity;
import org.pltw.examples.timelineapp.timeline.Timeline;
import org.pltw.examples.timelineapp.timeline.TimelinesSingleton;

/**
 * Created by pieperm on 3/8/17.
 */

public class TimelineListFragment extends Fragment {

    private FloatingActionButton createTimelineFab;
    private ListView timelinesListView;
    private TimelinesArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_timeline_list, container, false);

        createTimelineFab = (FloatingActionButton)rootView.findViewById(R.id.create_timeline_fab);
        timelinesListView = (ListView)rootView.findViewById(R.id.timeline_list_listview);

        adapter = new TimelinesArrayAdapter();
        timelinesListView.setAdapter(adapter);
        registerForContextMenu(timelinesListView);

        Backendless.Persistence.of(Timeline.class).find(new AsyncCallback<BackendlessCollection<Timeline>>() {
            @Override
            public void handleResponse(BackendlessCollection<Timeline> response) {
                TimelinesSingleton.getInstance().getTimelines().clear();
                TimelinesSingleton.getInstance().getTimelines().addAll(response.getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        createTimelineFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createTimelineIntent = new Intent(getActivity(), CreateTimelineActivity.class);
                startActivity(createTimelineIntent);
            }
        });

        timelinesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent goToTimeline = new Intent(TimelineListFragment.this.getActivity(), ViewTimelineActivity.class);
                goToTimeline.putExtra(ViewTimelineActivity.EXTRA_POSITION, position);
                startActivity(goToTimeline);

            }
        });

        return rootView;

    }

    private class TimelinesArrayAdapter extends ArrayAdapter<Timeline> {

        public TimelinesArrayAdapter() {
            super(TimelineListFragment.this.getActivity(), 0, TimelinesSingleton.getInstance().getTimelines());
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView == null) {
                convertView = TimelineListFragment.this.getActivity().getLayoutInflater().inflate(R.layout.timeline_list_view_item, parent, false);
            }

            Timeline timeline = getItem(position);

            TextView title = (TextView)convertView.findViewById(R.id.timeline_list_item_title);
            TextView description = (TextView)convertView.findViewById(R.id.timeline_list_item_description);

            title.setText(timeline.getName());
            description.setText(timeline.getDescription());

            return convertView;

        }

        @Nullable
        @Override
        public Timeline getItem(int position) {
            return TimelinesSingleton.getInstance().getTimeline(position);
        }

    }

}
