package org.pltw.examples.timelineapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;

import org.pltw.examples.timelineapp.R;
import org.pltw.examples.timelineapp.timeline.Timeline;
import org.pltw.examples.timelineapp.timeline.TimelineEvent;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by pieperm on 3/8/17.
 */

public class SearchFragment extends Fragment {

    private SearchView searchView;
    private ListView searchResults;
    private Spinner searchSpinner;
    private ArrayAdapter<Timeline> timelineArrayAdapter;
    private ArrayAdapter<TimelineEvent> timelineEventArrayAdapter;
    private ArrayAdapter<BackendlessUser> userArrayAdapter;
    private static final String TAG = "SearchFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = (SearchView)rootView.findViewById(R.id.search_view);
        searchResults = (ListView)rootView.findViewById(R.id.search_results);
        searchSpinner = (Spinner)rootView.findViewById(R.id.search_filter_spinner);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String whereClause = "";
                Class entity = null;
                BackendlessDataQuery query = null;
                final ProgressDialog dialog = ProgressDialog.show(getActivity(), "Loading", "Searching for results...");


                switch(searchSpinner.getSelectedItemPosition()) {

                    case 0:
                        Log.i(TAG, "Searching timelines");
                        entity = Timeline.class;
                        whereClause = "name = '" + searchView.getQuery() + "'";
                        break;
                    case 1:
                        Log.i(TAG, "Searching events");
                        entity = TimelineEvent.class;
                        whereClause = "title = '" + searchView.getQuery() + "'";
                        break;
                    case 2:
                        Log.i(TAG, "Searching users");
                        entity = BackendlessUser.class;
                        whereClause = "name = '" + searchView.getQuery() + "'";
                        break;
                    default:
                        Log.wtf(TAG, "Spinner error");
                        break;

                }

                final AtomicReference<Class> atomicEntity = new AtomicReference<Class>(entity);

                query.setWhereClause(whereClause);

                Backendless.Persistence.find(entity, query, new AsyncCallback<BackendlessCollection>() {
                    @Override
                    public void handleResponse(BackendlessCollection response) {

                        timelineArrayAdapter.clear();
                        timelineEventArrayAdapter.clear();
                        userArrayAdapter.clear();

                        if(atomicEntity.get().equals(Timeline.class)) {
                            timelineArrayAdapter.addAll(response.getData());
                        }


                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e(TAG, "Error");
                    }
                });


            }
        });

        return rootView;

    }

}
